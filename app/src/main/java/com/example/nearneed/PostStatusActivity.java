package com.example.nearneed;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nearneed.R;
import com.google.android.material.button.MaterialButton;

public class PostStatusActivity extends AppCompatActivity {

    private boolean isMichaelChecked = false;
    private boolean isElenaChecked = true;  // Default checked
    private boolean isSofiaChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_status);

        // Header
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // Completion Status Buttons
        MaterialButton btnYesHelped = findViewById(R.id.btnYesHelped);
        MaterialButton btnNotYet = findViewById(R.id.btnNotYet);

        if (btnYesHelped != null) {
            btnYesHelped.setOnClickListener(v -> {
                Toast.makeText(this, "Great! Task marked as completed.", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnNotYet != null) {
            btnNotYet.setOnClickListener(v -> {
                Toast.makeText(this, "Marked as in progress.", Toast.LENGTH_SHORT).show();
            });
        }

        // Volunteer Selection
        LinearLayout rowMichael = findViewById(R.id.rowMichael);
        ImageView cbMichael = findViewById(R.id.cbMichael);
        if (rowMichael != null) {
            rowMichael.setOnClickListener(v -> {
                isMichaelChecked = !isMichaelChecked;
                updateCheckbox(cbMichael, isMichaelChecked);
            });
        }

        LinearLayout rowElena = findViewById(R.id.rowElena);
        ImageView cbElena = findViewById(R.id.cbElena);
        if (rowElena != null) {
            // Elena is pre-checked by default
            updateCheckbox(cbElena, isElenaChecked);
            rowElena.setOnClickListener(v -> {
                isElenaChecked = !isElenaChecked;
                updateCheckbox(cbElena, isElenaChecked);
            });
        }

        LinearLayout rowSofia = findViewById(R.id.rowSofia);
        ImageView cbSofia = findViewById(R.id.cbSofia);
        if (rowSofia != null) {
            rowSofia.setOnClickListener(v -> {
                isSofiaChecked = !isSofiaChecked;
                updateCheckbox(cbSofia, isSofiaChecked);
            });
        }

        // Bottom Navigation
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }

    private void updateCheckbox(ImageView checkbox, boolean isChecked) {
        if (isChecked) {
            checkbox.setImageResource(R.drawable.ic_check_solid_white);
            checkbox.setImageTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#2563EB")));
            checkbox.setBackgroundResource(R.drawable.bg_circle_solid_red);
            checkbox.setBackgroundTintList(android.content.res.ColorStateList.valueOf(android.graphics.Color.parseColor("#2563EB")));
            checkbox.setPadding(4, 4, 4, 4);
        } else {
            checkbox.setImageDrawable(null);
            checkbox.setBackgroundResource(R.drawable.bg_card_outline);
            checkbox.setBackgroundTintList(null);
        }
    }
}
