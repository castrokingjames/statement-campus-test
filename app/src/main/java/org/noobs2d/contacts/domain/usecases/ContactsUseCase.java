package org.noobs2d.contacts.domain.usecases;

import org.noobs2d.contacts.domain.entities.Contact;
import org.noobs2d.contacts.domain.repositories.ContactRepository;
import org.noobs2d.contacts.domain.repositories.UserRepository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactsUseCase {

    private Callback callback;
    private UserRepository userRepository;
    private ContactRepository contactRepository;

    public ContactsUseCase(Callback callback, UserRepository userRepository, ContactRepository contactRepository) {
        this.callback = callback;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
    }


    public void load() {
        userRepository.loadToken().flatMap(user -> contactRepository.loadContact(user.token))
                .compose(this::applySchedulers)
                .subscribe(contacts -> callback.showContact(contacts));
    }


    private <T> Observable<T> applySchedulers(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public interface Callback {

        void showContact(List<Contact> contacts);
    }

}
