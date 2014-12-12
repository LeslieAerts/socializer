package com.leslieaerts.contactscraper.util;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

/**
 * Created by Leslie on 12-12-2014.
 */
public interface ContactListener {

    public void  onContactLoaded(PhoneContact contact);

    public void onAllContactsLoaded(List<PhoneContact> contacts);
}
