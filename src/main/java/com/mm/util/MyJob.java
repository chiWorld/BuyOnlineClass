package com.mm.util;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mm.service.MemberService;
import com.mm.service.MemberServiceImpl;

public class MyJob implements Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//스프링 빈 객체 얻기 - 구글링
		
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext(); 
		MemberService service = ctx.getBean(MemberServiceImpl.class);

		//서비스의 test메서드가 실행됨
		service.test();
		int result = 0;
		
		System.out.println("Quartz의 Job이 실행됨 /" + result + "명에게 포인트 1점 부여.");
	}
}
