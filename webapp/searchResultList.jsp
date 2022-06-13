<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>検索結果（リスト） | SportsMap</title>

	<!-- ★共通化できるかも、場所もここでいいか要確認 -->
	<script src="js/jquery-3.6.0.min.js"></script>
	<script src="js/paginathing.min.js"></script>

</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>検索結果（リスト）</h2>
    <button onclick="location.href='searchResultMap.jsp'">GoogleMap上で表示する</button>

	<div class="paging">
		<c:forEach var="circleInfo" items="${ circleInfoList }" varStatus="status">
			<section>
				<dl>
					<dt>サークル名</dt>
					<dd><a href="CircleDetail?index=${ status.index+1 }">${ circleInfo.getCircleName() }</a></dd>
					<dt>紹介文</dt>
					<dd>${ circleInfo.getIntroduce() }</dd>
					<dt>活動場所</dt>
					<dd>${ circleInfo.getAddress1() } ${ circleInfo.getAddress2() } ${ circleInfo.getAddress3() }</dd>
					<dt>活動曜日</dt>
					<dd>${ circleInfo.getWeek() }</dd>
					<dt>サークル規模</dt>
					<dd>${ circleInfo.getScale() }</dd>
					<dt>男女比</dt>
					<dd>${ circleInfo.getGenderRatio() }</dd>
				</dl>	
				<c:set var="fileNames" value="${ circleInfo.getImageSrc().split(\",\") }" scope="session"/>
  
				<c:forEach var="file" items="${fileNames}" varStatus="status" >				
			    	<c:if test="${file.length() > 0}" >
						<img src="<%= request.getContextPath() %>/file/<c:out value="${ file }" />" alt="サークルイメージ画像" style="height:100px; weight:100px">
					</c:if>
				</c:forEach> 
			</section>
		</c:forEach>
	</div>

	<script>
		$(function(){
			$('.paging').paginathing({
				perPage:10,
				prevText:'前へ',
				nextText:'次へ',
				// activeClass:'navi-active',//現在のページ番号に任意のclassを付与できます → ★後々設定するかも
			})
		});
	</script>

</body>
</html>