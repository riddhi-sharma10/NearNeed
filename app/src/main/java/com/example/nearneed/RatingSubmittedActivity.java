package com.example.nearneed;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class RatingSubmittedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_submitted);

        TextView btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        MaterialButton btnBackToFeed = findViewById(R.id.btnBackToFeed);
        if (btnBackToFeed != null) {
            btnBackToFeed.setOnClickListener(v -> {
                // Return to feed
                android.content.Intent intent = new android.content.Intent(RatingSubmittedActivity.this, MainActivity.class);
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }
        
        // Bottom Navigation handled via universal helper
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }
}
