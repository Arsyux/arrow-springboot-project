package com.arsyux.arrow.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ContentsVO;


@Mapper
public interface contentsMapper {
	
	
	@Insert("INSERT INTO arrow.arrow_exhibition"
			+ "(name_exhibit, subname_exhibit, space_exhibit, startDate_exhibit, endDate_exhibit)"
			+ "VALUES(#{name_exhibit}, #{subname_exhibit}, #{space_exhibit}, #{startDate_exhibit}, #{endDate_exhibit});")
	public ContentsVO insertContent(ContentsVO contetnt);
	
}
