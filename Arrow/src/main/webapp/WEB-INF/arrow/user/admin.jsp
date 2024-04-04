<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	
	<link rel="stylesheet" type="text/css" href="/css/admin.css">

	<!-- 제목 -->
    <title>게시글 작성</title>
    
</head>
<body>


<!-- 관리자 로그인시에만 div 노출 -->

<c:if test="${!empty principal }">

	<!-- admin 페이지 대현 구현 240330 -->
	<nav class="navbar navbar-expand-md p-0 m-0" style="background-color: #ffffff;">
		<div class="container">
			<a class="navbar-brand" href="#">
				<img alt="logo" src="/image/main/logo.svg" style="height: 100px;">
			</a>
			<button class="navbar-toggler border border-2" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
				<img class="navbar-toggler-icon" alt="icon" src="/image/main/icon.svg" style="height: 50px;">
			</button>
			<div class="collapse navbar-collapse justify-content-center" id="mynavbar">
				<ul class="navbar-nav" style="font-size: 20px;">
					<li class="nav-item p-5">
						<a class="nav-link a" href="#" style="color: #444444;">
							<b>게시글 작성</b>
						</a>
					</li>
					<li class="nav-item p-5">
						<a class="nav-link" href="#" style="color: #444444;">
							<b>전시품 등록</b>
						</a>
					</li>
				</ul>
 			</div>
		</div>
	</nav>
	
	<!-- 기본은 게시글 작성 -->
	<%@ include file="../exhibition/exhibitionWrite.jsp"%>
	
</c:if>

<script src="/js/admin.js"></script>

<%@ include file="../layout/footer.jsp" %>