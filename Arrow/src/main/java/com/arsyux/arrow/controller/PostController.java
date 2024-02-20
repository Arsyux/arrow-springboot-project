package com.arsyux.arrow.controller;

import org.springframework.boot.logging.LoggingSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.arsyux.arrow.domain.ContentsVO;

@Controller
public class PostController {

	//@Autowired
	//private PostService postService;

	// ========================================
	// 기본 화면 설정
	// ========================================
	
	// 메인 화면 이동
	@GetMapping({ "", "/" })
	public String getHome() {
		return "index";
	}
	
	// 박물관 장소 페이지 이동
	@GetMapping("/post/arrowInfo")
	public String getArrowInfo() {
		return "post/arrowInfo";
	}
	
	// 본관 페이지 이동
	@GetMapping("/post/exhibit")
	public String getExhibit() {
		System.out.println(LoggingSystem.SYSTEM_PROPERTY);;
		
		return "contents/exhibition";
	}
	
	// 본관 등록 페이지 이동
	@GetMapping("/post/exhibitionWrite")
	public String ExhibitWrite(Model model,  @ModelAttribute("ContentsVO") ContentsVO contentsVO) {
		System.out.println(LoggingSystem.class);;
		
		
		
		
		model.addAttribute("ContentsVO", contentsVO);
		
		
		
		return "contents/exhibitionWrite";
	}
	
	// 본관 등록 페이지 이동
	@PostMapping("/post/insertSpce")
	public String insertSpce(Model model, @ModelAttribute("ContentsVO") ContentsVO contentsVO) {
		System.out.println(LoggingSystem.class);;
		
		
			
		model.addAttribute("ContentVo", contentsVO);
		
		
		
		return "contents/exhibitionWrite";
	}	
	
}