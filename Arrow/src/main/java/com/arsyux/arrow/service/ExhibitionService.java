package com.arsyux.arrow.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.persistence.ExhibitionDAO;





@Service
public class ExhibitionService {

	@Autowired
	private ExhibitionDAO exhibitionDAO;

	@Transactional
	public void insertContent(ExhibitionVO content) {
		exhibitionDAO.insertContent(content);
	}


	public List<ExhibitionVO> selectAllContent(int pageNumber, int pageSize) {
		int offset = pageNumber * pageSize;
		 RowBounds rowBounds = new RowBounds(offset, pageSize);
		return exhibitionDAO.selectAllContent(rowBounds);
	}

    public int getTotalPages(int pageSize) {
        // 총 게시글 수를 가져와서 페이지 수 계산
        int totalContents = exhibitionDAO.getTotalPages(pageSize);
        int totalPages = (int) Math.ceil((double) totalContents / pageSize);
        return totalPages;
    }

	//글 작성	
	@Transactional
	public void insertFile(ExhibitionVO content, List<FilesVO> file) {
		// 정보 저장

		int exh_seq = content.getExh_seq();

		 for (FilesVO f : file) {
			 FilesVO vo = file.get(0);
			 f.setExh_seq(exh_seq);
			 exhibitionDAO.insertFileInfo(vo);
		 }


	}
	
    public List<ExhibitionVO> selectOneContent(int exh_seq) {
        
        return exhibitionDAO.selectOneContent(exh_seq);
    }
	
}
