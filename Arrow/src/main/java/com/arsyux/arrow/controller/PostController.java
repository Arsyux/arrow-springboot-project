package com.arsyux.arrow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	//@Autowired
	//private PostService postService;

	// ========================================
	// 기본 화면 설정
	// ========================================
	
	// 메인 화면
	@GetMapping({ "", "/" })
	public String getHome(Model model) {
		return "index";
	}
	
	// 메인 화면
	@GetMapping({ "/adm" })
	public String getAdminLogin(Model model) {
		return "user/loginUser";
	}
	
}