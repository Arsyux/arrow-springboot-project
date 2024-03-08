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
		
	//글 작성	
	@Transactional
	public void insertContent(ContentsVO content, List<FilesVO> file) {
		// 정보 저장
		
		int exh_seq = contetntsDAO.insertContent(content);
		 for (FilesVO f : file) {
			 f.setExh_seq(exh_seq);
		contetntsDAO.insertFileInfo(file);
		
		 }
	}
	
	
}
