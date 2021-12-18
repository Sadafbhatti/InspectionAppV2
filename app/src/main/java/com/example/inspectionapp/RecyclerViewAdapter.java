package com.example.inspectionapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

//        public interface OnItemClickListener {
//            void onItemClick(ListItem item);
//        }

       //-- private final OnItemClickListener listener;
        public static ArrayList<ListItem> dataSet;
    //--    private LayoutInflater mInflater;
        private  Context context;

    public RecyclerViewAdapter(Context context, ArrayList<ListItem> dataSet) {
//        super();
        this.context=context;
        this.dataSet = dataSet;
        //    this.mInflater = LayoutInflater.from(context);
        //-- this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view =LayoutInflater.from(context).inflate(R.layout.savedlist_row_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
// Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.getFinding_textView().setText(dataSet.get(position).getFindings());
        holder.getRecomendation_textView().setText(dataSet.get(position).getRecommendations());
        holder.getNotes_textView().setText(dataSet.get(position).getNotes());
        Glide.with(context).load(dataSet.get(position).getProblemPic()).into(holder.ProblemPic_ImageView);
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            //--    listener.onItemClick(dataSet.get(position));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView finding_textView;
            private final TextView recomendation_textView;
            private final TextView notes_textView;
            private final ImageView ProblemPic_ImageView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View
                finding_textView = (TextView) view.findViewById(R.id.txtFinding);
                recomendation_textView = (TextView) view.findViewById(R.id.txtRecommendation);
                notes_textView = (TextView) view.findViewById(R.id.txtNotes);
                ProblemPic_ImageView = (ImageView) view.findViewById(R.id.txtProblemPic);

            }

            public TextView getFinding_textView() {
                return finding_textView;
            }

            public TextView getRecomendation_textView() {
                return recomendation_textView;
            }

            public TextView getNotes_textView() {
                return notes_textView;
            }

            public ImageView getProblemPic_ImageView() {
                return ProblemPic_ImageView;
            }
    }
}