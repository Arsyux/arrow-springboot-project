package com.arsyux.arrow.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.annotations.Param;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
import com.arsyux.arrow.domain.Pagination;
import com.arsyux.arrow.dto.ExhibitionDTO;
import com.arsyux.arrow.dto.ExhibitionDTO.InsertExhibitionValidationGroup;
import com.arsyux.arrow.dto.ResponseDTO;
import com.arsyux.arrow.service.ExhibitionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Controller
public class PostController {
	
	// 20240324 주대현
	// 환경 변수로 파일 저장 경로 수정가능하게 변경
	@Value("${FILE_PATH}")
	private String FILE_PATH;
	
	@Autowired
	private ExhibitionService exhibitionService;
	
    @Autowired
    FileService fileService;
	
    @Autowired
	private ModelMapper modelMapper;
    
	// 메인 화면 이동
	@GetMapping({ "", "/" })
	public String getHome() {
		return "index";
	}
	
	// 로그인 페이지
	// 로그인 - admin 분리 240330
	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false)String error,
						@RequestParam(value = "exception", required = false)String exception,
						Model model) {
		// 로그인 실패시 error를 담음
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "user/login";
	}
	
	// Admin 페이지 -> 전시, 전시품 글쓰기와 통합
	// 전시 글쓰기 페이지 이동
	@GetMapping({"/adm", "/user/exhibitionWrite"})
	public String getExhibitWrite() {
		return "user/exhibitionWrite";
	}
	
	// 전시품 글쓰기 페이지 이동
	@GetMapping("user/collectionWrite")
	public String getCollectionWrite() {
		return "user/collectionWrite";
	}
	
	// 박물관 장소 페이지 이동
	@GetMapping("/exhibition/view/info")
	public String getInfo() {
		return "exhibition/info";
	}

	// 전시 글쓰기 - 임시 경로에 이미지 파일 업로드
	// 주대현 - 240326
	@PostMapping("/exhibition/function/uploadImageFile")
	public @ResponseBody ResponseDTO<?> uploadImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		
		// 임시 저장 경로
		//System.out.println("[전시 글쓰기] 임시 파일 저장 경로 : " + FILE_PATH);
		
		// 폴더가 없을 경우 폴더 생성
		File folder = new File(FILE_PATH);
		if(!folder.exists()) { folder.mkdir(); }
		
		// 오리지날 파일명
		String originalFileName = multipartFile.getOriginalFilename();
		// 파일 확장자
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		// 저장될 파일명
		String savedFileName = UUID.randomUUID() + extension;
		
		// 파일 객체 생성
		File targetFile = new File(FILE_PATH +savedFileName);
		
		try {
			// 파일을 임시 폴더에 복사
			multipartFile.transferTo(targetFile);
			
			//System.out.println("[전시 글쓰기] 임시 파일 저장 완료");
		} catch (IOException ioe) {
			//System.out.println("[전시 글쓰기] 임시 파일 저장 실패");
			ioe.printStackTrace();
		}
		
		// 저장 경로를 반환
		//System.out.println("[전시 글쓰기] 임시 파일 이미지 경로 반환 : " + "/image/temp/" + savedFileName);
		return new ResponseDTO<>(HttpStatus.OK.value(), "/image/temp/" + savedFileName);
	}
	
	// 전시 글쓰기 기능 구현
	// 주대현 - 240404
	@PostMapping("/exhibition/function/exhibitionWrite")
	public @ResponseBody ResponseDTO<?> insertExhibition(@Validated(InsertExhibitionValidationGroup.class) @RequestBody ExhibitionDTO exhibitionDTO,BindingResult bindingResult) {
		
		// ExhibitionDTO를 통해 유효성 검사
		ExhibitionVO exhibit = modelMapper.map(exhibitionDTO, ExhibitionVO.class);
		
		// 게시글 작성 날짜
		// 2024-04-04 -> 20240404
		LocalDateTime nowTime = LocalDateTime.now();
		String nowTimeStr = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		String nowTimeDate = nowTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		System.out.println("날짜: " + nowTimeStr);
		System.out.println("날짜: " + nowTimeDate);
		
		String localDate = LocalDateTime.now().toLocalDate().toString().replace("-", "");
		
		//exhibit.setCreateDt(localDate);
		
		// 상세 내용에 저장된 임시 이미지 경로를 저장될 폴더 경로로 수정
		exhibit.setDetails_exhibit(exhibit.getDetails_exhibit().replace("src=\"/image/temp/", "src=\"/image/" + localDate +"/"));
		
		// 게시글을 작성하고 해당 게시글 번호를 반환받기
		//int exhibitionPK = exhibitionService.insertExhibition(exhibit);
		
		// 파일 옮기기

	    return new ResponseDTO<>(HttpStatus.OK.value(), "작성되었습니다");
	    //return new ResponseDTO<>(HttpStatus.OK.value(), exhibitionPK + " 작성되었습니다");      
	}
	
	// 본관 - 프로그램 안내 이동
	// 이승현 - 백업
	
	@GetMapping("/exhibition/view/exhibition")
	public String getExhibit(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int range) {
		//page limit, offset 데이터 조회
		 Pagination pagination = new Pagination();

        // 총 페이지 수 계산
        int totalPages = exhibitionService.getTotalPages();
		pagination.pageInfo(page, range, totalPages);
        
		// 페이지 번호를 5개까지만 넘어가게 설정
		List<ExhibitionVO> contentsList = exhibitionService.selectAllContent(pagination);

		model.addAttribute("pagination", pagination);
        model.addAttribute("contentsList", contentsList);
		
        
		return "exhibition/exhibition";
	}
	
	

	/*
	 * 본관 게시글 작성 기능
	 */
	/*
	@PostMapping("/contents/function/exhibitionWrite")
	public @ResponseBody ResponseDTO<?> postBoard(@RequestPart(value = "filename", required = false) List<MultipartFile> files,
	        @Validated(InsertTextValidationGroup.class) ExhibitionDTO exhibitionDTO, BindingResult bindingResult) throws Exception, IOException {

	    // UserDTO를 통해 유효성 검사 
	    ExhibitionVO cont = modelMapper.map(exhibitionDTO, ExhibitionVO.class);

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
	        
	        exhibitionService.insertContent(cont);
	        exhibitionService.insertFile(cont, fileInfos);
	    } catch (Exception e) {
	        System.out.println("Error");
	    }
	    
	    return new ResponseDTO<>(HttpStatus.OK.value(), cont.getName_exhibit() + "작성되었습니다");      
	}
	*/
	
	// 전시 정보 페이지
	@GetMapping("/exhibition/view/exhibitionInfo/{encryptedExhseq}")
	public String getExhibitionInfo(Model model,@PathVariable("encryptedExhseq") String encryptedExhseq) {
		
		encryptedExhseq = new String(Base64.getDecoder().decode(encryptedExhseq));
		    
		    // exhseq를 정수형으로 변환하여 사용할 수 있음
		int exh_seq = Integer.parseInt(encryptedExhseq);
		List<ExhibitionVO> contentsList = exhibitionService.selectOneContent(exh_seq);
		
		model.addAttribute("content", contentsList);
		return "exhibition/exhibitionInfo";
	}
	
	// 전시 정보 페이지
	@PostMapping("/exhibition/view/exhibitionInfo")
	public @ResponseBody ResponseDTO<?> postExhibitionInfo(Model model,@RequestParam("exhseq") int exh_seq) {
		
		
		
		return  new ResponseDTO<>(HttpStatus.OK.value(), "");      
	}	
	
	
	
	
	
	
}