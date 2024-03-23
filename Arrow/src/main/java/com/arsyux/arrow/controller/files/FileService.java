package com.arsyux.arrow.controller.files;


import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;




@Service
public interface FileService {
    
	public void fileCheck(MultipartFile uploadFile);
    
	public String fileUploads(MultipartFile file);
}
