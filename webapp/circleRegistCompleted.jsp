<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>サークル登録完了 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>サークル登録完了</h2>
	
	<%@ page import="beans.UserInfo" %>
	<% UserInfo userInfo = (UserInfo)session.getAttribute("userInfo"); %>
    <p>
        登録が完了しました。<br>
        応募がありましたら<br>
        <%= userInfo.getMailAddress() %><br>
        宛にメールでお知らせいたします。
    </p>

    <button onclick="location.href='myPage.jsp'">マイページへ</button>
    <button onclick="location.href='index.jsp'">Topへ</button>

</body>
</html>