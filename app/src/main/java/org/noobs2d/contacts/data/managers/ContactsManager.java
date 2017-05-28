package org.noobs2d.contacts.data.managers;

import org.noobs2d.contacts.data.clients.web.ContactsWebService;
import org.noobs2d.contacts.data.clients.web.retrofit2.response.ContactsResponse;
import org.noobs2d.contacts.domain.entities.Contact;
import org.noobs2d.contacts.domain.repositories.ContactRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class ContactsManager
        implements ContactRepository {

    private ContactsWebService contactsWebService;

    public ContactsManager(ContactsWebService contactsWebService) {
        this.contactsWebService = contactsWebService;
    }

    @Override
    public Observable<List<Contact>> loadContact(String token) {
        return contactsWebService.get(token).flatMap(this::transform);
    }

    private Observable<List<Contact>> transform(List<ContactsResponse> responses) {
        List<Contact> contacts = new ArrayList<>();
        for (ContactsResponse response : responses) {
            Contact contact = new Contact(response.id, response.name);
            contact.phones.addAll(response.phones);
            contacts.add(contact);
        }
        return Observable.just(contacts);
    }
}
