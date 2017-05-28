package org.noobs2d.contacts.data.clients.web.retrofit2.service;

import org.noobs2d.contacts.data.clients.web.retrofit2.response.ContactsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ContactsService {

    @GET("/contacts")
    Observable<List<ContactsResponse>> get(@Header("token") String token);
}
