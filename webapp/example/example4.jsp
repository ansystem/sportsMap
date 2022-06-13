<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="aspect">
		<div id="gmap_canvas" class="g_canvas"></div>
		<span id="ver" style="font-size: 0.8em; color: gray;"></span>
	</div>
	
	<style type="text/css">
		.aspect{
			width:100%;
			height:50vw;
		}
		.aspect_normal,
		.aspect_dot,
		.aspect_key,
		.aspect_dotkey{
			width:100%;
			height:25vw;
		}
		#gmap_canvas,
		#gmap_canvas_normal,
		#gmap_canvas_dot,
		#gmap_canvas_key,
		#gmap_canvas_dotkey,
		div.g_canvas{
			width:100%;
			height:100%;
		}
	</style>
	
	<button class ="gmap_btn"  id="drop" onclick="dropMakers();"/>マーカーを全て表示する</button>
	<button class ="gmap_btn"  id="del" onclick="deleteMakers();"/>マーカーを全て削除</button>

	<script type="text/javascript">
		//global
		var markers = [];
		var gmap_canvas;
		var map_div = document.getElementById("gmap_canvas");
		var points = [
				{location:"姫路城", latlng:{lat: 34.839450, lng:134.693903}},
				{location:"大手門（姫路城）", latlng:{lat:34.83605 , lng:134.6931199}},
				{location:"菱の門（姫路城）", latlng:{lat:34.8386654 , lng:134.6924566}},
				{location:"いの門（姫路城）", latlng:{lat:34.839149 , lng:134.692505}},
				{location:"ろの門（姫路城）", latlng:{lat:34.8393545 , lng:134.6927349}},
				{location:"はの門（姫路城）", latlng:{lat:34.8395619 , lng:134.6930339}},
				{location:"にの門（姫路城）", latlng:{lat:34.8396451 , lng:134.693265}},
				{location:"ほの門（姫路城）", latlng:{lat:34.839660 , lng:134.693602}},
				{location:"への門（姫路城）", latlng:{lat:34.839550 , lng:134.694236}},
				{location:"との一門（姫路城）", latlng:{lat:34.839373 , lng:134.694409}},
				{location:"との二門（姫路城）", latlng:{lat:34.839411 , lng:134.694480}},
				{location:"との四門（姫路城）", latlng:{lat:34.839064 , lng:134.694884}},
				{location:"ちの門（姫路城）", latlng:{lat:34.839325 , lng:134.694375}},
				{location:"りの門（姫路城）", latlng:{lat:34.838754 , lng:134.694153}},
				{location:"ぬの門（姫路城）", latlng:{lat:34.8388135 , lng:134.6934537}},
				{location:"るの門（姫路城）", latlng:{lat:34.838744 , lng:134.693244}}
				];
		var lcnt = points.length-1;
		//地図を描画
		function init(){
			gmap_canvas = new google.maps.Map(map_div,{
				center: points[0].latlng,
				zoom : 17,
				mapTypeId : google.maps.MapTypeId.ROADMAP
			});
			dropMakers();
		}
		//マーカーの生成と表示
		function create_marker(options){
			var m =  new google.maps.Marker(options);
			return m;
		}
		//マーカーを落とす
		function dropMakers(){
			//既にあるマーカーを一旦削除
			deleteMakers();
			//新たに生成
			var i=0;
			for	 (i=0; i < lcnt; i++){
				marker = create_marker({
					map: gmap_canvas,
					position: points[i].latlng,
					title:points[i].location
				});
				//ここで生成したマーカーを順次格納する
				markers.push(marker);
			}
		}
		//マーカーを削除する
		function deleteMakers(idx=null) {
			if(idx == null){
				//生成済マーカーを順次すべて削除する
				for (var i = 0; i < markers.length; i++) {
						markers[i].setMap(null);
				}
			  	markers = [];	//参照を開放
			}else{
				//生成済マーカーからID指定されたマーカーを削除
				for (var i = 0; i < markers.length; i++) {
					if(idx.indexOf(i) >= 0){
						markers[i].setMap(null);
					}
				}
			}
		}
	
		$('#ver').text(google.maps.version);
	</script>

	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=init"></script>

</body>
</html>