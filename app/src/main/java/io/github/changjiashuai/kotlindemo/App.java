package io.github.changjiashuai.kotlindemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Email: changjiashuai@gmail.com
 *
 * Created by CJS on 16/7/27 17:31.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("kotlindemo.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
