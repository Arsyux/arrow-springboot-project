package com.arsyux.arrow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ExhibitionImageVO;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.domain.Pagination;



@Mapper
public interface ExhibitionMapper {
	
	// 주대현 240404
	// 게시글 작성 후 PK값 반환
	@Insert("INSERT INTO arrow_exhibition"
		  + "(tag_exhibit, title_exhibit, description_exhibit, startDate_exhibit, endDate_exhibit, space_exhibit, details_exhibit, createDt) "
		  + "VALUES(#{tag_exhibit}, #{title_exhibit}, #{description_exhibit}, date_format(#{startDate_exhibit},'%Y-%m-%d'), date_format(#{endDate_exhibit},'%Y-%m-%d'), #{space_exhibit}, #{details_exhibit}, #{createDt});")
	@Options(useGeneratedKeys = true, keyProperty = "exh_seq", keyColumn = "exh_seq")
	public ExhibitionVO insertExhibition(ExhibitionVO exhibition);
	
	// 주대현 240410
	// 이미지 정보 저장
	@Insert("INSERT INTO arrow_exhibitionImage"
		  + "(exh_seq, name_exhimg) "
		  + "VALUES(#{exh_seq}, #{name_exhimg});")
	public ExhibitionVO insertExhibitionImage(ExhibitionImageVO exhibitionImage);
	
	@Insert("INSERT INTO arrow_exhibition"
			+ "(exh_seq,name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, tag_exhibit,descript_exhibit , createDt)"
			+ " VALUES(0, #{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, date_format(#{startDate_exhibit},'%Y-%m-%d'), date_format((#{endDate_exhibit},'%Y-%m-%d'),#{descript_exhibit} ,#{tag_exhibit}, current_timestamp());")
	@Options(useGeneratedKeys = true, keyProperty = "exh_seq", keyColumn = "exh_seq")
	public ExhibitionVO insertContent(ExhibitionVO content);
	
	@Insert("INSERT INTO arrorw_fileinfo"
			+ "(exh_seq, file_name, originFile_name, file_type, createDt)"
			+ " VALUES(#{exh_seq}, #{file_code}, #{file_originName}, #{file_type}, current_timestamp());")
	public void insertFileInfo(FilesVO file);
	/*전시 메인화면*/
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, str_to_date(startDate_exhibit, '%Y-%m-%d' ) as startDate_exhibit ,str_to_date(endDate_exhibit, '%Y-%m-%d' ) as endDate_exhibit ,str_to_date(createDt, '%Y-%m-%d %T' ) as createDt, tag_exhibit,descript_exhibit"
			+ " FROM arrow_exhibition ORDER by createDt DESC LIMIT #{listSize} OFFSET #{startList}; ")
	public  List<ExhibitionVO> selectAllContent(Pagination page);
	
	@Select("SELECT count(*)"
			+ " FROM arrow_exhibition; ")
	public int getTotalContents();
	
	/*전시 상세페이지*/
	@Select("SELECT exh_seq, name_exhibit, subname_exhibit, space_exhibit, str_to_date(startDate_exhibit, '%Y-%m-%d' ) as startDate_exhibit ,str_to_date(endDate_exhibit, '%Y-%m-%d' ) as endDate_exhibit ,str_to_date(createDt, '%Y-%m-%d %T' ) as createDt, tag_exhibit,descript_exhibit"
			+ " FROM arrow_exhibition WHERE exh_seq = #{exh_seq}; ")
	public List<ExhibitionVO> selectOneContent(int exh_seq);


}
