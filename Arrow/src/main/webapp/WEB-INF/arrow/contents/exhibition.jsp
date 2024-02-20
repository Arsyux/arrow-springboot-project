<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<link rel="stylesheet" type="text/css" href="/css/contents.css">
<head>
    <title>이미지 2x2 구조</title>
</head>
<body>
<article>
<!-- 관리자 로그인시 div 노출 -->
<c:if test="${!empty principal }">
	<section style = "margin-top:100px; margin-left:65%; position: relative; ">
	<button type="button" class="write-btn" onclick="location.href='exhibitionWrite'">등록</button>
	<button type="button" class="modify-btn" onclick="javascript:fnUpdateCnrsSpceView('<c:out value=""/>');">수정</button>
	</section>
</c:if>
    <section class="image-container">
        <div >
            <img src="/image/image.png" class="image-item" alt="이미지 1">
        </div>
        <div>
            <img src="/image/image.png"  class="image-item"alt="이미지 2">
        </div>
        <div  >
            <img src="/image/image.png"  class="image-item"alt="이미지 3">
        </div>
        <div  >
            <img src="/image/image.png"  class="image-item"alt="이미지 4">
        </div>
    </section>

</article>
		
<%@ include file="../layout/footer.jsp" %>