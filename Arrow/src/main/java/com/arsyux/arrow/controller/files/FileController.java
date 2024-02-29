package com.arsyux.arrow.controller.files;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping("/files/file-upload")
    public String mains(@RequestPart(value = "file", required = false) MultipartFile uploadFile) {
    	System.out.println("@#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "/";
    }

    @PostMapping("/files/file-upload")
    public ResponseEntity fileUpload(@RequestPart(value = "file", required = false) MultipartFile uploadFile) {
        fileService.fileUpload(uploadFile);
        return ResponseEntity.ok().build();
    }
}