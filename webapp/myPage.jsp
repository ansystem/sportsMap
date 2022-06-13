<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>マイページ | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>マイページ</h2>
		
        <h3>ユーザ情報</h3>
            <dl>
                <dt>ユーザ名</dt>
                <dd>${ userInfo.getUserName() }</dd>
                <dt>メールアドレス</dt>
                <dd>${ userInfo.getMailAddress() }</dd>
            </dl>
            <button onclick="location.href='userChange.jsp'">ユーザ情報を変更する</button>

        <h3>登録サークル</h3>
        	<c:choose>
	        	<c:when test="${ circleInfo!=null }">
	        		
	        		<c:set var="circleInfoType" value="ownCircle" scope="session"/>
	        		
		            <a href="circleDetail.jsp">${ circleInfo.getCircleName() }</a>
		            <button onclick="location.href='circleChange.jsp'">サークル情報変更</button>
	            </c:when>
	            <c:otherwise>
	            	<p>サークル未登録</p>
		            <button onclick="location.href='circleRegistration.jsp'">サークル情報登録</button>
	 			</c:otherwise>
			</c:choose>
        <h3>チャット履歴</h3>
        	<%@ page import="beans.ChatHistory,java.util.ArrayList,java.util.List,java.text.SimpleDateFormat" %>
        	<% if (session.getAttribute("chatHistoryList")!=null) { %>
	        	<% List<ArrayList<ChatHistory>> chatHistoryList = (List<ArrayList<ChatHistory>>)session.getAttribute("chatHistoryList"); %>

	        	<% for (ArrayList<ChatHistory> chatHistory : chatHistoryList) { %>
	        		<section>
		        		<% ChatHistory currentChatHistory = null; %>
		        		<% for (ChatHistory chat : chatHistory) { %>
		        			<ul>
			                    <li><%= chat.getFromUserId() %> → <%= chat.getToUserId() %></li>
			                    <li><%= chat.getTitle() %></li>
			                    <li>
			                        <p style="white-space: pre-wrap"><%= chat.getContents() %> <!-- 改行を表示する為にwhite-space:pre-wrapを入れているので、左記は1行で記載する必要がある ★cssファイルに移す-->
			                        </p>
			                    </li>
			                    <li><%= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(chat.getSendDateTime()) %></li>
			                </ul>
		        			<% currentChatHistory = chat; %>
		        		<% } %>
		        		
						<button onclick="location.href='ReturnChat?counterUserId=<%= currentChatHistory.getCounterName() %>'">返信する</button>
	        		</section>
	        	<% } %>
        	<% } %>
        	
			
            <button onclick="location.href='index.jsp'">Topへ</button>
</body>
</html>