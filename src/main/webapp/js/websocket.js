var websocket;

var connect = function(){
	websocket = new WebSocket("ws://localhost:7777");
	websocket.onopen = function(message){ processOpen(message);};
	websocket.onclose = function(message){ processClose(message);};
	websocket.onmessage = function(message){ processMessage(message);};
};

var processOpen = function(message){
	setInterval("envoiPersoUpdate()", 50);
}

var processClose = function(message){
}

var processError = function(message){
}

var processMessage = function(message){
	var json = JSON.parse(message.data);
	if(json.type == "Carte"){
		afficherCarte(json);
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