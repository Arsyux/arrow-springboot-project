package com.arsyux.arrow.controller.files;


import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.persistence.contentsDAO;




@Service
public class FileServiceImpl implements FileService {

	@Override
    public void fileUpload(MultipartFile uploadFile) {
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
}