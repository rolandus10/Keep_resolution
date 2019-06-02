package com.example.keepresolutions;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddView extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);


        //init db
        final ResolutionBDD resolutionBDD = new ResolutionBDD(this);

        //init editText
        final EditText name_add = findViewById(R.id.name_add);
        final EditText description_add = findViewById(R.id.description_add);
        final EditText reason_add = findViewById(R.id.reason_add);
        final EditText damage_add = findViewById(R.id.damage_add);

        //init button
        Button addButton = findViewById(R.id.ajouter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name  = name_add.getText().toString();
                String descripion = description_add.getText().toString();
                String reason = reason_add.getText().toString();
                String damage = damage_add.getText().toString();

                if (!name.equals("") && !descripion.equals("") && !reason.equals("") && !damage.equals("") ){

                    //create object for adding in db
                    Resolution resolution = new Resolution();
                    resolution.setName(name_add.getText().toString());
                    resolution.setDescription(description_add.getText().toString());
                    resolution.setReason(reason_add.getText().toString());
                    resolution.setDamage(damage_add.getText().toString());

                    // adding resolution to db
                    resolutionBDD.openForWrite();
                    resolutionBDD.insertResolution(resolution);
                    resolutionBDD.close();

                    // cleanig editText
                    name_add.setText("");
                    description_add.setText("");
                    reason_add.setText("");
                    damage_add.setText("");

                    Toast.makeText(getApplicationContext(),"Resolution enregistrée",
                            Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(getApplicationContext(),"Veillez remplir tous les champs",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabBack);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddView.this,MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
