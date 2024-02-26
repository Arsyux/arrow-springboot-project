package com.arsyux.arrow.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.persistence.contentsDAO;





@Service
public class contentsService {

	@Autowired
	private contentsDAO contetntsDAO;
		
	//글 작성
	
	@Transactional
	public void insertContent(ContentsVO content) {
		// 정보 저장
		contetntsDAO.insertContent(content);
	}
	
	
}
