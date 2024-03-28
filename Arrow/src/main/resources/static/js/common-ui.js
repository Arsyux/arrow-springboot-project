window.onload = () => {
        //renderPostInfo();

        //findAllFile();
    }

$(document).ready(function(){
	
	//window.$app = {};
	
	//$( ".tag_exhibit" ).selectmenu();
	$("#descript").summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null
		  });
		  
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
	//var decript_exhibit = document.getElementById('decript_exhibit').value;
	
	if(!startDate_exhibit.length > 0  && endDate_exhibit.length > 0){
		alert("날짜 기간을 설정해주세요");
		
		return false;
	}
	
	var fileInputs = document.querySelectorAll('.file_list input[type="file"]');
	var formData = new FormData();

	// 파일 이름 추가
	fileInputs.forEach((input) => {
		
		formData.append("filename", input.files[0]);

		})

	// 기타 데이터 추가
	formData.append("name_exhibit", name_exhibit);
	formData.append("subname_exhibit", subname_exhibit);
	formData.append("space_exhibit", space_exhibit);
	formData.append("startDate_exhibit", startDate_exhibit);
	formData.append("endDate_exhibit", endDate_exhibit);
	formData.append("tag_exhibit", tag_exhibit);
	//formData.append("decript_exhibit", decript_exhibit);
	//formData.append("data", JSON.stringify(data));
	
    // Ajax 요청
    $.ajax({
        type: "POST",
        url: "/contents/function/exhibitionWrite",
        data: formData,
	    contentType: false,
	    processData: false,
	 	enctype: 'multipart/form-data',   
		dataType: "json",
        success: function(data) {
    	if (data.status == 200) {
            alert("작성 완료되었습니다.");

        	} else {            
				alert("등록에 실패 하였습니다.");
            	return false;
        	}
            	
        },
        error: function(xhr, status) {
            // 요청이 실패했을 때 수행할 작업
            alert("등록에 실패 하였습니다.");
            return false;
        }
    });
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

/**
 * 조사데이터 상세조회
 * @param contents
 * @returns
 */
function fnContentDetail(exhseq) {
	var encryptedExhseq = btoa(exhseq); // exhseq를 Base64로 인코딩
	window.location.href = "/exhibition/view/exhibitionInfo/" + encryptedExhseq;
    
}
