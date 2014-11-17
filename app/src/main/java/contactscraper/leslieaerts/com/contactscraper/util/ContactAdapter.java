package contactscraper.leslieaerts.com.contactscraper.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leslieaerts.contactscraper.domain.PhoneContact;

import java.util.List;

/**
 * Created by Leslie on 16-11-2014.
 */
public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneContact> contacts;

    public ContactAdapter(List<PhoneContact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ContactHolder {
        ImageView contactPhoto;
        TextView contactName;
        TextView contactEmail;
        Textview contactPhone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
