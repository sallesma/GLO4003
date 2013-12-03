package com.glo4003.project.user.helper;

import model.InstantiateGeneralAdmissionTicket;
import model.InstantiateReservedTicket;

import org.hamcrest.core.IsInstanceOf;
import org.springframework.stereotype.Component;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.viewModel.InstantiateGeneralAdmissionTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateReservedTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateTicketViewModel;
import com.glo4003.project.user.model.view.UserViewModel;

@Component
public class UserConverter {
	
	private InstantiateTicketConverter tConverter = new InstantiateTicketConverter();
	
	public UserViewModel convert(UserModel entry) {
		UserViewModel viewModel = new UserViewModel();
		viewModel.setId(entry.getId());
		viewModel.setAddress(entry.getAddress());
		viewModel.setFirstName(entry.getFirstName());
		viewModel.setLastName(entry.getLastName());
		viewModel.setPassword(entry.getPassword());
		viewModel.setPhoneNumber(entry.getPhoneNumber());
		viewModel.setUsername(entry.getUsername());
		viewModel.setIsAdmin(entry.isAdmin());
		for (InstantiateAbstractTicket ticket : entry.getTickets()) {
			
			if (ticket instanceof InstantiateGeneralAdmissionTicket) {
				InstantiateGeneralAdmissionTicketViewModel tVM = new InstantiateGeneralAdmissionTicketViewModel();
				InstantiateGeneralAdmissionTicket tGA = (InstantiateGeneralAdmissionTicket) ticket;
				tVM = tConverter.convert(tGA);
				viewModel.getTickets().add(tVM);
				System.out.println("Convert ticket UVM => UM GA: " + tVM.getCity());
			}
			else if (ticket instanceof InstantiateReservedTicket){
				InstantiateReservedTicketViewModel tVM = new InstantiateReservedTicketViewModel();
				InstantiateReservedTicket tR = (InstantiateReservedTicket)ticket;
				tVM = tConverter.convert(tR);
				viewModel.getTickets().add(tVM);
				System.out.println("Convert ticket UVM => UM R : " + tVM.getId());
			}
			
			
			
		}
		viewModel.setNbTickets(entry.getTickets().size());
		viewModel.setSearchCriteria(entry.getSearchCriteria());
		return viewModel;
	}
	
	public UserModel convert(UserViewModel userViewModel) throws PersistException {
		UserModel userModel = new UserModel();
		userModel.setId(userViewModel.getId());
		userModel.setAddress(userViewModel.getAddress());
		userModel.setFirstName(userViewModel.getFirstName());
		userModel.setLastName(userViewModel.getLastName());
		userModel.setPassword(userViewModel.getPassword());
		userModel.setPhoneNumber(userViewModel.getPhoneNumber());
		userModel.setUsername(userViewModel.getUsername());
		userModel.setIsAdmin(userViewModel.isAdmin());
		for (InstantiateTicketViewModel tVM : userViewModel.getTickets()) {
			InstantiateAbstractTicket ticket = null;
			if (tVM instanceof InstantiateGeneralAdmissionTicketViewModel) {
				InstantiateGeneralAdmissionTicketViewModel tGAVM = (InstantiateGeneralAdmissionTicketViewModel)tVM;
				ticket = tConverter.convert(tGAVM);
			} else if (tVM instanceof InstantiateReservedTicketViewModel) {
				InstantiateReservedTicketViewModel tRVM = (InstantiateReservedTicketViewModel)tVM;
				ticket = tConverter.convert(tRVM);
			}
			System.out.println("Convert ticket UM => UVM : " + ticket.getId());
			userModel.getTickets().add(ticket);
		}
		userModel.setSearchCriterias(userViewModel.getSearchCriteria());
		return userModel;
	}
}
