package com.mm.service;

import java.util.List;

import com.mm.dto.MemberDTO;

public interface MemberService {
	boolean loginCheck(String id, String pw);
	boolean registerMember(MemberDTO dto);
	List<MemberDTO> allMemInfo();
	int selectPoint(String id);
	int point1000(String id);
	boolean buyClass(String id, int price);
	String findName(String id);
	MemberDTO oneMemInfo(String id);
	boolean updateInfo(MemberDTO dto);
	boolean deleteInfo(String id);
	void test();
}
