package com.arsyux.arrow.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.Pagination;
import com.arsyux.arrow.service.CollectionService;

@Controller
public class CollectionController {
	
	// 파일이 저장되는 경로
	@Value("${FILE_PATH}")
	private String FILE_PATH;
	
	
	//@Autowired
	//private PostService postService;

	@Autowired
	private CollectionService collectionService;
	
    @Autowired
    FileService fileService;
	
    @Autowired
	private ModelMapper modelMapper;
    
	// ========================================
	// 기본 화면 설정
	// ========================================
	
	// 전시 상세 페이지
	@GetMapping("/exhibition/view/collection")
	public String getCollections(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int range) {
		Pagination pagination = new Pagination();
		int exh_seq = 1;
		
		int totalPages = collectionService.getTotalPages();
		pagination.pageInfo(page, range, totalPages);
		

		List<CollectionsVO> collectList = collectionService.selectCollect(exh_seq, pagination);
        
		
        model.addAttribute("collectList", collectList);
        model.addAttribute("pagination", pagination); 

		
		return "exhibition/collection/collection";
	}
	
	// 전시 정보 페이지
	@GetMapping("/exhibition/view/CollectionInfo/{encryptedCodeName}")
	public String getCollectInfo(Model model,@PathVariable("encryptedCodeName") String encryptedCode) {
		encryptedCode = new String(Base64.getDecoder().decode(encryptedCode));
	
		List<CollectionsVO> collectionList = collectionService.selectOneCollect(encryptedCode);
		try {
			BufferedImage image;
			//로컬 파일을 사용하는 경우 
			File imageFile = new File(FILE_PATH , "ad56a20b-422f-4d81-a0f5-b5772e2c70b1.jpg");
			image = ImageIO.read(imageFile);
			System.out.println("image"+image);
			model.addAttribute("image", image);
		 }catch (Exception e) {
			// TODO: handle exception
		}
		
		
		model.addAttribute("collect", collectionList);
		return "exhibition/collection/collectionInfo";
	}
	
	
}