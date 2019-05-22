package com.example.keepresolutions;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddView.class);
                startActivity(intent);

            }
        });

        //init db
        final ResolutionBDD resolutionBDD = new ResolutionBDD(this);

        // init view
        recyclerView = findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(this);

        resolutionBDD.openForRead();
        myData = resolutionBDD.getAllResolutions();
        if (myData != null){
            mAdapter = new MyAdapter(this, myData, this);
            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(layoutManager);
        }
        else {

            Intent intent = new Intent(MainActivity.this, AddView.class);
            startActivity(intent);

            Toast.makeText(getApplicationContext(),"Ajoutez une résolution",Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onClickListner(int position) {
        // ajouter une autre activitée ici
        Resolution resolution = myData.get(position);

        Intent intent = new Intent(this, ViewResolution.class);
        // envoyer les données pour les affichers
        intent.putExtra( "name",resolution.getName());
        intent.putExtra( "description",resolution.getDescription());
        intent.putExtra( "reason",resolution.getReason());
        intent.putExtra( "damage",resolution.getDamage());

        startActivity(intent);

    }
}
