package com.arsyux.arrow.controller.files;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;





@Service
public class FileServiceImpl implements FileService {
	
	   // 파일이 저장되는 경로
	private static final String FILE_PATH = "C:/NewFolder";
	
	@Override
    public void fileCheck(MultipartFile uploadFile) {
        try(InputStream inputStream = uploadFile.getInputStream()) {
            System.out.println("Content Type : " + uploadFile.getContentType());

            if(!uploadFile.isEmpty()) {
                boolean isValid = FileUtils.validImgFile(inputStream);
                if(!isValid) {
                    // exception 처리
                    System.out.println("이미지 파일만 업로드 가능합니다.");
                }
                // 업로드 로직
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 파일을 업로드
    public String fileUploads(MultipartFile file) {
        Path path = Paths.get(FILE_PATH).toAbsolutePath().normalize();
        String filename = file.getOriginalFilename();
        Path targetPath = path.resolve(filename).normalize();
        try {
            file.transferTo(targetPath);
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 업로드에 실패했습니다.");
        }
        return filename;
    }
}