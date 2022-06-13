<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
 	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>サークル詳細 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
  	<c:import url="header.jsp" charEncoding="UTF-8" />　
    
    <c:choose>
    	<%-- サークルタイプが検索したサークルの場合 --%>
    	<c:when test="${ circleInfoType == 'searchCircle' }">
    		<c:set var="circleInfo" value="${ searchCircleInfo }" scope="page"/>
    	</c:when>	
    	<%-- サークルタイプがログインユーザが登録したサークルの場合 --%>
    	<c:when test="${ circleInfoType == 'ownCircle' }">
    		<c:set var="circleInfo" value="${ circleInfo }" scope="page"/>
    	</c:when>
    </c:choose>
    <h2>サークル情報</h2>
    <dl>
        <dt>サークル名</dt>
        <dd>${ circleInfo.getCircleName() }</dd>
        <dt>種目</dt>
        <dd>${ circleInfo.getSportsType() }</dd>
        <dt>紹介文</dt>
        <dd>${ circleInfo.getIntroduce() }</dd>
        <dt>活動場所</dt>
        <dd>${ circleInfo.getAddress1() } ${ circleInfo.getAddress2() } ${ circleInfo.getAddress3() }</dd>
        <dt>活動曜日</dt>
        <dd>${ circleInfo.getWeek() }</dd>
        <dt>活動日時</dt>
        <dd>${ circleInfo.getActDateTime() }</dd>
        <dt>サークル規模</dt>
        <dd>${ circleInfo.getScale() }</dd>
        <dt>男女比</dt>
        <dd>${ circleInfo.getGenderRatio() }</dd>
    </dl>
    
    <h2>募集条件</h2>
    <dl>
        <dt>性別</dt>
        <dd>${ circleInfo.getSex() }</dd>
        <dt>経験年数</dt>
        <dd>${ circleInfo.getCarrier() }</dd>
        <dt>ポジション</dt>
        <dd>${ circleInfo.getPosition() }</dd>
    </dl>
    
    <figure>
    	<c:set var="fileNames" value="${ circleInfo.getImageSrc().split(\",\") }" scope="session"/>
    	
		<c:forEach var="file" items="${fileNames}" varStatus="status" >			
	    	<c:if test="${file.length() > 0}" >
				<img src="<%= request.getContextPath() %>/file/<c:out value="${ file }" />" alt="サークルイメージ画像" style="height:100px; weight:100px">
			</c:if>
		</c:forEach> 
    <!--  request.getContextPath() をJSTLにできない★ -->
    </figure>
	<%-- サークルタイプが検索したサークルの場合 --%>
   	<c:if test="${ circleInfoType == 'searchCircle' }">
	    <button onclick="location.href='index.jsp'">検索条件を再設定する</button>
	    <button onclick="location.href='chat.jsp'">応募する</button>				<!-- ★ログインしていない場合はログインさせる -->
   	</c:if>	
</body>
</html>