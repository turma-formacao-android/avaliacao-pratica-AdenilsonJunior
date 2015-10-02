package br.com.cast.turmaformacao.agenda.controllers.asyncTask;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;

public class ContactListUpdate extends AsyncTask<String, String, List<Contact>> {

    private ContactListUpdateInterface clui;
    private Context context;
    private ProgressDialog progress;

    public ContactListUpdate(Context context, ContactListUpdateInterface clui) {
        this.context = context;
        this.clui = clui;
    }

    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage(context.getString(R.string.msg_loading_message));
        progress.show();
    }

    @Override
    protected List<Contact> doInBackground(String... params) {

        publishProgress("Loading");

        List<Contact> contacts;

        contacts = ContactBusinessService.findAll();
        publishProgress(context.getString(R.string.msg_loaded));

        return contacts;
    }

    @Override
    protected void onProgressUpdate(String... params) {
        progress.setMessage(params[0]);

    }


    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        progress.dismiss();
    }
}

