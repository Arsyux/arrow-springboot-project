<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link rel="stylesheet" type="text/css" href="/css/contents.css">
<head>
    <title>이미지 2x2 구조</title>
</head>
<body>
<article>
<!-- 관리자 로그인시 div 노출 -->
<c:if test="${!empty principal }">
<form:form id="insertForm" modelAttribute="ContentsVO" commandName="ContentsVO" method="post" action="/post/insertSpce">
       <table class="fieldBookTable ov">
          	  <!-- 제목 -->
          	  <tr>
			      <c:set var="title"></c:set>
				  <th>제목</th>
				  <td>
					 <form:input path="name_exhibit" />
					  <div><form:errors path="name_exhibit" /></div>
			      </td>
		      </tr>
		      <!-- 간략 설명 -->
          	  <tr>
			      <c:set var="title"></c:set>
				  <th></th>
				  <td>
				      <form:input path="subname_exhibit" />
			   		  <div><form:errors path="subname_exhibit" /></div>
			      </td>
		      </tr>
		      <!-- 기간 설정 -->
          	  <tr>
			      <c:set var="title"></c:set>
				  <th></th>
				  <td>
				      <form:input path="space_exhibit" />
			   		  <div><form:errors path="space_exhibit" /></div>
			      </td>
		      </tr>



       </table>
       <div class="drag-div drag-active">
			<p class="drag-msg noselect">파일을 드래그하세요.</p>
		</div>
       <div class="BtnGroup">
			<div class="BtnRightArea">
				<button type="button" class="add-btn" onclick="javascript:fnInsertCnrsSpce(); return false;">생성</button>
			</div>
		</div>
   </form:form>
	

	<section style = "margin-top:100px; margin-left:65%; position: relative; ">
		<button type="button" class="write-btn" onclick="location.href='exhibitionWrite'">등록</button>
	</section>


</c:if>
</article>




<%@ include file="../layout/footer.jsp" %>