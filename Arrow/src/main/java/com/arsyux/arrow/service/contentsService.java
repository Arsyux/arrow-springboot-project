package com.arsyux.arrow.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.persistence.contentsDAO;





@Service
public class contentsService {

	@Autowired
	private contentsDAO contetntsDAO;
		
	
	@Transactional
	public void insertContent(ContentsVO content) {
		contetntsDAO.insertContent(content);
	}
	//글 작성	
	@Transactional
	public void insertFile(ContentsVO content, FilesVO file) {
		// 정보 저장
		
		int exh_seq = content.getExh_seq();

		file.setExh_seq(exh_seq);
			 contetntsDAO.insertFileInfo(file);
		
		
	}
	
}
