package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Telephone;


public class TelephoneContract {

    public static final String TABLE = "telephone";
    public static final String ID = "id";
    public static final String CONTACT_ID = "contact_id";
    public static final String NUMBER = "number";

    public static final String[] COLUNS = {ID, CONTACT_ID, NUMBER};

    private TelephoneContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(CONTACT_ID + " INTEGER, ");
        create.append(NUMBER + " INTEGER ");
        create.append(" ) ");

        return create.toString();
    }

    public static ContentValues getContentValues(Telephone telephone) {

        ContentValues values = new ContentValues();
        values.put(TelephoneContract.ID, telephone.getId());
        values.put(TelephoneContract.CONTACT_ID, telephone.getContact_id());
        values.put(TelephoneContract.NUMBER, telephone.getNumber());

        return values;

    }

    public static Telephone getTelephone(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Telephone telephone = new Telephone();

            telephone.setId(cursor.getLong(cursor.getColumnIndex(TelephoneContract.ID)));
            telephone.setContact_id(cursor.getLong(cursor.getColumnIndex(TelephoneContract.CONTACT_ID)));
            telephone.setNumber(cursor.getLong(cursor.getColumnIndex(TelephoneContract.NUMBER)));

            return telephone;
        }

        return null;
    }

    public static List<Telephone> getTelephones(Cursor cursor) {

        ArrayList<Telephone> telephones = new ArrayList<>();

        while (cursor.moveToNext()) {
            telephones.add(getTelephone(cursor));
        }

        return telephones;

    }
}
