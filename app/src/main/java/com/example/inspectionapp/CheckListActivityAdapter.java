package com.example.inspectionapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CheckListActivityAdapter extends BaseAdapter {
    Checklist checklistitems;

    private Context context; //context
    private ArrayList<ListItem> items; //data source of the list adapter
    //public constructor
    public CheckListActivityAdapter(Context context, ArrayList<ListItem> items) {
        checklistitems=new Checklist();
       // ((myApp)getApplication()).getChecklist();

        items=checklistitems.getlistItem();
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size(); //returns total of items in the list
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); //returns list item at the specified position
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_row_items, parent, false);
        }

        // get current item to be displayed
        ListItem currentItem = (ListItem) getItem(position);

        // get the TextView for item name and itemstatus on button title
        TextView txtItemName = (TextView)
                convertView.findViewById(R.id.txtItemName);
        Button btnStatus = (Button)
                convertView.findViewById(R.id.btnStatus);

        //sets the text for item name and item description from the current item object
        txtItemName.setText(currentItem.getItemName());
        btnStatus.setText(currentItem.getItemStatus());

        //set onclicklistener for the button in here
        btnStatus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("Btn clicked ", currentItem.getItemStatus());
                Toast.makeText(context.getApplicationContext(), "You clicked Status button", Toast.LENGTH_SHORT).show();

                if (v == btnStatus) {
                    if (btnStatus.getText() == "OK") {
                        btnStatus.setText("RECOMMENDED");
                        btnStatus.setBackgroundResource(R.color.Orange);
                        items.set(position,new ListItem(currentItem.itemName,"RECOMMENDED"));
                    } else if (btnStatus.getText() == "RECOMMENDED") {
                        btnStatus.setText("REQUIRED");
                        btnStatus.setBackgroundResource(R.color.Red);
                        currentItem.setItemStatus("REQUIRED");
                        items.set(position,new ListItem(currentItem.itemName,"REQUIRED"));
                    } else {
                        btnStatus.setText("OK");
                        currentItem.setItemStatus("OK");
                        btnStatus.setBackgroundResource(R.color.Green);
                        items.set(position,new ListItem(currentItem.itemName,"OK"));
                    }
                }

            }
        });

        // returns the view for the current row
        return convertView;
    }
}


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View rowView = convertView;
//
//        if (rowView == null) {
//            LayoutInflater layoutInflater = LayoutInflater.from(context);
//            rowView = layoutInflater.inflate(R.layout.conversation, null);
//            ViewHolder viewHolder = new ViewHolder(rowView);
//            rowView.setTag(viewHolder);
//        }
//
//        final ViewHolder holder = (ViewHolder) rowView.getTag();
//
//        holder.numbers.setText(numbers.get(position));
//
//        return rowView;
//    }
//
//public class ViewHolder {
//
//    private final TextView numbers;
//
//    public ViewHolder(View v) {
//        this.numbers = (TextView) v.findViewById(R.id.numbertitle);
//    }
//}

/*
* package com.example.assignment_2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;
public class Product_List_Adapter extends BaseAdapter{

        Context context;

        List<Product> productList;
        LayoutInflater inflter;

        public Product_List_Adapter(Context applicationContext, List<Product> pl ) {
            this.context = context;
            this.productList = pl;

            inflter = (LayoutInflater.from(applicationContext));
        }

        @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflter.inflate(R.layout.list_item, null);
            TextView name = (TextView)view.findViewById(R.id.Item_Name);
            TextView price = (TextView)view.findViewById(R.id.Item_Price);
            TextView quentity = (TextView)view.findViewById(R.id.Item_Quentity);

            name.setText(productList.get(i).getProduct_Name());
            price.setText(String.valueOf( productList.get(i).getProduct_Price()));
            quentity.setText(String.valueOf( productList.get(i).getProduct_quentity()));

            return view;
        }


}
*/