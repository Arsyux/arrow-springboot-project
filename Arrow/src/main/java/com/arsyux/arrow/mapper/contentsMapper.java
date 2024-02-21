package com.arsyux.arrow.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.arsyux.arrow.domain.ContentsVO;


@Mapper
public interface contentsMapper {
	
	
	@Insert("INSERT INTO arrow_exhibition"
		  + "FROM tb_user "
		  + "WHERE userid = #{userid}")
	public ContentsVO findById(int userid);
	
}
