package com.arsyux.arrow.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;



@Repository
public class CollectionDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
    
//    public List<ExhibitionVO> selectAllContent(int offset, int limit) {
//        RowBounds rowBounds = new RowBounds(offset, limit);
//        return mybatis.selectList("selectAllContent", rowBounds);
//    }
//    
//    
//    public int getTotalPages(int pageSize) {
//	  int totalContents = mybatis.selectOne("getTotalContents");
//
//      return totalContents;
//    }
//    
    
    public List<CollectionsVO> selectCollection(int exh_seq) {
        
        return mybatis.selectList("selectCollection", exh_seq);
    }
}
