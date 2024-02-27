package com.arsyux.arrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ContentsVO;



@Mapper
public interface contentsMapper {
	
	
	@Insert("INSERT INTO arrow_exhibition"
			+ "(name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, tag_exhibit, createDt)"
			+ "VALUES(#{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, #{startDate_exhibit}, #{endDate_exhibit}, #{tag_exhibit}, current_timestamp());")
	public ContentsVO insertContent(ContentsVO content);
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, createDt, tag_exhibit, image_exhibhit"
			+ "FROM arrow_exhibition WHERE tag_exhibit = #{tag_exhibit}; ")
	public List<ContentsVO> selectAllContent(ContentsVO content);
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, createDt, tag_exhibit, image_exhibhit"
			+ "FROM arrow_exhibition WHERE exh_seq = #{exh_seq} AND tag_exhibit = #{tag_exhibit}; ")
	public List<ContentsVO> selectOneContent(ContentsVO content);
	
	
	


}
