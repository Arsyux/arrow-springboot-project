let exhibitionObject = {
	
	
	
	init: function() {
		
		let _this = this;
		
		// 서머노트
		let toolbar = [
			// 글꼴 설정
	    	['fontname', ['fontname']],
	    	// 글자 크기 설정
	    	['fontsize', ['fontsize']],
		    // 굵기, 기울임꼴, 밑줄,취소 선, 서식지우기
		    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
		    // 글자색
		    ['color', ['forecolor','color']],
		    // 표만들기
		    ['table', ['table']],
		    // 글머리 기호, 번호매기기, 문단정렬
		    ['para', ['paragraph', 'ul', 'ol']],
		    // 줄간격
		    ['height', ['height']],
		    // 그림첨부, 링크만들기, 동영상첨부
		    ['insert',['picture','link','video']],
		    // 코드보기, 확대해서보기, 도움말
		    ['view', ['codeview','fullscreen', 'help']]
	    ];
		let setting = {
			width: "100%",
			height : "30vw",
         	minHeight : null,
        	maxHeight : null,
        	focus : true,
        	lang : 'ko-KR',
        	fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
			fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
        	toolbar : toolbar,
        	callbacks : {
				onImageUpload : function(files, editor, welEditable) {
					for (let i = files.length - 1; i >= 0; i--) {
						uploadSummernoteImageFile(files[i], this);
        			}
        		}
        	}
        };
     	$('#exhibitionDetails').summernote(setting);
     	$('#exhibitionDetails').summernote('fontName', '맑은 고딕');
		
		function uploadSummernoteImageFile(file, el) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				async : false,
				data : data,
				type : "POST",
				url : "/exhibition/function/uploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(response) {
					console.log(response["data"]);
					//$("test").src = response["data"];
					
					//alert(response["data"]);
					
					$(el).summernote('editor.insertImage', response["data"]);
				}
			});
		}
		
		// 본관 - 프로그램 안내 글쓰기
		$("#btn-insertExhibition").on("click", () => {
			//let doc = document.documentElement;
		
			//if(doc.requestFullscreen) { doc.requestFullscreen(); }
			_this.insertExhibition();
		});
		
	},
	
	// 본관 - 프로그램 안내 글쓰기
	insertExhibition: function() {
				
		let exhibition = {
			exhibitionTitle: $("#exhibitionTitle").val(),
			
		}
		
		console.log(exhibition.exhibitionTitle);
		
		
		/*
		if($("#username").val() == "" || $("#username").val() == null) { return; }
		
		let user = {
			username : $("#username").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/contents/function/exhibitionWrite",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			let status = response["status"];
			if(status == 200) {
				// 중복 체크 통과
				let message = response["data"];
				// 사용 여부 체크
				if(confirm(message)) { document.getElementById('username').setAttribute('disabled', 'true'); }
			} else {
				// 중복 체크 실패
				let errors = response["data"];
				
				try{
					let jsonValue = JSON.parse(JSON.stringify(errors));
					let jsonCheck = typeof jsonValue === 'object';
					
					if (jsonCheck) {
						// 값이 JSON 형태일 경우 (유효성 검사에서 에러가 발견된 경우)
						// 유효성에서 걸렸으므로 유효성 알람
						if(jsonValue['username'] != null) {
							alert(jsonValue['username']);
						}
					} else {
						// 아이디 중복 알람
						alert(errors);
					}
				} catch (e) {
					alert("에러");
				}
			}
		}).fail(function(error) {
			alert("에러 발생 : " + error);
		});
		*/
	},
	
}

exhibitionObject.init();