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
	
	// 본관 등록 페이지 이동
	@GetMapping("/post/exhibitionWrite")
	public String ExhibitWrite(Model model,  @ModelAttribute("ContentsVO") ContentsVO contentsVO) {
		System.out.println(LoggingSystem.class);;
		

		model.addAttribute("ContentsVO", contentsVO);
		
		
		
		return "contents/exhibitionWrite";
	}
	
//	// 본관 등록 페이지 이동
//	@PostMapping("/post/insertContent")
//	public String insertSpce(Model model, @ModelAttribute("ContentsVO") ContentsVO contentsVO,
//			 @RequestBody ContentsVO contentVO) {
//	
//		System.out.println("Contents VO "+contentVO);
//		try {
//		
//		model.addAttribute("message", "insert");
//		model.addAttribute("status", "success");
//		contentService.insertContent(contentsVO);
//
//		model.addAttribute("ContentVo", contentsVO);
//		
//		return "contents/exhibitionWrite";
//		}catch (Exception e) {
//			model.addAttribute("status", 400);
//			model.addAttribute("error", e.getMessage());
//			
//			return "redirect:/";
//		}
//		
//	}	
//	@PostMapping("/post/insertContent")
//	public String insertSpce(Model model, @RequestBody String info,
//	                         @RequestParam("name_exhibit") String nameExhibit,
//	                         @RequestParam("subname_exhibit") String subnameExhibit,
//	                         @RequestParam("space_exhibit") String spaceExhibit,
//	                         @RequestParam("startDate_exhibit") Date startDateExhibit,
//	                         @RequestParam("endDate_exhibit") Date endDateExhibit) {
//	    try {
//	        ContentsVO contentsVO = new ContentsVO();
//	        contentsVO.setName_exhibit(nameExhibit);
//	        contentsVO.setSubname_exhibit(subnameExhibit);
//	        contentsVO.setSpace_exhibit(spaceExhibit);
//	        contentsVO.setStartDate_exhibit(startDateExhibit);
//	        contentsVO.setEndDate_exhibit(endDateExhibit);
//
//	        contentService.insertContent(contentsVO);
//
//	        model.addAttribute("ContentVo", contentsVO);
//	        model.addAttribute("message", "insert");
//	        model.addAttribute("status", "success");
//
//	        return "contents/exhibition";
//	    } catch (Exception e) {
//	        model.addAttribute("status", 400);
//	        model.addAttribute("error", e.getMessage());
//	        return "redirect:/";
//	    }
//	}
	
	// 회원 가입 기능
		@PostMapping("/post/exhibitionWrite")
		public @ResponseBody ResponseDTO<?> insertUser(@Validated(InsertTextValidationGroup.class) @RequestBody ContentsDTO contentsDTO, BindingResult bindingResult) {
			
			// UserDTO를 통해 유효성 검사
			ContentsVO cont = modelMapper.map(contentsDTO, ContentsVO.class);
			System.out.println("@@@@@@@@@@@@@@@@"+cont.toString());
			contentService.insertContent(cont);
			return new ResponseDTO<>(HttpStatus.OK.value(),cont.getName_exhibit()+"작성되었습니다");		
		}
	
}