package com.example.nearneed;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nearneed.R;
import com.google.android.material.button.MaterialButton;

public class VolunteerResponsesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_responses);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Buttons for Anjali
        MaterialButton btnAcceptAnjali = findViewById(R.id.btnAcceptAnjali);
        MaterialButton btnMsgAnjali = findViewById(R.id.btnMsgAnjali);

        if (btnAcceptAnjali != null) {
            btnAcceptAnjali.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Anjali's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgAnjali != null) {
            btnMsgAnjali.setOnClickListener(v -> {
                Toast.makeText(this, "Messaging Anjali...", Toast.LENGTH_SHORT).show();
            });
        }

        // Buttons for Rahul
        MaterialButton btnAcceptRahul = findViewById(R.id.btnAcceptRahul);
        MaterialButton btnMsgRahul = findViewById(R.id.btnMsgRahul);

        if (btnAcceptRahul != null) {
            btnAcceptRahul.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Rahul's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgRahul != null) {
            btnMsgRahul.setOnClickListener(v -> {
                Toast.makeText(this, "Messaging Rahul...", Toast.LENGTH_SHORT).show();
            });
        }

        // Buttons for Sarah
        MaterialButton btnAcceptSarah = findViewById(R.id.btnAcceptSarah);
        MaterialButton btnMsgSarah = findViewById(R.id.btnMsgSarah);

        if (btnAcceptSarah != null) {
            btnAcceptSarah.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Sarah's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgSarah != null) {
            btnMsgSarah.setOnClickListener(v -> {
                Toast.makeText(this, "Messaging Sarah...", Toast.LENGTH_SHORT).show();
            });
        }


    }
}
