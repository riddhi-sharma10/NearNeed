package com.example.nearneed;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nearneed.R;
import com.google.android.material.button.MaterialButton;

public class MyCommunityPostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_community_post_detail);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        MaterialButton btnViewVolunteers = findViewById(R.id.btnViewVolunteers);
        MaterialButton btnViewResponses = findViewById(R.id.btnViewResponses);
        MaterialButton btnUpdateStatus = findViewById(R.id.btnUpdateStatus);

        btnViewVolunteers.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(MyCommunityPostDetailActivity.this, VolunteerManagementActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnViewResponses.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(MyCommunityPostDetailActivity.this, VolunteerResponsesActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        btnUpdateStatus.setOnClickListener(v -> {
            android.content.Intent intent = new android.content.Intent(MyCommunityPostDetailActivity.this, PostStatusActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}
