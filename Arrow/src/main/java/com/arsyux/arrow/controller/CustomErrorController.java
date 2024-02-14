package com.arsyux.arrow.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {
	
	// 에러페이지 이동
	@RequestMapping(value = "/error")
    public String handleError(HttpServletRequest request) {
        return "error/err";
    }
	
}
