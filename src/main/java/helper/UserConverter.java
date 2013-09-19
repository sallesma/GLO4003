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
	
	public static Collection<UserViewModel> convert(Map<Integer, UserModel> entries) {
		Collection<UserViewModel> viewModels = new LinkedList<UserViewModel>();
		for (Entry<Integer, UserModel> entry : entries.entrySet()) {
			UserViewModel viewModel = convert(entry.getValue());
			viewModel.setId(entry.getKey());
			viewModels.add(viewModel);
		}
		return viewModels;
	}
	
	public static UserViewModel convert(UserModel entry) {
		UserViewModel viewModel = new UserViewModel();
		viewModel.setAddress(entry.getAddress());
		viewModel.setFirstName(entry.getFirstName());
		viewModel.setLastName(entry.getLastName());
		viewModel.setPassword(entry.getPassword());
		viewModel.setPhoneNumber(entry.getPhoneNumber());
		viewModel.setUsername(entry.getUsername());
		
		return viewModel;
	}
	
	public static UserModel convert(UserViewModel userViewModel) {
		UserModel viewModel = new UserModel();
		viewModel.setAddress(userViewModel.getAddress());
		viewModel.setFirstName(userViewModel.getFirstName());
		viewModel.setLastName(userViewModel.getLastName());
		viewModel.setPassword(userViewModel.getPassword());
		viewModel.setPhoneNumber(userViewModel.getPhoneNumber());
		viewModel.setUsername(userViewModel.getUsername());
		
		return viewModel;
	}
}
