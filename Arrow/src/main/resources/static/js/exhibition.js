let exhibitionObject = {
	
	
	
	init: function() {
		
		let _this = this;
		
		// 서머노트
		let setting = {
			width: "100%",
			height : "30vw",
         	minHeight : null,
        	maxHeight : null,
        	focus : true,
        	lang : 'ko-KR',
        	fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
			placeholder: '상세 설명',
        	toolbar : [
				// 글꼴 설정
		    	['fontname', ['fontname']],
		    	// 글자 크기 설정
		    	['fontsize', ['fontsize']],
			    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
			    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
			    // 글자색
			    ['color', [/*'forecolor'*/,'color']],
			    // 표만들기
			    ['table', ['table']],
			    // 글머리 기호, 번호매기기, 문단정렬
			    ['para', ['paragraph', 'ul', 'ol']],
			    // 줄간격
			    ['height', ['height']],
			    // 그림첨부, 링크만들기, 동영상첨부
			    ['insert',['picture','link','video']],
			    // 코드보기, 확대해서보기, 도움말
			    ['view', ['codeview'/*,'fullscreen'*/, 'help']]
		    ],
		    fontName: '맑은 고딕',
        	callbacks : {
				// 이미지 업로드
				onImageUpload : function(files) {
					/*
					for (let i = files.length - 1; i >= 0; i--) {
						uploadImageFile(files[i], this);
        			}
        			*/
        			for (let i = 0; i < files.length; i++) {
						uploadImageFile(files[i], this);
        			}
        		}
        	}
        };
     	$('#exhibitionDetails').summernote(setting).summernote('fontName', '맑은 고딕');
		
		// 파일 업로드 부분
		function uploadImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/exhibition/function/uploadImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(response) {
					//console.log("이미지를 서버에 전송하였습니다. 이미지 경로: " + response["data"]);
					$(el).summernote('editor.insertImage', response["data"]);
				}
			});
		}
		
		// 본관 - 프로그램 안내 글쓰기
		$("#btn-insertExhibition").on("click", () => {
			let tag = $("#exhibitionTag").val();
			switch(tag) {
				case '전시':
					_this.insertExhibition();
					break;
				default:
					alert('준비중!');
					break;
			}
		});
		
	},
	
	// 본관 - 프로그램 안내 글쓰기
	insertExhibition: function() {
		
		let exhibition = {
			tag_exhibit: $("#exhibitionTag").val(),
			title_exhibit: $("#exhibitionTitle").val(),
			description_exhibit: $("#exhibitionDescription").val(),
			startDate_exhibit: $("#exhibitionStartDate").val(),
			endDate_exhibit: $("#exhibitionEndDate").val(),
			space_exhibit: $("#exhibitionSpace").val(),
			details_exhibit: $("#exhibitionDetails").val()
		}
		
		$.ajax({
			type: "POST",
			url: "/exhibition/function/exhibitionWrite",
			data: JSON.stringify(exhibition),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			if(response["status"] == 200) {
				alert(response["data"]);
				location = '/adm'
			} else {
				alert('게시글 작성에 실패하였습니다.');
				
			}
			
			//let status = response["status"];
			
			//console.log(response["status"]);
			/*
			if(status == 200) {
				let postid = response["data"];
				
				let form = document.createElement("form");
		
				let data = document.getElementsByClassName('files');
				
				let dataLength = data.length;
				
				let postidField = document.createElement("input");
				postidField.setAttribute("type", "hidden");
				postidField.setAttribute("name", "postid");
				postidField.setAttribute("value", postid);
				
				form.appendChild(postidField);
				
				// form에 데이터를 담고 청소
				for(i=0; i< dataLength; i++){ form.appendChild(data[0]); }		
				document.getElementById('file_list').innerHTML = '';
				
				// body에 부착
				document.body.appendChild(form);
				var formData = new FormData(form);
				$.ajax({
			        url : '/file/insertFiles',
			        type : 'POST',
			        data : formData,
			        contentType : false,
			        processData : false        
			    }).done(function(response){
					alert(response["data"]);
					location = "/";
			    }).faiil(function(error){
					let message = error["data"];
					alert("첨부파일 업로드 에러 발생 : " + message);
					let deletePost = {
						postid: postid
					}
					$.ajax({
						type: "DELETE",
						url: "/post/" + postid,
						data: JSON.stringify(deletePost),
						contentType: "application/json; charset=utf-8"
					});
				});
			} else {
				let warn = "";
				let errors = response["data"];
				if(errors.title != null) { warn = warn + errors.title + "\n" }
				if(errors.content != null) { warn = warn + errors.content + "\n" }
				if(errors.departures_postcode != null) { warn = warn + errors.departures_postcode + "\n" }
				if(errors.departures_address != null) { warn = warn + errors.departures_address + "\n" }
				if(errors.departures_detailAddress != null) { warn = warn + errors.departures_detailAddress + "\n" }
				if(errors.departures_extraAddress != null) { warn = warn + errors.departures_extraAddress + "\n" }
				if(errors.arrivals_postcode != null) { warn = warn + errors.arrivals_postcode + "\n" }
				if(errors.arrivals_address != null) { warn = warn + errors.arrivals_address + "\n" }
				if(errors.arrivals_detailAddress != null) { warn = warn + errors.arrivals_detailAddress + "\n" }
				if(errors.arrivals_extraAddress != null) { warn = warn + errors.arrivals_extraAddress }
				alert(warn);
			}
			*/
		}).fail(function(error) {
			let message = error["data"];
			alert("에러 발생 : " + message);
		});
		
		
	},
	
}

exhibitionObject.init();