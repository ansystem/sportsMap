<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
		#map {
			height: 100%;
		}
		html,body {
			height: 100%;
			margin: 0;
			padding: 0;
		}
	</style>
	
	<div id="map"></div>
	
	<script>
	function initMap() {
	    var map = new google.maps.Map(document.getElementById('map'), {
	        zoom: 14,
	        center: {lat: 35.665498, lng: 139.75964}
	    });

	    var marker = new google.maps.Marker({
	    	  position: {lat: 35.665498, lng: 139.75964},
	    	  map: map
	    });
	    
	    var box = '<div class="box">' +
	    			'<a href="http://qstudy.info:8080/qStudy/">別サイト</a>' +
	    			'</div>'
	    
		var infowindow = new google.maps.InfoWindow({
		    content: box
		});
		infowindow.open(map, marker);
	}
	var locations = [
	        {lat: 35.665498, lng: 139.75964},
	        {lat: 35.681382, lng: 139.76608399999998},
	        {lat: 35.675069, lng: 139.763328}
	]
	

	</script>
	
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&callback=initMap"></script>

</body>
</html>