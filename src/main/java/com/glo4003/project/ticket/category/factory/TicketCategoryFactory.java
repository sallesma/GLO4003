package com.glo4003.project.ticket.category.factory;

import com.glo4003.project.database.dto.AbstractTicketCategory;
import com.glo4003.project.database.dto.GeneralAdmissionTicketCategoryDto;
import com.glo4003.project.database.dto.ReservedTicketCategoryDto;

public class TicketCategoryFactory {

	public static AbstractTicketCategory getTicketCategory (String ticketCategory, String name, int numberInitialTickets, int numberSoldTickets, float price) {
		if (ticketCategory.contains(AbstractTicketCategory.RESERVED_TICKET)) {
			
			return new ReservedTicketCategoryDto(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
		else {
			return new GeneralAdmissionTicketCategoryDto(ticketCategory,name, numberInitialTickets, numberSoldTickets, price);
		}
	}
}
