package fr.lordkadoc.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import fr.lordkadoc.entities.Player;
import fr.remygenius.armechasseur.Balle;
import fr.remygenius.armepoulet.Bombe;
import fr.remygenius.armepoulet.Explosion;

public class Carte {
	
	public List<Explosion> getExplosions() {
		return explosions;
	}

	public void setExplosions(List<Explosion> explosions) {
		this.explosions = explosions;
	}

	private int[][] positions;
	private List<Player> players;
	private List<Balle> balles;
	private List<Bombe> bombes;
	private List<Explosion> explosions;
	
	public Carte(){
		this.init(20);
	}
	
	private void init(int size){
		this.positions = new int[size][size];
		this.players = new ArrayList<Player>();
		this.balles = new ArrayList<Balle>();
		this.bombes = new ArrayList<Bombe>();
		this.explosions = new ArrayList<Explosion>();
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(i==0 || i==size-1 || j==0 || j==size-1){
					positions[i][j] = 2;
				}else{	
					if(Math.random()*100 > 95){
						positions[i][j] = 3;
					}else{
						positions[i][j] = 1;						
					}
				}
			}
		}
	}

	public int[][] getPositions() {
		return positions;
	}

	public void setPositions(int[][] positions) {
		this.positions = positions;
	}
	
	public List<Player> getPlayers(){
		return this.players;
	}
	
	public void ajouterBalle(Balle balle){
		this.balles.add(balle);
	}
	
	public void ajouterBombe(Bombe bombe){
		this.bombes.add(bombe);
	}
	
	public void ajouterExplosions(Explosion explosion){
		this.explosions.add(explosion);
	}
	
	public JsonObjectBuilder getJSon(){
		
		JsonObjectBuilder carte = Json.createObjectBuilder();
		JsonArrayBuilder mapBuilder = Json.createArrayBuilder();
		JsonArrayBuilder playerBuilder = Json.createArrayBuilder();
		JsonArrayBuilder balleBuilder = Json.createArrayBuilder();
		JsonArrayBuilder bombeBuilder = Json.createArrayBuilder();
		JsonArrayBuilder explosionBuilder = Json.createArrayBuilder();
		
		JsonArrayBuilder line;
		
		for(int i=0;i<positions.length;i++){ // Crée le tableau de positions
			line = Json.createArrayBuilder();
			for(int j=0;j<positions[i].length;j++){
				line.add(positions[i][j]);
			}
			mapBuilder.add(line);
		}
		for(Player p : players){ // Crée les joueurs
			playerBuilder.add(Json.createObjectBuilder()
					.add("type",p.getType())
					.add("x", p.getX())
					.add("y", p.getY())
					.add("vieInitiale", p.getVieInitiale())
					.add("vie", p.getVie())
					.add("angle", p.getAngle())
					.add("arme", p.getArme().getNom()));
		}
		List<Balle> tmp = new ArrayList<Balle>();
		tmp.addAll(balles);
		for(Balle b : tmp){
			balleBuilder.add(Json.createObjectBuilder()
					.add("x", b.getX())
					.add("y", b.getY())
					.add("angle", b.getAngle()));
		}
		List<Bombe> tmpBombe = new ArrayList<Bombe>();
		tmpBombe.addAll(bombes);
		for(Bombe b : tmpBombe){
			bombeBuilder.add(Json.createObjectBuilder()
					.add("x", b.getX())
					.add("y", b.getY()));
		}
		List<Explosion> tmpExplosion = new ArrayList<Explosion>();
		tmpExplosion.addAll(explosions);
		for(Explosion e : tmpExplosion){
			explosionBuilder.add(Json.createObjectBuilder()
					.add("x", e.getX())
					.add("y", e.getY())
					.add("taille", e.getTailleActu()));
		}
		carte.add("carte", mapBuilder);
		carte.add("players", playerBuilder);
		carte.add("balles", balleBuilder);
		carte.add("bombes", bombeBuilder);
		carte.add("explosions", explosionBuilder);
		return carte;
	}
	
	
	public void deplacer(boolean[] c, Player player){
		
		List<Point> points = null;
		int x = 0;
		int y = 0;
				
		if(c[0]){ // north
			x = 0;
			y = -8;
			points = player.hitboxPoints("NORTH");
			calculerDeplacement("NORTH",x,y,points,player);
		}
		if(c[1]){ //south
			x = 0;
			y = 8;
			points = player.hitboxPoints("SOUTH");
			calculerDeplacement("SOUTH",x,y,points,player);
		}
		if(c[2]){ // west
			x = -8;
			y = 0;
			points = player.hitboxPoints("WEST");
			calculerDeplacement("WEST",x,y,points,player);
		}
		if(c[3]){ // east
			x = 8;
			y = 0;
			points = player.hitboxPoints("EAST");
			calculerDeplacement("EAST",x,y,points,player);
		}
			
	}
		
	private void calculerDeplacement(String direction, int x, int y, List<Point> points,Player player) {
		Point p1 = new Point(points.get(0).x+x,points.get(0).y+y);
		Point p2 = new Point(points.get(1).x+x,points.get(1).y+y);
		
		if(estVide(cellule(p1)) && estVide(cellule(p2))){
			player.setX(player.getX()+x);
			player.setY(player.getY()+y);
		}else{
			if(direction.equals("NORTH")){
				if(estVide(cellule(p1))){
					player.setY((p1.y/32+1)*32+1+player.getSize()/2);				
				}else{
					player.setY((p2.y/32+1)*32+1+player.getSize()/2);		
				}
			}else if(direction.equals("SOUTH")){
				if(estVide(cellule(p1))){
					player.setY(p1.y/32*32-player.getSize()/2-1);				
				}else{
					player.setY(p2.y/32*32-player.getSize()/2-1);	
				}
			}else if(direction.equals("WEST")){
				if(estVide(cellule(p1))){
					player.setX((p1.x/32+1)*32+1+player.getSize()/2);
				}else{
					player.setX((p2.x/32+1)*32+1+player.getSize()/2);
				}
			}else if(direction.equals("EAST")){
				if(estVide(cellule(p1))){
					player.setX(p1.x/32*32-player.getSize()/2-1);
				}else{
					player.setX(p2.x/32*32-player.getSize()/2-1);
				}
			}
		}
	}
	
	public int getNbPoulets(){
		int i =0;
		for(Player p : players){
			if(p.getType() =="Poulet"){
				i++;
			}
		}
		return i;
	}
	
	public int getNbChasseurs(){
		int i =0;
		for(Player p : players){
			if(p.getType() =="Chasseur"){
				i++;
			}
		}
		return i;
	}

	public int cellule(Point p){
		return this.positions[p.x/32][p.y/32];
	}
	
	public boolean estVide(int cellule){
		return cellule == 1;
	}

	public List<Balle> getBalles() {
		return balles;
	}

	public void setBalles(List<Balle> balles) {
		this.balles = balles;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Bombe> getBombes() {
		return bombes;
	}

	public void setBombes(List<Bombe> bombes) {
		this.bombes = bombes;
	}
}
