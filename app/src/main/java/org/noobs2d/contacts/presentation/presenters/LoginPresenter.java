package org.noobs2d.contacts.presentation.presenters;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.noobs2d.contacts.domain.repositories.UserRepository;
import org.noobs2d.contacts.domain.usecases.LoginUseCase;
import org.noobs2d.contacts.presentation.contracts.LoginContract;

public class LoginPresenter
        extends MvpBasePresenter<LoginContract.View>
        implements LoginContract.Presenter, LoginUseCase.Callback {

    private LoginUseCase useCase;

    public LoginPresenter(UserRepository userRepository) {
        useCase = new LoginUseCase(this, userRepository);
    }

    @Override
    public void login(String username, String password) {
        LoginContract.View view = getView();
        view.showLoadingDialog();
        useCase.login(username, password);
    }

    @Override
    public void showContactScreen() {
        LoginContract.View view = getView();
        view.hideLoadingDialog();
        view.showContactScreen();
    }

    @Override
    public void showErrorMessage(String message) {
        LoginContract.View view = getView();
        view.hideLoadingDialog();
        view.showErrorMessage(message);
    }
}
