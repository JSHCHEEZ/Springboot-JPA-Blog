<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">
	<form action="/auth/loginProc" method="post">
	  <div class="form-group">
	    <label for="email">사용자 이름:</label>
	    <input type="text" name="username" class="form-control" placeholder="이름을 입력하세요" id="username">
	  </div>
	  <div class="form-group">
	    <label for="pwd">비밀번호:</label>
	    <input type="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요" id="password">
	  </div>
	  <div class="form-group form-check">
	    <label class="form-check-label">
	      <input name="remember" class="form-check-input" type="checkbox"> Remember me
	    </label>
	  </div>
	  <button type="submit"  class="btn btn-primary" >로그인</button>
	  <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=1c9d40cff0accf45a7a586d1534bca0b&redirect_uri=http://localhost:8000/auth/kakao/callback" ><img height="38px" src="/image/kakao_login_medium.png" /></a>
	</form>
	
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>

</html>