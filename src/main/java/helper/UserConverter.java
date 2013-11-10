package helper;

import model.UserModel;
import model.UserViewModel;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	
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
		viewModel.setTickets(entry.getTickets());
		viewModel.setSearchCriteria(entry.getSearchCriteria());
		return viewModel;
	}
	
	public UserModel convert(UserViewModel userViewModel) {
		UserModel userModel = new UserModel();
		userModel.setId(userViewModel.getId());
		userModel.setAddress(userViewModel.getAddress());
		userModel.setFirstName(userViewModel.getFirstName());
		userModel.setLastName(userViewModel.getLastName());
		userModel.setPassword(userViewModel.getPassword());
		userModel.setPhoneNumber(userViewModel.getPhoneNumber());
		userModel.setUsername(userViewModel.getUsername());
		userModel.setIsAdmin(userViewModel.isAdmin());
		userModel.setTickets(userViewModel.getTickets());
		userModel.setSearchCriterias(userViewModel.getSearchCriteria());
		return userModel;
	}
}
