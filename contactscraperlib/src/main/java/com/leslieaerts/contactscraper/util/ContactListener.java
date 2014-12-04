package com.leslieaerts.contactscraper.util;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

/**
 * Created by aertsl on 27-11-2014.
 */
public interface ContactListener {

    public void onContactListLoaded(List<PhoneContact> allPhoneContacts);
}
