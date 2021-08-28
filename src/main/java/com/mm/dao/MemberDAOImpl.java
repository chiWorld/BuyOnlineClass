package com.mm.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.mm.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	@Autowired
	SqlSession sqlSession;
	
	public static final String NAMESPACE = "com.mm.mapper.MemberMapper";
	
	@Override
	public int loginCheck(String id, String pw) {
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("id", id);
		map1.put("pw", pw);
		int result = sqlSession.selectOne(NAMESPACE + ".loginCheck", map1);
		
		return result;	//return이 1: 로그인 성공, 0: 로그인 실패
	}

	@Override
	public int insert(MemberDTO dto) {
		int result = 0;
		
		try {
			result = sqlSession.insert(NAMESPACE + ".insertMember", dto);
		} catch(Exception e) {
			System.out.println("아이디(PK) 중복임!");
		}
		
		return result;
	}
	
	@Override
	public List<MemberDTO> allMem() {
		return sqlSession.selectList(NAMESPACE + ".allMem");
	}
	
	@Override
	public int selectPoint(String id) {
		int result = sqlSession.selectOne(NAMESPACE + ".checkPoint", id);
		//결과값을 return받아옴. 포인트가 0이면 0 1000이면 1000가져옴 
		return result;
	}

	@Override
	public int point1000(String id) {
		int result = sqlSession.update(NAMESPACE + ".upPoint", id);
		System.out.println("포인트 올리기 성공 " + result);
		result = sqlSession.selectOne(NAMESPACE + ".checkPoint", id);
		return result;
	}

	@Override
	public int buyClass(String id, int point) {
		HashMap <String, Object> map1 = new HashMap <String, Object>();
		map1.put("id", id);
		map1.put("point", point);
		int result = sqlSession.update(NAMESPACE + ".buyClass", map1);
		return result;
	}

	@Override
	public String findName(String id) {
		String name = sqlSession.selectOne(NAMESPACE + ".findName", id);
		return name;
	}
	
	@Override
	public MemberDTO oneId(String id) {
		return sqlSession.selectOne(NAMESPACE + ".selectOneMem", id);
	}
	
	@Override
	public int updateInfo(MemberDTO dto) {
		return sqlSession.update(NAMESPACE + ".updateInfo", dto);
	}

	@Override
	public int deleteInfo(String id) {
		return sqlSession.delete(NAMESPACE + ".deleteInfo", id);
	}

}
