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
        this.mOnClickListner = onClickListner;// pour rendre la résolution clickable
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // on crée le Holder en lui associant la vue model qui est resolution_item pour l'affichage
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.resolution_item, viewGroup, false);
        return new MyHolder(layout,mOnClickListner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {

        //on lie les donnée avec le Holder qui est la partie affichage (une ligne)
        myHolder.resolution_name.setText(myData.get(position).getName());
        myHolder.description.setText(myData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        // avoir le nbre d'élement de donnée pour savoir le nbre de holder à générer
        return myData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView resolution_name;
        TextView description;
        OnClickListner onClickListner;// pour rendre la résolution clickable

        public MyHolder(@NonNull View itemView, OnClickListner onClickListner) {
            super(itemView);

            // on construit le holder en le liant avec le model, le itemView est une ligne de la
            // recyclerView
            this.onClickListner = onClickListner;
            resolution_name = itemView.findViewById(R.id.resolution_name);
            description = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(this);
        }

        // pour rendre la résolution clickable
        @Override
        public void onClick(View v) {
            onClickListner.onClickListner(getAdapterPosition());

        }
    }
    // pour rendre la résolution clickable
    public interface OnClickListner{
        void onClickListner(int position);
    }

}
