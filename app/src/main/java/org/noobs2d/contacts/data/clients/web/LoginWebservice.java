package org.noobs2d.contacts.data.clients.web;

import org.noobs2d.contacts.data.clients.web.retrofit2.response.LoginResponse;

import io.reactivex.Observable;

public interface LoginWebservice {

    Observable<LoginResponse> login(String username, String password);
}
