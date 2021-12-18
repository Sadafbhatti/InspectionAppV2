package com.example.inspectionapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class CheckListActivityAdapter extends BaseAdapter {
    Checklist checklistitems;

    private  Context context; //context
    private ArrayList<ListItem> items; //data source of the list adapter
    //public constructor
    public CheckListActivityAdapter(Context context, ArrayList<ListItem> items) {
       // checklistitems=new Checklist();
        checklistitems= myApp.getInstance().getChecklist();
       // items=checklistitems.getlistItem();

        items=myApp.getInstance().getChecklist().getlistItem();
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
       //--- ListItem currentItemORIG = (ListItem) getItem(position);
        ListItem currentItem=myApp.getInstance().getChecklist().getlistItem().get(position);

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
                Toast.makeText(context.getApplicationContext(), "You updated item's status!", Toast.LENGTH_SHORT).show();

                if (v == btnStatus) {
                    if (btnStatus.getText() == "OK") {
                        btnStatus.setText("RECOMMENDED");
                        myApp.getInstance().getChecklist().getlistItem().get(position).setItemStatus("RECOMMENDED");
                        btnStatus.setBackgroundResource(R.color.Orange);
                        // items.set(position,new ListItem(currentItem.itemName,"RECOMMENDED"));
                    } else if (btnStatus.getText() == "RECOMMENDED") {
                        btnStatus.setText("REQUIRED");
                        currentItem.setItemStatus("REQUIRED");
                        myApp.getInstance().getChecklist().getlistItem().get(position).setItemStatus("REQUIRED");
                        btnStatus.setBackgroundResource(R.color.Red);

                        //items.set(position,new ListItem(currentItem.itemName,"REQUIRED"));
                    } else {
                        btnStatus.setText("OK");
                        currentItem.setItemStatus("OK");
                        btnStatus.setBackgroundResource(R.color.Green);
                        myApp.getInstance().getChecklist().getlistItem().get(position).setItemStatus("OK");
                        btnStatus.setBackgroundResource(R.color.Green);

                        // items.set(position,new ListItem(currentItem.itemName,"OK"));
                    }
                }

            }
        });

        // returns the view for the current row
        return convertView;
    }
}
