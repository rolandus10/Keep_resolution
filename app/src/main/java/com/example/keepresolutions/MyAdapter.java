package com.example.keepresolutions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    Context mContext;

    ArrayList <Resolution> myData;
    OnClickListner mOnClickListner;

    public MyAdapter(Context mContext, ArrayList<Resolution> myData, OnClickListner onClickListner) {
        this.mContext = mContext;
        this.myData = myData;
        this.mOnClickListner = onClickListner;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.resolution_item, viewGroup, false);

        return new MyHolder(layout,mOnClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {

        //bind data here

        myHolder.resolution_name.setText(myData.get(position).getName());
        myHolder.description.setText(myData.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }



    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView resolution_name;
        TextView description;
        OnClickListner onClickListner;

        public MyHolder(@NonNull View itemView, OnClickListner onClickListner) {
            super(itemView);

            this.onClickListner = onClickListner;
            resolution_name = itemView.findViewById(R.id.resolution_name);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListner.onClickListner(getAdapterPosition());

        }
    }

    public interface OnClickListner{
        void onClickListner(int position);
    }


}
