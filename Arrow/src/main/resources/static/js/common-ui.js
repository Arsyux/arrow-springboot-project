

$(document).ready(function(){
	window.$app = {};


	/*날짜 조회 조건처리 S*/
	$('.startDateExhibit').on('change', function (event) {
		var startDateInput = document.getElementById("startDateExhibit");
		var endDateInput = document.getElementById("endDateExhibit");
		
		var startDate = new Date(startDateInput.value);
	    var endDate = new Date(endDateInput.value);
		
		if (startDate > endDate) {
		    
	        alert("시작일은 종료일보다 이전 날짜여야 합니다.");
	 		$("#startDateExhibit").val('시작일자');
	 		$("#endDateExhibit").val('마감일자');
	    }
	    
		});
		
	$('.endDateExhibit').on('change', function (event) {
		var startDateInput = document.getElementById("startDateExhibit");
		var endDateInput = document.getElementById("endDateExhibit");
		
		var startDate = new Date(startDateInput.value);
	    var endDate = new Date(endDateInput.value);
		
 		if (startDate > endDate) {
	   
	        alert("종료일은 시작일보다 이후 날짜여야 합니다.");
	 		$("#startDateExhibit").val('시작일자');
	 		$("#endDateExhibit").val('마감일자');
	    } 
		
	});	
	/*날짜 조회 조건처리 E*/
	

	
	/*조사 상세검색  조회 처리  S*/
	$(".writeBtn").on("click", function (event) {
		
		//var name = event.target.name.substr(0,3);
		
		/* 설정 날짜 값 */
		var startDateInput = document.getElementById("startDateExhibit").value;
		var endDateInput = document.getElementById("endDateExhibit").value;		
	
		if(endDateInput.length > 0  && startDateInput.length > 0){
			
			
			alert("설정 한 값 정상");
			
			var url = "/";
			//var param = fnSerializeObject(name.concat('insertForm'));
			
			//param = $.extend(param, {type:name});
			
			// 벡터 조회
			//fncCmnSearch(url, param, fnCallbackSvyComptFeature);
		
		} else {
		
		 	alert("날짜 기간을 설정해주세요");
		
		}
	});
	/*조사 상세검색  조회 처리  E*/

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
        maxDate: '+2y',
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
	
	/*전시 기간*/
	$( "#startDateExhibit" ).datepicker(dateDefOpts); 	
	$( "#endDateExhibit" ).datepicker(dateDefOpts); 	
});


/**
 * @author shlee
 * @name fncSearch
 * @param {String}
 * url - request url
 * @param {JSON}
 * param - request param
 * @param {Function}
 * callback - callback function
 * @returns
 * @description 공통조회
 */
function fncSearch(url, param, callback) {
	$.ajax({
		url : url,
		data : param,
		dataType : 'json',
		success : callback
	})
}
function fnInsertContent() {
    // 필요한 데이터 수집
    var name_exhibit = document.getElementById('name_exhibit').value;
    var subname_exhibit = document.getElementById('subname_exhibit').value;
    var space_exhibit = document.getElementById('space_exhibit').value;
    
    
    var startDate_exhibit = document.getElementById('startDateExhibit').value;
    startDate_exhibit = startDate_exhibit.replace(/-/g, '')
    var endDate_exhibit = document.getElementById('endDateExhibit').value;
	endDate_exhibit = endDate_exhibit.replace(/-/g, '')
    // Ajax 요청을 위한 데이터 객체 생성
    var data = {
        name_exhibit: name_exhibit,
        subname_exhibit: subname_exhibit,
        space_exhibit: space_exhibit,
        startDate_exhibit: startDate_exhibit,
        endDate_exhibit: endDate_exhibit
    };

    // Ajax 요청
    $.ajax({
        type: "POST",
        url: "/post/insertContent",
        data: data,
        dataType:'json',
        success: function(response) {
            // 성공적으로 서버로부터 응답을 받았을 때 수행할 작업
            console.log("Success:", response);
            // 여기에 성공적으로 처리된 후에 할 작업을 추가하세요.
        },
        error: function(xhr, status) {
            // 요청이 실패했을 때 수행할 작업
            console.error("Error:", "error");
            // 여기에 오류 발생 시 처리할 작업을 추가하세요.
        }
    });
}

/*function exhibitionWrite(){
	
	var aja=$.ajax({
			url: $("#insertForm")[0].action,
			type:"POST",
			data: {"mst_id": mst_id},
			contentType:false,
	        processData: false,
	        cache: false,
	        data: $app.upload.form,
	        dataType:"json",
	        beforeSend: function(){
	        },
	        success: function(data){
	        	if (data.status == "success") {
	        		alert(data.message);
	        	}
	        },
	        error: function(request, status, error){

	        	alert("code : " + request.status + "\n" + "error : " + error);
	        }
		});
		
}*/


