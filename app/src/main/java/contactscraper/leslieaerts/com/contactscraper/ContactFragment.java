package contactscraper.leslieaerts.com.contactscraper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leslieaerts.contactscraper.ContactScraper;
import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

/**
 * Created by Leslie on 26-10-2014.
 */
public class ContactFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setContactList(List<PhoneContact> allPhoneContacts) {
        ContactScraper scraper = ContactScraper.getInstance(getActivity());
    }
}
