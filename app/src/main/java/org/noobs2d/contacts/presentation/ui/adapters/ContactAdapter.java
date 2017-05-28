package org.noobs2d.contacts.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.noobs2d.contacts.R;
import org.noobs2d.contacts.domain.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter
        extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> contacts = new ArrayList();

    public void addAll(List<Contact> contacts) {
        this.contacts.addAll(contacts);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_contacts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.name);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_texview);
        }
    }
}
