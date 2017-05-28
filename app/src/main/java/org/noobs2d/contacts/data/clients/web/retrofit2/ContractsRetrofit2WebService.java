package org.noobs2d.contacts.data.clients.web.retrofit2;

import org.noobs2d.contacts.data.clients.web.ContactsWebService;
import org.noobs2d.contacts.data.clients.web.retrofit2.response.ContactsResponse;
import org.noobs2d.contacts.data.clients.web.retrofit2.service.ContactsService;

import java.util.List;

import io.reactivex.Observable;

public class ContractsRetrofit2WebService
        implements ContactsWebService {

    @Override
    public Observable<List<ContactsResponse>> get(String token) {
        return CWContacts.create(ContactsService.class).get(token);
    }
}
