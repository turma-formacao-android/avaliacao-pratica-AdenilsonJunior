package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public class ContactRepository {

    private ContactRepository() {
        super();
    }

    public static void save(Contact contact) {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = ContactContract.getContentValues(contact);
        if (contact.getId() == null) {
            db.insert(ContactContract.TABLE, null, values);
        }else{
            String where = ContactContract.ID + " = ? ";
            String[] params = {contact.getId().toString()};
            db.update(ContactContract.TABLE, values, where, params);
        }

        db.close();
        databaseHelper.close();
    }

    public static List<Contact>  getAll() {
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, null,
                null, null, null, ContactContract.ID);
        List<Contact> values = ContactContract.getContacts(cursor);

        db.close();
        databaseHelper.close();

        return values;


    }

    public static void delete(long id){

        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String where = ContactContract.ID + " = ?";
        String[] params = {String.valueOf(id)};
        db.delete(ContactContract.TABLE, where, params);

        db.close();
        databaseHelper.close();
    }

    public static Long getIdContact(){
        DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ContactContract.TABLE, ContactContract.COLUNS, null,
                null, null, null, ContactContract.ID);
        Long id = ContactContract.getIdContactByLastPosition(cursor);

        db.close();
        databaseHelper.close();

        return id;
    }
}
