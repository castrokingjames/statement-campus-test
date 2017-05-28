package org.noobs2d.contacts.presentation.presenters;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import org.noobs2d.contacts.data.clients.disk.UserCache;
import org.noobs2d.contacts.domain.entities.Contact;
import org.noobs2d.contacts.domain.repositories.ContactRepository;
import org.noobs2d.contacts.domain.repositories.UserRepository;
import org.noobs2d.contacts.domain.usecases.ContactsUseCase;
import org.noobs2d.contacts.presentation.contracts.ContactsContract;

import java.util.List;

public class ContactsPresenter
        extends MvpBasePresenter<ContactsContract.View>
        implements ContactsContract.Presenter, ContactsUseCase.Callback {

    private ContactsUseCase useCase;

    public ContactsPresenter(ContactRepository contactRepository, UserRepository userRepository) {
        useCase = new ContactsUseCase(this, userRepository, contactRepository);
    }

    @Override
    public void load() {
        ContactsContract.View view = getView();
        view.showLoadingDialog();
        useCase.load();
    }

    @Override
    public void showContact(List<Contact> contacts) {
        ContactsContract.View view = getView();
        view.hideLoadingDialog();
        view.showContacts(contacts);
    }
}
