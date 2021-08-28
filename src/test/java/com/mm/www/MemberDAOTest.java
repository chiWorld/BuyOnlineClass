package com.mm.www;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mm.dao.MemberDAO;
import com.mm.dto.MemberDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MemberDAOTest {
	
	@Autowired //스프링한테 dao를 빈으로 등록해줘야함
	private MemberDAO dao;
	
	@Test
	public void testLoginCheck() throws Exception {
		int result = dao.loginCheck("admin", "1234");
		System.out.println(result); //1
		result = dao.loginCheck("admin", "123");
		System.out.println(result); //0
	}
	
	@Test
	public void testRegisterAction() throws Exception {
		int result = dao.insert(new MemberDTO("TestID", "TestPW", "Test이름", 0));
		if(result==1) {
			System.out.println("회원가입 성공!");
		}
	}
	
	@Test
	public void testAllMemInfo() throws Exception {
		List<MemberDTO> list = dao.allMem();
		for(MemberDTO dto : list) {
			System.out.println(dto.getId() + " " + dto.getPw() + " " + dto.getName() + " " + dto.getPoint());	
		}
	}
	
	@Test
	public void testSelectPoint() throws Exception {
		int result = dao.selectPoint("admin");
		System.out.println(result);
	}
	
	@Test
	public void testPoint1000() throws Exception {
		int result1 = dao.point1000("admin");
		System.out.println(result1);
		int result2 = dao.selectPoint("admin");
		System.out.println(result2);
	}
	
	@Test
	public void testBuyClass() throws Exception {
		int result = dao.buyClass("admin", 100000);
		System.out.println(result);
	}
	
	@Test
	public void testFindName() throws Exception {
		String result = dao.findName("admin");
		System.out.println(result);
	}
	
	@Test
	public void testSelectOneMem() throws Exception {
		MemberDTO dto = dao.oneId("TestID");
		System.out.println(dto.getName() + " " + dto.getPw() + " " + dto.getPoint());
	}
	
	@Test
	public void testUpdateInfo() throws Exception {
		System.out.println(dao.updateInfo(new MemberDTO("TestID", "1234", "이름", 1000))); //1
		System.out.println(dao.updateInfo(new MemberDTO("TESTID", "1234", "이름", 1000))); //0
	}
	
	@Test
	public void testDeleteInfo() throws Exception {
		System.out.println(dao.deleteInfo("테스트3")); //1
	}
}
