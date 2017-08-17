package com.jandewu.newapp;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by RENT on 2017-08-16.
 */

public class ContactsListAdapter extends SimpleCursorAdapter {


    private final Context context;
    private final Cursor cursor;

    public ContactsListAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to, int flags) {
        super(context, layout, cursor, from, to, flags);
        this.context = context;
        this.cursor = cursor;
    }

    static class ViewHolder {
        TextView contactDisplayName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        View rowView = convertView;
        if (convertView == null) {
            LayoutInflater layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //  return super.getView(position, convertView, parent);
            rowView = layoutinflater.inflate(R.layout.row, null, false);
            viewholder = new ViewHolder();
            viewholder.contactDisplayName = rowView.findViewById(R.id.contactName);
            rowView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        cursor.moveToPosition(position);
        String columnName = ContactsContract.Contacts.DISPLAY_NAME;
        viewholder.contactDisplayName.setText(cursor.getString(cursor.getColumnIndex(columnName)));

        return rowView;
    }
}
