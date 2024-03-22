package com.arsyux.arrow.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionVO {
	
	private int exh_seq;
	private String name_exhibit;
	private String subname_exhibit;
	private String space_exhibit;
	private String startDate_exhibit;
	private String endDate_exhibit;
	private String createDt;
	private String tag_exhibit;
	private String descript_exhibit;
	
}
