package com.leslieaerts.contactscraper.domain;

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
}
