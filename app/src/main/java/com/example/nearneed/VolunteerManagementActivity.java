package com.example.nearneed;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nearneed.R;
import com.google.android.material.button.MaterialButton;

public class VolunteerManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_management);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        TextView btnPromoteParth = findViewById(R.id.btnPromoteParth);
        TextView btnPromoteMehak = findViewById(R.id.btnPromoteMehak);

        if (btnPromoteParth != null) {
            btnPromoteParth.setOnClickListener(v -> {
                Toast.makeText(this, "Promoted Parth Sharma to Accepted!", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnPromoteMehak != null) {
            btnPromoteMehak.setOnClickListener(v -> {
                Toast.makeText(this, "Promoted Mehak to Accepted!", Toast.LENGTH_SHORT).show();
            });
        }

        MaterialButton btnAddSlot = findViewById(R.id.btnAddSlot);
        MaterialButton btnCloseApps = findViewById(R.id.btnCloseApps);

        if (btnAddSlot != null) {
            btnAddSlot.setOnClickListener(v -> {
                Toast.makeText(this, "Adding a new volunteer slot...", Toast.LENGTH_SHORT).show();
            });
        }

        if (btnCloseApps != null) {
            btnCloseApps.setOnClickListener(v -> {
                Toast.makeText(this, "Applications closed for this post.", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
