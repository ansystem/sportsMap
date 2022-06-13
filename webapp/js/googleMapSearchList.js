var mayMap;
var service;
var marker;
var markerList = [];
var registInfo;
// マップの初期設定
function initialize() {
    SearchGo();
    getSportsArea();
}

// 入力キーワードと表示範囲を設定
function SearchGo() {

    var initPos = new google.maps.LatLng(0,0);
    var mapOptions = {
        center : initPos,
        zoom: 15,
        mapTypeId : google.maps.MapTypeId.ROADMAP
    };
    
	mayMap = new google.maps.Map(document.getElementById("googleMap"), mapOptions);

    service = new google.maps.places.PlacesService(mayMap);
    // input要素に入力されたキーワードを検索の条件に設定
    // var myword = document.getElementById("search");
    var $prefecture = document.getElementById("prefecture");
    var $city = document.getElementById("city");
    var request = {
        query : $prefecture.value + $city.value,
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
    if (mayMap.getZoom() > 14) mayMap.setZoom(14); 
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
                document.getElementById("googleMap"), // マップを表示する要素
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
    let index = document.getElementsByName('index');
    let circleName = document.getElementsByName('circleName');
    let lat = document.getElementsByName('lat');
    let lng = document.getElementsByName('lng');

    for (i=0; i<lat.length; i++) {
        sportsArea(index[i].value, circleName[i].value, lat[i].value, lng[i].value);
    }
}


// 検索の結果を受け取る
function sportsArea(index,circleName,lat,lng) {
    var initPos = new google.maps.LatLng(lat, lng);
    var bounds = new google.maps.LatLngBounds();


    createMarker({
        position : initPos,
        text : circleName + '：<a href=\"CircleDetail?index=' + index +'\">サークル詳細</a>',
        map : mayMap
    });
    bounds.extend(initPos);
    // mayMap.fitBounds(bounds); //★なぜエラー？
}

// 該当する位置にマーカーを表示
function createMarker(options) {

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

    return marker;
}