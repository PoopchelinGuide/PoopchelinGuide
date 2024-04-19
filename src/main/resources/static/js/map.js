function initTmap(){
    var map = new Tmapv3.Map("map_div",
        {
            center: new Tmapv3.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
            width: "400px",
            height: "780px",
            zoom: 18
        });
}

function handleMapClick() {
    if (!window.map) {
        initTmap(); // 지도가 초기화되지 않았다면 초기화
    }
    document.getElementById('map_div').style.display = 'block'; // 지도 표시
    document.getElementById('map_div').style.backgroundColor = ''; // 배경색 제거
}

function handleHomeClick() {
    var mapDiv = document.getElementById('map_div');
    mapDiv.innerHTML = ''; // 내부 내용 삭제
    mapDiv.style.backgroundColor = 'white'; // 배경을 흰색으로 설정
    mapDiv.style.display = 'block'; // 화면에 표시
}

function handleUserClick() {
    var mapDiv = document.getElementById('map_div');
    mapDiv.innerHTML = ''; // 내부 내용 삭제
    mapDiv.style.backgroundColor = 'grey'; // 배경을 회색으로 설정
    mapDiv.style.display = 'block'; // 화면에 표시
}