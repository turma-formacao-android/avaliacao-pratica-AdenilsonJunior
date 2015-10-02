package br.com.cast.turmaformacao.agenda.model.persistence;


import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public class ContactContract {


    public static final String TABLE = "contract";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ZIPCODE = "zipcode";
    public static final String TYPE = "type";
    public static final String STREET = "street";
    public static final String NEIGHBORHOOD = "neighborhood";
    public static final String CITY = "city";
    public static final String STATE = "state";


    public static final String[] COLUNS = {ID, NAME, ZIPCODE, TYPE, STREET, NEIGHBORHOOD, CITY, STATE};

    private ContactContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(ZIPCODE + " INTEGER, ");
        create.append(TYPE + " TEXT, ");
        create.append(STREET + " TEXT, ");
        create.append(NEIGHBORHOOD + " TEXT, ");
        create.append(CITY + " TEXT, ");
        create.append(STATE + " TEXT ");
        create.append(" ) ");

        return create.toString();
    }

    public static ContentValues getContentValues(Contact contact) {

        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getName());
        values.put(ContactContract.ZIPCODE, contact.getAddress().getZipCode());
        values.put(ContactContract.TYPE, contact.getAddress().getType());
        values.put(ContactContract.STREET, contact.getAddress().getStreet());
        values.put(ContactContract.NEIGHBORHOOD, contact.getAddress().getNeighborhood());
        values.put(ContactContract.CITY, contact.getAddress().getCity());
        values.put(ContactContract.STATE, contact.getAddress().getState());

        return values;

    }

    public static Contact getContact(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Contact contact = new Contact();
            Address address = new Address();

            address.setZipCode(cursor.getLong(cursor.getColumnIndex(ContactContract.ZIPCODE)));
            address.setType(cursor.getString(cursor.getColumnIndex(ContactContract.TYPE)));
            address.setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.STREET)));
            address.setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.NEIGHBORHOOD)));
            address.setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CITY)));
            address.setState(cursor.getString(cursor.getColumnIndex(ContactContract.STATE)));

            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setName(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.setAddress(address);

            return contact;
        }

        return null;
    }

    public static List<Contact> getContacts(Cursor cursor) {

        ArrayList<Contact> contacts = new ArrayList<>();

        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }


        return contacts;


    }

    public static Long getIdContactByLastPosition(Cursor cursor){
        cursor.moveToLast();

        return cursor.getLong(cursor.getColumnIndex(ContactContract.ID));
    }

}
