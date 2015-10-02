package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.util.ApplicationUtil;

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "contactmanagerdb";
    private static final int DATABASE_VERSION = 1;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getInstance() {
        return new DataBaseHelper(ApplicationUtil.getContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ContactContract.getCreateTableScript());
        db.execSQL(EmailContract.getCreateTableScript());
        db.execSQL(SocialNetworkingContract.getCreateTableScript());
        db.execSQL(TelephoneContract.getCreateTableScript());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
