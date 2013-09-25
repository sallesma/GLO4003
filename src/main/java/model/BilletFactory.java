package model;

import config.ConfigManager;

public class BilletFactory {

	public static BilletCategory getBillet (String cat, String name, int nbInitialTickets, int nbSoldTickets, float price) {
		if (cat.contains(ConfigManager.BILLET_RESERVE)) {
			
			return new BilletSiegeReserve(cat,name, nbInitialTickets, nbSoldTickets, price);
		}
		else {
			return new BilletAdmissionGenerale(cat,name, nbInitialTickets, nbSoldTickets, price);
		}
	}
}
