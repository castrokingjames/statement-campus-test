package org.noobs2d.contacts.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.noobs2d.contacts.R;
import org.noobs2d.contacts.data.clients.disk.UserCache;
import org.noobs2d.contacts.data.clients.disk.preference.UserCacheSharedPreference;
import org.noobs2d.contacts.data.clients.web.ContactsWebService;
import org.noobs2d.contacts.data.clients.web.LoginWebservice;
import org.noobs2d.contacts.data.clients.web.retrofit2.ContractsRetrofit2WebService;
import org.noobs2d.contacts.data.clients.web.retrofit2.LoginRetrofit2Webservice;
import org.noobs2d.contacts.data.managers.ContactsManager;
import org.noobs2d.contacts.data.managers.UserManager;
import org.noobs2d.contacts.domain.entities.Contact;
import org.noobs2d.contacts.domain.repositories.ContactRepository;
import org.noobs2d.contacts.domain.repositories.UserRepository;
import org.noobs2d.contacts.presentation.contracts.ContactsContract;
import org.noobs2d.contacts.presentation.presenters.ContactsPresenter;
import org.noobs2d.contacts.presentation.ui.adapters.ContactAdapter;

import java.util.List;


public class ContactsActivity
        extends MvpActivity<ContactsContract.View, ContactsContract.Presenter>
        implements ContactsContract.View {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        contactAdapter = new ContactAdapter();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);

        presenter.load();
    }

    @NonNull
    @Override
    public ContactsContract.Presenter createPresenter() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        UserCache userCache = new UserCacheSharedPreference(sharedPreferences);
        ContactsWebService contactsWebService = new ContractsRetrofit2WebService();
        ContactRepository contactRepository = new ContactsManager(contactsWebService);
        LoginWebservice loginWebservice = new LoginRetrofit2Webservice();
        UserRepository userRepository = new UserManager(loginWebservice, userCache);
        return new ContactsPresenter(contactRepository, userRepository);
    }

    public static Intent newIntent(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ContactsActivity.class);
        return intent;
    }

    @Override
    public void showLoadingDialog() {
        progressDialog = ProgressDialog.show(this, "Loading", "Connecting to server");
    }

    @Override
    public void hideLoadingDialog() {
        progressDialog.hide();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        contactAdapter.addAll(contacts);
        contactAdapter.notifyDataSetChanged();
    }
}
