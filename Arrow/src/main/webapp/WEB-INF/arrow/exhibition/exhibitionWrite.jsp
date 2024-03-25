<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	<!-- 제목 -->
    <title>게시글 작성</title>
    
</head>
<body>


<!-- 관리자 로그인시에만 div 노출 -->

<c:if test="${!empty principal }">

	<!-- 대현 수정 240324 -->
	<div class="container-fluid mt-0 p-0 border border-1 border-dark" align="center">
		
		<header>
			<!-- 헤더 -->
			<div class="row m-0 p-0">
				<div class="col-12 m-0 p-0 border-bottom border-1 border-dark" style="background-color: #269630; color: #ffffff; font-size: 5vw;" align="center">
					게시글 작성
				</div>
			</div>
		</header>
	
		<article>
			
			<!-- 오늘 날짜 -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<c:set var="nowDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 
					
			<!-- 본문 -->
			<div class="row m-0 p-0">
				
				<form>
					<!-- 테스트 -->
					<div class="row ms-0 me-0 mt-3 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group">
								<!-- 태그 -->
								<select class="form-select" style="width: 20%; font-size: 2vw; justify-content: center;">
									<option value="전시" selected>전시</option>
									<option value="교육">교육</option>
									<option value="행사">행사</option>
									<option value="체험">체험</option>
								</select>
								
								<!-- 제목 -->
								<input type="text" class="form-control" id="exhibitionTitle" placeholder="제목" style="width: 60%; font-size: 2vw;"></input>
							</div>
						</div>
					</div>
					
					<!-- 간략 설명 -->
					<div class="row ms-0 me-0 mt-0 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group">
								<textarea class="form-control" id="exhibitionDescription" rows="2" placeholder="간략 설명" style="font-size: 2vw;"></textarea>
								<div id="exhibitionDescriptionInvalid" class="invalid-feedback"></div>
							</div>
						</div>
					</div>
					
					<!-- 전시 날짜 -->
					<div class="row ms-0 me-0 mt-0 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group">
								<input type="date" class="form-control" id="exhibitionStartDate" data-placeholder="시작 날짜" style="font-size: 2vw;" required></input>
								<span class="input-group-text" style="width: 10%; font-size: 2vw; justify-content: center;">~</span>
								<input type="date" class="form-control" id="exhibitionEndDate" data-placeholder="종료 날짜" style="font-size: 2vw;" required></input>
								<div id="exhibitionStartDateInvalid" class="invalid-feedback"></div>
							</div>
						</div>
					</div>
					
					<!-- 장소 -->
					<div class="row ms-0 me-0 mt-0 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group" align="left">
								<input type="text" class="form-control" id="exhibitionSpace" placeholder="장소" style="font-size: 2vw;"></input>
								<div id="exhibitionSpaceInvalid" class="invalid-feedback"></div>
							</div>
						</div>
					</div>
					
					<!-- 상세 설명 -->
					<div class="row ms-0 me-0 mt-0 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group" align="left">
								<textarea id="exhibitionDetails" placeholder="상세 설명"></textarea>
								<div id="exhibitionDetailsInvalid" class="invalid-feedback"></div>
							</div>
						</div>
					</div>
					
					<div class="row ms-0 me-0 mt-0 mb-3 p-0">
						<div class="col-1"></div>
						<div class="col-10">
							<div class="input-group" align="left">
								<img id="test">
							</div>
						</div>
					</div>
				</form>
				
			</div>
			
			
			<!-- 글쓰기 버튼 -->
			<div class="row ms-0 me-0 mt-3 mb-3 p-0">
				<div class="col-5 m-0 p-0"></div>
				<div class="col-2 m-0 p-0">
					<button id="btn-insertExhibition" class="btn btn-large w-100" style="background-color: #269630; color: #ffffff; font-size: 1vw; font-weight: bold;" value="글쓰기2">글쓰기</button>
				</div>
			</div>
			
		
		</article>
	</div>
</c:if>

<!-- 본관 - 프로그램 안내 글쓰기 JS -->
<script src="/js/exhibition.js"></script>

<%@ include file="../layout/footer.jsp" %>