/**
 * 기초조사 ~ 외관점검 등 조사 관련 공통 함수
 * 
 * 
 */
$(document).ready(function(){
	// 조사결과 조회 페이지 초기화
	fnResetSvyPagination();
});

/**
 * @author ipodo
 * @param {String} name - target name
 * @param {Integer} page - page number 
 * @param {Boolean} yn - 초기 검색 여부
 * @returns
 * @description 조사결과 조회 
 */
function fnSearchSvyComptLst(name,page, yn){
	var url = "/sys/gis/selectSvyComptLst.do";

	var param = fnSerializeObject(name.concat('SvyComptSchForm'));
	
	param = $.extend(param, {type:name, page:page});
	
	if(yn) fnSvyComptFeatureClear();
	fnSvyComptDataList(url, param);
}


/**
 * @author ipodo
 * @param {String} url - request url 
 * @param {JSON} param - request parameter 
 * @returns
 * @description 조사결과 조회 페이징 처리 
 */
function fnSvyComptDataList(url, param){
	
	$.ajax({
		url: url,
	    data: param,
	    type: "get",
	    dataType: "json",
	    success: fnCallbackSvyComptLst,
		error: function (error) {
	        console.log(error);
	        alert("검색 중 오류가 발생하였습니다.\n관리자에게 문의하세요.");
	    }
	});
}

/**
 * @author ipodo
 * @param {Integer} totalCnt - 전체레코드수
 * @param {Integer} dataSize - 페이지당 보여줄 데이타수
 * @param {Integer} pageSize - 페이지 그룹 범위 1 2 3 5 6 7 8 9 10
 * @param {Integer} pageNo - 현재페이지
 * @param {String} token - 타겟 엘리먼트 
 * @returns
 * @description 조사결과 조회 페이징 처리 
 */
function fnSvyComptPagination(totalCnt, dataSize, pageSize, pageNo, token){
	totalCnt = parseInt(totalCnt);// 전체레코드수
	dataSize = parseInt(dataSize); // 페이지당 보여줄 데이타수
	pageSize = parseInt(pageSize); // 페이지 그룹 범위 1 2 3 5 6 7 8 9 10
	pageNo = parseInt(pageNo); // 현재페이지

	var html = new Array();
  	if(!totalCnt){
  		return resetPagination();
  	}

  	// 페이지 카운트 총블럭수
  	var pageCnt = totalCnt % dataSize;
  	if(pageCnt == 0){
  		pageCnt = parseInt(totalCnt / dataSize);
  	}else{
  		pageCnt = parseInt(totalCnt / dataSize) + 1;
  	}

  	//페이지 그룹 번호
  	var pRCnt = parseInt(pageNo / pageSize);
  	if(pageNo % pageSize == 0){
  		pRCnt = parseInt(pageNo / pageSize) - 1;
  	}

  	//첫번째 번호
  	if(pRCnt != 0){
  		html.push("<button id=\"StartPagi\" type=\"button\" onclick=\"javascript:fnSearchSvyComptLst('".concat(token,"',1,false)\"><img src=\"/images/sub/first_arrow.png\" alt=\"맨처음\"></button>"));
  	}

  	//이전 번호
  	if(pageNo > pageSize){
  		var s2;
  		if(pageNo % pageSize == 0){
  			s2 = pageNo - pageSize;
  		}else{
  			s2 = pageNo - pageNo % pageSize;
  		}

  		html.push("<button id=\"prevPagi\" type=\"button\" onclick=\"javascript:fnSearchSvyComptLst('".concat(token,"',").concat(s2,",false)\"><img src=\"/images/sub/before.png\" alt=\"이전\"></button>"));
  	}else{
  		//html.push('<li><a href="javascript:void(0)" class="disabled">&lt;</a></li>');
  	}

  	//페이지 리스트
  	for(var index=pRCnt * pageSize + 1;index<(pRCnt + 1)*pageSize + 1;index++){
  		if(index == pageNo){
  			html.push("<a id=\"nowpage\" href=\"#none\" class=\"on\">".concat(index,"</a>"));
  		}else{
  			html.push("<a class=\"page\" href=\"javascript:fnSearchSvyComptLst('".concat(token,"',").concat(index,")\">").concat(index,"</a>"));
  		}

  		if(index == pageCnt){
  			break;
  		}
  	}

  	//다음 번호
  	if(pageCnt > (pRCnt + 1) * pageSize){
  		html.push("<button id=\"nextPagi\" type=\"button\" onclick=\"javascript:fnSearchSvyComptLst('".concat(token,"',").concat(((pRCnt + 1)*pageSize+1),",false)\"><img src=\"/images/sub/next.png\" alt=\"다음\"></button>")); 
  	}else{
  		//html.push('<li><a href="javascript:void(0)" class="disabled">&gt;</a></li>');
  	}

  	//마지막 번호
  	if(pageCnt > (pRCnt + 1) * pageSize){
  		html.push("<button id=\"EndPagi\" type=\"button\" onclick=\"javascript:fnSearchSvyComptLst('".concat(token,"',").concat(pageCnt,",false)\"><img src=\"/images/sub/last_arrow.png\" alt=\"맨끝\"></button>"));
  	}else{
  		//html.push('<li><a href="javascript:void(0)" class="disabled">&gt;&gt;</a></li>');
  	}

  	return html.join("");
}

/**
 * @author ipodo
 * @param 
 * @returns
 * @description 조사결과 조회 페이징 초기화 
 */
function fnResetSvyPagination() {
	$("#bscSchRsltLst").next().html(resetPagination());

}


/**
 * @author
 * @param {String} name - form tag name
 * @returns {JSON}
 * @description 폼 데이터 json 객체로 변환
 */
function fnSerializeObject(name) {
	var result = {};
	var items = $('form[name='.concat(name,']')).serializeArray();
	$.each(items, function(i,f){ 
		result[f.name.substr(11,f.name.length).toLowerCase()] = f.value;
	});
	
	return result;
}

/**
 * @author
 * @param {String} name - 조사 종류명
 * @returns {JSON}
 * @description 폼 데이터 json 객체로 변환
 */
function fnSvyComptScheRst(name) {
	// 폼 태그 초기화
	$("form[name="+name+"SvyComptSchForm]")[0].reset();
	// 조사유형 초기화
	$('#'+name+'SvyComptSvyType').trigger('change');
	// 조사지역 초기화 
	$('#'+name+'SvyComptSd').trigger('change');
	// 검색 결과 목록 초기화
	$("#"+name+"SchRsltLst tbody").empty();
	// 페이지네이션 초기화
	$("#"+name+"SchRsltLst").next().html(resetPagination());
	// 검색 결과 건수 초기화 	
	$(".txtsearch.".concat(name," > span.txtblue")).text(0);

	fnResetDatePicker(name);
	fnSvyComptFeatureClear();	
}

/**
 * @author ipodo
 * @param {Event} event - 클릭 이벤트
 * @returns 
 * @description 조사완료 상세화면 버튼 클릭
 */
function fnSvyComptDetail(event) {
	var name = event.target.name.substr(0,3);
	var props = $(this).data('props');
	
	fnMoveDetailPage(name, props);
}

/**
 * @author ipodo
 * @param {Event} event - 클릭 이벤트
 * @returns 
 * @description 조사완료 상세화면(오버레이) 버튼 클릭
 */
function fnSvyComptDetail2(event) {
	//var name = $(this).data('name');
	//var gid = $(this).data('gid');
	var props = $(this).data('props');
	var name = props.type;
	fnMoveDetailPage(name, props);
}

/**
 * @author ipodo
 * @param {String} name - 조사분류
 * @param {String} gid - 고유아이디
 * @returns 
 * @description 조사완료 상세화면 이동
 */
function fnMoveDetailPage(name, props) {
	var gid = props.gid;
	var targetNm = name+'detailinfo'+gid;
	window.open('',targetNm);
	
	var url = "";
	
	if(name == 'bsc') url = "/sys/lss/bsc/sct/selectBscSvyComptDetail.do";
	if(name == 'lcp') url = "/sys/lss/lcp/sct/selectLcpSvyComptDetail.do";
	if(name == 'wka') url = "/sys/lss/wka/sct/selectWkaSvyComptDetail.do";
	if(name == 'cnl') url = "/sys/lss/cnl/sct/selectCnlSvyComptDetail.do";
	if(name == 'apr') url = "/sys/fck/apr/sct/selectFckAprComptDetail.do";
	if(name == 'frd') url = "/sys/vyt/frd/sct/selectFrdSvyComptDetail.do";
	if(name == 'mse') url = "/sys/fck/mse/sct/selectFckMseComptDetail.do";
	if(name == 'pcs') url = "/sys/fck/pcs/sct/selectFckPcsComptDetail.do";
	
	var form = $('<form></form>')
	form.attr('action', url);
	form.attr('method', 'POST');
	form.attr('target', targetNm);
	
	//$('<input type="text" name="type"></input>').val(name).appendTo(form);
//	$('<input type="text" name="gid"></input>').val(gid).appendTo(form);
	form.append($('<input type="text" name="gid"></input>').val(gid));
	if(name == "frd"){
		var mstId = props.mstId;
		var svyLabel = props.svyid;
		form.append($('<input type="text" name="mstId"></input>').val(mstId));
		form.append($('<input type="text" name="svyLabel"></input>').val(svyLabel));
	}
	document.body.appendChild(form[0]);
	
	form[0].submit();	
	
	document.body.removeChild(form[0]);
}

/**
 * @author ipodo
 * @param {JSON} props - properties
 * @returns {String}
 * @description 조사완료 팝업 컨텐츠 설정 
 */
function fnSetPopupContents(props) {
	var table = $('<table><thead><colgroup><col width="25%"/><col width="25%"/><col width="25%"/><col width="25%"/></colgroup></thead></table>');
	var tbody = $('<tbody></tbody>'); 

	
	// 조사 주소
	var addr = props.sd + " " + props.sgg + " " + props.emd + " ";
	(props.ri.length > 0) ? addr += props.ri + " " + props.jibun : props.jibun;

	var tr1 = $('<tr><td class="title">조사지</td><td class="alignL" colspan="3">'+addr+'</td></tr>');
	//법적제한 팝업 호출 이벤트 

	$("<button type='button' class='search-btn-s'>법적제한</button>").data("type","eum").data("props", props).on('click', fncLadUsePlanPopup).appendTo($(tr1).children('td:eq(1)'));
	
	$(tbody).append(tr1);
	
	var type = "";
	var typeValue = "";
	
	if(props.type == 'frd'){
		type = "임도종류";
		typeValue = props.frdtype;
	}
	
	var tr2 = $('<tr><td class="title">'+type+'</td><td></td><td class="title">조사일</td><td></td></tr>');
	$(tr2).children("td:eq(1)").text(typeValue); // 조사 유형
	$(tr2).children("td:eq(3)").text(props.svydt);	// 조사일
	$(tbody).append(tr2);
	
	var tr3 = $('<tr><td class="title">조사ID</td><td></td><td class="title">조사자</td><td></td></tr>');
	$(tr3).children("td:eq(1)").text(props.svyid); 	// 조사아이디
	$(tr3).children("td:eq(3)").text(props.svyuser);	// 조사자 
	$(tbody).append(tr3);	
	
	var tr4 = $('<tr class="info close"><td class="title">시점좌표X</td><td></td><td class="title">시점좌표Y</td><td></td></tr>');
	$(tr4).children("td:eq(1)").text(props.lon);
	$(tr4).children("td:eq(3)").text(props.lat);


	$(table).append(tbody);
	return table;	
}

/**
 * @author ipodo
 * @param {Event} event - click event
 * @returns  
 * @description 조사완료 리스트 오버레이  
 */
function fnSvyComptInfo(event) {
	
	var name = $(this).data('props').type;
	
	var overlayId = "svyComptOverlay";
	
	// 오버레이 닫기
	fnCloseOverlay(overlayId);
	
	var props = $(this).data('props');  
	var content = fnSetPopupContents(props);
	var coordinate = [props.lon, props.lat];
	var options = { type: 'search', text: '상세보기', callback: fnSvyComptDetail2, data: { props: props } };
			
	// 오버레이 열기
	fnOpenOverlay(overlayId, coordinate, content, options);
	
	// 결과 창 닫기
	if($('.navClose').hasClass('on')) $('.navClose').trigger('click')
	
	var lon = (name == 'apr' ? parseFloat(props.lon) + 60 : props.lon);
	var lat = (name == 'apr' ? parseFloat(props.lat) + 80 : props.lat);
	
	//위치 이동
	SM.moveToPoint(lon, lat);
	
	SM.map.getView().setZoom(18);
	
}

/**
 * @author ipodo
 * @param {String} name - target Name
 * @returns  
 * @description 조사완료 리스트 오버레이  
 */
function fnResetDatePicker(name) {
	var dateDefOpts = {
		changeMonth: true, 
        changeYear: true,
        nextText: '다음 달',
        prevText: '이전 달', 
        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		dayNamesShort: ['일','월','화','수','목','금','토'],
		dayNamesMin: ['일','월','화','수','목','금','토'],
		dayNames: ['일','월','화','수','목','금','토'],
        dateFormat: "yy-mm-dd",
        maxDate: 0,
        showAnim:"slideDown",          
		showMonthAfterYear: true,
		minDate: new Date(2021, 1, 1),
		onClose: function (date, inst) {
			var id = inst.id;
		if(date != ''){
			if (id.indexOf('Start') > 0) {
				var name = id.replace('Start', 'End');
				var range = "minDate";
			} else if (id.indexOf('End') > 0) {
				var name = id.replace('End', 'Start');
				var range = "maxDate"; 
			}
	
			$("#" + name).datepicker("option", range, date);
			}
		}
	};
	
	$( "#"+name+"SvyComptStartDt" ).datepicker("destroy").datepicker(dateDefOpts); 	
	$( "#"+name+"SvyComptEndDt" ).datepicker("destroy").datepicker(dateDefOpts); 	
}

