package org.noobs2d.contacts.data.clients.disk.preference;

import android.content.SharedPreferences;

import org.noobs2d.contacts.data.clients.disk.UserCache;
import org.noobs2d.contacts.domain.entities.User;

import io.reactivex.Observable;

public class UserCacheSharedPreference
        implements UserCache {

    private static final String KEY_TOKEN = "KEY_TOKEN";

    private SharedPreferences sharedPreferences;


    public UserCacheSharedPreference(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Observable<User> save(User user) {
        sharedPreferences.edit().putString(KEY_TOKEN, user.token).apply();
        return Observable.just(user);
    }

    @Override
    public Observable<User> load() {
        String token = sharedPreferences.getString(KEY_TOKEN, null);
        User user = new User();
        user.token = token;
        return Observable.just(user);
    }
}
