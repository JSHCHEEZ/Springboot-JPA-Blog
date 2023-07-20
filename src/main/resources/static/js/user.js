let index = {
	init : function(){
		$("#btn-save").on("click", ()=>{ //function(){}, () => {} this를 바인딩하기 위해서
			this.save();
		});
		
		$("#btn-update").on("click", ()=>{ 
			this.update();
		});
		
		$("#btn-login").on("click", ()=>{
			this.login();
		});
	},
	
	save : function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		// ajax 호출시 default가 비동기 호출
		$.ajax({
			type:"POST",
			url:"/auth/joinProc",
			data:JSON.stringify(data), // http body데이터
			contentType:"application/json; charset=utf-8", // body데이터가 어떤 타입인지
			dataType:"json" // 요청 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript object로 변경 해 줌
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!! 
	},
	
	update : function(){
		let data = {
			id: $("#id").val(),
			username:$("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type:"PUT",
			url:"/user",
			data:JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("회원정보수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	},
	
	login : function(){
		let data = {
			username: $("#username").val(),
			password: $("#password").val()
		};
		
		$.ajax({
			type:"POST",
			url:"/api/user/login",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8",
			dataType:"json" 
		}).done(function(resp){
			alert("로그인이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!! 
	}
}

index.init();