package contactscraper.leslieaerts.com.contactscraper;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.leslieaerts.contactscraper.ContactScraper;
import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;


public class ExampleActivity extends Activity {

    ContactScraper scraper = new ContactScraper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ContactFragment contactFragment = new ContactFragment();
        List<PhoneContact> allPhoneContacts = scraper.getAllPhoneContacts();
        contactFragment.setContactList(allPhoneContacts);

        transaction.add(R.id.fragment_container,contactFragment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
