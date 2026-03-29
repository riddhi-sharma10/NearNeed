package com.example.nearneed;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class CommunityVolunteerStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_volunteer_step2);

        FrameLayout overlaySheet = findViewById(R.id.overlaySheet);
        if (overlaySheet != null) {
            // Dismiss activity when clicking outside the bottom sheet
            overlaySheet.setOnClickListener(v -> finish());
            
            // Prevent dismiss when clicking inside the actual white layout panel
            View bottomSheetContent = findViewById(R.id.bottomSheetContent);
            if (bottomSheetContent != null) {
                bottomSheetContent.setOnClickListener(v -> {});
            }
        }
        
        MaterialButton btnSendResponse = findViewById(R.id.btnSendResponse);
        if (btnSendResponse != null) {
            btnSendResponse.setOnClickListener(v -> finish());
        }
    }
}
