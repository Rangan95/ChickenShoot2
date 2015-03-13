package fr.lordkadoc.entities;

import fr.lordkadoc.entities.Player;
import fr.remygenius.armechasseur.Arbalete;

public class Chasseur extends Player {

	public Chasseur(int x, int y) {
		super(x, y, 32, "Chasseur", new Arbalete());
	}
}
