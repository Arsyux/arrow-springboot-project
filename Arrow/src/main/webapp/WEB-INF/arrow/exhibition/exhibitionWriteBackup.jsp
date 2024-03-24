<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	<!-- 제목 -->
    <title>게시글 작성</title>
    
</head>
<body>

<!-- 관리자 로그인시에만 div 노출 -->
<c:if test="${!empty principal }">

	<header></header>

	<article>
		<!-- 대현 수정 240324 -->
		<div class="container mt-4 p-0 border border-1 border-dark" align="center">
		
			<!-- 현재 위치 -->
			<div class="row m-0 p-0">
			<div class="col-12 m-0 p-0 border-bottom border-1 border-dark" style="height: 5vw; background-color: #269630; color: #ffffff; font-size: 3vw;" align="center">
				게시글 작성
			</div>
			
			<!-- 데이터 -->
			<form>
			
				<!-- 태그, 제목 -->
				<div class="row ms-0 me-0 mt-3 mb-3 p-0">
					<div class="input-group">
						<span class="input-group-text border-0" style="width: 20%; font-size: 1vw; font-weight: bold; justify-content: center; background-color: #ffffff;">전시 시작 날짜</span>
						
						<!-- 태그 -->
						<select class="form-select" style="width: 20%; font-size: 1vw; border-radius: 0px; justify-content: center;">
							<option value="전시" selected>전시</option>
							<option value="교육">교육</option>
							<option value="행사">행사</option>
							<option value="체험">체험</option>
						</select>
						<!-- 제목 -->
						<input type="text" class="form-control" id="exhibitionTitle" placeholder="제목" style="width: 60%; font-size: 1vw; border-radius: 0px;"></input>
					</div>
				</div>
				
				<div class="row ms-0 me-0 mt-3 mb-3 p-0">
					<div class="input-group">
						<span class="input-group-text border-0" style="font-size: 1vw; font-weight: bold; justify-content: center; background-color: #ffffff;">전시 시작 날짜</span>
						
						<!-- 태그 -->
						<select class="form-select" style="font-size: 1vw; border-radius: 0px; justify-content: center;">
							<option value="전시" selected>전시</option>
							<option value="교육">교육</option>
							<option value="행사">행사</option>
							<option value="체험">체험</option>
						</select>
						
						<!-- 제목 -->
						<input type="text" class="form-control" id="exhibitionTitle" placeholder="제목" style="font-size: 1vw; border-radius: 0px;"></input>
					</div>
				</div>
				
				<!-- 태그, 제목 -->
				<div class="row ms-0 me-0 mt-3 mb-3 p-0">
					<div class="input-group">
						<span class="input-group-text border-0" style="font-size: 1vw; font-weight: bold; justify-content: center; background-color: #ffffff;">전시 시작 날짜</span>
						
						<!-- 태그 -->
						<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">전시</button>
						  <ul class="dropdown-menu">
						    <li><a class="dropdown-item" href="#">전시</a></li>
						    <li><a class="dropdown-item" href="#">교육</a></li>
						    <li><a class="dropdown-item" href="#">행사</a></li>
						    <li><hr class="dropdown-divider"></li>
						    <li><a class="dropdown-item" href="#">체험</a></li>
						  </ul>
						  <input type="text" class="form-control" aria-label="Text input with dropdown button">
					</div>
				</div>
				
				  
			</form>
			
			
		</div>
		
		
			
		
		
		<form>
			
			<!-- 태그, 제목 -->
			<div class="row ms-0 me-0 mt-3 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>제목</b></div>
				<!-- 태그 -->
				<div class="col-8 m-0 p-0">
				<div class="input-group">
					<select class="form-select" style="font-size: 1vw; width: 12%;">
						<option value="전시" selected>전시</option>
						<option value="교육">교육</option>
						<option value="행사">행사</option>
						<option value="체험">체험</option>
					</select>
					<input type="text" class="form-control" id="exhibitionTitle" placeholder="제목" style="font-size: 1vw; width: 88%;"></input>
				</div>
				</div>
			</div>
				
			<!-- 간략 설명 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>간략 설명</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<textarea class="form-control" rows="5" id="exhibitionDescription" placeholder="간략 설명" style="resize: none; font-size: 1vw;"></textarea>
						<div id="exhibitionDescriptionInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
			<!-- 상세 설명 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>상세 설명</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group" align="left">
						<textarea class="form-control" rows="5" id="exhibitionDetails" placeholder="상세 설명" style="resize: none; font-size: 1vw;"></textarea>
						<div id="exhibitionDetailsInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
			<!-- 장소 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>장소</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<input type="text" class="form-control" id="exhibitionSpace" placeholder="장소" style="font-size: 1vw;"></input>
						<div id="exhibitionSpaceInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
			<!-- 오늘 날짜 -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:set var="nowDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 
			
			<!-- 전시 시작 날짜 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>전시 시작 날짜</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<input type="date" class="form-control" id="exhibitionStartDate" style="font-size: 1vw;" value="${ nowDate }"></input>
						<div id="exhibitionStartDateInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
			<!-- 전시 종료 날짜 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>전시 종료 날짜</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<input type="date" class="form-control" id="exhibitionEndDate" style="font-size: 1vw;" value="${ nowDate }"></input>
						<div id="exhibitionEndDateInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
							
		</form>
		
		<!-- 글쓰기 버튼 -->
		<div class="row ms-0 me-0 mt-3 mb-3 p-0">
			<div class="col-5 m-0 p-0"></div>
			<div class="col-2 m-0 p-0">
				<button id="btn-insertExhibition" class="btn btn-large w-100" style="background-color: #269630; color: #ffffff; font-size: 1vw; font-weight: bold;" value="글쓰기2">글쓰기</button>
			</div>
		</div>
		
	</div>
	
	</article>
</c:if>

<!-- 본관 - 프로그램 안내 글쓰기 JS -->
<script src="/js/exhibition.js"></script>

<%@ include file="../layout/footer.jsp" %>