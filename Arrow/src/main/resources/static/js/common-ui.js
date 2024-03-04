    window.onload = () => {
        //renderPostInfo();

        //findAllFile();
    }
    // 전체 파일 조회
    function findAllFile() {

        // 1. 신규 등록/수정 체크
        const post = [[ '${post}']];
        if ( !post ) {
            return false;
        }

        // 2. API 호출
        const response = getJson(`/posts/${post.id}/files`);

        // 3. 로직 종료
        if ( !response.length ) {
            return false;
        }

        // 4. 업로드 영역 추가
        for (let i = 0, len = (response.length - 1); i < len; i++) {
            addFile();
        }

        // 5. 파일 선택 & 삭제 이벤트 재선언 & 파일명 세팅
        const filenameInputs = document.querySelectorAll('.file_list input[type="text"]');
        filenameInputs.forEach((input, i) => {
            const fileInput = input.nextElementSibling.firstElementChild;
            const fileRemoveBtn = input.parentElement.nextElementSibling;
            fileInput.setAttribute('onchange', `selectFile(this, ${response[i].id})`);
            fileRemoveBtn.setAttribute('onclick', `removeFile(this, ${response[i].id})`);
            input.value = response[i].originalName;
        })
    }
    
    //첨부파일 1 row 추가
    function addFile() {
        const fileDiv = document.createElement('div');
        fileDiv.innerHTML =`
                <div class="file_list" style = "text-align:left; margin-top:10px">
	                <div class="file_input">
	                    <input type="text" readonly  style = "width:15%;"/>
							<label for = "files" > 첨부파일</label>
						<input type="file" id = "files" name="files" onchange="selectFile(this);"/>
					<button type="button" onclick="removeFile(this);" class="del-btn-m btns del_btn"><span>삭제</span></button>
				</div>
        `;
        document.querySelector('.file_list').appendChild(fileDiv);
    }

 // 파일 선택
    function selectFile(element) {

        const file = element.files[0];
        const filename = element.closest('.file_input').firstElementChild;
		
		var formData = new FormData();
		//formData.append("filename", file);
		formData.append("file", file);
			//image check
			 $.ajax({
	        type: "POST",
	        url: "/files/file-upload",
	        contentType: false,
	        processData: false,
	        data: formData,
	 		enctype: 'multipart/form-data',   
			dataType: "json",
	        success: function(response) {
				
	            alert("파일 확인");
	            
	        },
	        error: function(xhr, status, error) {
	            alert("파일 확장자 확인해주세요.");
	            
	            return false;
	        	}
	    	});
    	
        // 1. 파일 선택 창에서 취소 버튼이 클릭된 경우
        if ( !file ) {
            filename.value = '';
            return false;
        }

        // 2. 파일 크기가 10MB를 초과하는 경우
        const fileSize = Math.floor(file.size / 1024 / 1024);
        if (fileSize > 10) {
            alert('10MB 이하의 파일로 업로드해 주세요.');
            filename.value = '';
            element.value = '';
            return false;
        }

        // 3. 파일명 지정
        filename.value = file.name;
    }
    // 파일 삭제
    function removeFirstFile(element) {
    // 부모 요소
        var fileInputDiv = element.closest('.file_input');
        // 첨부 파일 input 요소
        var fileInput = fileInputDiv.querySelector('input[type="file"]');
        // 파일을 선택하지 않았을 경우
        if (!fileInput.files.length) {
            return false;
        }
        // 파일을 비우기
        fileInput.value = '';
        fileInputDiv.querySelector('input[type="text"]').value = '';
    }
    
    // 파일 삭제
    function removeFile(element) {
        const fileAddBtn = element.nextElementSibling;
        if (fileAddBtn) {
            const inputs = element.previousElementSibling.querySelectorAll('input');
            inputs.forEach(input => input.value = '')
            return false;
        }
        element.parentElement.remove();
    }
 



$(document).ready(function(){
	window.$app = {};
	$( ".tag_exhibit" ).selectmenu();
	
	//file drag-div start
	$(document).on({
		"dragenter":function(e){
        	$(this).addClass("over");
        },
        "dragleave":function(e){
        	$(this).removeClass("over");
        },
        "dragover":function(e){
        	e.preventDefault();
        },
        "drop":function(e){
        	
//        	alert("droped!");
        	var files = e.originalEvent.dataTransfer.files;
        	if(files.length > 0){
        		var fd = new FormData($("#insertForm")[0]);
        		fd.append('file', files[0]); 	
        		var nm = files[0].name.split(".");
        		nm.pop();
        		var filenm = nm.join(".");
        		var size = files[0].size;
        		var sizeMb = size ? size/1024/1024 : 0;
        		
        		$app.upload.nm = nm;
        		$app.upload.size = size;
        		$app.upload.form = fd;
        		console.log("file name is ",filenm,", file size is ",size);
        		
        		var table_html = '<table summary="업로드할 파일의 정보를 출력합니다.">';
        		table_html += '<caption class="Hidden">업로드파일 목록</caption>';
        		table_html += '<colgroup>';
        		table_html += '<col style="width: 60%;">';
        		table_html += '<col style="width: 30%;">';
        		table_html += '<col style="width: 10%;">';
        		table_html += '</colgroup>';
        		table_html += '<thead>';
        		table_html += '<tr>';
        		table_html += '<th>파일명</th>';
        		table_html += '<th>파일용량</th>';
        		table_html += '<th></th>';
        		table_html += '</tr>';
        		table_html += '</thead>';
        		table_html += '<tbody>';
        		table_html += '</tbody>';
        		table_html += '</table>';
        		
        		var $table = $(table_html);
        		
        		var $tr = $("<tr></tr>");
        		
        		$tr.append("<td>".concat(nm,"</td>"));
        		$tr.append("<td>".concat(sizeMb.toFixed(2)," Mb</td>"));
        		
        		var $td = $("<td><button></button></td>");
        		//var $del = $("<button></button>");
        		$td.find("button").attr("type","button").attr("class","del-file-btn");
        		
        		$tr.append($td);
        		$table.find("tbody").append($tr);
        		
        		$td.find("button").on("click", function(){
        			$(".drag-div").addClass("drag-active").removeClass("BoardList");
        			$(".drag-div").empty().append('<p class="drag-msg noselect">파일을 드래그하세요.</p>');
        		});
        		
        		$(".drag-div").addClass("BoardList").removeClass("drag-active");
        		$(".drag-div").empty().append($table);
        	}
        	$(this).removeClass("over");
        	e.preventDefault();
        	e.stopPropagation();
        }
    },".drag-div");
//drag-div end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

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
	
		if(!endDateInput.length > 0  && startDateInput.length > 0){
		 	alert("날짜 기간을 설정해주세요");
			return false;		
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
    var endDate_exhibit = document.getElementById('endDateExhibit').value;
    var tag_exhibit = document.getElementById('tag_exhibit').value;
    // Ajax 요청을 위한 데이터 객체 생성
    var data = {
        name_exhibit: name_exhibit,
        subname_exhibit: subname_exhibit,
        space_exhibit: space_exhibit,
        startDate_exhibit: startDate_exhibit,
        endDate_exhibit: endDate_exhibit,
        tag_exhibit: tag_exhibit
    };

    // Ajax 요청
    $.ajax({
        type: "POST",
        url: "/post/exhibitionWrite",
        data: data,
        success: function(response) {
            // 성공적으로 서버로부터 응답을 받았을 때 수행할 작업
            alert("작성 완료");
            // 여기에 성공적으로 처리된 후에 할 작업을 추가하세요.
        },
        error: function(xhr, status) {
            // 요청이 실패했을 때 수행할 작업
            alert("등록에 실패 하였습니다.");
            // 여기에 오류 발생 시 처리할 작업을 추가하세요.
        }
    });
}

