package com.arsyux.arrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	// 박물관 장소 페이지 이동
	@RequestMapping(value = "/post/exhibit", method = RequestMethod.GET)
	public String getExhibit() {
		
		return "post/exhibition";
	}
}