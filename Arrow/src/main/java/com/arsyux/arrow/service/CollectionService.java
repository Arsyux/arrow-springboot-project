package com.arsyux.arrow.service;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.domain.Pagination;
import com.arsyux.arrow.persistence.CollectionDAO;
import com.arsyux.arrow.persistence.ExhibitionDAO;





@Service
public class CollectionService {

	@Autowired
	private CollectionDAO collectionDAO;

	


	public List<CollectionsVO> selectCollect(int exh_seq,Pagination page) {
		int startList = page.getStartList();
		int listSize = page.getListSize();
		RowBounds rowBounds = new RowBounds(startList, listSize);
		System.out.println("row Bounds "+ rowBounds.getOffset()+"+"+rowBounds.getLimit());
	
		return collectionDAO.selectCollect(exh_seq, rowBounds);
	}

    public int getTotalPages() {      
    
        return collectionDAO.getTotalPages();
    }

    
    public List<CollectionsVO> selectOneCollect(String encryptedCode){
    	
    	return collectionDAO.selectOneCollect(encryptedCode);
    }
	
}
