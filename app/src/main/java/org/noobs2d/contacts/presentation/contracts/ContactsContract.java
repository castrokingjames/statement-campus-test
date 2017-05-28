package org.noobs2d.contacts.presentation.contracts;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import org.noobs2d.contacts.domain.entities.Contact;

import java.util.List;

public interface ContactsContract {

    interface View extends MvpView {

        void showLoadingDialog();

        void hideLoadingDialog();

        void showContacts(List<Contact> contacts);
    }

    interface Presenter extends MvpPresenter<View> {

        void load();
    }
}
