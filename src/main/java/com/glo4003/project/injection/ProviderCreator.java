package com.glo4003.project.injection;

import com.glo4003.project.user.model.view.LoginViewModel;
import com.glo4003.project.user.model.view.UserViewModel;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class ProviderCreator {
	@Inject
	public Provider<LoginViewModel> loginViewModelProvider;
	@Inject
	public Provider<UserViewModel> userViewModelProvider;
}
