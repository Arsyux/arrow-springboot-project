<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/contents.css">

<%@ include file="../layout/header.jsp"%>
		
	<!-- 제목 -->
	<title>본관 / 전시 2023 지홍전</title>
	
</head>
<body>
	<header><!-- 키오스크이므로 헤더 및 푸터는 구현하지않음 --></header>
	
	<article>
		
		<div class="container-fluid background-gradient p-0">
			
			<div class="mainBox p-0" align="left">
				<!-- 헤더 영역 -->
				<div class="row m-0 p-0">
					<!-- 로고 -->
					<div class="boxHeaderLeft background-white border border-dark border-2">
						<img class="infoImage" alt="icon" src="/image/main/icon.svg" width="500" height="500">
					</div>
					<!-- 안내 제목 -->
					<div class="boxHeaderRight background-white border border-start-0 border-dark border-2">
						<div align="center" style="margin-top: 2vw;">
							<span style="font-size: 2vw;">영집 궁시박물관안내<br></span>
							<span style="font-size: 5vw; font-weight: bold;">본관 - 프로그램 안내</span>
						</div>
					</div>
				</div>
				
				<div class="row m-0 p-0">
					<!-- QR 코드 -->
					<div class="boxHeaderLeft background-white border-start border-end border-bottom border-dark border-2">
						<img class="infoImage" alt="QRCode" src="/image/main/QRCode.svg" width="500" height="500">
					</div>
					<!-- 안내사항 -->
					<div class="boxHeaderRight background-white border-bottom border-end border-dark border-2">
						<div align="center" style="margin-top: 2.5vw;">
							<span style="font-size: 2vw;">공지 및 문의는 홈페이지나<br>전화로 연락 바랍니다.<br></span>
							<span style="color: #00b7c3; font-size: 1.5vw; font-weight: bold;">QR 코드 스캔시 이동</span>
						</div>
					</div>
				</div>
				
				<!-- 여백 -->
				<div class="row m-0 p-0">
					<div class="boxWhiteSpace background-clear m-0 p-0"></div>
				</div>
				
				<!-- 메인 컨텐츠 -->
				<div class="background-white border border-dark border-2 p-4" align="center">
					
					<div class="row m-0 p-0">
						<!-- 안내 -->
						<div align="center" class="border border-dark border-1" 
						style="background-color: #269630; color: #ffffff; height: 5vw; font-size: 2vw; width: 70%; display: inline;">
							본관 / 전시 - 2023 지홍전
						</div>
						<!-- 버튼 -->
						<c:if test="${!empty principal }">
							<div class="m-0 p-0" align="right" style="width: 30%; display: inline;">
								<button class="btn" style="background-color: #00498c; color: #ffffff;" onclick="#">글수정</button>							
								<button class="btn" style="background-color: crimson; color: #ffffff;" onclick="#">글삭제</button>
							</div>
						</c:if>
					</div>
					
					<!-- 메인 화면 -->
					<div class="row m-0 p-0">
						<!-- 포스터나 홍보 사진 -->
						<div class="background-white border border-dark border-1 p-0" style="display: inline; width: 60%;">
							<img class="w-100" alt="exhibition" src="/image/exhibition/2023지홍전.jpg">
						</div>
						<!-- 간단한 설명 -->
						<div class="background-white border boder-start-0 border-dark border-1 p-0" style="display: inline; width: 40%;">
							<div align="center" style="margin-top: 2.5vw;">
								<span style="font-size: 2vw;">간단한 설명</span>
							</div>
						</div>
						<!-- 상세 설명 -->
						<div class="background-white border border-top-0 border-dark border-1 p-0" style="min-height: 20vw;">
							<div align="center" style="margin-top: 2.5vw;">
								<span style="font-size: 2vw;">상세 설명</span>
							</div>
						</div>
						
						<!-- 여백 -->
						<div class="boxWhiteSpace background-clear m-0 p-0" style="height: 3vw;"></div>
						
						<!-- 버튼 -->
						<div class="row p-0 m-0" style="height: 10vw;">
							<a class="btn w-100 h-100" href="/contents/view/exhibitionDetails" style="background-color: #005666; color: #ffffff;">전시 상세 소개</a>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
	</article>
		
<%@ include file="../layout/footer.jsp" %>