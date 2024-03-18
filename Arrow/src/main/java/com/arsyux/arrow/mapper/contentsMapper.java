package com.arsyux.arrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;



@Mapper
public interface contentsMapper {
	
	@Insert("INSERT INTO arrow_exhibition"
			+ "(exh_seq,name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, tag_exhibit, createDt)"
			+ " VALUES(0, #{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, #{startDate_exhibit}, #{endDate_exhibit}, #{tag_exhibit}, current_timestamp());")
	@Options(useGeneratedKeys = true, keyProperty = "exh_seq", keyColumn = "exh_seq")
	public ContentsVO insertContent(ContentsVO content);
	
	@Insert("INSERT INTO arrorw_fileinfo"
			+ "(exh_seq, file_name, originFile_name, file_type, createDt)"
			+ " VALUES(#{exh_seq}, #{file_code}, #{file_originName}, #{file_type}, current_timestamp());")
	public void insertFileInfo(FilesVO file);
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, str_to_date(startDate_exhibit, '%Y-%m-%d' ) as startDate_exhibit ,str_to_date(endDate_exhibit, '%Y-%m-%d' ) as endDate_exhibit ,str_to_date(createDt, '%Y-%m-%d %T' ) as createDt, tag_exhibit,descript_exhibit, image_exhibhit"
			+ " FROM arrow_exhibition ORDER by createDt DESC LIMIT #{limit} OFFSET #{offset}; ")
	public  List<ContentsVO> selectAllContent(RowBounds rowBounds);
	
	@Select("SELECT count(*)"
			+ " FROM arrow_exhibition; ")
	public int getTotalContents();
	
	
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, str_to_date(startDate_exhibit, '%Y-%m-%d' ) as startDate_exhibit ,str_to_date(endDate_exhibit, '%Y-%m-%d' ) as endDate_exhibit ,str_to_date(createDt, '%Y-%m-%d %T' ) as createDt, tag_exhibit,descript_exhibit , image_exhibhit"
			+ " FROM arrow_exhibition WHERE exh_seq = #{exh_seq}; ")
	public List<ContentsVO> selectOneContent(int exh_seq);


}
