package com.arsyux.arrow.domain;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilesVO {
	private int exh_seq;
	private String files;
	private String file_type;
	private String file_code;
	private Date createDt;
	private String file_originName;
	private String image_exhibhit;
	
	
	private String SaveFolder;
	private String OriginFile;
	private String SaveFile;
	
	
}
