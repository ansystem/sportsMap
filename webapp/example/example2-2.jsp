<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スポーツサークル2-2</title>
</head>
<body>

	
	<%@ page import="java.util.ArrayList" %>
	
	<div>
		<% ArrayList<String> displayList = (ArrayList<String>)session.getAttribute("displayList"); %>
		
		<% for (int i=0; i<displayList.size(); i++) { %>
			<input type="hidden" name="displayList" value="
				<%= displayList.get(i) %>
			">
		<% } %>
	</div>

	<input type="text" size="55" id="search" value="東京駅"/><br>
	<input type="button" value="検索" onClick="SearchGo()"/>
	<input type="button" value="現在位置" onClick="currentLocation()"/>
	<div id="map_canvas" style="width:100%; height:90%; position: absolute;"></div>
		
	<script>
		var mayMap;
		var service;
		var marker;
		var markerList = [];
		var registInfo;
		// マップの初期設定
		function initialize() {
			console.log('2-2に遷移しました');

			currentLocation();
			
		}

		// 検索の結果を受け取る
		function sportsArea(location,lat,lng) {
				var initPos = new google.maps.LatLng(lat, lng);

				var bounds = new google.maps.LatLngBounds();

				createMarker({
					position : initPos,
					text : location + "<button>ボタン貼れるよ</button>",
					map : mayMap
				});
				bounds.extend(initPos);
				// mayMap.fitBounds(bounds); //★なぜエラー？
			}

		// 該当する位置にマーカーを表示
		function createMarker(options) {
			// マップ情報を保持しているmayMapオブジェクトを指定


			// Markcrクラスのオブジェクトmarkerを作成
			let marker = new google.maps.Marker(options);

			markerList.push(marker);
			

			// 各施設の吹き出し(情報ウインドウ)に表示させる処理
			var infoWnd = new google.maps.InfoWindow();
			infoWnd.setContent(options.text);
			// addListenerメソッドを使ってイベントリスナーを登録
			google.maps.event.addListener(marker, 'click', function(){
				infoWnd.open(mayMap, marker);
			});

			console.log('リストのサイズは'+markerList.length);

			return marker;
		}

		// 入力キーワードと表示範囲を設定
		function SearchGo() {
			
			

			var initPos = new google.maps.LatLng(0,0);
			var mapOptions = {
				center : initPos,
				zoom: 15,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			// #map_canva要素にMapクラスの新しいインスタンスを作成
			service = new google.maps.places.PlacesService(mayMap);
			// input要素に入力されたキーワードを検索の条件に設定
			var myword = document.getElementById("search");
			var request = {
				query : myword.value,
				radius : 5000,
				location : mayMap.getCenter()
			};
			service.textSearch(request, result_search);
			
		}

		// 検索の結果を受け取る
		function result_search(results, status) {
			var bounds = new google.maps.LatLngBounds();
			for(var i = 0; i < results.length; i++){

				bounds.extend(results[i].geometry.location);
			}
			mayMap.fitBounds(bounds);
			// alert('zoomは'+mayMap.getZoom());
			// mayMap.setZoom(14); 
			if (mayMap.getZoom() > 14) mayMap.setZoom(14); 
			// alert('zoom2は'+mayMap.getZoom());
		}
		
	



	//------------------------------------ 現在位置取得関数 ------------------------------------
	
		// 現在地取得処理
		function currentLocation() {
			// Geolocation APIに対応している
			if (navigator.geolocation) {
				// 現在地を取得
				navigator.geolocation.getCurrentPosition(
					// 取得成功した場合
					function(position) {
						// 緯度・経度を変数に格納
						var mapLatLng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
						// マップオプションを変数に格納
						var mapOptions = {
						zoom : 15,          // 拡大倍率
						center : mapLatLng  // 緯度・経度
						};
						// マップオブジェクト作成
						mayMap = new google.maps.Map(
						document.getElementById("map_canvas"), // マップを表示する要素
						mapOptions         // マップオプション
						);

						getSportsArea();	

					},
					// 取得失敗した場合
					function(error) {
						// エラーメッセージを表示
						switch(error.code) {
						case 1: // PERMISSION_DENIED
							alert("位置情報の利用が許可されていません");
							break;
						case 2: // POSITION_UNAVAILABLE
							alert("現在位置が取得できませんでした");
							break;
						case 3: // TIMEOUT
							alert("タイムアウトになりました");
							break;
						default:
							alert("その他のエラー(エラーコード:"+error.code+")");
							break;
						}
					}
				);
			// Geolocation APIに対応していない
			} else {
				alert("この端末では位置情報が取得できません");
			}
		}

		function getSportsArea(){
			let displayList = document.getElementsByName('displayList');
			var str;
			for (i=0; i<displayList.length; i++) {
				str = displayList[i].value.split(',');

				sportsArea(str[0],str[1],str[2]);
			}
		}
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>

</body>
</html>