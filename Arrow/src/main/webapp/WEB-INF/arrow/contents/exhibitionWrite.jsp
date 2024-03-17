<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css" href="/css/contents.css">
<script type="text/javascript" src="/js/common-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js" integrity="sha512-+k1pnlgt4F1H8L7t3z95o3/KO+o78INEcXTbnoJQ/F2VqDVhWoaiVml/OEHv9HsVgxUaVW+IbiZPUJQfF/YxZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js" integrity="sha512-uto9mlQzrs59VwILcLiRYeLKPPbS/bT71da/OEBYEwcdNUk8jYIy+D176RYoop1Da+f9mvkYrmj5MCLZWEtQuA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css" integrity="sha512-aOG0c6nPNzGk+5zjwyJaoRUgCdOrfSDhmMID2u4+OIslr0GjpLKo7Xm0Ao3xmpM4T8AmIouRkqwj1nrdVsLKEQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />



<!-- 오늘일자 -->
<c:set var="nowDt"><fmt:formatDate value="<%=new Date() %>" pattern="yyyy-mm-dd"/></c:set>
<head>
    <title>게시글 작성</title>
</head>
<body>
<article class = "BoardList">
<!-- 관리자 로그인시 div 노출 -->
<c:if test="${!empty principal }">
<form:form id="insertForm" modelAttribute="ContentsVO" commandName="ContentsVO" method="post" action="/post/insertContent" enctype="multipart/form-data">
	<table class="contentsTable">
		<tbody>
	     	  <!-- 제목 -->
		<tr>
		
			<th>제목</th>
			<td>
				<form:input path="name_exhibit" />
				<div><form:errors path="name_exhibit" /></div>
			 </td>
		</tr>
	<!-- 간략 설명 -->
		<tr>
		
			<th>간략 설명</th>
			<td>
		    	<form:input path="subname_exhibit" />
			<div><form:errors path="subname_exhibit" /></div>
		 	</td>
		</tr>
	<!-- 장소 -->
		<tr>
		
			<th>장소</th>
			<td id="space" style="width: 70%;">
		 		<form:input path="space_exhibit" />
			<div><form:errors path="space_exhibit" /></div>
			</td>
			<td id = "dateExhibit" style="width: 30%; display: inline-flex;">
			<label for = "startDateExhibit" style = "font-size: 16px; font-weight: bold; margin-right:20px;">전시 기간</label>
				<form:input path="startDate_exhibit" type="text" class="startDateExhibit" id="startDateExhibit" name="startDateExhibit" title="시작날짜" placeholder="시작일자"/> 
				-
				<form:input path="endDate_exhibit" type="text" class="endDateExhibit" id="endDateExhibit" name="endDateExhibit" title="종료날짜" placeholder="종료일자"/>			
			<div><form:errors path="startDate_exhibit" /></div>
			</td>
		</tr>
	<!-- 기간 설정 -->
		<tr>
		<th>전시 종류</th>
			<td  style="margin-left: -78%; margin-top:10px;" >
				<form:select path="tag_exhibit" class ="tag_exhibit" id="tag_exhibit">
					<option value = ""  selected>선택하세요</option>
					<form:option value = "전시"/>
					<form:option value = "교육"/>
					<form:option value = "행사"/>
					<form:option value = "체험"/>
				</form:select>
			<div><form:errors path="tag_exhibit"/></div>
			</td>
		</tr>
		</tbody>
	</table>
		
	            <div class="file_list" style = "text-align:left; margin-top:10px">
	                <div>
	                    <div class="file_input">
	                        <input type="text" readonly  style = "width:15%;"/>
	                        <label for = "files" > 첨부파일</label>
	                            <input type="file" id = "files" name="files" onchange="selectFile(this);" />
	                    <button type="button" onclick="removeFirstFile(this);" class="del-btn-m btns del_btn"><span>삭제</span></button>
	                    <button type="button" onclick="addFile();" class="add-btn-m btns fn_add_btn"><span>파일 추가</span></button>
	                    </div>
	                </div>
	            </div>
	</form:form>

	<!-- 등록 버튼 -->
	<section style = "margin-top:10px; margin-left:80%; position: relative; ">
		<button type="button" class="add-btn writeBtn" name="writeBtn" onclick="javascript:fnInsertContent(); return false;">등록</button>
	</section>
	
</c:if>





<!-- 대현 테스트 240318 -->
<hr>

<!-- 본관 - 프로그램 안내 글쓰기 JS -->
<script src="/js/exhibition.js"></script>

<c:if test="${!empty principal }">
	<div>
		<!-- 위치 -->
		<div class="row m-0 p-0">
			<div class="col-12 m-0 p-0" style="height: 5vw; background-color: #269630; color: #ffffff; font-size: 3vw; border-radius: 10px;">
				&lt;&lt; 본관 - 프로그램 안내 &gt;&gt; 글쓰기
			</div>
		</div>
		
		<form>
			
			<!-- 태그 -->
			<div class="row ms-0 me-0 mt-3 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>태그</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<select class="form-select" style="font-size: 1vw;">
							<option value="전시" selected>전시</option>
							<option value="교육">교육</option>
							<option value="행사">행사</option>
							<option value="체험">체험</option>
						</select>
						<div id="exhibitionTagInvalid" class="invalid-feedback"></div>
					</div>
				</div>
			</div>
			
			<!-- 제목 -->
			<div class="row ms-0 me-0 mt-0 mb-3 p-0">
				<div class="col-2 m-0 p-0"><b>제목</b></div>
				<div class="col-8 m-0 p-0">
					<div class="input-group">
						<input type="text" class="form-control" id="exhibitionTitle" placeholder="제목" style="font-size: 1vw;"></input>
						<div id="exhibitionTitleInvalid" class="invalid-feedback"></div>
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
				<button id="btn-insertExhibition" class="btn btn-large w-100" style="background-color: #269630; color: #ffffff; font-size: 1vw; font-weight: bold;">글쓰기</button>
			</div>
		</div>
		
	</div>
	
</c:if>
</article>

</body>

<%@ include file="../layout/footer.jsp" %>