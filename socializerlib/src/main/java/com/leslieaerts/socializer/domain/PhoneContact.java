package com.leslieaerts.socializer.domain;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * A contactperson that's in your phone's local contact database.
 * Created by Leslie on 25-10-2014.
 */
public class PhoneContact {

    /**
     * The name used when displaying the contact.
     */
    private String displayName;

    /**
     * A map containing all the contact's email addresses, Key = type of email, Value = Email address
     */
    private Map<String, String> emailAddresses;

    /**
     * A map containing all the contact's phonenumbers, Key = type of phone number, Value = phone numbers
     */
    private Map<String, String> phoneNumbers;

    /**
     * The contact's display photo
     */
    private Bitmap photo;

    /**
     * The key used to find the contact in the contact database
     */
    private String lookupKey;

    /**
     * The id for the contact in the database
     */
    private String contactId;

    /**
     * Constructor
     */
    public PhoneContact() {
        this.emailAddresses = new HashMap<String, String>();
        this.phoneNumbers = new HashMap<String, String>();
    }

    public String getDisplayName() {
        return displayName;
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

    public void setDisplayName(String fullName) {
        this.displayName = fullName;
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

    /**
     * Add a phonenumber to the map
     * @param phoneNumber
     * @param phoneNumberType
     */
    public void addPhoneNumber(String phoneNumber, String phoneNumberType) {
        phoneNumbers.put(phoneNumberType, phoneNumber);
    }

    /**
     * Add an email address to the map
     * @param type
     * @param address
     */
    public void addEmailAddress(String type, String address) {
        emailAddresses.put(type, address);
    }

    /**
     * Gets the first phonenumber in the map. For debugging purposes only
     * @return The first phonenumber, or an empty string if nothing can be found.
     */
    public String getMainPhoneNumber() {
        String phoneNumber = "";

        for (Map.Entry<String, String> entry : phoneNumbers.entrySet()) {
            phoneNumber = entry.getValue();
            break;
        }
        return phoneNumber;
    }

    public String getMainEmailAddress() {
        String emailAddress = "";
        for (Map.Entry<String, String> map : emailAddresses.entrySet()) {
            emailAddress = map.getValue();
            break;
        }
        return emailAddress;
    }

    /**
     * Adds multiple phone numbers at once
     * @param phoneMap
     */
    public void addPhoneNumbers(Map<String, String> phoneMap) {
        this.phoneNumbers.putAll(phoneMap);
    }

    /**
     * Adds multiple email addresses at once
     * @param emailsMap
     */
    public void addEmailAddresses(Map<String, String> emailsMap) {
        this.emailAddresses.putAll(emailsMap);
    }
}
