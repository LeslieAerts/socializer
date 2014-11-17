package com.leslieaerts.contactscraper.domain;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leslie on 25-10-2014.
 */
public class PhoneContact {


    private String firstName;
    private String lastName;
    private Map<String, String> emailAddress;
    private Map<String, String> phoneNumbers;
    private Drawable photo;
    private String lookupKey;
    private String contactId;

    public PhoneContact()
    {
        this.emailAddress = new HashMap<String, String>();
        this.phoneNumbers = new HashMap<String, String>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Map<String, String> getEmailAddress() {
        return emailAddress;
    }

    public Map<String, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Drawable getPhoto() {
        return photo;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }

    public void addPhoneNumber(String phoneNumber, String phoneNumberType) {
        phoneNumbers.put(phoneNumberType,phoneNumber);
    }

    public void addEmailAddress(String s) {
        emailAddress.put("",s);
        throw new UnsupportedOperationException();

    }
}
