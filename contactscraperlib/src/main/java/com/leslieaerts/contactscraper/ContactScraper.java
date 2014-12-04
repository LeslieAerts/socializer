package com.leslieaerts.contactscraper;

import android.content.Context;
import android.os.AsyncTask;

import com.leslieaerts.contactscraper.domain.PhoneContact;
import com.leslieaerts.contactscraper.util.ContactListener;
import com.leslieaerts.contactscraper.util.ScrapeSystem;

import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */
public class ContactScraper {
    private static ScrapeSystem scraper;
    private static ContactScraper instance;
    private static ContactListener contactListener;

    public ContactScraper(Context context) {
        scraper = new ScrapeSystem(context);
    }

    public static ContactScraper getInstance(Context context) {
        if (instance == null) {
            instance = new ContactScraper(context);
        }
        return instance;
    }

    public PhoneContact getPhoneContactByName(String partialName) {
        return scraper.getPhoneContactByName(partialName);
    }

    public static void getAllPhoneContactsAsynchronous() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<PhoneContact> allPhoneContacts = scraper.getAllPhoneContacts();
                contactListener.onContactListLoaded(allPhoneContacts);
            }
        });
        t.start();
    }
}
