package com.arsyux.arrow.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentsVO {
	
	private String name_exhibit;
	private String subname_exhibit;
	private String space_exhibit;
	private Date startDate_exhibit;
	private Date endDate_exhibit;
	private Date createDt;
	private String tag_exhibit;
	private String image_exhibhit;
	private int exh_seq;
	
}
