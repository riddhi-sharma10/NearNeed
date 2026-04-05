package com.example.nearneed;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.button.MaterialButton;

public class CommunityVolunteerStep2Activity extends AppCompatActivity {

    private float initialY = 0;
    private static final float DRAG_THRESHOLD = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_volunteer_step2);

        FrameLayout overlaySheet = findViewById(R.id.overlaySheet);
        if (overlaySheet != null) {
            // Dismiss activity when clicking outside the bottom sheet
            overlaySheet.setOnClickListener(v -> finish());
        }
        
        NestedScrollView bottomSheetContent = findViewById(R.id.bottomSheetContent);
        if (bottomSheetContent != null) {
            // Prevent dismiss when clicking inside the actual bottom sheet
            bottomSheetContent.setOnClickListener(v -> {});
            
            // Handle drag-to-close gesture
            bottomSheetContent.setOnTouchListener((v, event) -> {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialY = event.getY();
                        return false;
                    case MotionEvent.ACTION_MOVE:
                        float currentY = event.getY();
                        float dragDistance = currentY - initialY;
                        if (dragDistance > DRAG_THRESHOLD && !bottomSheetContent.canScrollVertically(-1)) {
                            finish();
                            overridePendingTransition(0, android.R.anim.fade_out);
                            return true;
                        }
                        return false;
                    default:
                        return false;
                }
            });
        }
        
        MaterialButton btnSendResponse = findViewById(R.id.btnSendResponse);
        if (btnSendResponse != null) {
            btnSendResponse.setOnClickListener(v -> {
                android.content.Intent intent = new android.content.Intent(CommunityVolunteerStep2Activity.this, ResponseSentActivity.class);
                startActivity(intent);
                finish();
            });
        }
    }
}
