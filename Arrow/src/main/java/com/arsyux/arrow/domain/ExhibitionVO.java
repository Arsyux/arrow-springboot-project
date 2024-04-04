package com.arsyux.arrow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionVO {
	
	private int exh_seq; // PK
	private String tag_exhibit; // 태그
	private String title_exhibit; // 제목
	private String description_exhibit; // 간략설명
	private String startDate_exhibit; // 전시 시작 날짜
	private String endDate_exhibit; // 전시 종료 날짜
	private String space_exhibit; // 장소
	private String details_exhibit; // 상세 내용
	private String createDt; // 글 작성 날짜

}
