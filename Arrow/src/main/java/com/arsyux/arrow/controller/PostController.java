package com.arsyux.arrow.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.arsyux.arrow.controller.files.FileService;
import com.arsyux.arrow.domain.ExhibitionVO;
import com.arsyux.arrow.domain.FilesVO;
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
	
	// 박물관 장소 페이지 이동
	@GetMapping("/exhibition/view/info")
	public String getInfo() {
		return "exhibition/info";
	}
	
	// 주대현 - 작업 240318
	// 전시(본관 - 프로그램) 안내 이동
	@GetMapping("/exhibition/view/exhibition")
	public String getExhibition(Model model) {
		
		// 본관 게시글 조회
		List<ExhibitionVO> exhibits = null; //contentService.;
		
		model.addAttribute("Exhibits", exhibits);
		
		return "exhibition/exhibition";
	}
	
	// 본관 - 프로그램 안내 글쓰기
	// 주대현 - 작업 240324
	@PostMapping("/exhibition/function/exhibitionWrite")
	public @ResponseBody ResponseDTO<?> insertExhibition(@Validated(InsertExhibitionValidationGroup.class) ExhibitionDTO exhibitionDTO, BindingResult bindingResult) {
		
		// ExhibitionDTO를 통해 유효성 검사
		ExhibitionVO exhibit = modelMapper.map(exhibitionDTO, ExhibitionVO.class);
		
		
		
	    return new ResponseDTO<>(HttpStatus.OK.value(), /*cont.getName_exhibit() +*/ "작성되었습니다");      
	}
	
	// 파일 업로드
	//@RequestMapping(value="/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@PostMapping("/exhibition/function/uploadSummernoteImageFile")
	public @ResponseBody ResponseDTO<?> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		
		System.out.println("파일 업로드 진입");
		//JsonObject jsonObject = new JsonObject();
		
		// 내부경로로 저장
		//String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		//String fileRoot = contextRoot+"resources/fileupload/";
		
		//String fileRoot = FILE_PATH + "/";
		
		// 업로드 경로
		//String fileRoot = Paths.get("C:", "DEV", "eclipse-workspace", "arrow-springboot-project", 
		//		"Arrow", "src", "main", "resources", "static", "image", "test").toString();
		String fileRoot = "C:/DEV/eclipse/arrow-springboot-project/Arrow/src/main/resources/static/image/test/";
		//fileRoot ="/resources/static/image/test/";
		fileRoot = Paths.get("C:", "DEV", "eclipse-workspace", "arrow-springboot-project", 
				"Arrow", "src", "main", "resources", "static", "image", "test").toString();
		
		fileRoot = fileRoot + "/";
		
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot +savedFileName);	
		try {
			multipartFile.transferTo(targetFile);
			
			//InputStream fileStream = multipartFile.getInputStream();
			
			//FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			//jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName); // contextroot + resources + 저장할 내부 폴더명
			//jsonObject.addProperty("responseCode", "success");
			System.out.println("파일 저장 성공 " + fileRoot + savedFileName);
		} catch (IOException ioe) {

			System.out.println("파일 저장 실패");
			File deleteFile = new File(FILE_PATH + savedFileName);
			
			try { if(deleteFile.exists()) { deleteFile.delete(); } } 
			catch (Exception e) { 
				e.printStackTrace();
				/*throw new RuntimeException(e);*/
			}
			
			//FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			//jsonObject.addProperty("responseCode", "error");
			ioe.printStackTrace();
		}
		//String a = jsonObject.toString();
		return new ResponseDTO<>(HttpStatus.OK.value(), fileRoot + savedFileName);
	}
	
	
	
	// 본관 - 프로그램 안내 이동
	// 이승현 - 백업
	/*
	@GetMapping("/contents/view/exhibition")
	public String getExhibit(Model model, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "4") int pageSize) {
		//page limit, offset 데이터 조회
		List<ExhibitionVO> contentsList = exhibitionService.selectAllContent(pageNumber, pageSize);
		System.out.println(contentsList);
        // 총 페이지 수 계산
        int totalPages = exhibitionService.getTotalPages(pageSize);
        
        // 페이지 번호를 5개까지만 넘어가게 설정
        int maxPageNumber = Math.min(totalPages, 5);
        List<Integer> pageNumbers = new ArrayList<>();
        
        for (int i = 0; i < maxPageNumber; i++) {
            pageNumbers.add(i);
        }
        
        model.addAttribute("totalPages", totalPages); 
        model.addAttribute("pageNumber", pageNumber); // 현재 페이지 번호 전달
        model.addAttribute("pageNumbers", pageNumbers); // 현재 페이지 번호 전달
        model.addAttribute("pageSize", pageSize); 	
        model.addAttribute("contentsList", contentsList);
		
        
		return "exhibition/exhibition";
	}
	*/
	
	// 본관 게시글 작성 페이지 이동
	@GetMapping("/exhibition/function/exhibitionWrite")
	public String ExhibitWrite(Model model,  @ModelAttribute("ExhibitionVO") ExhibitionVO ExhibitionVO) {
		System.out.println("ExhibitWrite Page"+LoggingSystem.class);;
		
		model.addAttribute("ExhibitionVO", ExhibitionVO);

		return "exhibition/exhibitionWrite";
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