<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更 | SportsMap</title>
</head>

<!-- ★直リンク(直接URL入力でのアクセス)を禁止するhttpd.conf設定 -->

<body>
    <h2>パスワード変更</h2>
    	<form action="ChangePasswordCompleted" method="post">
	        <dl>
	            <dt>ユーザID</dt>
	            <dd>：${ userInfo.getUserId() }</dd>
	            <dt>ユーザ名</dt>
	            <dd>：<input type="text" name="userName" value="${ userInfo.getUserName() }" required></dd>
	            <dt>新しいパスワード</dt>
	            <dd>：<input type="password" name="newPassword"></dd>
	            <dt>新しいパスワード（確認用）</dt>
	            <dd>：<input type="password" name="newPassword2"></dd>
	        </dl>
	
	        <input type="submit" value="変更する">
        </form>
</body>
</html>