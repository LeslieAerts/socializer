package com.leslieaerts.contactscraper;

import android.content.Context;
import android.os.AsyncTask;

import com.leslieaerts.contactscraper.domain.PhoneContact;
import com.leslieaerts.contactscraper.util.ScrapeSystem;

import java.util.List;

/**
 * Created by Leslie on 25-10-2014.
 */
public class ContactScraper {

    private static ScrapeSystem scraper;
    private static ContactScraper instance;

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

    static class LoadContactTask extends AsyncTask<Void, Void, Boolean> {
        List<PhoneContact> allPhoneContacts = null;

        public LoadContactTask() {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            allPhoneContacts = scraper.getAllPhoneContacts();
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    public static List<PhoneContact> getAllPhoneContacts() {
        return scraper.getAllPhoneContacts();
    }

    public static void loadPhoneContactsAsync(List<PhoneContact> phoneContacts) {

    }
}
