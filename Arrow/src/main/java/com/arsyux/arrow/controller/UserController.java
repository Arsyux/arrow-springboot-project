package com.arsyux.arrow.controller;

//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

//import com.arsyux.arrow.domain.UserVO;
//import com.arsyux.arrow.dto.ResponseDTO;
//import com.arsyux.arrow.dto.UserDTO;
//import com.arsyux.arrow.dto.UserDTO.InsertUserValidationGroup;
//import com.arsyux.arrow.service.UserService;

@Controller
public class UserController {
	
	//@Autowired
	//private UserService userService;

	//@Autowired
	//private ModelMapper modelMapper;
	
	// ========================================
	// 1. 로그인
	// ========================================
	
	// 로그인 이동
	@GetMapping("/auth/loginUser")
	public String login(@RequestParam(value = "error", required = false)String error,
						@RequestParam(value = "exception", required = false)String exception,
						Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "user/loginUser";
	}
	
	/*
	// 회원 가입 이동
	@GetMapping("/auth/insertUser")
	public String insertUser() {
		return "user/insertUser";
	}
	
	// 회원 가입 기능
	@PostMapping("/auth/insertUser")
	public @ResponseBody ResponseDTO<?> insertUser(@Validated(InsertUserValidationGroup.class) @RequestBody UserDTO userDTO, BindingResult bindingResult) {
		
		// UserDTO를 통해 유효성 검사
		UserVO user = modelMapper.map(userDTO, UserVO.class);
		
		userService.insertUser(user);
		return new ResponseDTO<>(HttpStatus.OK.value(), user.getUsername() + "님 환영합니다!");		
	}
	*/
	
}
