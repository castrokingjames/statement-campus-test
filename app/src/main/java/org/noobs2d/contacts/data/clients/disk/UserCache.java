package org.noobs2d.contacts.data.clients.disk;

import org.noobs2d.contacts.domain.entities.User;

import io.reactivex.Observable;

public interface UserCache {

    Observable<User> save(User user);

    Observable<User> load();
}