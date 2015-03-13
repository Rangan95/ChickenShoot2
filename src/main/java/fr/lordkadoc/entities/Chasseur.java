package fr.lordkadoc.entities;

import fr.lordkadoc.map.Carte;
import fr.remygenius.armechasseur.Arbalete;

public class Chasseur extends Player {

	public Chasseur(Carte carte, int x, int y) {
		super(carte, x, y, 32, "Chasseur", new Arbalete());
	}
}
