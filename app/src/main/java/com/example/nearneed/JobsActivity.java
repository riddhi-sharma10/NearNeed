package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class JobsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // "Post a Repair" button
        findViewById(R.id.btnPostRepair).setOnClickListener(v -> {
            navigateToCreatePost();
        });

        // Middle FAB
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            navigateToCreatePost();
        });

        // Bottom Navigation
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavView);
        if (bottomNav != null) {
            // Highlight map or messages if appropriate, but since this is a new "Jobs" view,
            // we'll just handle clicks to other sections.
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_map) {
                    startActivity(new Intent(this, DiscoveryMapActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_messages) {
                    startActivity(new Intent(this, MessagesActivity.class));
                    finish();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(this, UserProfileActivity.class));
                    finish();
                    return true;
                }
                return true;
            });
        }
    }

    private void navigateToCreatePost() {
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
