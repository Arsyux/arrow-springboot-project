package com.arsyux.arrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;



@Mapper
public interface CollectionMapper {
	

	
//	@Insert("INSERT INTO arrorw_fileinfo"
//			+ "(exh_seq, file_name, originFile_name, file_type, createDt)"
//			+ " VALUES(#{exh_seq}, #{file_code}, #{file_originName}, #{file_type}, current_timestamp());")
//	public void insertFileInfo(FilesVO file);
//	
	/*소장품 리스트*/
	@Select("SELECT exh_seq, name_collect, codename_collect, period_collect, tag_exhibit, metarial_collect, size_collect, usage_collect, feature_collect, descript_collect, str_to_date(createDt,'%Y-%m-%d')as createDt"
			+ " FROM arrow_collection WHERE exh_seq = #{exh_seq} ORDER BY createDt DESC ")
	public  List<CollectionsVO> selectCollect(int exh_seq, RowBounds rowBounds);
	
	@Select("SELECT count(*)"
			+ " FROM arrow_collection; ")
	public int getTotalCollect();
	


}
