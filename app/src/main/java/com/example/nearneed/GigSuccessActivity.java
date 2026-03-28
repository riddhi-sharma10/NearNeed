package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GigSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig_success);

        // All CTAs go back to the main feed
        findViewById(R.id.btnClose).setOnClickListener(v -> goHome());
        findViewById(R.id.btnBackToFeed).setOnClickListener(v -> goHome());
        findViewById(R.id.btnViewMyPost).setOnClickListener(v -> {
            // TODO: navigate to the created post's detail page
            goHome();
        });
    }

    private void goHome() {
        Intent intent = new Intent(this, MainActivity.class);
        // Clears the entire Create Post back-stack (CreatePost → CreateGig → Step2 → Success)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
