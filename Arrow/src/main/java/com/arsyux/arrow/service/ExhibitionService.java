package com.arsyux.arrow.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.ExhibitionImageVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.domain.Pagination;
import com.arsyux.arrow.persistence.ExhibitionDAO;





@Service
public class ExhibitionService {
	
	@Autowired
	private ExhibitionDAO exhibitionDAO;


	// 주대현 240404
	// 전시 게시글 저장
	public int insertExhibition(ExhibitionVO exhibition) {
		return exhibitionDAO.insertExhibition(exhibition);
	}
	
	// 주대현 240410
	// 전시 및 이미지 저장
	@Transactional
	public void insertExhibitionAndImage(ExhibitionVO exhibition, ArrayList<String> fileNames) {
		// 전시 insert 후 PK값 반환
		int exhibitionPK = exhibitionDAO.insertExhibition(exhibition);
		
		// 전시 PK를 사용해 이미지 insert
		for (String fileName : fileNames) {
			ExhibitionImageVO exhibitionImage = new ExhibitionImageVO(exhibitionPK, fileName);
			exhibitionDAO.insertExhibitionImage(exhibitionImage);
		}
	}
	
	
	@Transactional
	public void insertContent(ExhibitionVO content) {
		exhibitionDAO.insertContent(content);
	}


	public List<ExhibitionVO> selectAllContent(Pagination page) {

		return exhibitionDAO.selectAllContent(page);
	}

    public int getTotalPages() {
        // 총 게시글 수를 가져와서 페이지 수 계산
        int totalContents = exhibitionDAO.getTotalPages();

        return totalContents;
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
