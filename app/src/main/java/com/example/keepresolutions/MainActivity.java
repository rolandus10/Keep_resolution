package com.example.keepresolutions;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnClickListner{

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Resolution> myData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init view

        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        myData = new ArrayList<>();

        //remplir le tableau avec les données de l adb

        Resolution r = new Resolution("aaaa","bbbb", "ccccc","ddddd");
        Resolution r2 = new Resolution("eeee","ffff", "gggg","hhhh");
        Resolution r3 = new Resolution("iiii","jjjj", "kkkk","llll");

        myData.add(r);
        myData.add(r2);
        myData.add(r3);


        mAdapter = new MyAdapter(this, myData, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    public void onClickListner(int position) {
        // ajouter une autre activitée ici
        Resolution resolution = myData.get(position);

        // naviguer vers la page someting
        Intent intent = new Intent(this, ViewResolution.class);
        // envoyer les données pour les affichers
        intent.putExtra( "name",resolution.getName());
        intent.putExtra( "description",resolution.getDescription());
        intent.putExtra( "reason",resolution.getReason());
        intent.putExtra( "damage",resolution.getDamage());

        startActivity(intent);

        Toast.makeText(getApplicationContext(),"Reason: " + resolution.getReason(),Toast.LENGTH_LONG).show();

    }
}
