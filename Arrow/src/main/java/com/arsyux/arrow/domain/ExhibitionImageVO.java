package com.arsyux.arrow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionImageVO {
	
	//private int exhimg_seq; // 전시 이미지 PK
	private int exh_seq; // 전시 PK
	private String name_exhimg; // 이미지 파일 이름

}
