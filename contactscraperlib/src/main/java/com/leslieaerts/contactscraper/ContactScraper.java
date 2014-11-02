package com.leslieaerts.contactscraper;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */
public class ContactScraper {

    private ScrapeSystem scraper;
    public ContactScraper() {

    }

    public PhoneContact getPhoneContactByName(String firstName, String lastName) {
        return scraper.getPhoneContactByName(firstName,lastName);
    }

    public List<PhoneContact> getAllPhoneContacts() {
        throw new UnsupportedOperationException();

    }

}
