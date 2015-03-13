package fr.remygenius.thread;

import java.util.ArrayList;
import java.util.List;

import fr.lordkadoc.launcher.Server;
import fr.remygenius.armepoulet.Bombe;
import fr.remygenius.armepoulet.Explosion;

public class ThreadBombe extends Thread {
	private List<Bombe> bombes;
	private int delai;
	
	public ThreadBombe(List<Bombe> bombes){
		this.bombes = bombes;
		this.delai = 20;
	}
	
	@Override
	public void run(){
		
		while(true){
					
			try {
				Thread.sleep(delai);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			List<Bombe> tmp = new ArrayList<Bombe>();
			List<Bombe> tmp2 = new ArrayList<Bombe>();
			tmp.addAll(bombes);
			tmp2.addAll(bombes);
			for(Bombe b : tmp){
				if(b.verifierTempsSurCarte()){
					tmp2.remove(b);
					Server.serverInstance.getCarte().ajouterExplosions(new Explosion(b.getX(), b.getY(), b.getRayonExplosion()));
				}
			}
			bombes.clear();
			bombes.addAll(tmp2);
			
		}
		
	}
}
