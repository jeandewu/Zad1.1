
package com.jandewu.newapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.Contacts;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  //  private static final int REQEST_CODE = 997;
    private static final int CONTACT_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            inflateLayout();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, CONTACT_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       switch (requestCode) {
           case CONTACT_REQUEST_CODE: {
               if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                   inflateLayout();
               }
               else {
                   finish();
               }
           }
           break;
       }

    }

    private Cursor getContactsCursor(){
        Uri uri = ContactsContract.RawContacts.CONTENT_URI;
        String[] projection = {RawContacts._ID, Contacts.DISPLAY_NAME};
        String selection = RawContacts.DELETED + "=?";
        String[] selectionArgs = {Integer.toString(0)};
        String sortOrder = Contacts.DISPLAY_NAME + " ASC";
        return getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    private void inflateLayout() {
     //   Toast.makeText(this, "perm ok", Toast.LENGTH_SHORT).show();
        Cursor contactsCursor = getContactsCursor();
        startManagingCursor(contactsCursor);

    }


}
