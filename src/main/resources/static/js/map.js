

function initTmap(){
    var map = new Tmapv3.Map("map_div",
        {
            center: new Tmapv3.LatLng(37.566481622437934,126.98502302169841), // 지도 초기 좌표
            position : "fixed",
            width: "400px",
            height: "780px",
            overflowY: "hidden",
            zoom: 18
        });
}