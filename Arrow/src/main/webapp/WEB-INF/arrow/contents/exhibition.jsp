<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<head>
    <title>이미지 2x2 구조</title>
    <style>
        .image-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-gap: 10px;
        }
        .image-item {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }
    </style>
</head>
<body>
    <div class="image-container">
        <div class="image-item">
            <img src="" class="image-item" alt="이미지 1">
        </div>
        <div class="image-item">
            <img src="" class="image-item" alt="이미지 2">
        </div>
        <div class="image-item">
            <img src="" class="image-item" alt="이미지 3">
        </div>
        <div class="image-item">
            <img src="" class="image-item" alt="이미지 4">
        </div>
    </div>
		
<%@ include file="../layout/footer.jsp" %>