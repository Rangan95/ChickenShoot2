var movement = {};
movement.north = false;
movement.south = false;
movement.west = false;
movement.east = false;
var tir = {};
tir.on = false;
tir.x;
tir.y;

var souris = {};
souris.x = 0;
souris.y = 0;

function press(event){
	switch(event.keyCode){
	case 68: // droite
		movement.east = true;
		break;
	case 81: // gauche
		movement.west = true;
		break;
	case 83: // bas
		movement.south = true;
		break;
	case 90: // haut
		movement.north = true;
		break;
	}
}

function clique(event){
	var x = event.pageY - canvas.offsetTop;
	var y = event.pageX - canvas.offsetLeft;
	tir.on = true;
	tir.x = x;
	tir.y = y;
}

function release(event){
	switch(event.keyCode){
	case 68: // droite = d
		movement.east = false;
		break;
	case 81: // gauche = q
		movement.west = false;
		break;
	case 83: // bas = s
		movement.south = false;
		break;
	case 90: // haut = z
		movement.north = false;
		break;
	}
}

function motion(event){
	var x = event.pageX - canvas.offsetLeft;
	var y = event.pageY - canvas.offsetTop;
	souris.x = x;
	souris.y = y;
}
