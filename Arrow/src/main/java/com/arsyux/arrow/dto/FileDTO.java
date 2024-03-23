package com.arsyux.arrow.dto;

import java.util.Date;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 유저 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

	// 유효성 검사 그룹
	public interface InsertTextValidationGroup { } //text 입력 유효성 검사
	public interface ChangePasswordValidationGroup { } // 비밀번호 변경시 유효성 검사
	public interface CheckDateValidateGroup{ } // 전시기간 날짜 유효성 검사
	
	@NotBlank(groups = { InsertTextValidationGroup.class }, 
			  message = "제목은 필수 입력 항목입니다.")
	private String files;
	private String file_size;
	private String file_name;
	private Date createDt;
	private String file;
	private String image_exhibhit;
	
	private String SaveFolder;
	private String OriginFile;
	private String SaveFile;
	
	
	
}
