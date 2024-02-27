package com.arsyux.arrow.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.dto.ContentsDTO;
import com.arsyux.arrow.dto.ContentsDTO.InsertTextValidationGroup;
import com.arsyux.arrow.dto.ResponseDTO;
import com.arsyux.arrow.service.contentsService;

@Controller
public class PostController {

	//@Autowired
	//private PostService postService;

	@Autowired
	private contentsService contentService;
	
	@Autowired
	private ModelMapper modelMapper;	
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
	
	// 게시글 작성 페이지 이동
	@GetMapping("/post/exhibitionWrite")
	public String ExhibitWrite(Model model,  @ModelAttribute("ContentsVO") ContentsVO contentsVO) {
		System.out.println(LoggingSystem.class);;
		
		model.addAttribute("ContentsVO", contentsVO);

		return "contents/exhibitionWrite";
	}
	
	
	/*
	 * 게시글 작성 기능
	 * */
		@PostMapping("/post/exhibitionWrite")
		public @ResponseBody ResponseDTO<?> insertUser(@Validated(InsertTextValidationGroup.class) ContentsDTO contentsDTO, BindingResult bindingResult) {
			
			// UserDTO를 통해 유효성 검사
			ContentsVO cont = modelMapper.map(contentsDTO, ContentsVO.class);
			
			System.out.println("@@@@@@@@@@@@@@@@"+cont.toString());
			
			contentService.insertContent(cont);
			return new ResponseDTO<>(HttpStatus.OK.value(),cont.getName_exhibit()+"작성되었습니다");		
		}
	
}