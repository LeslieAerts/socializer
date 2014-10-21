package com.leslieaerts.contactscraper.domain;

import android.graphics.drawable.Drawable;

import java.util.Map;
import java.util.Set;

/**
 * Created by Leslie on 21-10-2014.
 */
public class PhoneContact {

    private String firstName;
    private String lastName;
    private Map<String, String> emailAddress;
    private Map<String,String> phoneNumbers;
    private Drawable photo;

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
}

