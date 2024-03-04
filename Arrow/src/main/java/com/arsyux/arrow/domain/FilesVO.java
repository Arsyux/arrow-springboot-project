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
public class FilesVO {
	
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
