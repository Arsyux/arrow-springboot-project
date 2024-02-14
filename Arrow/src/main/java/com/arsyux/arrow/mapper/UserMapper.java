package com.arsyux.arrow.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.arsyux.arrow.domain.UserVO;

@Mapper
public interface UserMapper {

	// ========================================
	// 1. 회원가입
	// ========================================
	
	// 회원가입
	@Insert("INSERT INTO tb_user(username, password, name, birth, gender, phone, email) "
		  + "VALUES(#{username}, #{password}, #{name}, #{birth}, #{gender}, #{phone}, #{email})")
	public void insertUser(UserVO user);
	
	// ========================================
	// 2. 회원 검색
	// ========================================
	
	// 회원 번호로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE userid = #{userid}")
	public UserVO findById(int userid);
	
	// 로그인 아이디로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE username = #{username}")
	public UserVO findByUsername(String username);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE phone = #{phone}")
	public UserVO findByPhone(String phone);
	
	// 휴대폰으로 회원 1명 조회
	@Select("SELECT * "
		  + "FROM tb_user "
		  + "WHERE email = #{email}")
	public UserVO findByEmail(String email);
	
	// 게시글번호로 회원 1명 조회
	@Select("SELECT u.* "
		  + "FROM tb_user u, tb_post p "
		  + "WHERE u.userid = p.userid AND p.postid = #{postid}")
	public UserVO findByPostId(int postid);
	
	// 회원 정보 변경
	@Update("UPDATE tb_user "
		  + "SET PASSWORD = #{password} "
		  + "WHERE userid = #{userid}")
	public void updateUser(UserVO user);
	
	// 비밀번호 변경
	@Update("UPDATE tb_user "
		  + "SET PASSWORD = #{password} "
		  + "WHERE userid = #{userid}")
	public void changePassword(UserVO user);

	// 회원 탈퇴
	@Delete("DELETE FROM tb_user "
		  + "WHERE userid = #{userid}")
	public void deleteUser(UserVO user);
	
	
}
