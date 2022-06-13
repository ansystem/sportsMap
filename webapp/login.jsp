<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>ログイン | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>ログイン</h2>
		<form action="Login" method="post" name="userInfoInput">
	        <dl>
	            <dt>ユーザID</dt>
	            <dd><input type="text" name="userId"></dd>
	            <dt>パスワード</dt>
	            <dd><input type="password" name="password"></dd>
	        </dl>
	
	        <button type="button" onclick="location.href='index.jsp'">Topへ</button>
	        <button type="button" onclick="sendMailForPasswordChange()">パスワードを忘れた</button>
	        <button type="button" onclick="location.href='userRegistration.jsp'">新規登録</button>
	        <input type="submit" value="ログイン" id="test">
        </form>
        
        <script>
        	/* ★後でｊｓファイルに移す*/
        	function sendMailForPasswordChange() {
				document.userInfoInput.setAttribute('action','SendMailForChangePassword');

        		document.userInfoInput.submit();
			}
        </script>
        
        <c:if test="${ checkExistUser == 'NotExist' }">
        	<p class="errorMsg">ユーザIDまたはパスワードが間違っています</p>
        </c:if>
</body>
</html>