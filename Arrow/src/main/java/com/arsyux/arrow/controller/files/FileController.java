package com.arsyux.arrow.controller.files;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.dto.ResponseDTO;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/files/file-upload")
    public String mains(@RequestPart(value = "filename", required = false) MultipartFile uploadFile) {
    	System.out.println("@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "/";
    }

    @PostMapping("/files/file-upload")
    public ResponseEntity<ResponseDTO<?>> fileUpload(@RequestPart(value = "file", required = false)
    MultipartFile uploadFile)throws IOException {
      try {
    	if(!uploadFile.isEmpty()) {
        	System.out.println("file isn't Empty : "+uploadFile.toString());
        	fileService.fileUpload(uploadFile);

        }else {
        	System.out.println("file is Empty : "+uploadFile.toString());
        }
      }catch (Exception e) {
		System.out.println(e);
	}
        return ResponseEntity.ok(new ResponseDTO<>());
    }
}