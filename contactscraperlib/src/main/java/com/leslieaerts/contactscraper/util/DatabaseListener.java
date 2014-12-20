package com.leslieaerts.contactscraper.util;

import com.leslieaerts.contactscraper.domain.PhoneContact;

/**
 * Created by Leslie on 13-12-2014.
 */
public interface DatabaseListener {

    public void onProgress(PhoneContact contact);
}
