package com.arsyux.arrow.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.persistence.CollectionDAO;
import com.arsyux.arrow.persistence.ExhibitionDAO;





@Service
public class CollectionService {

	@Autowired
	private CollectionDAO collectionDAO;

	

//	@Transactional
//	public List<ExhibitionVO> selectAllContent(int pageNumber, int pageSize) {
//		int offset = pageNumber * pageSize;
//		return collectionDAO.selectAllContent(offset, pageSize);
//	}
//	@Transactional
//    public int getTotalPages(int pageSize) {
//        // 총 게시글 수를 가져와서 페이지 수 계산
//        int totalContents = collectionDAO.getTotalPages(pageSize);
//        int totalPages = (int) Math.ceil((double) totalContents / pageSize);
//        return totalPages;
//    }

	
    public List<CollectionsVO> selectCollection(int exh_seq) {
        
        return collectionDAO.selectCollection(exh_seq);
    }
	
}
