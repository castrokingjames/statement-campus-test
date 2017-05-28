package org.noobs2d.contacts.presentation.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import org.noobs2d.contacts.R;
import org.noobs2d.contacts.data.clients.disk.UserCache;
import org.noobs2d.contacts.data.clients.disk.preference.UserCacheSharedPreference;
import org.noobs2d.contacts.data.clients.web.LoginWebservice;
import org.noobs2d.contacts.data.clients.web.retrofit2.LoginRetrofit2Webservice;
import org.noobs2d.contacts.data.managers.UserManager;
import org.noobs2d.contacts.domain.repositories.UserRepository;
import org.noobs2d.contacts.presentation.contracts.LoginContract;
import org.noobs2d.contacts.presentation.presenters.LoginPresenter;

public class LoginActivity
        extends MvpActivity<LoginContract.View, LoginContract.Presenter>
        implements LoginContract.View, View.OnClickListener {

    private EditText passwordEditText;
    private EditText usernameEditText;
    private Button loginButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordEditText = (EditText) findViewById(R.id.password_edittext);
        usernameEditText = (EditText) findViewById(R.id.username_edittext);
        loginButton = (Button) findViewById(R.id.button_login);
        loginButton.setOnClickListener(this);
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        LoginWebservice loginWebservice = new LoginRetrofit2Webservice();
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_APPEND);
        UserCache userCache = new UserCacheSharedPreference(sharedPreferences);
        UserRepository userRepository = new UserManager(loginWebservice, userCache);
        return new LoginPresenter(userRepository);
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
    public void showContactScreen() {
        Intent intent = ContactsActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        presenter.login(username, password);
    }
}
