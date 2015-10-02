package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetWorking;

public class SocialNetworkingContract {

    public static final String TABLE = "socialnetworking";
    public static final String ID = "id";
    public static final String CONTACT_ID = "contact_id";
    public static final String LINK = "link";

    public static final String[] COLUNS = {ID, CONTACT_ID, LINK};

    private SocialNetworkingContract() {
        super();
    }

    public static String getCreateTableScript() {
        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(CONTACT_ID + " INTEGER, ");
        create.append(LINK + " TEXT ");
        create.append(" ) ");

        return create.toString();
    }

    public static ContentValues getContentValues(SocialNetWorking socialNetWorking) {

        ContentValues values = new ContentValues();
        values.put(SocialNetworkingContract.ID, socialNetWorking.getId());
        values.put(SocialNetworkingContract.CONTACT_ID, socialNetWorking.getContact_id());
        values.put(SocialNetworkingContract.LINK, socialNetWorking.getLink());

        return values;

    }

    public static SocialNetWorking getSocialNetworking(Cursor cursor) {

        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            SocialNetWorking socialNetWorking = new SocialNetWorking();

            socialNetWorking.setId(cursor.getLong(cursor.getColumnIndex(SocialNetworkingContract.ID)));
            socialNetWorking.setContact_id(cursor.getLong(cursor.getColumnIndex(SocialNetworkingContract.CONTACT_ID)));
            socialNetWorking.setLink(cursor.getString(cursor.getColumnIndex(SocialNetworkingContract.LINK)));

            return socialNetWorking;
        }

        return null;
    }

    public static List<SocialNetWorking> getSocialNetworkings(Cursor cursor) {

        ArrayList<SocialNetWorking> socialNetWorkings = new ArrayList<>();

        while (cursor.moveToNext()) {
            socialNetWorkings.add(getSocialNetworking(cursor));
        }

        return socialNetWorkings;

    }
}
