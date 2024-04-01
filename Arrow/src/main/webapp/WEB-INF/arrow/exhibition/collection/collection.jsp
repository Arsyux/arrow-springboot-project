<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css" href="/css/contents.css">
<%@ page import="java.lang.Math" %>
<%@ include file="../../layout/header.jsp"%>
		
	<!-- 제목 -->
	<title>상세 안내</title>
	
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
						style="background-color: #269630; color: #ffffff; height: 5vw; font-size: 3vw; width: 30%; display: inline;">
							상세 정보
						</div>
						<!-- 버튼 -->
						<c:if test="${!empty principal }">
							<div class="m-0 p-0" align="right" style="width: 70%; display: inline;">
								<button class="btn" style="background-color: #269630; color: #ffffff;" onclick="location.href='/contents/function/exhibitionWrite'">+ 글쓰기</button>
							</div>
						</c:if>
					</div>
					
					<!-- 메인 화면 -->
					<div class="border border-dark border-1">
						<div class="row m-0 p-0">
						
							<!-- 반복문 시작 -->
							<div class="border border-dark border-1">
								<div class="row m-0 p-0">
							    <c:forEach items="${collectList}" var="collect"  varStatus="i">
							        <div class="col-6 m-0 p-4">
							            <div class="border border-dark border-1">
							                <img class="w-100" alt="exhibition" src="/image/exhibition/details/백각궁.jpg">
							            </div>
							            <div class="border border-dark border-top-0 border-1" style="background-color: #005666; color: #ffffff;" 
							            onclick="javascript:fnCollectDetail('<c:out value="${collect.codename_collect}"/>');">
							            	<span>${i.count }.명칭: ${collect.name_collect}</span><br>
							                <span>고유번호: ${collect.codename_collect}</span><br>
							                <span>재질: ${collect.metarial_collect}</span><br>
							                <span>연도: ${collect.period_collect}</span>
							            </div>
							        </div>
							    </c:forEach>
					                                                                                               			    
								</div>
							</div>
							<!-- 페이지네이션 -->
						</div>
					</div>
					<!-- Pagination S-->
					<div id="paginationBox">
						<div class="pagination">
							<c:if test="${pagination.prev}">
								<div class="page-item"><a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a></div>
							</c:if>
							<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx" step="1"> 
								<div class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
								<a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a></div>
							</c:forEach>
							<c:if test="${pagination.next}">
								<div class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', '${pagination.range}', '${pagination.rangeSize}')" >Next</a></div>
							</c:if>
						</div>
					</div>			
				    <!-- Pagination E-->
				</div>
			</div>
			
		</div>
		
	</article>
	<script>
	//이전 버튼 이벤트
	function fn_prev(page, range, rangeSize) {

			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;
			var url = "/exhibition/view";
			url = url + "/collection?page=" + page;
			url = url + "&range=" + range;

			location.href = url;

		}

	//페이지 번호 클릭
	function fn_pagination(page, range, rangeSize) {
		var url = "/exhibition/view";
		url = url + "/collection?page=" + page;
		url = url + "&range=" + range;

			location.href = url;	
		}


	//다음 버튼 이벤트
	function fn_next(page, range, rangeSize) {
			var page = parseInt((range * rangeSize)) + 1;
			var range = parseInt(range) + 1;
			
			var url = "/exhibition/view";
			url = url + "/collection?pageNumber=" + page;
			url = url + "&range=" + range;

			location.href = url;
		}

	
	
	</script>
	</body>	
<%@ include file="../../layout/footer.jsp" %>