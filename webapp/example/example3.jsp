<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head> 
    <title>Google Maps V3</title> 
    <meta http-equiv="content-type" content="text/html; charset=utf-8" /> 
    
     
    <script type="text/javascript"> 
 
    function initialize() {
        google.maps.event.addDomListener(window, 'load', function() { 
            var markerObj; 
            var mapObj; 
            var lng = 139.90744471549988; 
            var lat = 35.7399986893804; 
            var latlng = new google.maps.LatLng(lat, lng); 
 
            var mapOptions = { 
                zoom: 12, 
                center: latlng, 
                mapTypeId: google.maps.MapTypeId.ROADMAP, 
                scaleControl: true 
            }; 
            mapObj = new google.maps.Map(document.getElementById('gmap'), mapOptions); 
 
            markerObj = new google.maps.Marker({ 
                position: latlng, 
                map: mapObj 
            }); 
 
            // マップクリックイベントを追加 
            google.maps.event.addListener(mapObj, 'click', function(e) { 
            	console.log('通過しちょ' +  e.latLng);
                // ポジションを変更 
                markerObj.position = e.latLng; 
              
                markerObj.setMap(null);

				markerObj = new google.maps.Marker({ 
					position: e.latLng, 
					map: mapObj 
				}); 
				
// 				// マーカーをセット 
//                 markerObj.setMap(mapObj); 
            }); 
        }); 
     }  
    </script> 
    
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAZ4kY_eZsLMezpzKp9ghUSaUoZg-aKknU&libraries=places&callback=initialize"></script>
</head> 
<body> 
<div id="gmap" style="width: 99%; height: 370px; border: 1px solid Gray;"> 
</div> 
</body> 
</html> 