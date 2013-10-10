package model;

import config.ConfigManager;

public class TicketCategoryFactory {

	public static AbstractTicketCategory getTicketCategory (String ticketCategory, String name, int numberInitialTickets, int numberSoldTickets, float price) {
		if (ticketCategory.contains(ConfigManager.RESERVED_TICKET)) {
			
			return new ReservedTicketCategory(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
		else {
			return new GeneralAdmissionTicketCategory(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
	}
}
