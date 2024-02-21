<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

	<article>
		
		<!-- 이미지 정사각형으로 잘라야함 -->
		<div class="container-fluid p-0 bg_gradient" style="height: 600px;">

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
			
			<div align="center" 
				 style="position: absolute; 
				 color: #000000; background-color: rgba(255, 255, 255, 1); 
				 top: 40%; left: 12.5%;
				 width: 75%;">
			
				<div class="row" style="position: relative;">
					<div style="position: absolute;">
						<img alt="infoImage" src="/image/infoImage.png">
					</div>
				</div>
			</div>
		</div>
		
		
	</article>
		
<%@ include file="../layout/footer.jsp" %>