package fr.lordkadoc.entities;

import fr.lordkadoc.map.Carte;
import fr.remygenius.armepoulet.BombeBasique;

public class Poulet extends Player {

	public Poulet(Carte carte, int x, int y) {
		super(carte, x, y, 30, "Poulet", new BombeBasique());
	}

}
