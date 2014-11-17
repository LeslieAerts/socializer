package com.leslieaerts.contactscraper;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.leslieaerts.contactscraper.domain.PhoneContact;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import java.util.ArrayList;
import java.util.List;

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

    public List<PhoneContact> getAllPhoneContacts() {
        List<PhoneContact> contacts = new ArrayList<PhoneContact>();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor idCursor = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME_PRIMARY, Contacts.PHOTO_ID}, null, null, null);

        if (idCursor.moveToFirst()) {
            while (idCursor.moveToNext()) {
                String contactId = idCursor.getString(idCursor.getColumnIndex(Contacts._ID));
                String name = idCursor.getString(idCursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));

                String photoId = idCursor.getString(idCursor.getColumnIndex(Contacts.PHOTO_ID));
                Drawable photo = getPhoneContactPhoto(photoId);

                PhoneContact contact = new PhoneContact();
                contact.setContactId(contactId);

                contact.setFirstName(name);
                contact.setPhoto(photo);
                String phoneNumber = null;
                String phoneNumberType = null;

                //Phone stuff
                Cursor phoneCursor = contentResolver.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
                if (phoneCursor.moveToFirst()) {
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
                        phoneNumberType = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.TYPE));
                        contact.addPhoneNumber(phoneNumber, phoneNumberType);
                    }
                }
                phoneCursor.close();

                //Email stuff
                Cursor emailCursor = contentResolver.query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
                if (emailCursor.moveToFirst()) {
                    while (emailCursor.moveToNext()) {
                        phoneNumber = emailCursor.getString(emailCursor.getColumnIndex(Phone.NUMBER));
                        phoneNumberType = emailCursor.getString(emailCursor.getColumnIndex(Phone.TYPE));
                        contact.addEmailAddress("");
                    }
                }
                phoneCursor.close();


                contacts.add(contact);
            }
        }
        idCursor.close();

        return contacts;
    }

    private Drawable getPhoneContactPhoto(String photoId) {
        throw new UnsupportedOperationException();
    }

    public String getContactEmail(String key) {
        return null;
    }

    public PhoneContact getPhoneContactByName(String name) {
        PhoneContact contact = new PhoneContact();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts.LOOKUP_KEY}, Contacts.DISPLAY_NAME_PRIMARY + "LIKE ?", new String[]{name}, null);

        if (c.moveToFirst()) {

            while (c.moveToNext()) {
                String lookup = c.getString(0);
                contact.setLookupKey(lookup);
            }
            c.close();
        }
        return new PhoneContact();
        // c = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{ContactsContract.CommonDataKinds.Phone.  }, Contacts.look+ " = ?", new String[]{firstName}, null);
    }

//    private Cursor createCursor(Uri table, String[] projection, String[] selection)
//    {
//        ContentResolver contentResolver = context.getContentResolver();
//        return contentResolver.query(table, projection, Contacts.DISPLAY_NAME_PRIMARY + "LIKE ?", selection, null);
//    }
}


