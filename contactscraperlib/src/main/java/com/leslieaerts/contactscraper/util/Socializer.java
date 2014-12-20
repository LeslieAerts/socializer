package com.leslieaerts.contactscraper.util;

import android.content.Context;
import android.util.Log;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */

/**
 * The Socializer keeps track of all the phone contacts currently loaded. It can return phone contacts in several ways. If
 */
public class Socializer {

    private final List<PhoneContact> filterContacts;
    private ContactDatabaseAccess database;
    private List<PhoneContact> loadedContacts;
    private Thread loader;
    private ContactListener listener;
    private boolean threadIsRunning = false;

    public Socializer(Context context) {
        database = new ContactDatabaseAccess(context);
        loadedContacts = new ArrayList<PhoneContact>();
        filterContacts = new ArrayList<PhoneContact>();
        createThread();
        startThreadIfNotRunning();

        database.setDatabaseListener(new DatabaseListener() {

            @Override
            public void onProgress(PhoneContact contact) {
                listener.onContactLoaded(contact);
                loadedContacts.add(contact);
            }
        });
    }

    /**
     * Creates a separate thread that handles loading the contacts from the Database.
     */
    private void createThread() {
        loader = new Thread(new Runnable() {
            @Override
            public void run() {
                threadIsRunning = true;
                database.getAllPhoneContacts();
                threadIsRunning = false;

                if (listener != null) {
                    listener.onAllContactsLoaded(loadedContacts);
                }
            }
        });
    }

    /**
     * Safe restarting of contact thread
     */
    private void startThreadIfNotRunning() {
        if (!threadIsRunning) {
            waitForThreadToFinish();
            loader.start();
        }
    }

    /**
     * Blocks the main thread until contact thread is finished.
     */
    private void waitForThreadToFinish() {
        try {
            loader.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears all loaded contacts and start reloading them.
     */
    public void reloadContacts()
    {
        loadedContacts.clear();
        startThreadIfNotRunning();
    }
    /**
     * Return all phone contacts after they have been loaded.
     *
     * @return
     */
    public List<PhoneContact> getAllPhoneContacts() {
        waitForThreadToFinish();
        return loadedContacts;
    }

    /**
     * Returns a list of contacts based on a string filter
     *
     * @param filter
     * @return
     */
    public List<PhoneContact> getFilteredContacts(final String filter) {
        final List<PhoneContact> copyList = new ArrayList<PhoneContact>();
        copyList.addAll(loadedContacts);

        //Filtering
        String filterString = filter;
        for (PhoneContact contact : copyList) {
            CharSequence cs = filterString.toLowerCase();
            if (contact.getDisplayName().toLowerCase().contains(cs)) {
                filterContacts.add(contact);
            }
        }
        return filterContacts;
    }


    /**
     * Sets the listener for events of loading contacts
     * @param listener
     */
    public void setContactListener(ContactListener listener) {
        this.listener = listener;
    }
}
