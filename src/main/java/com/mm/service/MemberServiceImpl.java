package com.mm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.dao.MemberDAO;
import com.mm.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO dao;
	
	@Override
	public boolean loginCheck(String id, String pw) {
		int result = dao.loginCheck(id, pw);
		//result가 1이면 true 아니면 false
		return result == 1;
	}

	@Override
	public boolean registerMember(MemberDTO dto) {
		int result = dao.insert(dto);
		//result가 1이면 성공
		return result == 1;
	}
	
	@Override
	public List<MemberDTO> allMemInfo() {
		return dao.allMem();
	}

	@Override
	public void test() {
		System.out.println("서비스의 test메서드가 실행됨");
	}

	@Override
	public int selectPoint(String id) {
		int result = dao.selectPoint(id);
		return result;
	}

	@Override
	public int point1000(String id) {
		int result = dao.point1000(id);
		System.out.println(result);
		result = dao.selectPoint(id);
		System.out.println(id + " : " + result);
		return result;
	}

	@Override
	public boolean buyClass(String id, int pointPay) {
		int result = dao.buyClass(id, pointPay);
		return result == 1;
	}

	@Override
	public String findName(String id) {
		String name = dao.findName(id);
		return name;
	}

	@Override
	public MemberDTO oneMemInfo(String id) {
		MemberDTO dto = dao.oneId(id);
		return dto;
	}
	
	@Override
	public boolean updateInfo(MemberDTO dto) {
		int updateInfo = dao.updateInfo(dto);
		boolean result = false;

		if (updateInfo == 1) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean deleteInfo(String id) {
		int deleteInfo = dao.deleteInfo(id);
		boolean result = false;
		
		if (deleteInfo == 1) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}

	@Override
	public int schePoint() {
		return dao.schePoint();
	}

}
