package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;

public class EmailRepository {
    private EmailRepository() {
        super();
    }

    public static void save(Email email) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = EmailContract.getContentValues(email);
        if (email.getId() == null) {
            db.insert(EmailContract.TABLE, null, values);
        }else{
            String where = EmailContract.ID + " = ? ";
            String[] params = {email.getId().toString()};
            db.update(EmailContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Email> getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(EmailContract.TABLE, EmailContract.COLUNS, null,
                null, null, null, EmailContract.ID);
        List<Email> values = EmailContract.getEmails(cursor);

        db.close();
        databaseHelper.close();

        return values;


    }

    public static void delete(long id){

        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = EmailContract.CONTACT_ID + " = ?";
        String[] params = {String.valueOf(id)};
        db.delete(EmailContract.TABLE, where, params);

        db.close();
        databaseHelper.close();


    }
}
