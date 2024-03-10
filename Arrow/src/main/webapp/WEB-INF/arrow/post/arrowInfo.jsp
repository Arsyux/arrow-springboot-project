<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	<article>
		
		<div class="container-fluid p-0 mainBgGradient">
			
			<div class="mainBox" align="left">
				<!-- 헤드 -->
				<div class="row m-0 p-0">
					<!-- 로고 -->
					<div class="mainBoxTopLeft">
						<img alt="icon" src="/image/main/icon.svg" width="500" height="500">
					</div>
					<!-- 안내 제목 -->
					<div class="mainBoxTopRight">
						<div align="center" style="font-size: 5vw; font-weight: bold;">영집 궁시박물관안내</div>
					</div>
				</div>
				
				<div class="row m-0 p-0">
					<div class="mainBoxBottomLeft">
						<img alt="icon" src="/image/image.png" width="500" height="500">
					</div>
					<div class="mainBoxBottomRight">
						<div align="center" style="font-size: 2vw;">
							<span>공지 및 문의는 홈페이지나<br>전화로 연락 바랍니다.<br></span>
							<span style="color: #00b7c3; font-weight: bold;">QR 코드 스캔시 이동</span>
						</div>
					</div>
				</div>
				
				<div class="row m-0 p-0">
					<div style="width: 100%; background-color: rgba(255, 255, 255, 0); margin: 0px; display: inline; min-height: 10vw;">
					</div>
				</div>
				
				<!-- 메인 컨텐츠 -->
				<div class="mainBoxContent" align="center">
					<img alt="infoImage" id="infoImage" src="/image/main/infoImage.png" usemap="#infoImageMap" width="701" height="701">
					<map id="infoImageMap" name="infoImageMap">
						<area shape="rect" alt="본관" title="본관" coords="49,88,336,282" href="#" />
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