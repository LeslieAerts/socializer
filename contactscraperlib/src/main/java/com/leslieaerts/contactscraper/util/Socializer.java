package com.leslieaerts.contactscraper.util;

import android.content.Context;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */
public class Socializer {

    private ContactDatabaseAccess scraper;
    private List<PhoneContact> loadedContacts;
    private Thread loader;
    private ContactListener listener;
    private boolean threadIsRunning = false;

    public Socializer(Context context) {
        scraper = new ContactDatabaseAccess(context);

        loadedContacts = new ArrayList<PhoneContact>();
        loader = new Thread(new Runnable() {
            @Override
            public void run() {
                threadIsRunning = true;
                loadedContacts = scraper.getAllPhoneContacts();
                threadIsRunning = false;
                if (listener != null) {
                    listener.onAllContactsLoaded(loadedContacts);
                }
            }
        });
        startThreadIfNotRunning();
    }

    public PhoneContact getPhoneContactByName(String partialName) {
        return scraper.getPhoneContactByName(partialName);
    }

    public List<PhoneContact> getAllPhoneContactsOld() {
        return scraper.getAllPhoneContacts();
    }

    public List<PhoneContact> getAllPhoneContacts() {
        waitForThread();

        return loadedContacts;
    }

    public List<PhoneContact> getFilteredContacts(String filter) {

        waitForThread();
        List<PhoneContact> copyList = new ArrayList<PhoneContact>(loadedContacts);
        List<PhoneContact> filters = new ArrayList<PhoneContact>();

        for (PhoneContact contact : copyList) {
            CharSequence cs = filter.toLowerCase();
            if (contact.getDisplayName().toLowerCase().contains(cs)) {
                filters.add(contact);
            }
        }
        return filters;
    }

    public void loadPhoneContactsAsync(List<PhoneContact> phoneContacts) {
        throw new UnsupportedOperationException();
    }

    private void startThreadIfNotRunning() {
        if (!threadIsRunning) {
            loader.start();
        }
    }

    private void waitForThread()
    {
        try {
            loader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
