package helper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import model.UserModel;
import model.UserViewModel;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {
	
	public Collection<UserViewModel> convert(Map<Integer, UserModel> entries) {
		Collection<UserViewModel> viewModels = new LinkedList<UserViewModel>();
		for (Entry<Integer, UserModel> entry : entries.entrySet()) {
			UserViewModel viewModel = convert(entry.getValue());
			viewModel.setId(entry.getKey());
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public UserViewModel convert(UserModel entry) {
		UserViewModel viewModel = new UserViewModel();
		viewModel.setAddress(entry.getAddress());
		viewModel.setFirstName(entry.getFirstName());
		viewModel.setLastName(entry.getLastName());
		viewModel.setPassword(entry.getPassword());
		viewModel.setPhoneNumber(entry.getPhoneNumber());
		viewModel.setUsername(entry.getUsername());
		viewModel.setIsAdmin(entry.isAdmin());
		viewModel.setTickets(entry.getTickets());
		return viewModel;
	}
	
	public UserModel convert(UserViewModel userViewModel) {
		UserModel userModel = new UserModel();
		userModel.setAddress(userViewModel.getAddress());
		userModel.setFirstName(userViewModel.getFirstName());
		userModel.setLastName(userViewModel.getLastName());
		userModel.setPassword(userViewModel.getPassword());
		userModel.setPhoneNumber(userViewModel.getPhoneNumber());
		userModel.setUsername(userViewModel.getUsername());
		userModel.setIsAdmin(userViewModel.isAdmin());
		userModel.setTickets(userViewModel.getTickets());
		return userModel;
	}
}
