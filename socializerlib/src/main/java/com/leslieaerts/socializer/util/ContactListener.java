package com.leslieaerts.socializer.util;

import com.leslieaerts.socializer.domain.PhoneContact;

import java.util.List;

/**
 * Created by Leslie on 12-12-2014.
 */
public interface ContactListener {

    /**
     * Event thrown when all contacts are loaded
     * @param contact
     */
    public void onContactLoaded(PhoneContact contact);

    public void onAllContactsLoaded(List<PhoneContact> contacts);
}
