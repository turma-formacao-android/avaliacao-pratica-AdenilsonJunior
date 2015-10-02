package br.com.cast.turmaformacao.agenda.util;

import android.app.Application;

public class ContactManagerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationUtil.setContext(getApplicationContext());

    }
}
