package com.arsyux.arrow.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;



@Repository
public class contentsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

 
	public int insertContent(ContentsVO content) {
		mybatis.insert("insertContent", content);
		return content.getExh_seq();
	}
 
    public void insertFileInfo(List<FilesVO> file) {
	   mybatis.insert("insertFileInfo", file);
    }	
}
