var imgWall = new Image();
var imgGrass = new Image();
var imgPoulet = new Image();
var imgChasseur = new Image();
var imgTree = new Image();
var imgFleche = new Image();
var imgBombe = new Image();

imgWall.src = "res/wall.png";
imgGrass.src = "res/grass.png";
imgPoulet.src = "res/poulet.png";
imgChasseur.src = "res/joueur.png";
imgTree.src = "res/tree.png";
imgFleche.src = "res/fleche.png";
imgBombe.src = "res/bombe.png";

imgWall.onload = function(){
	console.log("Image de mur chargée");
}

imgGrass.onload = function(){
	console.log("Image d'herbe chargée");
}

imgPoulet.onload = function(){
	console.log("Image du poulet chargée");
}

imgChasseur.onload = function(){
	console.log("Image du chasseur chargée");
}

imgTree.onload = function(){
	console.log("Image de l'arbre chargée");
}

imgFleche.onload = function(){
	console.log("Image de la balle chargée");
}

imgBombe.onload = function(){
	console.log("Image de la bombe chargée");
}