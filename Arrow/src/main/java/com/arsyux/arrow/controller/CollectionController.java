package com.arsyux.arrow.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.service.CollectionService;

@Controller
public class CollectionController {
	
	// 파일이 저장되는 경로
	private static final String FILE_PATH = "C:/NewFolder";
	
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
	@GetMapping("/contents/view/collection")
	public String getCollections(Model model) {
		System.out.println("/exhibition/view/exhibitionDetails");
		int exh_seq = 1;
		List<CollectionsVO> contentsList = collectionService.selectCollection(exh_seq);
		System.out.println(contentsList);
		model.addAttribute("contentsList", contentsList);
		
		return "exhibition/collection/collection";
	}
	
	
	
	
}