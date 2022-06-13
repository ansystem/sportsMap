<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>スポーツサークル</title>
</head>
<body>

	<input type="text" size="55" id="search" value="鶯谷駅"/><br>
	<input type="button" value="検索" onClick="SearchGo()"/>
	<input type="button" value="現在位置" onClick="currentLocation()"/>
	<input type="button" value="登録" onClick="registration()"/>
	<input type="button" value="リスト表示" onClick="displayList()"/><br>
	<form action="http://localhost:8080/sportsMaps/example" method="POST" id="formPosition">
		<input type="text" name="regist" id="regist" value="ここに座標"><br>
		<input type="text" name="Msg" id="Msg" value="ここにメッセージ">
	</form>
	<div id="map_canvas" style="width:100%; height:90%; position: absolute;"></div>
		
	<script>
		var mayMap;
		var service;
		var marker;
		var markerList = [];
		var registInfo;
		// マップの初期設定
		function initialize() {
			// Mapクラスのインスタンスを作成（緯度経度は池袋駅に設定）
			var initPos = new google.maps.LatLng(35.729756, 139.711069);
			// 地図のプロパティを設定（倍率、マーカー表示位置、地図の種類）
			var myOptions = {
				zoom: 13,
				center: initPos,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};
			// #map_canva要素にMapクラスの新しいインスタンスを作成
			myMap = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
			// 検索の条件を指定（緯度経度、半径、検索の分類）
			var request = {
				location: initPos,
				radius: 1000,      // ※１ 表示する半径領域を設定(1 = 1M)

				query : 'カフェ'
			};
			var service = new google.maps.places.PlacesService(myMap);
					
			marker = new google.maps.Marker(); 

			// マップクリックイベントを追加 
			google.maps.event.addListener(myMap, 'click', function(e) { 
						console.log('通過しちょ' +  e.latLng);

						registInfo = e.latLng;
						console.log('クリック時'+e.latLng);


						marker.setMap(null);

						marker = new google.maps.Marker({ 
							position: e.latLng, 
							map: myMap 
						}); 
						console.log('リストのサイズは'+markerList.length);
			}); 
		}
		
		// 入力キーワードと表示範囲を設定
		function SearchGo() {
			
			for (i=0; i<markerList.length; i++) {
					markerList[i].setMap(null);
			}
			markerList = [];

			var initPos = new google.maps.LatLng(0,0);
			var mapOptions = {
				center : initPos,
				zoom: 0,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			};
			// #map_canva要素にMapクラスの新しいインスタンスを作成
			myMap = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
			service = new google.maps.places.PlacesService(myMap);
			// input要素に入力されたキーワードを検索の条件に設定
			var myword = document.getElementById("search");
			var request = {
				query : myword.value,
				radius : 5000,
				location : myMap.getCenter()
			};
			service.textSearch(request, result_search);
			

			// マップクリックイベントを追加 
			google.maps.event.addListener(myMap, 'click', function(e) 
			{ 
				console.log('通過しちょ' +  e.latLng);

				registInfo = e.latLng;
				
				for (i=0; i<markerList.length; i++) {
					markerList[i].setMap(null);
				}
				markerList = [];
				
				marker.setMap(null);

				marker = new google.maps.Marker({ 
					position: e.latLng, 
					map: myMap 
				}); 
				
				console.log('リストのサイズは'+markerList.length);
				
			}); 
		}

		// 検索の結果を受け取る
		function result_search(results, status) {
			// 表示領域の生成
			var bounds = new google.maps.LatLngBounds();
			for(var i = 0; i < results.length; i++){

				createMarker({
					position : results[i].geometry.location,
					text : results[i].name + "<button>ボタン貼れるよ</button>",
					map : myMap
				});
				// 地図表示領域をマーカーの位置に合わせて拡大
				bounds.extend(results[i].geometry.location);
			}
			// 地図表示領域の変更を反映
			myMap.fitBounds(bounds);
		}
		
		// 該当する位置にマーカーを表示
		function createMarker(options) {
			// マップ情報を保持しているmyMapオブジェクトを指定
			options.draggable = true;

			
			// Markcrクラスのオブジェクトmarkerを作成
			let marker = new google.maps.Marker(options);

			markerList.push(marker);
			

			// 各施設の吹き出し(情報ウインドウ)に表示させる処理
			var infoWnd = new google.maps.InfoWindow();
			infoWnd.setContent(options.text);
			// addListenerメソッドを使ってイベントリスナーを登録
			google.maps.event.addListener(marker, 'click', function(){
				infoWnd.open(myMap, marker);
			});

			console.log('リストのサイズは'+markerList.length);

			return marker;
		}

		// ------------------------------------ 登録ボタン押下時処理 ------------------------------------
		function registration() {
			let regist = document.getElementById('regist');
			regist.value = registInfo;
			
			if (markerList.length > 1) {
				document.getElementById('Msg').innerText = '複数のマーカーが設定されています。マップをクリックしてマーカを一つにしてください。';
			} else {
				document.getElementById('Msg').innerText = 'ここにメッセージ';
			}

			// POSTで渡す
			let formPosition = document.getElementById('formPosition');
			formPosition.submit();
		}

		// ------------------------------------ リスト表示ボタン押下時処理 ------------------------------------
		function displayList() {

			// let displayList = document.getElementsByName('displayList');

			// for (i=0; i<displayList.length; i++) {
			// 	console.log(displayList[i].value);
			// }
			location.href = "http://localhost:8080/sportsMaps/displayList";
		}

	</script>

	<!------------------------------------ 現在位置取得関数 ------------------------------------>
	<script>
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
						//　マップにマーカーを表示する
						marker = new google.maps.Marker({
						map : mayMap,             // 対象の地図オブジェクト
						position : mapLatLng   // 緯度・経度
						});

						// マップクリックイベントを追加 
						google.maps.event.addListener(mayMap, 'click', function(e) { 
						console.log('通過しちょ' +  e.latLng);

						registInfo = e.latLng;
						marker.setMap(null);

						marker = new google.maps.Marker({ 
							position: e.latLng, 
							map: mayMap 
						}); 
						console.log('リストのサイズは'+markerList.length);
			}); 

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
	</script>

	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>

</body>
</html>