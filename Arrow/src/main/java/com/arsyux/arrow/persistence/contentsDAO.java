package com.arsyux.arrow.persistence;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.ContentsVO;



@Repository
public class contentsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

 
	// 회원 번호로 1명 조회
	public int insertContent(ContentsVO content) {
		return mybatis.insert("insertContent", content);
	}
 
	
}
