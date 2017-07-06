package contactscraper.leslieaerts.com.contactscraper.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leslieaerts.socializer.domain.PhoneContact;

import java.util.List;

import contactscraper.leslieaerts.com.contactscraper.R;

/**
 * Created by Leslie on 16-11-2014.
 */
public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<PhoneContact> contacts;

    public ContactAdapter(Context context, List<PhoneContact> contacts) {
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
        TextView contactPhone;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ContactHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.contact_item, parent, false);

            holder = new ContactHolder();
            holder.contactName = (TextView) convertView.findViewById(R.id.lbContactName);
            holder.contactEmail = (TextView) convertView.findViewById(R.id.lbContactEmail);
            holder.contactPhoto = (ImageView) convertView.findViewById(R.id.imageView);
            holder.contactPhone = (TextView) convertView.findViewById(R.id.lbContactPhoneNumber);

            convertView.setTag(holder);
        } else {
            holder = (ContactHolder) convertView.getTag();
        }
        PhoneContact contact = contacts.get(position);
        holder.contactName.setText(contact.getDisplayName());
        holder.contactPhone.setText(contact.getMainPhoneNumber());
        holder.contactEmail.setText(contact.getMainEmailAddress());
        holder.contactPhoto.setImageBitmap(contact.getPhoto());

        return convertView;
    }
}
