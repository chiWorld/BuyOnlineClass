package com.mm.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mm.dto.MemberDTO;
import com.mm.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberServiceTest {
	
	@Autowired
	private MemberService service;
	
	@Test
	public void testLoginCheckService() throws Exception {
		System.out.println(service.loginCheck("admin", "1"));	//true
		System.out.println(service.loginCheck("admi", "1234"));		//false
	}
	
	@Test
	public void testRegisterMemberService() throws Exception {
		System.out.println(service.registerMember(new MemberDTO("테스트1", "1234", "테스트이름", 0)));
		//id PK이기 때문에 false떠야함
		System.out.println(service.registerMember(new MemberDTO("테스트1", "12345", "테스트이름2", 0)));
	}
	
	@Test
	public void testAllMemberInfo() throws Exception {
		List<MemberDTO> list = service.allMemInfo();
		for(MemberDTO dto : list) {
			System.out.println("아이디 : " + dto.getId());
			System.out.println("비밀번호 : " + dto.getPw());
			System.out.println("이름 : " + dto.getName());
			System.out.println("포인트 : " + dto.getPoint());
		}
	}
	
	@Test
	public void testSeletePoint() throws Exception {
		System.out.println(service.selectPoint("admin"));	//true
	}
	
	@Test
	public void testPoint1000() throws Exception {
		System.out.println(service.point1000("admin"));
	}
	
	@Test
	public void testBuyClass() throws Exception {
		System.out.println("현재 admin에 있는 포인트 : " + service.selectPoint("admin")); //100000
		System.out.println("buyClass실행 결과 " + service.buyClass("admin", 100000)); //false
		System.out.println("포인트 사용 후 test1계정에 있는 포인트 : " + service.selectPoint("admin")); //303000
	}
	
	@Test
	public void testFindName() throws Exception {
		System.out.println(service.findName("admin"));
	}
	
	@Test
	public void testOneMemInfo() throws Exception {
		MemberDTO dto = service.oneMemInfo("TestID");
		System.out.println(dto.getName() + " " + dto.getPw() + " " + dto.getPoint());
	}
	
	@Test
	public void testUpdateInfo() throws Exception {
		System.out.println(service.updateInfo(new MemberDTO("test2", "1234", "테스트", 1000)));
	}
	
	@Test
	public void testDeleteInfo() throws Exception {
		System.out.println(service.deleteInfo("test4"));
	}
}
