package com.arsyux.arrow.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.dto.ContentsDTO;
import com.arsyux.arrow.dto.ContentsDTO.InsertTextValidationGroup;
import com.arsyux.arrow.dto.FileDTO;
import com.arsyux.arrow.dto.ResponseDTO;
import com.arsyux.arrow.service.contentsService;

@Controller
public class PostController {
	
	// 파일이 저장되는 경로
	private static final String FILE_PATH = "C:/NewFolder";
	//@Autowired
	//private PostService postService;

	@Autowired
	private contentsService contentService;
	
    @Autowired
    FileService fileService;
	
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
	public @ResponseBody ResponseDTO<?> postBoard(@RequestPart(value = "filename", required = false) List<MultipartFile> files,
	        @Validated(InsertTextValidationGroup.class) ContentsDTO contentsDTO, BindingResult bindingResult) throws Exception, IOException {

	    // UserDTO를 통해 유효성 검사 
	    ContentsVO cont = modelMapper.map(contentsDTO, ContentsVO.class);
	    //FileUpload file = new FileUpload();
	    
	    //fileService.fileUpload(uploadFile);
	    //String realPath = "/Users/hvvany/Desktop/OISO_BE/last_pjt/trip/src/main/resources/static/imgs";  // 스프링 부트에서 파일 저장 시 상대경로로 하면 경로 못찾음
	    try {
	        String today = new SimpleDateFormat("yyMMdd").format(new Date());
	        File folder = new File(FILE_PATH);
	        
	        // 파일 경로 없으면 폴더 생성
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }
	        List<FileDTO> fileInfos = new ArrayList<FileDTO>();
	        for (MultipartFile mfile : files) {
	            FileDTO fileInfo = new FileDTO();
	            String originalFileName = mfile.getOriginalFilename();

	            
	            if (!originalFileName.isEmpty()) {
	                String saveFileName = UUID.randomUUID().toString()  // UUID는 이미지 이름 중복 방지 위해 랜덤하게 생성된 고유값
	                        + originalFileName.substring(originalFileName.lastIndexOf('.'));
	                fileInfo.setSaveFolder(today);
	                fileInfo.setOriginFile(originalFileName);
	                fileInfo.setSaveFile(saveFileName);
	                
	                mfile.transferTo(new File(folder, saveFileName));
//	            FileCopyUtils.copy(mfile.getInputStream(), new FileOutputStream(realPath + Paths.get(saveFileName).toFile()));
	            }


	            fileInfos.add(fileInfo);
	        }
	    } catch (Exception e) {
	        // TODO: handle exception
	    }
	    
	    contentService.insertContent(cont);
	    return new ResponseDTO<>(HttpStatus.OK.value(), cont.getName_exhibit() + "작성되었습니다");      
	}
	
}