package com.leslieaerts.contactscraper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.leslieaerts.contactscraper.domain.PhoneContact;

/**
 * Created by Leslie on 26-10-2014.
 */
public class ScrapeSystem {
    private static final String[] BASIC_CONTACT_PROJECTION = {
            Contacts._ID,
            Contacts.LOOKUP_KEY,
            Contacts.DISPLAY_NAME_PRIMARY
    };

    private static final String[] DETAILED_CONTACT_PROJECTION = {
            ContactsContract.Data._ID,
            ContactsContract.Data.LOOKUP_KEY,
            ContactsContract.Data.DISPLAY_NAME_PRIMARY
    };
    private final Context context;

    public ScrapeSystem(Context context) {
        this.context = context;
    }

    public Bitmap getContactPhotoByLookupKey(String key) {
        throw new UnsupportedOperationException();
    }

    private long GetContactLookupKeyFromName(String name) {
        throw new UnsupportedOperationException();
    }

    public PhoneContact getPhoneContactByName(String firstName, String lastName) {

        PhoneContact contact = new PhoneContact();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts.LOOKUP_KEY}, Contacts.DISPLAY_NAME_PRIMARY + "LIKE ?", new String[]{name}, null);

        if (c.moveToFirst()) {

            while (c.moveToNext()) {
                String lookup = c.getString(0);
                contact.setLookupKey(lookup);
            }
        }

        c = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{}, + " = ?", new String[]{name}, null);
    }

//    private Cursor createCursor(Uri table, String[] projection, String[] selection)
//    {
//        ContentResolver contentResolver = context.getContentResolver();
//        return contentResolver.query(table, projection, Contacts.DISPLAY_NAME_PRIMARY + "LIKE ?", selection, null);
//    }
}


