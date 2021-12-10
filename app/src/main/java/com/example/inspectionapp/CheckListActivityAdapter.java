package com.example.inspectionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CheckListActivityAdapter extends ArrayAdapter<ListItem> {

    // invoke the suitable constructor of the ArrayAdapter class
    public CheckListActivityAdapter(@NonNull Context context, ArrayList<ListItem> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context.getApplicationContext(), 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_items, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        ListItem currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
//        ImageView numbersImage = currentItemView.findViewById(R.id.imageView);
//        assert currentNumberPosition != null;
//        numbersImage.setImageResource(currentNumberPosition.getNumbersImageId());

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView txtItemName = currentItemView.findViewById(R.id.textView);
        txtItemName.setText(currentNumberPosition.getItemName());

        // then according to the position of the view assign the desired TextView 2 for the same
        Button status = currentItemView.findViewById(R.id.BtnStatus);
        status.setText(currentNumberPosition.getItemStatus());

        // then return the recyclable view
        return currentItemView;
    }
}
