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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.CollectionsVO;
import com.arsyux.arrow.domain.ExhibitionVO;
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
	@GetMapping("/exhibition/view/collection")
	public String getCollections(Model model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "4") int pageSize) {
		
		int exh_seq = 1;
		List<CollectionsVO> contentsList = collectionService.selectCollect(exh_seq, pageNumber, pageSize);
		
		int totalPages = collectionService.getTotalPages(pageSize);
        int maxPageNumber = Math.min(totalPages, 5);
        List<Integer> pageNumbers = new ArrayList<>();
        
        for (int i = 0; i < maxPageNumber; i++) {
            pageNumbers.add(i);
        }
        
        model.addAttribute("contentsList", contentsList);
        model.addAttribute("pageNumbers", pageNumbers); 
        model.addAttribute("totalPages", totalPages); 
        model.addAttribute("pageNumber", pageNumber); // 현재 페이지 번호 전달
        model.addAttribute("pageSize", pageSize); 	
		
		return "exhibition/collection/collection";
	}
	
	// 전시 정보 페이지
	@GetMapping("/exhibition/view/CollectionInfo/{encryptedCode}")
	public String getCollectInfo(Model model,@PathVariable("encryptedCode") String encryptedCode) {
		 
		encryptedCode = new String(Base64.getDecoder().decode(encryptedCode));
		
		System.out.println("List<CollectionsVO> contentsList = collectionService.selectOneCollect(exh_seq);"+encryptedCode);
		
		List<CollectionsVO> collectionList = collectionService.selectOneCollect(encryptedCode);
		model.addAttribute("collect", collectionList);
		return "exhibition/collection/collectionInfo";
	}
	
	
}