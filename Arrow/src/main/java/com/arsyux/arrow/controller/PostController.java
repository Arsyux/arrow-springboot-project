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
	
	// 로그인 페이지
	@GetMapping("/adm")
	public String login(@RequestParam(value = "error", required = false)String error,
						@RequestParam(value = "exception", required = false)String exception,
						Model model) {
		// 로그인 실패시 error를 담음
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		return "user/loginUser";
	}
	
	// 박물관 장소 페이지 이동
	@GetMapping("/exhibition/view/info")
	public String getInfo() {
		return "exhibition/info";
	}

	// 전시 글쓰기 기능 구현
	// 주대현 - 240326
	@PostMapping("/exhibition/function/exhibitionWrite")
	public @ResponseBody ResponseDTO<?> insertExhibition(@Validated(InsertExhibitionValidationGroup.class) ExhibitionDTO exhibitionDTO, BindingResult bindingResult) {
		
		// ExhibitionDTO를 통해 유효성 검사
		ExhibitionVO exhibit = modelMapper.map(exhibitionDTO, ExhibitionVO.class);
		
		
		
	    return new ResponseDTO<>(HttpStatus.OK.value(), /*cont.getName_exhibit() +*/ "작성되었습니다");      
	}
	
	// 전시 글쓰기 페이지 이동
	@GetMapping("/exhibition/function/exhibitionWrite")
	public String ExhibitWrite() {
		return "exhibition/exhibitionWrite";
	}
	
	// 전시 글쓰기 이미지 파일 업로드
	// 주대현 - 240326
	@PostMapping("/exhibition/function/uploadImageFile")
	public @ResponseBody ResponseDTO<?> uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile)  {
		
		// 저장 경로
		System.out.println("[전시 글쓰기] 파일 저장 경로 : " + FILE_PATH);
		
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
			
			System.out.println("[전시 글쓰기] 파일 저장 완료");
		} catch (IOException ioe) {
			System.out.println("[전시 글쓰기] 파일 저장 실패");
			ioe.printStackTrace();
		}
		
		// 저장 경로를 반환
		System.out.println("[전시 글쓰기] 이미지 경로 반환 : " + "/image/temp/" + savedFileName);
		return new ResponseDTO<>(HttpStatus.OK.value(), "/image/temp/" + savedFileName);
	}

	
	
	
	
	
	
	// 본관 - 프로그램 안내 이동
	// 이승현 - 백업
	
	@GetMapping("/exhibition/view/exhibition")
	public String getExhibit(Model model, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "1") int range) {
		//page limit, offset 데이터 조회
		 Pagination pagination = new Pagination();

        // 총 페이지 수 계산
        int totalPages = exhibitionService.getTotalPages();
        System.out.println("totalPages"+totalPages);

		pagination.pageInfo(pageNumber, range, totalPages);
        
		// 페이지 번호를 5개까지만 넘어가게 설정
		List<ExhibitionVO> contentsList = exhibitionService.selectAllContent(pagination);
		System.out.println(contentsList);

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
	
	
	
	//20240327 페이징 기능 수정중
	//이승현
	public class Pagination {
		private int listSize = 5;                //초기값으로 목록개수를 10으로 셋팅
		private int rangeSize = 5;            //초기값으로 페이지범위를 10으로 셋팅
		private int page;
		private int range;
		private int listCnt;
		private int pageCnt;
		private int startPage;
		private int startList;
		private int endPage;
		private boolean prev;
		private boolean next;

		public int getRangeSize() {
			return rangeSize;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getRange() {
			return range;

		}

		public void setRange(int range) {
			this.range = range;
		}

		public int getStartPage() {
			return startPage;
		}


		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}

		public int getEndPage() {
			return endPage;
		}

		public void setEndPage(int endPage) {
			this.endPage = endPage;
		}

		public boolean isPrev() {
			return prev;
		}

		public void setPrev(boolean prev) {
			this.prev = prev;
		}

		public boolean isNext() {
			return next;
		}

		public void setNext(boolean next) {
			this.next = next;
		}

		public int getListSize() {
			return listSize;
		}

		public void setListSize(int listSize) {
			this.listSize = listSize;
		}

		public int getListCnt() {
			return listCnt;
		}

		public void setListCnt(int listCnt) {
			this.listCnt = listCnt;
		}

		public int getStartList() {
			return startList;
		}

		public void pageInfo(int page, int range, int listCnt) {
			this.page = page;
			this.range = range;
			this.listCnt = listCnt;

			//전체 페이지수 
			this.pageCnt = (int) Math.ceil(listCnt/listSize);			

			//시작 페이지
			this.startPage = (range - 1) * rangeSize + 1 ;

			//끝 페이지
			this.endPage = range * rangeSize;

			//게시판 시작번호
			this.startList = (page - 1) * listSize;

			//이전 버튼 상태
			this.prev = range == 1 ? false : true;

			//다음 버튼 상태
			this.next = endPage > pageCnt ? false : true;
			
			if (this.endPage > this.pageCnt) {
				this.endPage = this.pageCnt;
				this.next = false;
			}
		}
	}
	
	
}