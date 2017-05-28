package org.noobs2d.contacts.data.managers;

import org.noobs2d.contacts.data.clients.disk.UserCache;
import org.noobs2d.contacts.data.clients.web.LoginWebservice;
import org.noobs2d.contacts.data.clients.web.retrofit2.response.LoginResponse;
import org.noobs2d.contacts.domain.entities.User;
import org.noobs2d.contacts.domain.repositories.UserRepository;


import io.reactivex.Observable;

public class UserManager
        implements UserRepository {

    private LoginWebservice loginWebservice;
    private UserCache userCache;

    public UserManager(LoginWebservice loginWebservice, UserCache userCache) {
        this.loginWebservice = loginWebservice;
        this.userCache = userCache;
    }

    @Override
    public Observable<User> login(String username, String password) {
        return loginWebservice.login(username, password).flatMap(this::transform).flatMap(this::save);
    }

    @Override
    public Observable<User> loadToken() {
        return userCache.load();
    }

    private Observable<User> transform(LoginResponse response) {
        User user = new User();
        user.token = response.token;
        return Observable.just(user);
    }

    private Observable<User> save(User user) {
        return userCache.save(user);
    }
}
