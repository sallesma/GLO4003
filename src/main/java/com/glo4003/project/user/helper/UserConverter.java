package com.glo4003.project.user.helper;

import org.springframework.stereotype.Component;

import com.glo4003.project.database.model.UserModel;
import com.glo4003.project.user.model.UserConcreteModel;
import com.glo4003.project.user.model.view.UserViewModel;

@Component
public class UserConverter {
	
	public UserViewModel convertToView(UserConcreteModel entry) {
		UserViewModel viewModel = new UserViewModel();
		viewModel.setId(entry.getId());
		viewModel.setAddress(entry.getAddress());
		viewModel.setFirstName(entry.getFirstName());
		viewModel.setLastName(entry.getLastName());
		viewModel.setPassword(entry.getPassword());
		viewModel.setPhoneNumber(entry.getPhoneNumber());
		viewModel.setUsername(entry.getUsername());
		viewModel.setIsAdmin(entry.isAdmin());
		viewModel.setTickets(entry.getTickets());
		viewModel.setSearchCriteria(entry.getSearchCriteria());
		return viewModel;
	}
	
	public UserConcreteModel convertFromView(UserViewModel userViewModel) {
		UserConcreteModel userConcreteModel = new UserConcreteModel();
		userConcreteModel.setId(userViewModel.getId());
		userConcreteModel.setAddress(userViewModel.getAddress());
		userConcreteModel.setFirstName(userViewModel.getFirstName());
		userConcreteModel.setLastName(userViewModel.getLastName());
		userConcreteModel.setPassword(userViewModel.getPassword());
		userConcreteModel.setPhoneNumber(userViewModel.getPhoneNumber());
		userConcreteModel.setUsername(userViewModel.getUsername());
		userConcreteModel.setIsAdmin(userViewModel.isAdmin());
		userConcreteModel.setTickets(userViewModel.getTickets());
		userConcreteModel.setSearchCriterias(userViewModel.getSearchCriteria());
		return userConcreteModel;
	}
	
	public UserConcreteModel convertFromDB(UserModel entry) {
		UserConcreteModel userConcreteModel = new UserConcreteModel();
		userConcreteModel.setId(entry.getId());
		userConcreteModel.setAddress(entry.getAddress());
		userConcreteModel.setFirstName(entry.getFirstName());
		userConcreteModel.setLastName(entry.getLastName());
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
		userModel.setPassword(userConcreteModel.getPassword());
		userModel.setPhoneNumber(userConcreteModel.getPhoneNumber());
		userModel.setUsername(userConcreteModel.getUsername());
		userModel.setIsAdmin(userConcreteModel.isAdmin());
		userModel.setTickets(userConcreteModel.getTickets());
		userModel.setSearchCriterias(userConcreteModel.getSearchCriteria());
		return userModel;
	}
}
