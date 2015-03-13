var websocket;

var connect = function(){
	websocket = new WebSocket("ws://localhost:7777");
	websocket.onopen = function(message){ joinGame(message);};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
};

var create = function(){
	websocket = new WebSocket("ws://localhost:7777");
	websocket.onopen = function(message){ createGame(message);};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
};

var joinGame = function(message){
	websocket.send("join");
}

var createGame = function(message){
	websocket.send("create");
}

var processClose = function(message){
	console.log(message);
}

var processError = function(message){
	alert(message);
}

var processMessage = function(message){
	var json = JSON.parse(message.data);
	if(json.type == "Carte"){
		afficherCarte(json);
	}else if(json.type == "erreur"){
		alert(json.data);
	}else if(json.type == "start"){
		setInterval("envoiPersoUpdate()",50);
	}
}

function envoiPersoUpdate(){
	var msg = {};
	msg.type = "playerUpdate";
	msg.movement = movement;
	msg.tir = tir;
	msg.souris = souris;
	var json = JSON.stringify(msg);
	websocket.send(json);
	tir.on = false;
}