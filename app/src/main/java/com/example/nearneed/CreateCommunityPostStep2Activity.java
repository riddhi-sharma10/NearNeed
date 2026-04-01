package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CreateCommunityPostStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community_post_step2);

        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        MaterialButton btnPostNow = findViewById(R.id.btnPostNow);
        if (btnPostNow != null) {
            btnPostNow.setOnClickListener(v -> {
                navigateToSuccess();
            });
        }
    }

    private void navigateToSuccess() {
        startActivity(new Intent(this, CommunityPostSuccessActivity.class));
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
        finish();
    }
}
