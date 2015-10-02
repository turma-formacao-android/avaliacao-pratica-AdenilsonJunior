package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;

public class EmailContract {

    public static final String TABLE = "email";
    public static final String ID = "id";
    public static final String CONTACT_ID = "contact_id";
    public static final String EMAIL = "email";

    public static final String[] COLUNS = {ID, CONTACT_ID, EMAIL};

    private EmailContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(CONTACT_ID + " INTEGER, ");
        create.append(EMAIL + " TEXT ");
        create.append(" ) ");

        return create.toString();
    }

    public static ContentValues getContentValues(Email email) {

        ContentValues values = new ContentValues();
        values.put(EmailContract.ID, email.getId());
        values.put(EmailContract.CONTACT_ID, email.getContact_id());
        values.put(EmailContract.EMAIL, email.getEmail());

        return values;

    }

    public static Email getEmail(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Email email = new Email();

            email.setId(cursor.getLong(cursor.getColumnIndex(EmailContract.ID)));
            email.setContact_id(cursor.getLong(cursor.getColumnIndex(EmailContract.CONTACT_ID)));
            email.setEmail(cursor.getString(cursor.getColumnIndex(EmailContract.EMAIL)));

            return email;
        }

        return null;
    }

    public static List<Email> getEmails(Cursor cursor) {

        ArrayList<Email> emails = new ArrayList<>();

        while (cursor.moveToNext()) {
            emails.add(getEmail(cursor));
        }

        return emails;

    }
}
