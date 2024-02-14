<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 정수의 몫을 구하기 위해 라이브러리 추가 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- JSP에서 다국어 메시지를 출력하기 위해 스프링에서 제공하는 태그 라이브러리를 선언한다. -->
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- substring 사용을 위한 태그 라이브러리 설정 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- 시큐리티가 제공하는 커스텀 라이브러리에 대한 태그 라이브러리 설정 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!-- 로그인 인증에 성공한 브라우저만 접근할 수 있는 영역 -->
<sec:authorize access="isAuthenticated()">
	<sec:authentication var="principal" property="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- 부트스트랩 -->
	<link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
	
	<!-- 제이쿼리 -->
	<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
	
	<!-- 서머노트 -->
	<link href="/webjars/summernote/0.8.10/summernote-bs4.css" rel="stylesheet">
	<script src="/webjars/summernote/0.8.10/summernote-bs4.min.js"></script>
	
	<!-- 아이콘 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
	
	<!-- 메인 css -->
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	
	<!-- 제목 -->
	<title>영집궁시 박물관</title>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
			
			<div class="container">
				
				<!-- 메인 로고 높이 40px -->
				<a class="navbar-brand" href="/">
					<img id="mainLogo" alt="logo" src="/image/main/logo-white.svg" height="40px">
				</a>
				
				<!-- 검색바 -->
				<form>
					<input type="search" placeholder="Search...">
  					<button type="submit">Search</button>
				</form>
				
				<!-- 접히는 영역 -->
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
					<img alt="toggler" src="/image/main/navbar-toggler-icon-white.svg" height="30px" width="30px">
				</button>
				
				<div class="collapse navbar-collapse" id="mynavbar">
					<ul class="navbar-nav me-auto">
						<li class="nav-item">
							<c:if test="${empty principal }">
								<a class="nav-link" href="/auth/loginUser">로그인</a>
							</c:if>
							<c:if test="${ principal }">
								<a class="nav-link" href="/auth/loginUser">로그아웃</a>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		
	</header>