package com.arsyux.arrow.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.CollectionsVO;



@Repository
public class CollectionDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
    
    public List<CollectionsVO> selectCollect(int exh_seq,int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return mybatis.selectList("selectCollect",exh_seq ,rowBounds);
    }
    
    
    public int getTotalPages(int pageSize) {
	  int totalContents = mybatis.selectOne("getTotalCollect");

      return totalContents;
    }
    
}
