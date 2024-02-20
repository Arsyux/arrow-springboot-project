$(document).ready(function(){

/*	날짜 조회 조건처리 S
	$('.startDateExhibit').on('change', function (event) {
	//	var name = event.target.id.substr(0,3);
		var name = $(this).attr('name').substr(0,3);
		var startDateInput = document.getElementById(name+"SvyComptStartDt");
		var endDateInput = document.getElementById(name+"SvyComptEndDt");
		
		var startDate = new Date(startDateInput.value);
	    var endDate = new Date(endDateInput.value);
		
		if (startDate > endDate) {
		    var reDate = new Date();
	        alert("시작일은 종료일보다 이전 날짜여야 합니다.");
	 		$("#"+name+"SvyComptStartDt").val('reDate');
	 		$("#"+name+"SvyComptEndDt").val('reDate');
	    }
	    
		});
		
	$('.endDateExhibit').on('change', function (event) {
	//	var name = event.target.id.substr(0,3);
		var name = $(this).attr('name').substr(0,3);
		var startDateInput = document.getElementById(name+"SvyComptStartDt");
		var endDateInput = document.getElementById(name+"SvyComptEndDt");
		
		var startDate = new Date(startDateInput.value);
	    var endDate = new Date(endDateInput.value);
		
 		if (startDate > endDate) {
	    var reDate = new Date();
	        alert("종료일은 시작일보다 이후 날짜여야 합니다.");
	 		$("#"+name+"SvyComptStartDt").val('reDate');
	 		$("#"+name+"SvyComptEndDt").val('reDate');
	    } 
		
	});	
	날짜 조회 조건처리 E
	
*/
	
	/*조사 상세검색  조회 처리  S*/
	$(".SvyComptBtn").on("click", function (event) {
		
		var name = event.target.name.substr(0,3);
		
		/* 2023.08.31 추가 -승현 */
		var startDateInput = document.getElementById(name+"SvyComptStartDt").value;
		var endDateInput = document.getElementById(name+"SvyComptEndDt").value;		
	
		if(endDateInput.length > 0  && startDateInput.length > 0){
			//행정구역 검색항목이 있을 경우 행정구역코드가 아닌 행정구역명칭을 파라메터로 넘겨주기 위한 처리
			
			// 페이징 조회		
			fnSearchSvyComptLst(name, 1, true);
			
			var url = "/sys/gis/selectSvyComptWKT.do";
			var param = fnSerializeObject(name.concat('SvyComptSchForm'));
			
			param = $.extend(param, {type:name});
			
			// 벡터 조회
			fncCmnSearch(url, param, fnCallbackSvyComptFeature);
		
		} else {
		
		 	alert("날짜 기간을 설정해주세요");
		
		}
	});
	/*조사 상세검색  조회 처리  E*/
	


	
	/* 정밀점검 조사유형 별 검색조건 변경 */
	$("#pcsSvyComptSvyType").on('change', function(event){
		var idx = this.selectedIndex;
		$("div[class*='pcs']").addClass("Hidden");
		$(".pcs".concat(idx)).removeClass("Hidden");
	});
	
	/*조사완료 초기화 버튼 클릭 이벤트 */
	$('.SvyComptBtnReset').on('click', function (event) {
		var name = event.target.name.substr(0,3);
		
		fnSvyComptScheRst(name);
	});

/**
 * @author
 * @param {String} name - 조사 종류명
 * @returns {JSON}
 * @description 폼 데이터 json 객체로 변환
 */
/*function fnSvyComptScheRst(name) {
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
}*/

	//데이트피커 기본 옵션 
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	
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
	
	/*기초조사*/
	$( "#startDateExhibit" ).datepicker(dateDefOpts); 	
	$( "#endDateExhibit" ).datepicker(dateDefOpts); 	
});