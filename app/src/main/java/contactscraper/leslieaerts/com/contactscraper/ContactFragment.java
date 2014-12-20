package contactscraper.leslieaerts.com.contactscraper;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.leslieaerts.socializer.util.ContactListener;
import com.leslieaerts.socializer.util.Socializer;
import com.leslieaerts.socializer.domain.PhoneContact;

import java.util.ArrayList;
import java.util.List;

import contactscraper.leslieaerts.com.contactscraper.util.ContactAdapter;

/**
 * Created by Leslie on 26-10-2014.
 */
public class ContactFragment extends Fragment {

    private ListView list;
    private Socializer socializer;
    private ContactAdapter contactAdapter;
    private List<PhoneContact> contacts;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);
        list = (ListView) view.findViewById(R.id.list);

        /**
         * Create an adapter just like any other
         */
        contacts = new ArrayList<PhoneContact>();
        contactAdapter = new ContactAdapter(getActivity(), contacts);
        list.setAdapter(contactAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Toast.makeText(getActivity(), "Loading contacts...", Toast.LENGTH_SHORT).show();

        /**
         * All you have to do is create a new socializer object and set the listener.
         * Implement the listener to asynchronously get each contact's data.
         * If you don't want to use the listener, you can also use the method
         * socializer.getAllPhoneContacts();
         * This blocks the thread until the contacts are finished loading.
         */
        socializer = new Socializer(getActivity());
        socializer.setContactListener(new ContactListener() {


            @Override
            public void onContactLoaded(final PhoneContact contact) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!contacts.contains(contact)) {
                            contacts.add(contact);
                            contactAdapter.notifyDataSetChanged();
                        }
                    }
                });

            }

            @Override
            public void onAllContactsLoaded(List<PhoneContact> con) {
                contacts.clear();
                contacts.addAll(con);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Loaded " + contacts.size() + " contacts.", Toast.LENGTH_SHORT).show();
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    /**
     * Alternatively, you can use a filter for the name to obtain a specific contact list, filtered with the filterString.
     *
     * @param filterString
     */
    public void loadFilteredContacts(String filterString) {
        List<PhoneContact> filteredContacts = socializer.getFilteredContacts(filterString);

        contacts.clear();
        contacts.addAll(filteredContacts);
        contactAdapter.notifyDataSetChanged();
    }
}
