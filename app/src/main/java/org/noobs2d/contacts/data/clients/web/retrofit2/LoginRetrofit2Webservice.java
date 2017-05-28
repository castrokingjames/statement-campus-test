package org.noobs2d.contacts.data.clients.web.retrofit2;

import org.noobs2d.contacts.data.clients.web.LoginWebservice;
import org.noobs2d.contacts.data.clients.web.retrofit2.response.LoginResponse;
import org.noobs2d.contacts.data.clients.web.retrofit2.service.LoginService;

import io.reactivex.Observable;

public class LoginRetrofit2Webservice
        implements LoginWebservice {

    @Override
    public Observable<LoginResponse> login(String username, String password) {
        return CWContacts.create(LoginService.class).login(username, password);
    }
}