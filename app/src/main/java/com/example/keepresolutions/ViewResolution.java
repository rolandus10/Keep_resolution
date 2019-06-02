package com.example.keepresolutions;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v7.app.AlertDialog.*;

public class ViewResolution extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_resolution);

        //init db
        final ResolutionBDD resolutionBDD = new ResolutionBDD(this);

        FloatingActionButton backButton = findViewById(R.id.backButton);
        final TextView name = findViewById(R.id.name_view);
        final TextView description = findViewById(R.id.description_view);
        final TextView reason = findViewById(R.id.reason_view);
        final TextView damage = findViewById(R.id.damage_view);
        Button delButton = findViewById(R.id.delButton);

        Intent intent = getIntent();


        // init des textView
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        reason.setText(intent.getStringExtra("reason"));
        damage.setText(intent.getStringExtra("damage"));
        final int id = intent.getIntExtra("id",0);

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // builde alert
                Builder builder = new Builder(ViewResolution.this);
                builder.setTitle("Confirmer la suppression");
                builder.setMessage("Voulez-vous vraiment supprimer " + name.getText() + "?");
                builder.setCancelable(false);
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                        // deleting resolution
                        resolutionBDD.openForRead();
                        resolutionBDD.removeResolution(id);
                        resolutionBDD.close();

                        Toast.makeText(getApplicationContext(),
                                name.getText() + " supprimée", Toast.LENGTH_SHORT).show();

                        Intent intent = new  Intent(ViewResolution.this, MainActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });

                builder.show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewResolution.this, MainActivity.class);
                startActivity(intent);

                // Update resolution in db
                Resolution resolution = new Resolution();
                resolution.setName(name.getText().toString());
                resolution.setDescription(description.getText().toString());
                resolution.setReason(reason.getText().toString());
                resolution.setDamage(damage.getText().toString());
                resolutionBDD.openForWrite();
                resolutionBDD.updateResolution(id,resolution);

            }
        });

    }
}
