package com.arsyux.arrow.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;



@Repository
public class contentsDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
//	public interface QuestionRepository extends JpaRepository<ContentsVO, Integer> {
//
//	    Page<ContentsVO> findAll(Pageable pageable);
//	}
 
	public int insertContent(ContentsVO content) {
		mybatis.insert("insertContent", content);
		return content.getExh_seq();
	}

    public void insertFileInfo(FilesVO file) {
	   mybatis.insert("insertFileInfo", file);
    }	
    
    public List<ContentsVO> selectAllContent(int offset, int limit) {
        RowBounds rowBounds = new RowBounds(offset, limit);
        return mybatis.selectList("selectAllContent", rowBounds);
    }
    
    
    public int getTotalPages(int pageSize) {
	  int totalContents = mybatis.selectOne("getTotalContents");

      return totalContents;
    }
}
