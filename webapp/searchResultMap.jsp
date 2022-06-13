<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>検索結果（マップ）</h2>

	<div>検索結果：${circleInfoList.size()}件</div>
	
    <button onclick="location.href='searchResultList.jsp'">リスト形式で表示する</button>
    
	<input type="hidden" id="prefecture" value="${ prefecture }">
	<input type="hidden" id="city" value="${ city }">
			
	<div>
		<c:forEach var="circleInfo" items="${circleInfoList}" varStatus="status" >
			<input type="hidden" name="index" value="
				${ status.index+1 }
			">
			<input type="hidden" name="circleName" value="
				${circleInfo.getCircleName()}
			">
			<input type="hidden" name="lat" value="
				${circleInfo.getLatitude()}
			">
			<input type="hidden" name="lng" value="
				${circleInfo.getLongitude()}
			">
		</c:forEach>
	</div>
	<input type="button" value="現在位置" onClick="currentLocation()"/>
	
	
    <!-- ★cssは別ファイルに移す -->
    <section id="googleMap" style="width:100%; height:90%; position: absolute;"></section>
	
	<script src="js/googleMapSearchList.js"></script>
	
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>
</body>
</html>