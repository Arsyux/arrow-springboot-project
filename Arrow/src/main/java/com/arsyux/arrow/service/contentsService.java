package com.arsyux.arrow.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.persistence.contentsDAO;





@Service
public class contentsService {

	@Autowired
	private contentsDAO contentsDAO;

	@Transactional
	public void insertContent(ContentsVO content) {
		contentsDAO.insertContent(content);
	}

	@Transactional
	public List<ContentsVO> selectAllContent(int pageNumber, int pageSize) {
		int offset = pageNumber * pageSize;
		return contentsDAO.selectAllContent(offset, pageSize);
	}
	@Transactional
    public int getTotalPages(int pageSize) {
        // 총 게시글 수를 가져와서 페이지 수 계산
        int totalContents = contentsDAO.getTotalPages(pageSize);
        int totalPages = (int) Math.ceil((double) totalContents / pageSize);
        return totalPages;
    }

	//글 작성	
	@Transactional
	public void insertFile(ContentsVO content, List<FilesVO> file) {
		// 정보 저장

		int exh_seq = content.getExh_seq();

		 for (FilesVO f : file) {
			 FilesVO vo = file.get(0);
			 f.setExh_seq(exh_seq);
			 contentsDAO.insertFileInfo(vo);
		 }


	}
	
}
