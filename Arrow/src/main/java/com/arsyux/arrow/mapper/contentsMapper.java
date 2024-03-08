package com.arsyux.arrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;



@Mapper
public interface contentsMapper {
	
	
	@Insert("INSERT INTO arrow_exhibition"
			+ "(exh_seq,name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, tag_exhibit, createDt)"
			+ "VALUES(0, #{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, #{startDate_exhibit}, #{endDate_exhibit}, #{tag_exhibit}, current_timestamp());")
	@Options(useGeneratedKeys = true, keyProperty = "exh_seq", keyColumn = "exh_seq")
	public ContentsVO insertContent(ContentsVO content);
	
	@Insert("INSERT INTO arrorw_fileinfo\r\n"
			+ "(exh_seq, file, file_name, createDt)"
			+ "VALUES(#{exh_seq}, NULL, NULL, current_timestamp())")
	public ContentsVO insertFileInfo(FilesVO file);
		
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, createDt, tag_exhibit, image_exhibhit"
			+ "FROM arrow_exhibition WHERE tag_exhibit = #{tag_exhibit}; ")
	public List<ContentsVO> selectAllContent(ContentsVO content);
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, createDt, tag_exhibit, image_exhibhit"
			+ "FROM arrow_exhibition WHERE exh_seq = #{exh_seq} AND tag_exhibit = #{tag_exhibit}; ")
	public List<ContentsVO> selectOneContent(ContentsVO content);
	
	
	




}
