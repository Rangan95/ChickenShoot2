package fr.lordkadoc.entities;

import fr.lordkadoc.entities.Player;
import fr.remygenius.armepoulet.BombeBasique;

public class Poulet extends Player {

	public Poulet(int x, int y) {
		super(x, y, 30, "Poulet", new BombeBasique());
	}

}
