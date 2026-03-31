package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        FrameLayout cardMyPost = findViewById(R.id.cardMyPost);
        FrameLayout cardGig1 = findViewById(R.id.cardGig1);
        FrameLayout cardCommunity1 = findViewById(R.id.cardCommunity1);
        FrameLayout cardCommunity2 = findViewById(R.id.cardCommunity2);
        FrameLayout cardGig2 = findViewById(R.id.cardGig2);
        ChipGroup cgFilters = findViewById(R.id.cgFilters);
        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        View ivProfile = findViewById(R.id.ivProfile);

        ivProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        View btnVolC1 = findViewById(R.id.btnVolC1);
        if (btnVolC1 != null) {
            btnVolC1.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, CommunityVolunteerActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }

        View btnVolC2 = findViewById(R.id.btnVolC2);
        if (btnVolC2 != null) {
            btnVolC2.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, CommunityVolunteerActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }

        View btnViewC0 = findViewById(R.id.btnViewC0);
        if (btnViewC0 != null) {
            btnViewC0.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, MyCommunityPostDetailActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }

        View btnApplyGig1 = findViewById(R.id.btnApplyGig1);
        if (btnApplyGig1 != null) {
            btnApplyGig1.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, GigDetailActivity.class);
                intent.putExtra(GigDetailActivity.EXTRA_TITLE, getString(R.string.txt_plumbing_repair));
                intent.putExtra(GigDetailActivity.EXTRA_PRICE, getString(R.string.txt_price_400));
                intent.putExtra(GigDetailActivity.EXTRA_DESC, "Bathroom pipe burst, need experienced plumber ASAP.");
                intent.putExtra(GigDetailActivity.EXTRA_DISTANCE, "0.3 km  ·  2 min ago");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }

        View btnApplyGig2 = findViewById(R.id.btnApplyGig2);
        if (btnApplyGig2 != null) {
            btnApplyGig2.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, GigDetailActivity.class);
                intent.putExtra(GigDetailActivity.EXTRA_TITLE, getString(R.string.txt_groceries_delivery));
                intent.putExtra(GigDetailActivity.EXTRA_PRICE, getString(R.string.txt_price_100));
                intent.putExtra(GigDetailActivity.EXTRA_DESC, "Need someone to pick up groceries from D-Mart, Sector 14.");
                intent.putExtra(GigDetailActivity.EXTRA_DISTANCE, "0.7 km  ·  8 min ago");
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            });
        }

        // ── Bottom Navigation ────────────────────────────────────────────────
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavView);
        if (bottomNav != null) {
            bottomNav.setSelectedItemId(R.id.nav_home);
            bottomNav.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_home) {
                    return true; // already here
                } else if (itemId == R.id.nav_map) {
                    startActivity(new Intent(MainActivity.this, DiscoveryMapActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                } else if (itemId == R.id.nav_messages) {
                    startActivity(new Intent(MainActivity.this, MessagesActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                    return true;
                } else if (itemId == R.id.nav_profile) {
                    startActivity(new Intent(MainActivity.this, UserProfileActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    return true;
                }
                return true;
            });
        }

        fabAdd.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        cgFilters.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            
            int id = checkedIds.get(0);
            
            // Note: Since IDs are not strictly mapped in ChipGroup, we inspect the chip child index
            int idx = group.indexOfChild(findViewById(id));
            
            if (idx == 0) {
                // All
                cardMyPost.setVisibility(View.VISIBLE);
                cardGig1.setVisibility(View.VISIBLE);
                cardGig2.setVisibility(View.VISIBLE);
                cardCommunity1.setVisibility(View.VISIBLE);
                cardCommunity2.setVisibility(View.VISIBLE);
            } else if (idx == 1) {
                // Gigs
                cardMyPost.setVisibility(View.GONE);
                cardGig1.setVisibility(View.VISIBLE);
                cardGig2.setVisibility(View.VISIBLE);
                cardCommunity1.setVisibility(View.GONE);
                cardCommunity2.setVisibility(View.GONE);
            } else if (idx == 2) {
                // Community
                cardMyPost.setVisibility(View.VISIBLE);
                cardGig1.setVisibility(View.GONE);
                cardGig2.setVisibility(View.GONE);
                cardCommunity1.setVisibility(View.VISIBLE);
                cardCommunity2.setVisibility(View.VISIBLE);
            } else if (idx == 3) {
                // Emergency (Urgent posts)
                cardMyPost.setVisibility(View.GONE);
                cardGig1.setVisibility(View.GONE);
                cardGig2.setVisibility(View.GONE);
                cardCommunity1.setVisibility(View.VISIBLE);
                cardCommunity2.setVisibility(View.VISIBLE);
            }
        });
        
        // Initial filter state: show All
        cardMyPost.setVisibility(View.VISIBLE);
        cardGig1.setVisibility(View.VISIBLE);
        cardGig2.setVisibility(View.VISIBLE);
        cardCommunity1.setVisibility(View.VISIBLE);
        cardCommunity2.setVisibility(View.VISIBLE);
    }
}