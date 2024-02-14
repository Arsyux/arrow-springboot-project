package com.arsyux.arrow.persistence;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// 회원 번호로 1명 조회
	public UserVO findById(int id) {
		return mybatis.selectOne("findById", id);
	}
	
}
