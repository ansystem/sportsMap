var myMap;
var service;
var marker;
var markerList = [];
var lat;
var lng;
var latLocation = document.getElementById('lat');
var lngLocation = document.getElementById('lng');
// マップの初期設定
function initialize() {

	// 現在地取得処理
	initialLocation();
	
	marker = new google.maps.Marker(); 

}

// 初期設定地取得処理
function initialLocation() {
		latLocation.value;
		lngLocation.value;

		// 緯度・経度を変数に格納
		var mapLatLng = new google.maps.LatLng(latLocation.value, lngLocation.value);
		
		lat = latLocation.value;
		lng = lngLocation.value;

		// マップオプションを変数に格納
		var mapOptions = {
		zoom : 15,          // 拡大倍率
		center : mapLatLng  // 緯度・経度
		};
		// マップオブジェクト作成
		myMap = new google.maps.Map(
		document.getElementById("googleMap"), // マップを表示する要素
		mapOptions         // マップオプション
		);
		//　マップにマーカーを表示する
		marker = new google.maps.Marker({
		map : myMap,             // 対象の地図オブジェクト
		position : mapLatLng   // 緯度・経度
		});

		// マップクリックイベントを追加 
		google.maps.event.addListener(myMap, 'click', function(e) { 

		lat = e.latLng.lat();
		lng = e.latLng.lng();

		marker.setMap(null);

		marker = new google.maps.Marker({ 
			position: e.latLng, 
			map: myMap 
		}); 
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
	myMap = new google.maps.Map(document.getElementById("googleMap"), mapOptions);
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

		lat = e.latLng.lat();
		lng = e.latLng.lng();

		for (i=0; i<markerList.length; i++) {
			markerList[i].setMap(null);
		}
		markerList = [];
		
		marker.setMap(null);

		marker = new google.maps.Marker({ 
			position: e.latLng, 
			map: myMap 
		}); 
		
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


	return marker;
}

// ------------------------------------ 登録ボタン押下時処理 ------------------------------------
function submitCheck(e) {
	latLocation.value = lat;
	lngLocation.value = lng;

	// ★マーカーをゼロ（場所は現在地）からスタートし、ゼロの場合はエラーメッセージを出す 

	if (markerList.length > 1) {
		document.getElementById('Msg').innerHTML = '<span class="errorMsg">複数のマーカーが設定されています。マップをクリックしてマーカを一つにしてください。</span>';
		document.getElementById('Msg').style.display = 'block';
		console.log('1111');
		e.preventDefault();
	} else {
		document.getElementById('Msg').innerHTML = '';
		document.getElementById('Msg').style.display = 'none';
		console.log('2222');
	}
}




