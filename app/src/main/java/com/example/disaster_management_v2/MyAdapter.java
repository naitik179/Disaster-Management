package com.example.disaster_management_v2;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.disaster_management_v2.R;

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

        return new ViewHolder(v, context, (ArrayList<ListItem>) listItems);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.title.setText(holder.title.getText() + String.valueOf(position+1));
        holder.name.setText(String.valueOf(listItem.getName()));
        holder.gender.setText(String.valueOf(listItem.getGender()));


    }

    @Override
    public int getItemCount() {
        return listItems.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView gender;
        public TextView family_Members;
        public TextView title;

        public ViewHolder(View view, Context ctx, ArrayList<ListItem> items) {
            super(view);
            listItems = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);

            title=(TextView) view.findViewById(R.id.title);
            name = (TextView) view.findViewById(R.id.name);
            gender = (TextView) view.findViewById(R.id.gender);
            //family_Members = (TextView) view.findViewById(R.id.family_members);

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


