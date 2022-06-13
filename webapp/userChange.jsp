<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>ユーザ情報変更 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>ユーザ情報変更</h2>
    	<form action="UserChangeCompleted" method="post" onsubmit="return cancelsubmit()">
	        <dl>
	            <dt>ユーザID</dt>
	            <dd>${ userInfo.getUserId() }</dd>
	            <dt>ユーザ名</dt>
	            <dd><input type="text" name="userName" value="${ userInfo.getUserName() }" required></dd>
	            <dt>メールアドレス</dt>
	            <dd>${ userInfo.getMailAddress() }</dd>
	            <dt>パスワードを変更する</dt>
	            <dd><input type="checkbox" name="passwordChange" id="passwordChange"></dd>
	            <div id="passwordArea" style="display:none;">
		            <dt>新しいパスワード</dt>
		            <dd><input type="password" name="newPassword" id="newPassword"></dd>
		            <dt>新しいパスワード（確認用）</dt>
		            <dd><input type="password" name="newPassword2" id="newPassword2"></dd>
	            </div>
	        </dl>
	        <div style="display:none;" class="errorMsg" id="errorMsg">javascriptでエラーメッセージ</div>
	        
	        <!-- サーブレットに渡すパラメータを設定する -->
	        <input type="hidden" name="userId" value="${ userInfo.getUserId() }"> 
	        <input type="hidden" name="mailAddress" value="${ userInfo.getMailAddress() }"> 
	        <input type="hidden" name="oldPassword" value="${ userInfo.getPassword() }"> <!-- 変更前パスワード -->
	
	        <button type="button" onclick="location.href='index.jsp'">Topへ</button>
	        <input type="submit" value="変更する">
        </form>
       <script src="js/changePassword.js"></script>
</body>
</html>