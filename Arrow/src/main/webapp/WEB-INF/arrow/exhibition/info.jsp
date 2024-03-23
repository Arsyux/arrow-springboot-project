<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
		
	<!-- 제목 -->
	<title>영집 궁시박물관</title>
	
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
						<div align="center" style="font-size: 5vw; font-weight: bold; margin-top: 3vw;">영집 궁시박물관안내</div>
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
				<div class="background-white border border-dark border-2" align="center">
					<img class="infoImage" alt="infoImage" id="infoImage" src="/image/main/infoImage.png" usemap="#infoImageMap" width="701" height="701">
					<map id="infoImageMap" name="infoImageMap">
						<area shape="rect" alt="본관" title="본관" coords="49,88,336,282" href="/contents/view/exhibition" />
						<area shape="rect" alt="야외 마당" title="야외 마당" coords="49,301,455,578" href="#" />
						<area shape="rect" alt="공방" title="공방" coords="21,596,153,681" href="#" />
						<area shape="rect" alt="활터" title="활터" coords="560,89,671,281" href="#" />
					</map>
					<script type="text/javascript">
						$(document).ready(function(e) {
							$('img[usemap]').rwdImageMaps();
						});
					</script>
				</div>
			</div>
			
			
			
		
		</div>
		
		
	</article>
		
<%@ include file="../layout/footer.jsp" %>