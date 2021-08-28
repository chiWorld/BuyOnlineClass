package com.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mm.dto.MemberDTO;
import com.mm.service.MemberService;
import com.mm.util.MyJob;


@Controller
public class HomeController {
	@Autowired
	MemberService service;
	
	//로그인페이지
	@RequestMapping(value = "/")	//localhost:9090/www
	public String homeLogin() {
		return "homeLogin";
	}
	
	//로그인 여부
	@RequestMapping(value="/loginAction")
	public String loginAction(HttpSession session, String id, String pw, HttpServletRequest request) {
		//로그인 여부 확인
		boolean result = service.loginCheck(id, pw);
		
		String ret = "";
		
		if(result) {
			session.setAttribute("id", id);
			if(id.equals("admin")) {
				ret="adminMain";	//관리자 페이지 경로
				request.setAttribute("member", service.allMemInfo());
			} else {
				ret="main";			//메인페이지 경로
			}
			
		} else {
			//로그인페이지로 보내되, Attribute 'msg'에 로그인실패 메세지를 담아서 보내줌
			request.setAttribute("msg", "아이디/비밀번호 확인해주세요.");
			ret = "homeLogin";
		}
		
		return ret;
	}
	
	//로그아웃
	@RequestMapping(value = "/logoutAction")
	public String logoutAction(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("id");
		request.setAttribute("msg", "로그아웃 되었습니다.");
		return "homeLogin";
	}
	
	//회원가입 페이지
	@RequestMapping(value = "/register")
	public String register() {
		return "registerForm";
	}
	
	//회원가입 확인
	@RequestMapping(value = "/registerAction")
	public String registerAction(MemberDTO dto, HttpServletRequest request, String id, String pw, String name) {
		//회원DB에 insert -> login페이지(msg:"가입되었습니다. 로그인해주세요.")
		dto.setId(id);
		dto.setPw(pw);
		dto.setName(name);
		
		boolean result = service.registerMember(dto);
		
		String ret = "";
		
		if(result) {
			request.setAttribute("msg", "가입되었습니다. 로그인해주세요.");
			ret = "homeLogin";
		} else {
			request.setAttribute("msg", "사용중인 아이디입니다");
			ret = "register";
		}
		
		return ret;
	}
	
	//메인
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, HttpSession session) {
		String userId = (String)session.getAttribute("id");
		
		String userName = service.findName(userId);
		int userPoint = service.selectPoint(userId);
		
		request.setAttribute("id", userId);
		request.setAttribute("name", userName);
		request.setAttribute("point", userPoint);
		System.out.println("id :" + userId + " 이름 : " + userName + " 포인트 : " + userPoint);
		return "main";
	}
	
	//intro구입
	@RequestMapping(value = "/intro350")
	public String intro350(HttpServletRequest request, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int intro = 100000;
		int userPoint = service.selectPoint(id);
		
		if(userPoint > intro) {
			service.buyClass(id, intro);
			request.setAttribute("msg", "컨텐츠(intro)를 구입하였습니다.");
		} else {
			request.setAttribute("msg", "포인트가 부족합니다. 광고를 클릭하세요.");
		}
		
		return "main";
	}
	
	//java구입
	@RequestMapping(value = "/java350")
	public String java350(HttpServletRequest request, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int java350 = 500000;
		int userPoint = service.selectPoint(id);
		
		if(userPoint > java350) {
			service.buyClass(id, java350);
			request.setAttribute("msg", "컨텐츠(java)를 구입하였습니다.");
		} else {
			request.setAttribute("msg", "포인트가 부족합니다. 광고를 클릭하세요.");
		}
		return "main";
	}
	
	//cpp구입
	@RequestMapping(value = "/cpp350")
	public String cpp350(HttpServletRequest request, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int cpp350 = 300000;
		int userPoint = service.selectPoint(id);
		
		if(userPoint > cpp350) {
			service.buyClass(id, cpp350);
			request.setAttribute("msg", "컨텐츠(cpp)를 구입하였습니다.");
		} else {
			request.setAttribute("msg", "포인트가 부족합니다. 광고를 클릭하세요.");
		}
		return "main";
	}
	
	//광고 클릭
	@RequestMapping(value = "/point1000")
	public String intro(HttpServletRequest request, HttpSession session) {
		String id = (String)session.getAttribute("id");
		service.point1000(id);
		request.setAttribute("koreaIT", "이동");
		return "main";
	}
	
	//회원정보 수정
	@RequestMapping(value = "adminModify")
	public String adminModify(Model model, String id) {
		MemberDTO info = service.oneMemInfo(id);
		model.addAttribute("info", info);
		return "adminMain";
	}
	@RequestMapping(value = "adminModifyAction")
	public String adminModifyAction(Model model, String id, String name, String pw, int point) {
		boolean updateResult = service.updateInfo(new MemberDTO(id, pw, name, point));
		String msg = "회원정보가 ";
		if(updateResult) {
			msg += "수정되었습니다.";
		} else {
			msg += "수정되지 않았습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return "adminMain";
	}

	
	//회원정보 삭제
	@RequestMapping(value = "adminDelete")
	public String adminDelete(String id, Model model) {
		boolean result = service.deleteInfo(id);
		String msg = "회원정보가 ";
		
		if(result) {
			msg += "삭제되었습니다.";
		} else {
			msg += "삭제되지 않았습니다.";
		}
		
		model.addAttribute("msg", msg);
		
		return "adminMain";
	}
	
	@RequestMapping(value = "/start")  // 스케줄러 시작할게요 (= 10초마다 MyJob 실행할게요).
	public String homeStart() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		
		  // define the job and tie it to our HelloJob class
		  JobDetail job = JobBuilder.newJob(MyJob.class)
		      .withIdentity("job1", "group1")
		      .build();

		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.newTrigger()
		      .withIdentity("trigger1", "group1")
		      .startNow()
		      .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
		      .build();

		  // Tell quartz to schedule the job using our trigger
		  scheduler.scheduleJob(job, trigger);
		  scheduler.start();
		  System.out.println("스케줄러가 시작됨.");
		return "home";
	}
	
	@RequestMapping(value = "/end")    // 스케줄러 끝낼게요 (= MyJob 그만 할게요).
	public String homeEnd() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		scheduler.shutdown();
		System.out.println("스케줄러가 종료됨.");
		return "home";
	}	
	
}
