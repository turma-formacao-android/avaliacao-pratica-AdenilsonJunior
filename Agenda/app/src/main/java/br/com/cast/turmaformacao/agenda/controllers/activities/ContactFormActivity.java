package br.com.cast.turmaformacao.agenda.controllers.activities;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.SocialNetWorking;
import br.com.cast.turmaformacao.agenda.model.entities.Telephone;
import br.com.cast.turmaformacao.agenda.model.http.AddressService;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactContract;
import br.com.cast.turmaformacao.agenda.model.services.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.SocialNetWorkingBusinessService;
import br.com.cast.turmaformacao.agenda.model.services.TelephoneBusinessService;
import br.com.cast.turmaformacao.agenda.util.FormContactHelper;

public class ContactFormActivity extends AppCompatActivity {

    Contact contact;
    Address address;
    EditText editTextName;
    EditText editTextTelephone;
    ImageView imageViewAddTelephone;
    ListView listViewTelephones;
    EditText editTextEmail;
    ImageView imageViewAddEmail;
    ListView listViewEmail;
    EditText editTextSocialNetworking;
    ImageView imageViewAddSocial;
    ListView listViewSocialNetworking;
    TextView imageViewAddress;
    EditText editTextZipCode;
    EditText editTextType;
    EditText editTextStreet;
    EditText editTextNeighborhood;
    EditText editTextCity;
    EditText editTextState;
    Button buttonSearch;

    ArrayList<Telephone> telephones;
    ArrayList<Email> emails;
    ArrayList<SocialNetWorking> socialNetworkings;

    ArrayList<String> numbersString;
    ArrayList<String> linksString;
    ArrayList<String> emailsString;


    public static String PARAM_CONTACT = "CONTACT";
    public static String PARAM_ADDRESS = "ADDRESS";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_contact_activity);

        createListInformations();
        createArrayStrings();
        initContact();
        initAddress();
        if(contact== null){
            loadListViewTelephone();
        }else{
            bindImageViewAddTelephone();
        }
        bindEditTextName();
        bindEditTextTelephone();
        bindListViewTelephone();

        bindEditTextEmail();
        bindListViewEmail();
        bindImageViewAddEmail();
        bindEditTextSocialNetworking();
        bindListViewSocialNetWorking();
        bindImageViewAddSocialNetWorking();
        bindTextViewAddress();
        bindEditTextZipCode();
        bindEditTextType();
        bindEditTextStreet();
        bindEditTextNeighborhood();
        bindEditTextCity();
        bindEditTextState();
        bindButtonSearch();


    }

    private void initAddress() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.address = getIntent().getExtras().getParcelable(PARAM_ADDRESS);
        }
        this.address = address == null ? new Address() : this.address;
    }

    //metodo resposavel por criar os array de strings que sera responsavel que servira pro adapter inserir e atualizar
    //as lists view
    private void createArrayStrings() {
        numbersString = new ArrayList<>();
        linksString = new ArrayList<>();
        emailsString = new ArrayList<>();

    }

    private void loadListViewTelephone(){

        listViewTelephones = (ListView) findViewById(R.id.listViewTelephones);

        ArrayList<String> telephonesBD = new ArrayList<>();
        List<Telephone> telephones = TelephoneBusinessService.findAll();

        if(!(telephones.isEmpty())){
            for(Telephone t :  telephones){
                if(t.getContact_id().equals(contact.getId())){
                    telephonesBD.add(t.getNumber().toString());
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, telephonesBD);
            listViewSocialNetworking.setAdapter(adapter);


        }

        listViewTelephones.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });


    }
    private void bindListViewTelephone() {
        listViewTelephones = (ListView) findViewById(R.id.listViewTelephones);


        listViewTelephones.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindListViewEmail() {
        listViewEmail = (ListView) findViewById(R.id.listViewEmail);
        listViewEmail.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });

    }

    private void bindListViewSocialNetWorking() {
        listViewSocialNetworking = (ListView) findViewById(R.id.listViewSocialNetworkig);
        listViewSocialNetworking.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindTextViewAddress() {
        imageViewAddress = (TextView) findViewById(R.id.textViewAddress);
    }

    private void bindImageViewAddSocialNetWorking() {
        imageViewAddSocial = (ImageView) findViewById(R.id.imageViewAddSocialNetworking);
        imageViewAddSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialNetWorking socialNetWorking = new SocialNetWorking();

                socialNetWorking.setLink(editTextSocialNetworking.getText().toString());

                linksString.add(socialNetWorking.getLink());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, linksString);
                listViewSocialNetworking.setAdapter(adapter);

                socialNetworkings.add(socialNetWorking);
                Toast.makeText(ContactFormActivity.this, R.string.msg_add_socialNet, Toast.LENGTH_SHORT).show();
                editTextSocialNetworking.setText("");
            }
        });
    }

    private void bindImageViewAddEmail() {
        imageViewAddEmail = (ImageView) findViewById(R.id.imageViewAddEmail);
        imageViewAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email email = new Email();

                email.setEmail(editTextEmail.getText().toString());

                emailsString.add(email.getEmail());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, emailsString);
                listViewEmail.setAdapter(adapter);

                emails.add(email);
                Toast.makeText(ContactFormActivity.this, R.string.msg_add_email, Toast.LENGTH_SHORT).show();
                editTextEmail.setText("");
            }
        });
    }

    private void bindImageViewAddTelephone() {
        imageViewAddTelephone = (ImageView) findViewById(R.id.imageViewAddTelephone);
        imageViewAddTelephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telephone telephone = new Telephone();

                telephone.setNumber(Long.parseLong(editTextTelephone.getText().toString()));

                numbersString.add(telephone.getNumber().toString());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, numbersString);
                listViewTelephones.setAdapter(adapter);

                telephones.add(telephone);
                Toast.makeText(ContactFormActivity.this, R.string.msg_add_telephone, Toast.LENGTH_SHORT).show();
                editTextTelephone.setText("");
            }
        });
    }

    //metodo que cria arrays para telefones, emails e redes, para posteriormente serem adicionados
    //ao contact
    private void createListInformations() {
        telephones = new ArrayList<>();
        emails = new ArrayList<>();
        socialNetworkings = new ArrayList<>();
    }

    private void bindButtonSearch() {
        buttonSearch = (Button) findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask<String, Void, Address> execute = new GetAddressTask().execute(editTextZipCode.getText().toString());


                try {
                    editTextType.setText(execute.get().getType());
                    editTextStreet.setText(execute.get().getStreet());
                    editTextNeighborhood.setText(execute.get().getNeighborhood());
                    editTextCity.setText(execute.get().getCity());
                    editTextState.setText(execute.get().getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = getIntent().getExtras().getParcelable(PARAM_CONTACT);
        }
        this.contact = contact == null ? new Contact() : this.contact;

    }

    private void bindEditTextState() {
        editTextState = (EditText) findViewById(R.id.edit_state);
        editTextState.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
    }

    private void bindEditTextCity() {
        editTextCity = (EditText) findViewById(R.id.edit_city);
        editTextCity.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
    }

    private void bindEditTextNeighborhood() {
        editTextNeighborhood = (EditText) findViewById(R.id.editText_neighborhood);
        editTextNeighborhood.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());

    }

    private void bindEditTextStreet() {
        editTextStreet = (EditText) findViewById(R.id.editText_street);
        editTextStreet.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
    }

    private void bindEditTextType() {
        editTextType = (EditText) findViewById(R.id.editText_type);
        editTextType.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getType());
    }

    private void bindEditTextZipCode() {
        editTextZipCode = (EditText) findViewById(R.id.editText_zipCode);
        editTextZipCode.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode().toString());
    }

    private void bindEditTextSocialNetworking() {
        editTextSocialNetworking = (EditText) findViewById(R.id.editText_socialNetworking);

    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.editText_email);
    }

    private void bindEditTextTelephone() {
        editTextTelephone = (EditText) findViewById(R.id.editText_telephone);
    }

    private void bindEditTextName() {
        editTextName = (EditText) findViewById(R.id.editText_name);
        editTextName.setText(contact.getName() == null ? "" : contact.getName());

        editTextName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_action_account_child, 0);
        editTextName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;


                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (editTextName.getRight() - editTextName.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        final Intent goToSOContacts = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                        goToSOContacts.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        startActivityForResult(goToSOContacts, 999);
                    }
                }
                return false;
            }
        });

    }

    private void bindAddress() {
        address.setZipCode(Long.parseLong(editTextZipCode.getText().toString()));
        address.setType(editTextType.getText().toString());
        address.setStreet(editTextStreet.getText().toString());
        address.setNeighborhood(editTextNeighborhood.getText().toString());
        address.setCity(editTextCity.getText().toString());
        address.setState(editTextState.getText().toString());

    }


    private void bindContact() {
        contact.setName(editTextName.getText().toString());
        contact.setAddress(address);
        contact.setTelephones(telephones);
        contact.setEmails(emails);
        contact.setSocialNetworking(socialNetworkings);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.done_contact):
                onMenuDoneContact();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuDoneContact() {

        String requiredMessage = getString(R.string.msg_required_fild);

        if (!FormContactHelper.validateRequired(requiredMessage, editTextName)) {
            bindAddress();
            bindContact();
            ContactBusinessService.save(contact);

            //metodos serão responsaveis por pegar o id do contato ja salvo e setar em cada um
            //dos telefones, socials e emails
            setContactIdInTelephones();
            setContactIdInSocialNetworking();
            setContactIdInEmail();

            //metodos salvara cada um das listas no banco
            saveTelephonesInDB();
            saveSocialsInDB();
            saveEmailsInDB();

            Toast.makeText(ContactFormActivity.this, R.string.msg_save_sucess, Toast.LENGTH_SHORT).show();
            Intent backToContactList = new Intent(ContactFormActivity.this, ContactListActivity.class);
            startActivity(backToContactList);
            ContactFormActivity.this.finish();


        }


    }

    private void saveEmailsInDB() {
        for (Email e : emails) {
            EmailBusinessService.save(e);
        }
    }

    private void saveSocialsInDB() {
        for (SocialNetWorking sn : socialNetworkings) {
            SocialNetWorkingBusinessService.save(sn);
        }

    }

    private void saveTelephonesInDB() {
        for (Telephone t : telephones) {
            TelephoneBusinessService.save(t);
        }

    }

    private void setContactIdInEmail() {
        for (Email e : emails) {
            e.setContact_id(ContactBusinessService.getIdContact());
        }

    }

    private void setContactIdInSocialNetworking() {
        for (SocialNetWorking sn : socialNetworkings) {
            sn.setContact_id(ContactBusinessService.getIdContact());
        }

    }

    private void setContactIdInTelephones() {
        for (Telephone t : telephones) {
            t.setContact_id(ContactBusinessService.getIdContact());
        }

    }


    private class GetAddressTask extends AsyncTask<String, Void, Address> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Address doInBackground(String... params) {
            return AddressService.getAddressByZipCode(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    final Uri contactUri = data.getData();
                    final String[] projection = {
                            ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER,
                            ContactsContract.CommonDataKinds.Email.ADDRESS,
                            ContactsContract.CommonDataKinds.Website.URL,
                            ContactsContract.CommonDataKinds.SipAddress.SIP_ADDRESS
                    };
                    final Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    editTextName.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME)));
                    editTextTelephone.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    editTextEmail.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)));
                    editTextSocialNetworking.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                    editTextStreet.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.SipAddress.SIP_ADDRESS)));

                    cursor.close();
                } catch (Exception e) {
                    Log.d("TAG", "Unexpected error");
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
