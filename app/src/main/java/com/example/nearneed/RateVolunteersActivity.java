package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class RateVolunteersActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private EditText reviewText;
    private String applicantName = "";
    private String gigId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_volunteers);

        // Get data from intent
        if (getIntent() != null) {
            applicantName = getIntent().getStringExtra("applicantName");
            gigId = getIntent().getStringExtra("gigId");
        }

        // ── Back Button ─────────────────────────────────────────────────────
        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // ── Applicant Name ──────────────────────────────────────────────────
        TextView tvApplicantName = findViewById(R.id.tvApplicantName);
        if (tvApplicantName != null) {
            tvApplicantName.setText(applicantName);
        }

        // ── Rating Bar ──────────────────────────────────────────────────────
        ratingBar = findViewById(R.id.ratingBar);
        TextView tvRatingValue = findViewById(R.id.tvRatingValue);
        if (ratingBar != null) {
            ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
                // Update status text based on rating
                if (tvRatingValue != null) {
                    if (rating == 0) {
                        tvRatingValue.setText("");
                    } else if (rating < 2) {
                        tvRatingValue.setText("Poor");
                    } else if (rating < 3) {
                        tvRatingValue.setText("Average");
                    } else if (rating < 4) {
                        tvRatingValue.setText("Good");
                    } else if (rating < 5) {
                        tvRatingValue.setText("Very Good");
                    } else {
                        tvRatingValue.setText("Excellent!");
                    }
                }
            });
        }

        // ── Review Text ─────────────────────────────────────────────────────
        reviewText = findViewById(R.id.etReviewText);

        // ── Submit Rating Button ────────────────────────────────────────────
        MaterialButton btnSubmitRating = findViewById(R.id.btnSubmitRating);
        if (btnSubmitRating != null) {
            btnSubmitRating.setOnClickListener(v -> submitRating());
        }

        // ── Cancel Button ───────────────────────────────────────────────────
        MaterialButton btnCancel = findViewById(R.id.btnCancelRating);
        if (btnCancel != null) {
            btnCancel.setOnClickListener(v -> finish());
        }

        // ── Bottom Navigation ───────────────────────────────────────────────
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }

    private void submitRating() {
        float rating = ratingBar.getRating();
        String review = reviewText.getText().toString().trim();

        if (rating == 0) {
            Toast.makeText(this, "Please provide a rating", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show success message
        Toast.makeText(this, "Rating of " + rating + " stars submitted for " + applicantName,
                Toast.LENGTH_SHORT).show();

        // Navigate back to gig detail or task completion
        Intent resultIntent = new Intent(this, GigDetailActivity.class);
        resultIntent.putExtra("gigId", gigId);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(resultIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
