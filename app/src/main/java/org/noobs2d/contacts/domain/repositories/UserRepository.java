package org.noobs2d.contacts.domain.repositories;

import org.noobs2d.contacts.domain.entities.User;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<User> login(String username, String password);

    Observable<User> loadToken();
}