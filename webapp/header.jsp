<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
	<!-- cssに移す★、margin-top:-17px;を調べる -->
	<a href="index.jsp"><img src="img/logo.png" style="width:100%; border-radius:0px; margin-top:-20px;"></a>
	<c:choose>
		<c:when test="${ userInfo==null }">
			<button onclick="location.href='userRegistration.jsp'">新規登録</button>
			<button onclick="location.href='login.jsp'">ログイン</button>
		</c:when>
		<c:otherwise>
			<span>${ userInfo.getUserName() }様がログイン済み</span>
			<button onclick="location.href='myPage.jsp'">マイページへ</button>
			<button onclick="location.href='Logout'">ログアウト</button>
		</c:otherwise>
	</c:choose>
</header>
