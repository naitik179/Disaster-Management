package Adapter;

import android.content.Context;
import android.media.TimedText;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.disaster_management_v2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import Model.ListItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private Context context;
    private List<ListItem> listItems;

    public MyAdapter(Context context, List listItem) {
        this.context = context;
        this.listItems = listItem;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new ViewHolder(v, context, (List<ListItem>) listItems);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.name.setText(listItem.getName());
        holder.description.setText(listItem.getGender());
        holder.age.setText(listItem.getAge());
        holder.address.setText(listItem.getAddress());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }


    ////////////

    public void setListData1(List<ListItem> pdfModels) {
        this.listItems = pdfModels;
        notifyDataSetChanged();
    }





    //////////

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView description;
        public TextView age,address;


        public ViewHolder(View view, Context ctx, List<ListItem> items) {
            super(view);
            listItems = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);

            name = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.gender);
            age = (TextView) view.findViewById(R.id.age);
            address = (TextView) view.findViewById(R.id.address);

        }


        @Override
        public void onClick(View v) {
            //Get int position
            int position = getAdapterPosition();
            ListItem item = listItems.get(position);
            //  Intent intent = new Intent(context, MyActivity.class);
            Toast.makeText(context, item.getName(), Toast.LENGTH_LONG).show();
        }
    }
}


