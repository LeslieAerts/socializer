package com.leslieaerts.socializer.util;

import com.leslieaerts.socializer.domain.PhoneContact;

/**
 * Created by Leslie on 13-12-2014.
 */
public interface DatabaseListener {

    public void onProgress(PhoneContact contact);
}
