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
<form:form id="insertForm" modelAttribute="ContentsVO" commandName="ContentsVO" method="post" action="/post/insertSpce">
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
			<td id="space" style="width: 50%;">
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
			<th>전시 기간</th>
			<td id = "dateExhibit">
				
			<div><form:errors path="startDate_exhibit" /></div>
			</td>
		</tr>
		</tbody>
	
	</table>
	<div>
		<div class="drag-div drag-active">
			<p class="drag-msg noselect">파일을 드래그하세요.</p>
		</div>
		<div class="BtnGroup">
			<div class="BtnRightArea">
				<button type="button" class="add-btn" onclick="javascript:fnInsertCnrsSpce(); return false;">생성</button>
			</div>
		</div>
	</div>	
</form:form>
	<!-- 등록 버튼 onclick="location.href='exhibitionWrite'"-->
	<section style = "margin-top:10px; margin-left:80%; position: relative; ">
		<button type="button" class="writeBtn" class="writeBtn" name="writeBtn" onclick="location.href='exhibitionWrite'">등록</button>
	</section>


</c:if>
</article>

</body>
<!-- <script>$(function(){
    $('.datepicker').datepicker();
})</script> -->

<%@ include file="../layout/footer.jsp" %>