package fr.remygenius.thread;

import fr.remygenius.arme.Arme;

public class ThreadRecharge extends Thread {
	private int delai;
	private Arme arme;
	
	public ThreadRecharge(int delai, Arme arme){
		this.delai = delai;
		this.arme = arme;
	}
	
	@Override
	public void run(){
		try {
			Thread.sleep(delai*1000);
			arme.setRechargeTermine(true);
			System.out.println("TEEEEESSSSTTTTT");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
