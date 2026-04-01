package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

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

        // Bottom Navigation handled via universal helper
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }

    private void navigateToCreatePost() {
        Intent intent = new Intent(this, CreatePostActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
