package com.leslieaerts.contactscraper;

import android.content.Context;

import com.leslieaerts.contactscraper.domain.PhoneContact;
import com.leslieaerts.contactscraper.util.ScrapeSystem;

import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */
public class ContactScraper {

    private static ScrapeSystem scraper;
    private static ContactScraper instance;

    public ContactScraper(Context context)
    {
        scraper = new ScrapeSystem(context);
    }

    public static ContactScraper getInstance(Context context) {
        if (instance == null) {
            instance = new ContactScraper(context);

        }
        return instance;
    }

    public static void initialize(Context context) {

    }

    public PhoneContact getPhoneContactByName(String firstName, String lastName) {
        return scraper.getPhoneContactByName(firstName);
    }

    public static List<PhoneContact> getAllPhoneContacts() {
        return scraper.getAllPhoneContacts();
    }

}
