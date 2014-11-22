package com.leslieaerts.contactscraper.domain;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Leslie on 25-10-2014.
 */
public class PhoneContact {


    private String firstName;
    private String lastName;
    private Map<String, String> emailAddresses;
    private Map<String, String> phoneNumbers;
    private Bitmap photo;
    private String lookupKey;
    private String contactId;

    public PhoneContact() {
        this.emailAddresses = new HashMap<String, String>();
        this.phoneNumbers = new HashMap<String, String>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Map<String, String> getEmailAddresses() {
        return emailAddresses;
    }

    public Map<String, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setLookupKey(String lookupKey) {
        this.lookupKey = lookupKey;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public void setFullName(String fullName) {
        String[] split = fullName.split(" ");

        for (int i = 0; i < split.length; i++) {
            if (i == 0) {
                this.firstName = split[0];
            } else {
                this.lastName += " " + split[1];
            }
        }
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public void addPhoneNumber(String phoneNumber, String phoneNumberType) {
        phoneNumbers.put(phoneNumberType, phoneNumber);
    }

    public void addEmailAddress(String type, String address) {
        emailAddresses.put(type, address);
    }

    public String getMainPhoneNumber() {
        String phoneNumber = "No phonenumber found";
        for (Map.Entry<String, String> entry : phoneNumbers.entrySet()) {
            phoneNumber = entry.getValue();
        }
        return phoneNumber;
    }

    public String getMainEmailAddress() {
        String emailAddress = "No email found";
        for (Map.Entry<String, String> map : emailAddresses.entrySet()) {
            emailAddress = map.getValue();
            break;
        }
        return emailAddress;
    }
}
