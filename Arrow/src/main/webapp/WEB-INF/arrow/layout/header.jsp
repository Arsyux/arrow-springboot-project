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
<%@ page import="java.lang.Math" %>
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
	<script src="/summernote/summernote-lite.js"></script>
  	<link rel="stylesheet" href="/summernote/summernote-lite.css">
  	<script src="/summernote/font/summernote-ko-KR.js"></script>
  
	<!-- 아이콘 -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
	
	<!-- 메인 css -->
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	
	<!-- 이미지맵 크기에 따라 변경해주는 JS -->
	<script src="/js/jquery/jquery.rwdImageMaps.js"></script>
	
	<!-- content 관련 JS -->
	<script src="/js/common-ui.js"></script>
 	
 	<!-- 주소줄 없애기(안드로이드) -->
	<meta name="mobile-web-app-capable" content="yes">
	
	<!-- 주소줄 없애기(애플) -->
 	<meta name="apple-mobile-web-app-capable" content="yes">

	<!-- 상태바 모습 바꾸기(애플) -->
 	<meta name="apple-mobile-web-app-status-bar-style" content="blank">
	
	<!-- 즐겨찾기 아이콘 --> 	
 	<link rel="shortcut icon" type="image/x-icon" href="/image/main/icon">

 	<!-- 아이콘 표시(안드로이드) -->
 	<link rel="shortcut icon" href="/image/main/icon.svg">
 	
 	<!-- 아이콘 표시(애플) -->
 	<link rel="apple-touch-icon" href="/image/main/icon.svg">
 	
 	<!-- 확대 금지 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
