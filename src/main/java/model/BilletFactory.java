package model;

import config.ConfigManager;

public class BilletFactory {

	public static BilletCategory getBillet (String cat, int nbInitialTickets, int nbSoldTickets, float price) {
		if (cat.contains(ConfigManager.BILLET_RESERVE)) {
			
			return new BilletSiegeReserve("Billet siège réservé", nbInitialTickets, nbSoldTickets, price);
		}
		else {
			return new BilletAdmissionGenerale("Billet Placement libre", nbInitialTickets, nbSoldTickets, price);
		}
	}
}
