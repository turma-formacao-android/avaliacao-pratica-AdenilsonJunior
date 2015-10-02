package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.controllers.asyncTask.ContactListUpdate;
import br.com.cast.turmaformacao.agenda.controllers.asyncTask.ContactListUpdateInterface;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.SocialNetWorkingBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.TelephoneBusinessService;


public class ContactListActivity extends AppCompatActivity implements ContactListUpdateInterface {

    TextView textViewFilter;
    EditText editTextFilter;
    ListView listViewContact;
    TextView textViewId;
    TextView textViewName;
    Contact selectedContact;
    ContactListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact);


        bindTextViewFilter();
        bindEditTextFilter();
        bindListViewContact();
        bindTextViewId();
        bindTextViewName();


    }

    private void bindEditTextFilter() {
        editTextFilter = (EditText) findViewById(R.id.editTextFilter);
        editTextFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                 adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void bindTextViewFilter() {
        textViewFilter = (TextView) findViewById(R.id.textViewFilter);
    }

    private void bindTextViewName() {
        textViewName = (TextView) findViewById(R.id.textView_name);
    }

    private void bindTextViewId() {
        textViewId = (TextView) findViewById(R.id.textView_id);
    }

    private void bindListViewContact() {
        listViewContact = (ListView) findViewById(R.id.list_contacts);
        registerForContextMenu(listViewContact);

        listViewContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ContactListAdapter adapter = (ContactListAdapter) listViewContact.getAdapter();
                selectedContact = adapter.getItem(position);

                return false;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.create_contact:
                onMenuCreateContact();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuCreateContact() {
        Intent goToContactForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        startActivity(goToContactForm);
        ContactListActivity.this.finish();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
                break;

        }
        return super.onContextItemSelected(item);
    }

    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        goToTaskForm.putExtra(ContactFormActivity.PARAM_CONTACT, selectedContact);
        startActivity(goToTaskForm);
    }


    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactBusinessService.delete(selectedContact);
                        TelephoneBusinessService.delete(selectedContact);
                        SocialNetWorkingBusinessService.delete(selectedContact);
                        EmailBusinessService.delete(selectedContact);
                        selectedContact = null;
                        String message = getString(R.string.msg_delete_sucessful);
                        updateContactList();
                        Toast.makeText(ContactListActivity.this, message, Toast.LENGTH_SHORT).show();

                    }
                }).setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();


    }


    @Override
    public void updateContactList() {
        List<Contact> contacts;
        try {
            contacts = new ContactListUpdate(this, this).execute().get();
            listViewContact.setAdapter(new ContactListAdapter(this, contacts));
            adapter = (ContactListAdapter) listViewContact.getAdapter();
            adapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        updateContactList();
        super.onResume();
    }
}
