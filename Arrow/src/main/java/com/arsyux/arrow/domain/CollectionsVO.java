package com.arsyux.arrow.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionsVO {
	
	private int exh_seq;//전시글 pk
	private String name_collect; //소장품이름
	private String codename_collect; //소장품코드
	private String period_collect; //소장품 국적,시대
	private String tag_exhibit; //태그
	private String metarial_collect; //소장품재질
	private String size_collect; //소장품 크기
	private String usage_collect; //소장품용도
	private String feature_collect; //소장품특징
	private String descript_collect; //상세 설명
	private String createDt; //게시글 생성일
	

	
}
