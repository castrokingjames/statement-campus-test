package org.noobs2d.contacts.data.clients.web;

import org.noobs2d.contacts.data.clients.web.retrofit2.response.ContactsResponse;

import java.util.List;

import io.reactivex.Observable;

public interface ContactsWebService {

    Observable<List<ContactsResponse>> get(String token);
}
