package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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

        ImageButton btnOptions = findViewById(R.id.btnOptions);
        if (btnOptions != null) {
            btnOptions.setOnClickListener(v -> showManagementMenu(v));
        }

        ImageButton btnShare = findViewById(R.id.btnShare);
        if (btnShare != null) {
            btnShare.setOnClickListener(v -> {
                Toast.makeText(this, "Sharing post...", Toast.LENGTH_SHORT).show();
            });
        }

        // Bind Dynamic Data
        String title = getIntent().getStringExtra("POST_TITLE");
        String desc = getIntent().getStringExtra("POST_DESC");
        String category = getIntent().getStringExtra("POST_CATEGORY");

        android.widget.TextView tvHeadline = findViewById(R.id.tvDetailHeadline);
        android.widget.TextView tvDesc = findViewById(R.id.tvDetailDescription);
        android.widget.TextView tvPillCat = findViewById(R.id.tvDetailPillCategory);

        if (title != null && tvHeadline != null) tvHeadline.setText(title);
        if (desc != null && tvDesc != null) tvDesc.setText(desc);
        if (category != null) {
            if (tvPillCat != null) tvPillCat.setText(category);
        }

        // Bottom Sticky Button (As seen on Volunteer Page)
        MaterialButton btnVolunteerSticky = findViewById(R.id.btnVolunteerSticky);
        if (btnVolunteerSticky != null) {
            btnVolunteerSticky.setOnClickListener(v -> {
                Toast.makeText(this, "Edit feature coming soon", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private void showManagementMenu(android.view.View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.getMenuInflater().inflate(R.menu.menu_my_post_detail, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_view_volunteers) {
                Intent intent = new Intent(this, VolunteerManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.menu_view_responses) {
                Intent intent = new Intent(this, VolunteerResponsesActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.menu_update_status) {
                Intent intent = new Intent(this, PostStatusActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                return true;
            } else if (id == R.id.menu_save) {
                Toast.makeText(this, "Post saved", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.menu_help) {
                Toast.makeText(this, "Opening Help...", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.menu_report) {
                Toast.makeText(this, "Opening Report...", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.menu_settings) {
                Toast.makeText(this, "Opening Settings...", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        popup.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Since buttons moved to overflow menu, we can still update menu item states here if needed,
        // but simple linking is preserved.
    }
}
