package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class IdVerificationActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_FRONT = 1001;
    private static final int REQUEST_PICK_BACK  = 1002;

    private ImageButton btnBack;
    private MaterialButton btnSubmit;
    private TextView btnSkip;
    private android.view.View cardUploadFront, cardUploadBack;
    private boolean frontUploaded = false;
    private boolean backUploaded = false;
    private boolean isFullyVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_verification);

        btnBack = findViewById(R.id.btnBack);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSkip = findViewById(R.id.btnSkip);
        cardUploadFront = findViewById(R.id.cardUploadFront);
        cardUploadBack = findViewById(R.id.cardUploadBack);
        
        if (getIntent().getBooleanExtra("HIDE_SKIP", false)) {
            btnSkip.setVisibility(View.GONE);
        }

        // Initial state: submit button disabled/dimmed
        btnSubmit.setEnabled(false);
        btnSubmit.setAlpha(0.6f);

        setupListeners();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        cardUploadFront.setOnClickListener(v -> openImagePicker(REQUEST_PICK_FRONT));
        cardUploadBack.setOnClickListener(v  -> openImagePicker(REQUEST_PICK_BACK));

        btnSubmit.setOnClickListener(v -> {
            btnSubmit.setEnabled(false);
            btnSubmit.setText("Verifying Authenticity...");
            btnSubmit.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF4F46E5)); // Deep blue
            
            // Final verification simulation
            new android.os.Handler().postDelayed(() -> {
                isFullyVerified = true;
                btnSubmit.setText("ID Verified Successfully");
                btnSubmit.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF1D5EF3)); // Brand blue
                
                new android.os.Handler().postDelayed(() -> {
                    if (isFullyVerified) {
                        Intent intent = new Intent(this, IdVerifiedActivity.class);
                        startActivity(intent);
                    }
                }, 1000);
            }, 2500);
        });

        btnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileSuccessActivity.class);
            startActivity(intent);
        });
    }

    private void openImagePicker(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select ID Image"), requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == REQUEST_PICK_FRONT) {
                setCardUploadedState(cardUploadFront, "Front of ID");
            } else if (requestCode == REQUEST_PICK_BACK) {
                setCardUploadedState(cardUploadBack, "Back of ID");
            }
        }
    }

    private void setCardUploadedState(android.view.View card, String side) {
        // Find views in the card
        android.widget.ImageView icon = null;
        TextView title = null;
        TextView desc = null;

        if (card instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) card;
            for (int i = 0; i < group.getChildCount(); i++) {
                android.view.View child = group.getChildAt(i);
                if (child instanceof android.widget.ImageView) icon = (android.widget.ImageView) child;
                if (child instanceof android.widget.TextView) {
                    if (title == null) title = (android.widget.TextView) child;
                    else desc = (android.widget.TextView) child;
                }
            }
        }

        if (icon != null && title != null && desc != null) {
            final android.widget.ImageView fIcon = icon;
            final TextView fTitle = title;
            final TextView fDesc = desc;

            // Step 1: Processing
            fTitle.setText("Scanning ID...");
            fDesc.setText("Extracting security features...");
            fIcon.setImageResource(R.drawable.ic_search_grey);
            fIcon.setColorFilter(0xFF2563EB); // Blue

            // Step 2: Final State after simulation
            new android.os.Handler().postDelayed(() -> {
                card.setBackgroundResource(R.drawable.bg_id_uploaded);
                fIcon.setImageResource(R.drawable.ic_checked_blue);
                fIcon.setColorFilter(null); // Use resource color
                fTitle.setText(side + " verified");
                fTitle.setTextColor(0xFF1D5EF3); // Blue
                fDesc.setText("Data extracted successfully");
                fDesc.setTextColor(0xFF1D5EF3);

                if (side.contains("Front")) frontUploaded = true;
                else backUploaded = true;
                
                checkReadyToSubmit();
            }, 2000);
        }
    }

    private void checkReadyToSubmit() {
        if (frontUploaded && backUploaded) {
            btnSubmit.setEnabled(true);
            btnSubmit.setAlpha(1.0f);
            btnSubmit.setBackgroundTintList(android.content.res.ColorStateList.valueOf(0xFF1D5EF3)); // Brand blue
        }
    }
}
