package org.noobs2d.contacts.data.clients.web.retrofit2.service;

import org.noobs2d.contacts.data.clients.web.retrofit2.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("/user")
    Observable<LoginResponse> login(@Field("username") String username, @Field("password") String password);
}
