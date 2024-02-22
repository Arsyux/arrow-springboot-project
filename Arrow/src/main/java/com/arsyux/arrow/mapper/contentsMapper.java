package com.arsyux.arrow.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ContentsVO;


@Mapper
public interface contentsMapper {
	
	
	@Insert("INSERT INTO arrow.arrow_exhibition"
			+ "(name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit, createDt, tag_exhibit, image_exhibhit)"
			+ "VALUES(#{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, #{startDate_exhibit}, #{endDate_exhibit}, current_timestamp(), NULL, NULL);")
	public ContentsVO insertContent(ContentsVO contetnt);
	
}
