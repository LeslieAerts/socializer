package contactscraper.leslieaerts.com.contactscraper;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.leslieaerts.contactscraper.ContactScraper;
import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

import contactscraper.leslieaerts.com.contactscraper.util.ContactAdapter;

/**
 * Created by Leslie on 26-10-2014.
 */
public class ContactFragment extends Fragment {

    ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_example, container, false);
        list = (ListView) view.findViewById(R.id.list);
        setContactList();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void setContactList() {
        ContactScraper scraper = ContactScraper.getInstance(getActivity());
        List<PhoneContact> contacts = scraper.getAllPhoneContactsAsynchronous();
        list.setAdapter(new ContactAdapter(contacts, getActivity()));
    }
}
