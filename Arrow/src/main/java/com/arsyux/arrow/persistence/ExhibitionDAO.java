package com.arsyux.arrow.persistence;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.domain.Pagination;



@Repository
public class ExhibitionDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;

	// 주대현 240404
	// 게시글 작성 후 PK값 반환
	public int insertExhibition(ExhibitionVO exhibition) {
		mybatis.insert("insertExhibition", exhibition);
		return exhibition.getExh_seq();
	}
	
//	public interface QuestionRepository extends JpaRepository<ContentsVO, Integer> {
//
//	    Page<ContentsVO> findAll(Pageable pageable);
//	}
 
	public int insertContent(ExhibitionVO content) {
		mybatis.insert("insertContent", content);
		return content.getExh_seq();
	}

    public void insertFileInfo(FilesVO file) {
	   mybatis.insert("insertFileInfo", file);
    }	
    
    public List<ExhibitionVO> selectAllContent(Pagination page) {
    
        return mybatis.selectList("selectAllContent", page);
    }
    
    
    public int getTotalPages() {
	  int totalContents = mybatis.selectOne("getTotalContents");

      return totalContents;
    }
    
    
    public List<ExhibitionVO> selectOneContent(int exh_seq) {
        
        return mybatis.selectList("selectOneContent", exh_seq);
    }
}
