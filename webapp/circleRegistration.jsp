<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>サークル登録 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>サークル登録</h2>

    <form action="CircleRegistCompleted" enctype="multipart/form-data" method="post" onsubmit="submitCheck(event)">
        <dl>
            <dt>種目<span class="required">※</span></dt>
            <dd>
                <c:import url="sportsList.jsp" charEncoding="UTF-8" />
            </dd>
            <dt>サークル名<span class="required">※</span></dt>
            <dd><input type="text" name="circleName" maxlength="100" required></dd>
            <dt>紹介文<span class="required">※</span></dt>
            <dd><textarea name="introduce" rows="5" maxlength="2000" required></textarea></dd>
            <dt>活動場所</dt>
	        <dd>
                <select name="prefecture" id="prefecture" required>
                    <option value=""></option>
                </select>
                <select name="city" id="city" required>
                    <option value=""></option>
                </select>
       
                <input type="text" name="detailAddress" maxlength="100" placeholder="市区町村以降の住所" required>
            </dd>
            <dt>活動日時<span class="required">※</span></dt>
            <dd>
                <label><input type="checkbox" name="week" value="土曜">土曜</label>
                <label><input type="checkbox" name="week" value="日曜">日曜</label>
                <label><input type="checkbox" name="week" value="月曜">月曜</label>
                <label><input type="checkbox" name="week" value="火曜">火曜</label>
                <label><input type="checkbox" name="week" value="水曜">水曜</label>
                <label><input type="checkbox" name="week" value="木曜">木曜</label>
                <label><input type="checkbox" name="week" value="金曜">金曜</label>
                <input type="text" name="activeDateTime" maxlength="100" required>
            </dd>
            <dt>サークル規模<span class="required">※</span></dt>
            <dd><input type="text" name="scale" maxlength="100" required></dd>
            <dt>男女比</dt>
            <dd><input type="text" name="genderRatio" maxlength="100"></dd>
        </dl>

        
        <h2>募集条件</h2>
        <dl>
            <dt>性別<span class="required">※</span></dt>
            <dd>
                <label><input type="radio" name="sex" value="制限なし" checked>制限なし</label>
                <label><input type="radio" name="sex" value="男性のみ">男性のみ</label>
                <label><input type="radio" name="sex" value="女性のみ">女性のみ</label>
            </dd>
            <dt>経験年数<span class="required">※</span></dt>
            <dd>
                <label><input type="radio" name="carrier" value="初心者歓迎" checked>初心者歓迎</label>
                <label><input type="radio" name="carrier" value="1年以上">1年以上</label>
                <label><input type="radio" name="carrier" value="3年以上">3年以上</label>
                <label><input type="radio" name="carrier" value="5年以上">5年以上</label>
            </dd>
            <dt>ポジション</dt>
            <dd>
                <input type="text" name="position" maxlength="100">
            </dd>
        </dl>

        <input type="file" name="file" id="file" accept="image/jpeg,image/png" multiple>
        <div id="preview"></div>

        <input type="submit" value="登録する" onClick="return checkBoxRequired()">
        
		<div id="Msg" class="errorMsg" style="display:none;"></div>
        
        <!-- googleMap -->
		<input type="text" size="50" id="search" value="" placeholder="検索したい地名を入力してください"/><br>
		<input type="button" value="検索" onClick="SearchGo()"/>
		<input type="button" value="現在位置" onClick="currentLocation()"/>
		
		<p>活動場所にピンを設定してください（複数ある場合は1番活動が多い場所を設定してください）</p>
		
		<input type="hidden" name="lat" id="lat" value="経度"><br>
		<input type="hidden" name="lng" id="lng" value="緯度"><br>
	
		<!-- googleMap埋め込み ★cssを移す -->
		<section id="googleMap" style="width:100%; height:90%; position:absolute;"></section>
    </form>
    <script src="js/uploadPicture.js"></script>
    <script src="js/sportsDropdown.js"></script>
    <script src="js/checkBoxRequired.js"></script>
    <!-- 都道府県、市区町村設定 -->
	<script src="js/prefectureCity.js"></script>
    <script src="js/googleMapSelectLocation.js"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>
</body>
</html> 