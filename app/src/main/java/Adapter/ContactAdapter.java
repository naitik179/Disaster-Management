package Adapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.disaster_management_v2.R;

import java.util.ArrayList;

import Model.Contacts;

public class ContactAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<Contacts> firebrigadelist;
    public ArrayList<Contacts> orig;
    LayoutInflater linf;
    View view;
    int positionGlobal;

    public ContactAdapter(Context context, ArrayList<Contacts> firebrigadelist) {
        super();
        this.context = context;
        this.firebrigadelist = firebrigadelist;
        linf=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class ContactHolder
    {
        TextView name;
        TextView mob;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<Contacts> results = new ArrayList<Contacts>();
                if (orig == null)
                    orig = firebrigadelist;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final Contacts g : orig) {
                            if (g.getName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                firebrigadelist = (ArrayList<Contacts>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return firebrigadelist.size();
    }

    @Override
    public Object getItem(int position) {
        return  firebrigadelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactHolder holder;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(R.layout.searchable, parent, false);
            holder=new ContactHolder();
            holder.name=(TextView) convertView.findViewById(R.id.txtName);
            holder.mob=(TextView) convertView.findViewById(R.id.txtMob);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ContactHolder) convertView.getTag();
        }

        view=convertView;
        positionGlobal=position;
        holder.name.setText(firebrigadelist.get(position).getName());
        holder.mob.setText(String.valueOf(firebrigadelist.get(position).getContact()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+firebrigadelist.get(positionGlobal).getContact()));
                view.getContext().startActivity(intent);

            }
        });
        return convertView;
    }

}