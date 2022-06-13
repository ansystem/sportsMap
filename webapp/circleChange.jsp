<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
	<!-- 共通部品の各種定義を呼び出す -->
	<c:import url="definition.jsp" charEncoding="UTF-8" />
	<meta charset="UTF-8">
	<title>サークル変更 | SportsMap</title>
</head>
<body>
    <!-- 共通部品のヘッダを呼び出す -->
	<c:import url="header.jsp" charEncoding="UTF-8" />
    <h2>サークル変更</h2>
    
    <form action="CircleChangeCompleted" enctype="multipart/form-data" method="post" onsubmit="submitCheck(event)">
        <dl>
            <dt>種目※</dt>
            <dd>
                <c:import url="sportsList.jsp" charEncoding="UTF-8" />
                <input type="hidden" id="sportsTypeHidden" value="${ circleInfo.getSportsType() }">
            </dd>
            <dt>サークル名※</dt>
            <dd><input type="text" name="circleName" maxlength="100" required value="${ circleInfo.getCircleName() }"></dd>
            <dt>紹介文※</dt>
            <dd><textarea name="introduce" rows="5" maxlength="2000" required>${ circleInfo.getIntroduce() }</textarea></dd>
            <dt>活動場所※</dt>
            <dd>
                <select name="prefecture" id="prefecture" required>
                    <option value=""></option>
                </select>
                <input type="hidden" id="prefectureHidden" value="${ circleInfo.getAddress1() }">
                <select name="city" id="city">
                    <option value=""></option>
                </select>
                <input type="hidden" id="cityHidden" value="${ circleInfo.getAddress2() }">
                <input type="text" name="detailAddress" maxlength="100" required value="${ circleInfo.getAddress3() }">
            </dd>
            <dt>活動日時※</dt>
            <dd>
                <label><input type="checkbox" name="week" value="土曜">土曜</label>
                <label><input type="checkbox" name="week" value="日曜">日曜</label>
                <label><input type="checkbox" name="week" value="月曜">月曜</label>
                <label><input type="checkbox" name="week" value="火曜">火曜</label>
                <label><input type="checkbox" name="week" value="水曜">水曜</label>
                <label><input type="checkbox" name="week" value="木曜">木曜</label>
                <label><input type="checkbox" name="week" value="金曜">金曜</label>
                <input type="hidden" id="weekHidden" value="${ circleInfo.getWeek() }">
                <input type="text" name="activeDateTime" maxlength="100" required value="${ circleInfo.getActDateTime() }">
            </dd>
            <dt>サークル規模※</dt>
            <dd><input type="text" name="scale" maxlength="100" required value="${ circleInfo.getScale() }"></dd>
            
            <dt>男女比</dt>
            <dd><input type="text" name="genderRatio" maxlength="100" value="${ circleInfo.getGenderRatio() }"></dd>
        </dl>

        
        <h2>募集条件</h2>
        <dl>
            <dt>性別<span class="required">※</span></dt>
            <dd>
                <label><input type="radio" name="sex" value="制限なし" checked>制限なし</label>
                <label><input type="radio" name="sex" value="男性のみ">男性のみ</label>
                <label><input type="radio" name="sex" value="女性のみ">女性のみ</label>
                <input type="hidden" id="sexHidden" value="${ circleInfo.getSex() }">
            </dd>
            <dt>経験年数<span class="required">※</span></dt>
            <dd>
                <label><input type="radio" name="carrier" value="初心者歓迎" checked>初心者歓迎</label>
                <label><input type="radio" name="carrier" value="1年以上">1年以上</label>
                <label><input type="radio" name="carrier" value="3年以上">3年以上</label>
                <label><input type="radio" name="carrier" value="5年以上">5年以上</label>
                <input type="hidden" id="carrierHidden" value="${ circleInfo.getCarrier() }">
            </dd>
            <dt>ポジション</dt>
            <dd>
                <input type="text" name="position" maxlength="100" value="${ circleInfo.getPosition() }">
            </dd>
        </dl>

        <input type="file" name="file" id="file" accept="image/jpeg,image/png" multiple>
        <div id="preview">
        	<c:set var="fileNames" value="${ circleInfo.getImageSrc().split(\",\") }" scope="session"/>
	    	
			<c:forEach var="file" items="${fileNames}" varStatus="status" >			
		    	<c:if test="${file.length() > 0}" >
					<img src="<%= request.getContextPath() %>/file/<c:out value="${ file }" />" alt="サークルイメージ画像" style="height:100px; weight:100px">
				</c:if>
			</c:forEach> 
        </div>
				
        <script>
            const preview = document.getElementById('preview');
            const fileInput = document.getElementById('file');
            /* ファイルプレビュー表示関数 */
            function previewFile(file) {
                const reader = new FileReader();

                reader.onload = function(e) {
                    const imageUrl = e.target.result;
                    const img = document.createElement("img");
                    img.src = imageUrl;
                    img.style = "height:100px; weight:100px";
                    preview.appendChild(img);
                }
                reader.readAsDataURL(file);
            }
            const handleFileSelect = () => {
            	/* 子要素を最後から順に削除する */
                while(preview.lastChild){
                    preview.removeChild(preview.lastChild);
                }
                const files = fileInput.files;
                for (let i=0; i<files.length; i++){
                    // 5枚以上アップロードされた場合は、打ち切ってメッセージを出す
                    if (i == 5) {
                        const div = document.createElement("div");
                        div.innerText = "写真は5枚までしかアップロードできません";
                        preview.appendChild(div);
                        break;                        
                    }
                    previewFile(files[i]);
                }
            }
            /* ファイルがアップロードされた際に */
            fileInput.addEventListener('change', handleFileSelect);
        </script>

        <input type="submit" value="変更する" onClick="return checkBoxRequired()">
        <div id="Msg" class="errorMsg" style="display:none;"></div>
        
        <!-- googleMap -->
		<input type="text" size="50" id="search" value="" placeholder="検索したい地名を入力してください"/><br>
		<input type="button" value="検索" onClick="SearchGo()"/>
		<input type="button" value="現在位置" onClick="currentLocation()"/>
		
		<input type="hidden" name="lat" id="lat" value="${ circleInfo.getLatitude() }"><br>
		<input type="hidden" name="lng" id="lng" value="${ circleInfo.getLongitude() }"><br>
	
		<!-- googleMap埋め込み ★cssを移す -->
		<section id="googleMap" style="width:100%; height:90%; position:absolute;"></section>
    </form>
    <script src="js/checkBoxRequired.js"></script>
    <!-- 都道府県、市区町村設定 -->
	<script src="js/prefectureCityForCIrcleChange.js"></script>
	<!-- 種目でその他を選択した場合にドロップダウンを表示 -->
	<script src="js/sportsDropdown.js"></script>
    <!-- プルダウン、ラジオボタンの初期値を設定する -->
    <script src="js/changeCircleInfoDefalutValue.js"></script>
	<script src="js/googleMapSelectLocationForCircleChange.js"></script>
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>
</body>
</html>