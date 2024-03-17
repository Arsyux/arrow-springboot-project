let exhibitionObject = {
	
	init: function() {
		let _this = this;
		
		// 본관 - 프로그램 안내 글쓰기
		$("#btn-insertExhibition").on("click", () => {
			_this.insertExhibition();
		});
		
	},
	
	// 본관 - 프로그램 안내 글쓰기
	insertExhibition: function() {
		
		alert('클릭');
		
		/*
		let exhibition = {
			exhibitionTitle: $("#exhibitionTitle").val(),
		}
		*/
		
		alert($("#exhibitionTitle").val());
		
		/*
		if($("#username").val() == "" || $("#username").val() == null) { return; }
		
		let user = {
			username : $("#username").val(),
		}
		
		$.ajax({
			type: "POST",
			url: "/auth/usernameCheck",
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