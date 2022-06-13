<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>問い合わせ完了 | SportsMap</title>
</head>
<body>
    <h2>問い合わせ完了</h2>
	
	<!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
	
    <p>
        お問い合わせありがとうございました。
    </p>

    <button onclick="location.href='myPage.jsp'">マイページへ</button>
    <button onclick="location.href='index.jsp'">Topへ</button>

</body>
</html>