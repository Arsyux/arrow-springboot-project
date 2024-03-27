<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/contents.css">

<%@ include file="../layout/header.jsp"%>

<head>		
	<!-- 제목 -->
	<title>본관 - 프로그램 안내</title>
	
</head>
<body>
	
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
						style="background-color: #269630; color: #ffffff; height: 5vw; font-size: 3vw; width: 30%; display: inline;">
							본관/프로그램
						</div>
						<!-- 버튼 -->
						<c:if test="${!empty principal }">
							<div class="m-0 p-0" align="right" style="width: 70%; display: inline;">
								<button class="btn" style="background-color: #269630; color: #ffffff;" onclick="location.href='/exhibition/function/exhibitionWrite'">+ 글쓰기</button>
							</div>
						</c:if>
					</div>
					
					<!-- 메인 화면 -->
					<div class="border border-dark border-1">
						<div class="row m-0 p-0">
					    <c:forEach items="${contentsList}" var="contents">
					        <div class="col-6 m-0 p-4">
					            <div class="border border-dark border-1">
					                <img class="w-100" alt="exhibition" src="/image/exhibition/2023지홍전.jpg">
					            </div>
					            <div class="border border-dark border-top-0 border-1" style="background-color: #005666; color: #ffffff;" 
					            onclick="javascript:fnFieldBookDetail('<c:out value="${contents.exh_seq}"/>');">
					            	<span>번호: ${contents.exh_seq}</span><br>
					                <span>전시: ${contents.name_exhibit}</span><br>
					                <span>부제목: ${contents.subname_exhibit}</span><br>
					                <span>일자: ${contents.startDate_exhibit} ~ ${contents.endDate_exhibit}</span>
					            </div>
					        </div>
					    </c:forEach>
			                                                                                               			    
						</div>
					</div>
					
					<!-- Pagination S-->
					<c:if test ="#{totalpages > 0}">
					<div class="pagination" id="pagination">
					    <!-- Previous Page Link -->
					    <c:if test="${pageNumber > 0}">
					        <a href="/exhibition/view/exhibition?pageNumber=${pageNumber - 1}&pageSize=${pageSize}" id="prevPage">Previous</a>
					    </c:if>
					    
					    <!-- Page Numbers -->
					    <script>
					        var totalPages = ${totalPages};
					        var pageNumber = ${pageNumber};
					        var pageSize = ${pageSize};
					        
					        var paginationHTML = '';
					        for (var page = pageNumber + 1; page <= Math.min(pageNumber + 5, totalPages); page++) {
					            if (page == pageNumber + 1) {
					                paginationHTML += '<span>' + page + '</span>';
					            } else {
					                paginationHTML += '<a href="/exhibition/view/exhibition?pageNumber=' + (page - 1) + '&pageSize=' + pageSize + '">' + page + '</a>';
					            }
					        }
					        document.getElementById('pagination').innerHTML += paginationHTML;
					    </script>
					    
					    <!-- Next Page Link -->
					    <c:if test="${pageNumber + 5 < totalPages}">
					        <a href="/exhibition/view/exhibition?pageNumber=${pageNumber + 5}&pageSize=${pageSize}" id="nextPage">Next</a>
					    </c:if>
					</div>
					</c:if>
				    <!-- Pagination E-->
				</div>
			</div>
		</div>
	</article>
	</body>
		
<%@ include file="../layout/footer.jsp" %>