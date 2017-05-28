package org.noobs2d.contacts.presentation.contracts;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface LoginContract {

    interface View extends MvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showContactScreen();

        void showErrorMessage(String message);
    }

    interface Presenter extends MvpPresenter<View> {

        void login(String username, String password);
    }
}
