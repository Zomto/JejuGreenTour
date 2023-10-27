var mapContainer = document.getElementById('map'); // 지도를 표시할 div
var mapOption = {
    center: new kakao.maps.LatLng(33.450701, 126.570667), // 초기 중심 좌표
    level: 3 // 초기 확대 레벨
};

var geocoder = new kakao.maps.services.Geocoder();

var addressElement = document.getElementById('accomAddr'); // 주소를 표시하는 HTML 요소
var address = addressElement.textContent; // HTML 요소의 텍스트를 가져옵니다

var nameElement = document.getElementById('accomName'); // 업소명을 표시하는 HTML 요소
var name = nameElement.textContent; // HTML 요소의 텍스트를 가져옵니다

// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        var latitude = coords.getLat(); // 위도 (latitude) 가져오기
        var longitude = coords.getLng(); // 경도 (longitude) 가져오기

        var map = new kakao.maps.Map(mapContainer, mapOption);

        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        var link = 'https://map.kakao.com/link/to/' + name + ',' + latitude + ',' + longitude;

        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="padding:5px;z-index:1;">' + name + '<a href="' + link + '" style="color:#03c75a" target="_blank">길찾기</a></div>'
        });
        infowindow.open(map, marker);

        map.setCenter(coords); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    }
});
