<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	<article>
		
		<div class="container-fluid p-0 mainBgGradient" style="">
			
			<!-- 네비 영역 -->
			<div class="mainNavBox" align="left">
				<div class="row" style="margin: 0px; padding: 0px;">
					<div style="width: 20%; border-right: solid 3px #000000; border-bottom: solid 3px #000000; margin: 0px; display: inline;">
						<img alt="icon" src="/image/main/icon.svg" width="500" height="500">
					</div>
					<div style="width: 80%; border-bottom: solid 3px #000000; margin: 0px; display: inline;">
						<div style="margin-top: 10%;">영집 궁시박물관안내</div>
					</div>
				</div>
				
				<div style="background-image: url('/image/image.png'); 
					background-size: cover; width: 10%; height: 20px; padding-bottom: 10%;">
				</div>
			</div>
		<!--
			<div align="center" 
				 style="position: absolute; 
				 color: #000000; background-color: rgba(255, 255, 255, 1); 
				 top: 10%; left: 12.5%;
				 width: 75%;">
				
				<div class="row">
					<div class="col-2" style="background-color: black;">
						<img alt="icon" src="/image/main/icon.svg" width="100%">
					</div>
					
					<div class="col-9" style="background-color: red;">
						<span style="font-weight: bold; font-size: 2vw;">영집 궁시박물관안내</span>
					</div>
						
				</div>
			
		
				<div class="row" style="">
					<div style="background-image: url('/image/image.png'); 
					background-size: cover; width: 10%; height: 20px; padding-bottom: 10%;"></div>			
				
				</div>
		</div>
		-->
			
			
			<!-- 메인 컨텐츠 -->
			<div class="mainContentBox" align="center">
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
		
		
	</article>
		
<%@ include file="../layout/footer.jsp" %>