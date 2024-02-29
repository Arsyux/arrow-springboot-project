package com.arsyux.arrow.controller.files;


import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.persistence.contentsDAO;



@Service
public interface FileService {
    void fileUpload(MultipartFile uploadFile);
}
