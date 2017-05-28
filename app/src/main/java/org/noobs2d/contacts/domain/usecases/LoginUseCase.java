package org.noobs2d.contacts.domain.usecases;

import org.noobs2d.contacts.domain.entities.User;
import org.noobs2d.contacts.domain.repositories.UserRepository;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoginUseCase {

    private Callback callback;
    private UserRepository userRepository;

    public LoginUseCase(Callback callback, UserRepository userRepository) {
        this.callback = callback;
        this.userRepository = userRepository;
    }

    public void login(String username, String password) {
        userRepository.login(username, password)
                .compose(this::applySchedulers)
                .subscribe(this::onLogin, this::onLoginError);
    }

    private void onLogin(User user) {
        callback.showContactScreen();
    }

    private void onLoginError(Throwable throwable) {
        callback.showErrorMessage(throwable.getMessage());
    }

    private <T> Observable<T> applySchedulers(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface Callback {

        void showContactScreen();

        void showErrorMessage(String message);
    }
}
