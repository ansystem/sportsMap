<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>チャット返信 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>チャット返信</h2>

        <h3>返信内容</h3>
        	<form action="ReturnSendChat" method="post">
	            <dl>
	                <dt>送信先</dt>
	                <dd>
		                <!-- ★相手が応募者かつサークルを持っている場合はユーザ名を表示するように制御を入れる -->
		                <c:choose>
		                	<c:when test="${ searchCircleInfo!=null }">
		                		${ searchCircleInfo.getCircleName() }
		                	</c:when>
		                	<c:otherwise>
		                		${ counterUserInfo.getUserName() }
		                	</c:otherwise>
		                </c:choose>
	                </dd>    
	                <dt>件名<span class="required">※</span></dt>
	                <dd><input type="text" name="subject" required></dd>
	                <dt>本文<span class="required">※</span></dt>
	                <dd><textarea name="contents" rows="5" required></textarea></dd>
	            </dl>
	            <input type="submit" value="送信する">
			</form>
            <!-- ★チャット履歴は共通化したほうがいいかも -->
        <h3>チャット履歴</h3>
            <%@ page import="beans.ChatHistory,java.util.ArrayList,java.util.List" %>
        	<% List<ChatHistory> chatHistoryList = (List<ChatHistory>)session.getAttribute("returnChatHistoryList"); %>
        
       		<section>
	       		<% for (ChatHistory chat : chatHistoryList) { %>
	       			<ul>
	                    <li>送信元：<%= chat.getFromUserId() %></li>
	                    <li>宛先：<%= chat.getToUserId() %></li>
	                    <li>送信時刻：<%= chat.getSendDateTime() %></li>
	                    <li>タイトル：<%= chat.getTitle() %></li>
	                    <li>
	                        <p style="white-space: pre-wrap"><%= chat.getContents() %><!-- 改行を表示する為にwhite-space:pre-wrapを入れているので、左記は1行で記載する必要がある ★cssファイルに移す-->
	                        </p>
	                    </li>
	                </ul>
	       		<% } %>
       		</section>

            <button onclick="location.href='index.jsp'">Topへ</button>
            <button onclick="location.href='myPage.jsp'">マイページへ</button>
</body>
</html>