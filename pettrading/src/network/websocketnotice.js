export function createwebsocketnotice(uid) {
  let websocket = null;
  //判断当前浏览器是否支持WebSocket
  if ("WebSocket" in window) {
    // websocket = new WebSocket("ws://localhost:8081/message/" + uid);
    websocket = new WebSocket(`ws://${window.location.host}/api/message/` + uid);
  } else {
    alert("不支持建立socket连接");
  }
  return websocket;
}
