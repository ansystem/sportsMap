<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>チャット送信 | SportsMap</title>
</head>
<body>
    <h2>チャット送信</h2>
	 	<!-- 共通部品のヘッダを呼び出す -->
		<c:import url="header.jsp" charEncoding="UTF-8" />
        <h3>返信内容</h3>
        	<form action="SendChat" method="post">
	            <dl>
	                <dt>送信先</dt>
	                <dd>${ searchCircleInfo.getCircleName() }</dd>
	                <dt>件名<span class="required">※</span></dt>
	                <dd><input type="text" name="subject" required></dd>
	                <dt>本文<span class="required">※</span></dt>
	                <dd><textarea name="contents" rows="5" required></textarea></dd>
	            </dl>
	            <input type="submit" value="送信する">
			</form>
       
            <button onclick="location.href='index.jsp'">Topへ</button>
            <button onclick="location.href='myPage.jsp'">マイページへ</button>
</body>
</html>