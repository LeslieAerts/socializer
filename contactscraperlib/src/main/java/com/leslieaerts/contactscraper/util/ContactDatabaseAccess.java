package com.leslieaerts.contactscraper.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.leslieaerts.contactscraper.R;
import com.leslieaerts.contactscraper.domain.PhoneContact;

import android.provider.ContactsContract.CommonDataKinds.Phone;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Leslie on 26-10-2014.
 */
class ContactDatabaseAccess {

    private final Context context;
    private DatabaseListener dbListener;

    public ContactDatabaseAccess(Context context) {
        this.context = context;
    }

    /**
     * Gets a Bitmap representation of contact's photo
     *
     * @param id
     * @return
     */
    public Bitmap getContactPhotoByContactId(String id) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, id);
        InputStream photoInputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(), contactUri);
        Bitmap photo = BitmapFactory.decodeStream(photoInputStream);
        if (photo != null) {
            return photo;
        }

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_default_picture);
        return bitmap;
    }


    /**
     * Get a single contact by name.
     *
     * @param partialName
     * @return
     */
    public PhoneContact getPhoneContactByName(String partialName) {
        PhoneContact contact = new PhoneContact();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME_PRIMARY}, Contacts.DISPLAY_NAME_PRIMARY + "LIKE ?", new String[]{partialName}, null);

        if (c.moveToFirst()) {
            while (c.moveToNext()) {
                String contactId = c.getString(c.getColumnIndex(Contacts.LOOKUP_KEY));

                String contactName = c.getString(c.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));
                contact.setContactId(contactId);
                Bitmap photo = getContactPhotoByContactId(contactId);

                contact.setContactId(contactId);
                contact.setDisplayName(contactName);
                contact.setPhoto(photo);

                Map<String, String> phoneMap = getContactPhoneNumbersById(contactId);
                contact.addPhoneNumbers(phoneMap);

                Map<String, String> emailsMap = getContactEmailByContactId(contactId);
                contact.addEmailAddresses(emailsMap);
            }
            c.close();
        }
        return new PhoneContact();
    }


    /**
     * Returns a list of every phone contact available on the phone.
     *
     * @return
     */
    public List<PhoneContact> getAllPhoneContacts() {
        List<PhoneContact> contacts = new ArrayList<PhoneContact>();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor idCursor = contentResolver.query(Contacts.CONTENT_URI, new String[]{Contacts._ID, Contacts.DISPLAY_NAME_PRIMARY}, null, null, null);

        if (idCursor.moveToFirst()) {
            do {
                String contactId = idCursor.getString(idCursor.getColumnIndex(Contacts._ID));
                String name = idCursor.getString(idCursor.getColumnIndex(Contacts.DISPLAY_NAME_PRIMARY));
                Bitmap photo = getContactPhotoByContactId(contactId);

                PhoneContact contact = new PhoneContact();
                contact.setContactId(contactId);

                contact.setDisplayName(name);
                contact.setPhoto(photo);

                //Phone stuff
                Map<String, String> phoneMap = getContactPhoneNumbersById(contactId);
                contact.addPhoneNumbers(phoneMap);

                //Email stuff
                Map<String, String> emailsMap = getContactEmailByContactId(contactId);
                contact.addEmailAddresses(emailsMap);

                contacts.add(contact);
                if (dbListener != null) {
                    dbListener.onProgress(contact);
                }
            } while (idCursor.moveToNext());
        }
        idCursor.close();

        return contacts;
    }

    /**
     * Returns a map of all the phone numbers a contact has.
     *
     * @param contactId
     * @return
     */
    private Map<String, String> getContactPhoneNumbersById(String contactId) {
        Map<String, String> phoneNumbers = new HashMap<String, String>();

        Cursor phoneCursor = context.getContentResolver().query(Phone.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
        if (phoneCursor.moveToFirst()) {
            do {
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.NUMBER));
                String phoneNumberType = phoneCursor.getString(phoneCursor.getColumnIndex(Phone.TYPE));
                phoneNumbers.put(phoneNumberType, phoneNumber);
            } while (phoneCursor.moveToNext());
        }

        phoneCursor.close();
        return phoneNumbers;
    }

    /**
     * Returns all the email addresses a contact has.
     *
     * @param contactId
     * @return
     */
    private Map<String, String> getContactEmailByContactId(String contactId) {
        Cursor emailCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, new String[]{Phone.NUMBER, Phone.TYPE}, Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);
        Map<String, String> emails = new HashMap<String, String>();
        if (emailCursor.moveToFirst()) {
            do {
                String emailAddress = null;
                String emailType = null;
                emailAddress = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                emailType = emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                emails.put(emailType, emailAddress);
            } while (emailCursor.moveToNext());
        }
        emailCursor.close();
        return emails;
    }

    public void setDatabaseListener(DatabaseListener listener)
    {
        this.dbListener = listener;
    }
}