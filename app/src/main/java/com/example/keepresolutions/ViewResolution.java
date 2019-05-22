package com.example.keepresolutions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewResolution extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_resolution);



        FloatingActionButton backButton = findViewById(R.id.backButton);
        TextView name = findViewById(R.id.name_view);
        TextView description = findViewById(R.id.description_view);
        TextView reason = findViewById(R.id.reason_view);
        TextView damage = findViewById(R.id.damage_view);

        Intent intent = getIntent();


        // init des textView

        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        reason.setText(intent.getStringExtra("reason"));
        damage.setText(intent.getStringExtra("damage"));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewResolution.this, MainActivity.class);
                startActivity(intent);
            }
        });








    }
}
