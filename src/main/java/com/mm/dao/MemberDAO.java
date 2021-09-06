package com.mm.dao;

import java.util.List;

import com.mm.dto.MemberDTO;

public interface MemberDAO {
	//로그인
	int loginCheck(String id, String pw);
	
	//회원가입
	int insert(MemberDTO dto);
	
	//모든 회원 정보
	List<MemberDTO> allMem();
	//포인트 찾기
	int selectPoint(String id);
	
	//광고 클릭
	int point1000(String id);
	
	//클래스 구매
	int buyClass(String id, int pointPrice);
	
	//이름 찾기
	String findName(String id);
	
	//특정 회원 정보 불러오기
	MemberDTO oneId(String id);
	
	//회원정보 수정
	int updateInfo(MemberDTO dto);
	
	//회원정보 삭제
	int deleteInfo(String id);
	
	//스케줄러
	int schePoint();
}
