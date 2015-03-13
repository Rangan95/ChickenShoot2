package fr.remygenius.armechasseur;

import fr.lordkadoc.launcher.Server;
import fr.remygenius.arme.Arme;
import fr.remygenius.thread.ThreadRecharge;

/**
 * Classe qui définit les attributs qu'a une arme de chasseur
 * @author remy
 *
 */

public class ArmeChasseur extends Arme {
	private int vitesseBalle;

	public ArmeChasseur(String nom, int degat, int tempsDeRecharge, int munitions, int vitesseBalle) {
		super(nom, degat, tempsDeRecharge, munitions);
		this.vitesseBalle = vitesseBalle;
	}
	
	public void tirer(int x1, int y1, int x2, int y2){
		double vx = x2-x1;
		double vy = y2-y1;
		int d = (int)Math.sqrt(vx*vx+vy*vy);
		vx = vx/d;
		vy = vy/d;
		vx = vx*vitesseBalle;
		vy = vy*vitesseBalle;
		this.setMunitions(this.getMunitions()-1);
		
		Server.serverInstance.getCarte().ajouterBalle(new Balle(x1, y1, vx, vy, this.getDegat()));
		new ThreadRecharge(this.getTempsDeRecharge(), this).start();
	}
	
	public int getVitesseBalle() {
		return vitesseBalle;
	}

	public void setVitesseBalle(int vitesseBalle) {
		this.vitesseBalle = vitesseBalle;
	}
	
	/**
	 * Inutiliser par le chasseur
	 */
	@Override
	public void poser(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
