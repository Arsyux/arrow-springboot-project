package com.arsyux.arrow.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 유저 유효성 검사
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionDTO {

	// 유효성 검사 그룹
	public interface InsertExhibitionValidationGroup { } // 전시 - 안내 게시글 작성시 유효성 검사
	//public interface InsertTextValidationGroup { } //text 입력 유효성 검사
	//public interface CheckDateValidateGroup{ } // 전시기간 날짜 유효성 검사
	
	// 태그
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "태그는 필수 선택 항목입니다.")
	private String tag_exhibit;
	
	// 제목
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "제목은 필수 입력 항목입니다.")
	@Size(groups = { InsertExhibitionValidationGroup.class }, min = 1, max = 40, message = "제목은 1자 이상 40자 미만으로 입력해주세요.")
	private String title_exhibit;
	
	// 간략 설명
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "간략 설명은 필수 입력 항목입니다.")
	@Size(groups = {InsertExhibitionValidationGroup.class}, min = 1, max = 40, message = "간략 설명은 1자 이상 40자 미만으로 입력해주세요")
	private String description_exhibit;

	// 전시 시작 날짜
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "전시 시작 날짜는 필수 입력 항목입니다.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String startDate_exhibit;

	// 전시 종료 날짜
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "전시 종료 날짜는 필수 입력 항목입니다.")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String endDate_exhibit;
	
	// 장소
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "장소 입력은 필수 입력 항목입니다.")
	@Size(groups = { InsertExhibitionValidationGroup.class }, min = 1, max = 15, message = "장소는 1자 이상 15자 미만으로 입력해주세요")
	private String space_exhibit;
	
	// 장소
	@NotBlank(groups = { InsertExhibitionValidationGroup.class }, message = "상세 내용은 필수 입력 항목입니다.")
	@Size(groups = { InsertExhibitionValidationGroup.class }, min = 1, max = 2000, message = "상세 내용은 1자 이상 2000자 미만으로 입력해주세요")
	private String details_exhibit;
	
}
