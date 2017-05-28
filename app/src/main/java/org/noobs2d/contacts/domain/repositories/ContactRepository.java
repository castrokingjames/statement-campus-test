package org.noobs2d.contacts.domain.repositories;

import org.noobs2d.contacts.domain.entities.Contact;

import java.util.List;

import io.reactivex.Observable;

public interface ContactRepository {

    Observable<List<Contact>> loadContact(String token);
}