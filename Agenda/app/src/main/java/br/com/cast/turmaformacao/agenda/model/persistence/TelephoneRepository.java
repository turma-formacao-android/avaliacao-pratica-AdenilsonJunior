package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Telephone;

public class TelephoneRepository{
        private TelephoneRepository() {
            super();
        }

        public static void save(Telephone telephone) {
            DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
            SQLiteDatabase db = databaseHelper.getWritableDatabase();

            ContentValues values = TelephoneContract.getContentValues(telephone);
            if (telephone.getId() == null) {
                db.insert(TelephoneContract.TABLE, null, values);
            }else{
                String where = TelephoneContract.ID + " = ? ";
                String[] params = {telephone.getId().toString()};
                db.update(TelephoneContract.TABLE, values, where, params);
            }

            db.close();
            databaseHelper.close();
        }

        public static List<Telephone> getAll() {
            DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            Cursor cursor = db.query(TelephoneContract.TABLE, TelephoneContract.COLUNS, null,
                    null, null, null, TelephoneContract.ID);
            List<Telephone> values = TelephoneContract.getTelephones(cursor);

            db.close();
            databaseHelper.close();

            return values;


        }

        public static void delete(long id){

            DataBaseHelper databaseHelper = DataBaseHelper.getInstance();
            SQLiteDatabase db = databaseHelper.getReadableDatabase();

            String where = TelephoneContract.CONTACT_ID + " = ?";
            String[] params = {String.valueOf(id)};
            db.delete(TelephoneContract.TABLE, where, params);

            db.close();
            databaseHelper.close();


        }
}
