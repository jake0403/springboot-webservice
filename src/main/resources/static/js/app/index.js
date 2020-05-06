var index = {
	init: function() {
		var _this = this;
		$('#btn-delete').on('click', function() {
			_this.delete();
		});

		$('#btn-save').on('click', function() {
			_this.save();
		});
		$('#btn-update').on('click', function() {
			_this.update();
		});
		
	}, 
	save: function() {
		var data = {
			title: $('#title').val(), 
			content: $('#content').val(),
			author: $('#author').val()
		};
		/*
		console.log(data);
		console.log(typeof data);
		console.log(JSON.stringify(data));
		console.log(typeof JSON.stringify(data));
		*/
		/* https://api.jquery.com/jquery.ajax/ */
		/* JSON.stringify() : JavaScript 값 또는 객체를 JSON 문자열로 변환하는 함수 */
		$.ajax({
			type: 'POST',					/* 요청 방식(request method) */
			url: '/api/v1/posts', 			/* URL 주소 */
			dataType: 'JSON', 				/* 서버의 응답으로 기대하는 데이터 타입 */
			contentType: 'application/json; charset=UTF-8', /* 서버로 요청할 때 요청 데이터의 타입 */
			data: JSON.stringify(data)			/* 서버로 전달할 데이터 (요청 본문에 포함됨) */
		}).done(function() {
			alert('글이 등록되었습니다.');
			window.location.href = "/";
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});		
	},
	update: function() {
		// PostsUpdateRequestDto 객체에 값이 전달
		// PostsApiController.update()를 통해서 전달
		var data = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		var id = $("#id").val();
		$.ajax({
			type: "PUT",
			url: "/api/v1/posts/" + id,
			contentType: "application/json; charset=UTF-8",
			data: JSON.stringify(data)
		}).done(function() {
			alert("글이 수정되었습니다.");
			window.location.href = "/";
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});
	},
	delete: function() {
		var id = $('#id').val();
		$.ajax({
			type: 'DELETE', 
			url: '/api/v1/posts/' + id,
			contentType: 'application/json; charset=UTF-8',
			dataType: 'json'
		}).done(function() {
			alert('글이 삭제되었습니다.');
			window.location.href = '/';
		}).fail(function(e) {
			alert(JSON.stringify(e));
		});
	}


};

index.init();