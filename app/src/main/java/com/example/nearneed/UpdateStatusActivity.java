package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;

public class UpdateStatusActivity extends AppCompatActivity {

    private RadioButton radioPending, radioCompleted;
    private CardView cardPending, cardCompleted;
    private String selectedApplicantName = "Rahul S.";
    private String gigId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        // Get data from intent
        if (getIntent() != null) {
            selectedApplicantName = getIntent().getStringExtra("applicantName");
            gigId = getIntent().getStringExtra("gigId");
        }

        // ── Back Button ─────────────────────────────────────────────────────
        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // ── Radio Buttons ──────────────────────────────────────────────────
        radioPending = findViewById(R.id.radioPending);
        radioCompleted = findViewById(R.id.radioCompleted);

        // ── Cards for selection ────────────────────────────────────────────
        cardPending = findViewById(R.id.cardWorkPending);
        cardCompleted = findViewById(R.id.cardWorkCompleted);

        // Default status: Work in Progress
        radioPending.setChecked(true);

        // ── Radio Button Click Listeners ──────────────────────────────────
        if (radioPending != null) {
            radioPending.setOnClickListener(v -> {
                radioCompleted.setChecked(false);
                radioPending.setChecked(true);
            });
        }

        if (radioCompleted != null) {
            radioCompleted.setOnClickListener(v -> {
                radioPending.setChecked(false);
                radioCompleted.setChecked(true);
            });
        }

        // ── Card Click Listeners for easier selection ──────────────────────
        if (cardPending != null) {
            cardPending.setOnClickListener(v -> {
                radioPending.setChecked(true);
                radioCompleted.setChecked(false);
            });
        }

        if (cardCompleted != null) {
            cardCompleted.setOnClickListener(v -> {
                radioCompleted.setChecked(true);
                radioPending.setChecked(false);
            });
        }

        // ── Save Status Button ─────────────────────────────────────────────
        MaterialButton btnSaveStatus = findViewById(R.id.btnSaveStatus);
        if (btnSaveStatus != null) {
            btnSaveStatus.setOnClickListener(v -> {
                String status = radioPending.isChecked() ? "In Progress" : "Completed";
                Toast.makeText(this, "Status updated to: " + status, Toast.LENGTH_SHORT).show();

                // If work is completed, navigate to rating page
                if (radioCompleted.isChecked()) {
                    Intent ratingIntent = new Intent(this, RateVolunteersActivity.class);
                    ratingIntent.putExtra("gigId", gigId);
                    ratingIntent.putExtra("applicantName", selectedApplicantName);
                    ratingIntent.putExtra("applicantId", "rahul_id"); // Should come from selection
                    startActivity(ratingIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                } else {
                    finish();
                }
            });
        }

        // ── Cancel Button ──────────────────────────────────────────────────
        MaterialButton btnCancel = findViewById(R.id.btnCancel);
        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> finish());
        }

        // ── Bottom Navigation ──────────────────────────────────────────────
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }
}
