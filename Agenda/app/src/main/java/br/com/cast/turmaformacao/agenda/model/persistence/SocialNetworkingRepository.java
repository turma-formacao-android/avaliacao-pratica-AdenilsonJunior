package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.SocialNetWorking;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;

public class SocialNetworkingRepository {

    private SocialNetworkingRepository() {
        super();
    }

    public static void save(SocialNetWorking socialNetWorking) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = SocialNetworkingContract.getContentValues(socialNetWorking);
        if (socialNetWorking.getId() == null) {
            db.insert(SocialNetworkingContract.TABLE, null, values);
        }else{
            String where = SocialNetworkingContract.ID + " = ? ";
            String[] params = {socialNetWorking.getId().toString()};
            db.update(SocialNetworkingContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<SocialNetWorking> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(SocialNetworkingContract.TABLE, SocialNetworkingContract.COLUNS, null,
                null, null, null, SocialNetworkingContract.ID);
        List<SocialNetWorking> values = SocialNetworkingContract.getSocialNetworkings(cursor);

        db.close();
        databaseHelper.close();

        return values;


    }

    public static void delete(long id){

        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = SocialNetworkingContract.CONTACT_ID + " = ?";
        String[] params = {String.valueOf(id)};
        db.delete(SocialNetworkingContract.TABLE, where, params);

        db.close();
        databaseHelper.close();


    }

}
