package com.arsyux.arrow.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentsVO {
	
	// 주대현 240318 수정
	private int exh_seq; // PK?
	private String name_exhibit; // 제목
	private String subname_exhibit; // 부제목
	private String space_exhibit; // 장소
	private String startDate_exhibit; // 전시 시작일
	private String endDate_exhibit; // 전시 종료일
	private String createDt; // 데이터 삽입 날짜
	private String tag_exhibit; // 태그
	private String image_exhibhit; // 이미지?

}
