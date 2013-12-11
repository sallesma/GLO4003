package com.glo4003.project.user.helper;


import org.springframework.stereotype.Component;

import com.glo4003.project.database.exception.PersistException;
import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.ticket.helper.InstantiateTicketConverter;
import com.glo4003.project.ticket.model.InstantiateAbstractTicket;
import com.glo4003.project.ticket.model.InstantiateGeneralAdmissionTicket;
import com.glo4003.project.ticket.model.InstantiateReservedTicket;
import com.glo4003.project.ticket.viewModel.InstantiateGeneralAdmissionTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateReservedTicketViewModel;
import com.glo4003.project.ticket.viewModel.InstantiateTicketViewModel;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

@Component
public class UserConverter {
	
	private InstantiateTicketConverter tConverter = new InstantiateTicketConverter();
	
	public UserViewModel convertToView(UserConcreteModel entry) {

		UserViewModel viewModel = new UserViewModel();
		viewModel.setId(entry.getId());
		viewModel.setAddress(entry.getAddress());
		viewModel.setEmail(entry.getEmail());
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
			}
			else if (ticket instanceof InstantiateReservedTicket){
				InstantiateReservedTicketViewModel tVM = new InstantiateReservedTicketViewModel();
				InstantiateReservedTicket tR = (InstantiateReservedTicket)ticket;
				tVM = tConverter.convert(tR);
				viewModel.getTickets().add(tVM);
			}
			
			
			
		}
		viewModel.setNbTickets(entry.getTickets().size());
		viewModel.setSearchCriteria(entry.getSearchCriteria());
		return viewModel;
	}
	

	public UserConcreteModel convertFromView(UserViewModel userViewModel) throws PersistException {
		UserConcreteModel userModel = new UserConcreteModel();
		userModel.setId(userViewModel.getId());
		userModel.setAddress(userViewModel.getAddress());
		userModel.setFirstName(userViewModel.getFirstName());
		userModel.setLastName(userViewModel.getLastName());
		userModel.setEmail(userViewModel.getEmail());
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
			userModel.getTickets().add(ticket);
		}
		userModel.setSearchCriterias(userViewModel.getSearchCriteria());
		
		return userModel;
}

	
	public UserConcreteModel convertFromDB(UserModel entry) {
		UserConcreteModel userConcreteModel = new UserConcreteModel();
		userConcreteModel.setId(entry.getId());
		userConcreteModel.setAddress(entry.getAddress());
		userConcreteModel.setFirstName(entry.getFirstName());
		userConcreteModel.setLastName(entry.getLastName());
		userConcreteModel.setEmail(entry.getEmail());
		userConcreteModel.setPassword(entry.getPassword());
		userConcreteModel.setPhoneNumber(entry.getPhoneNumber());
		userConcreteModel.setUsername(entry.getUsername());
		userConcreteModel.setIsAdmin(entry.isAdmin());
		userConcreteModel.setTickets(entry.getTickets());
		userConcreteModel.setSearchCriterias(entry.getSearchCriteria());
		return userConcreteModel;
	}
	
	public UserModel convertToDB(UserConcreteModel userConcreteModel) {
		UserModel userModel = new UserModel();
		userModel.setId(userConcreteModel.getId());
		userModel.setAddress(userConcreteModel.getAddress());
		userModel.setFirstName(userConcreteModel.getFirstName());
		userModel.setLastName(userConcreteModel.getLastName());
		userModel.setEmail(userConcreteModel.getEmail());
		userModel.setPassword(userConcreteModel.getPassword());
		userModel.setPhoneNumber(userConcreteModel.getPhoneNumber());
		userModel.setUsername(userConcreteModel.getUsername());
		userModel.setIsAdmin(userConcreteModel.isAdmin());
		userModel.setTickets(userConcreteModel.getTickets());
		userModel.setSearchCriterias(userConcreteModel.getSearchCriteria());
		return userModel;
	}
}

