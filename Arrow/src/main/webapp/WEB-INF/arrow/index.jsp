<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="./layout/header.jsp"%>

	<article>
	
		<div class="container-fluid p-0" style="position: relative;">
			<c:if test="${!empty principal }">
				<!-- 로그인 되어있을 경우 -->
				<div align="center" class="p-3" 
					 style="position: absolute; 
					 color: #000000; background-color: rgba(0, 0, 0, 0.5); 
					 top: 5%; left: 80%;
					 font-weight: bold; 
					 border-color: black;
					 border-width: 1px;
					 border-radius: 25%">
					<a href="/logout">
						<i class="bi bi-box-arrow-right" style="color: #FFFFFF;"></i>
					</a>
				</div>
			</c:if>
			
			<div align="center" class="p-2" 
				 style="position: absolute; 
				 color: #000000; background-color: rgba(255, 255, 255, 0.8); 
				 top: 20%; left: 12.5%;
				 width: 75%; 
				 font-weight: bold; 
				 border-color: black; 
				 border-style: solid; 
				 border-width: 1px;">
				<h1>영집 궁시박물관</h1>
				<h1>안내</h1>
			</div>
			
			<div align="center" class="p-2" 
				 style="position: absolute; 
				 color: #000000; background-color: rgba(255, 255, 255, 0.8); 
				 top: 55%; left: 25%;
				 width: 50%; 
				 font-weight: bold; 
				 border-color: black; 
				 border-style: solid; 
				 border-width: 1px;"
				 onclick="location.href='/contents/view/info'">
				<h1>입장하기</h1>
			</div>
			
			<div align="center" 
				 style="position: absolute; 
				 color: #000000; background-color: rgba(255, 255, 255, 1); 
				 top: 85%; left: 37.5%;
				 width: 25%; 
				 font-weight: bold; 
				 border-color: black; 
				 border-style: solid;
				 border-width: 1px;">
				 <img alt="logo" src="/image/main/logo.svg" width="100%">
			</div>
			<img alt="background" src="/image/main/background.png" width="100%">
		</div>
		
		
	</article>
		
<%@ include file="./layout/footer.jsp" %>