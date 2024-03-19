package com.arsyux.arrow.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.ContentsVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.dto.ContentsDTO;
import com.arsyux.arrow.dto.ContentsDTO.InsertTextValidationGroup;
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
	@GetMapping("/contents/view/info")
	public String getInfo() {
		return "contents/info";
	}
	
	//
	@GetMapping("/contents/view/exhibition")
	public String getExhibit(Model model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "4") int pageSize) {
		//page limit, offset 데이터 조회
		List<ContentsVO> contentsList = contentService.selectAllContent(pageNumber, pageSize);
		System.out.println(contentsList);
        // 총 페이지 수 계산
        int totalPages = contentService.getTotalPages(pageSize);
        
        // 페이지 번호를 5개까지만 넘어가게 설정
        int maxPageNumber = Math.min(totalPages, 5);
        List<Integer> pageNumbers = new ArrayList<>();
        
        for (int i = 0; i < maxPageNumber; i++) {
            pageNumbers.add(i);
        }
        
        model.addAttribute("totalPages", totalPages); 
        model.addAttribute("contentsList", contentsList);
        model.addAttribute("pageNumber", pageNumber); // 현재 페이지 번호 전달
        model.addAttribute("pageSize", pageSize); 	
		

		return "contents/exhibition";
	}
	
	// 본관 게시글 작성 페이지 이동
	@GetMapping("/contents/function/exhibitionWrite")
	public String ExhibitWrite(Model model,  @ModelAttribute("ContentsVO") ContentsVO contentsVO) {
		System.out.println("ExhibitWrite Page"+LoggingSystem.class);;
		
		model.addAttribute("ContentsVO", contentsVO);

		return "contents/exhibitionWrite";
	}

	/*
	 * 본관 게시글 작성 기능
	 * */
	@PostMapping("/contents/function/exhibitionWrite")
	public @ResponseBody ResponseDTO<?> postBoard(@RequestPart(value = "filename", required = false) List<MultipartFile> files,
	        @Validated(InsertTextValidationGroup.class) ContentsDTO contentsDTO, BindingResult bindingResult) throws Exception, IOException {

	    // UserDTO를 통해 유효성 검사 
	    ContentsVO cont = modelMapper.map(contentsDTO, ContentsVO.class);

	    try {
	        String today = new SimpleDateFormat("yyMMdd").format(new Date());
	        File folder = new File(FILE_PATH);
	        
	        // 파일 경로 없으면 폴더 생성
	        if (!folder.exists()) {
	            folder.mkdirs();
	        }
	        List<FilesVO> fileInfos = new ArrayList<FilesVO>();
	        for (MultipartFile mfile : files) {
	        	FilesVO fileInfo = new FilesVO();
	            String originalFileName = mfile.getOriginalFilename();
	            String fileType = mfile.getContentType();
	            
	            if (!originalFileName.isEmpty()) {
	                String saveFileCode = UUID.randomUUID().toString();  // UUID는 이미지 이름 중복 방지 위해 랜덤하게 생성된 고유값
	                //originalFileName = originalFileName.substring(originalFileName);
	                
	                fileInfo.setSaveFolder(today);
	                fileInfo.setFile_type(fileType);
	                fileInfo.setFile_code(saveFileCode);
	                fileInfo.setFile_originName(originalFileName);
	                
	                mfile.transferTo(new File(folder, saveFileCode));
//	            FileCopyUtils.copy(mfile.getInputStream(), new FileOutputStream(realPath + Paths.get(saveFileName).toFile()));
	            }


	            fileInfos.add(fileInfo);
	        }
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        cont.setStartDate_exhibit(dateFormat.format(cont.getStartDate_exhibit()));
	        cont.setEndDate_exhibit(dateFormat.format(cont.getEndDate_exhibit()));
	        cont.setCreateDt(dateFormat.format(cont.getCreateDt()));
	        
	        contentService.insertContent(cont);
	        contentService.insertFile(cont, fileInfos);
	    } catch (Exception e) {
	        System.out.println("Error");
	    }
	    
	    return new ResponseDTO<>(HttpStatus.OK.value(), cont.getName_exhibit() + "작성되었습니다");      
	}
	
	// 전시 정보 페이지
	@GetMapping("/contents/view/exhibitionInfo")
	public String getExhibitionInfo(Model model,@RequestParam("exhseq") int exh_seq) {
		
		List<ContentsVO> contentsList = contentService.selectOneContent(exh_seq);
		
		model.addAttribute("content", contentsList);
		return "contents/exhibitionInfo";
	}
	// 전시 정보 페이지
	@PostMapping("/contents/view/exhibitionInfo")
	public @ResponseBody ResponseDTO<?> postExhibitionInfo(Model model,@RequestParam("exhseq") String exh_seq) {
		
		
		System.out.println("@GetMapping(\"/contents/view/exhibitionInfo/{exh_seq}\")"+exh_seq);
		
		return  new ResponseDTO<>(HttpStatus.OK.value(), "");      
	}	
	// 전시 상세 페이지
	@GetMapping("/contents/view/exhibitionDetails")
	public String getExhibitionDetails() {
		return "contents/exhibitionDetails";
	}
	
	
}