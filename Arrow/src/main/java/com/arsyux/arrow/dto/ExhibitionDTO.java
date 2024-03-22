package com.arsyux.arrow.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.arsyux.arrow.dto.UserDTO.InsertUserValidationGroup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 유저 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionDTO {

	// 유효성 검사 그룹
	public interface InsertTextValidationGroup { } //text 입력 유효성 검사
	public interface ChangePasswordValidationGroup { } // 비밀번호 변경시 유효성 검사
	public interface CheckDateValidateGroup{ } // 전시기간 날짜 유효성 검사
	
	
	@NotBlank(groups = { InsertTextValidationGroup.class }, 
			  message = "제목은 필수 입력 항목입니다.")
	@Size(groups = { InsertTextValidationGroup.class }, 
	      min = 2, max = 40,
		  message = "제목은 2자 이상 40자 미만으로 입력해주세요.")
	private String name_exhibit;
	
	@NotBlank(groups = { InsertTextValidationGroup.class }, 
			  message = "부제목은 필수 입력 항목입니다.")
	@Size(groups = {InsertTextValidationGroup.class},
			min = 2, max = 40,
			message = "부제목은 자 이상 40자 미만으로 입력해주세요")
	private String subname_exhibit;
	
	@NotBlank(groups = { InsertTextValidationGroup.class }, 
			  message = "장소 입력은 필수 입력 항목입니다.")
	@Size(groups = {InsertTextValidationGroup.class},
	min = 1, max = 15,
	message = "장소는 1자 이상 15자 미만으로 입력해주세요")
	private String space_exhibit;
	
	@NotBlank(groups = { InsertTextValidationGroup.class }, 
			  message = "태그 값은 필수 선택 항목입니다.")
	private String tag_exhibit;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String startDate_exhibit;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String endDate_exhibit;
}
