package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

public class ContactListAdapter extends BaseAdapter implements Filterable{

    private List<Contact> contactList;
    private List<String> originalNames;
    private List<String> arrayList;
    private Activity context;

    public ContactListAdapter(Activity context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;

        createArrayString();

    }

    private void createArrayString() {
        arrayList = new ArrayList<>();

        for(Contact c : contactList){
            arrayList.add(c.getName());
        }
    }

    public void setDataValues(List<Contact> values) {
        contactList.clear();
        contactList.addAll(values);
    }


    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Contact getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        View contactListItemView = context.getLayoutInflater().inflate(R.layout.contact_item_list, parent, false);

        TextView textViewId = (TextView) contactListItemView.findViewById(R.id.textViewId);
        textViewId.setText(contact.getId().toString());

        TextView textViewName = (TextView) contactListItemView.findViewById(R.id.textViewName);
        textViewName.setText(contact.getName());


        return contactListItemView;
    }


    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                arrayList = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> FilteredArrList = new ArrayList<>();

                if (originalNames == null) {
                    originalNames = new ArrayList<>(arrayList);
                }


                if (constraint == null || constraint.length() == 0) {

                    results.count = originalNames.size();
                    results.values = originalNames;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < originalNames.size(); i++) {
                        String data = originalNames.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }

                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}

