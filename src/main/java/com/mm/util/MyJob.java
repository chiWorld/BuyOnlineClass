package com.mm.util;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mm.service.MemberService;

public class MyJob implements Job{
	
	private MemberService memberService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext(); 
		memberService = ctx.getBean(MemberService.class);
		
		if(memberService == null) {
			System.out.println("memberService is null");
			return;
		}
		
		int result = memberService.schePoint();
		System.out.println("Quartz의 Job이 실행됨 : " + new Date(System.currentTimeMillis()) + " / " + result + "명에게 포인트 부여(1점).");
	}
}
