package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.button.MaterialButton;

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
        MaterialButton fabAdd = findViewById(R.id.fabAdd);
        View notificationPopup = findViewById(R.id.notificationPopup);
        android.widget.ImageView ivNotifications = findViewById(R.id.ivNotifications);
        ivNotifications.setOnClickListener(v -> {
            ivNotifications.setImageResource(R.drawable.ic_bell);
            if (notificationPopup.getVisibility() == View.VISIBLE) {
                notificationPopup.setVisibility(View.GONE);
            } else {
                notificationPopup.setVisibility(View.VISIBLE);
            }
        });

        setupNotificationInteractions(notificationPopup);

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
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);

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

    private void setupNotificationInteractions(View popup) {
        View btnClose = findViewById(R.id.btnClosePopup);
        if (btnClose != null) btnClose.setOnClickListener(v -> popup.setVisibility(View.GONE));

        View btnClearAll = findViewById(R.id.btnClearAllNotifications);
        View scroll = findViewById(R.id.scrollNotifications);
        View emptyView = findViewById(R.id.empty_notif_view);
        android.widget.LinearLayout list = findViewById(R.id.llNotificationsList);

        if (btnClearAll != null) {
            btnClearAll.setOnClickListener(v -> {
                if (scroll != null) scroll.setVisibility(View.GONE);
                if (emptyView != null) emptyView.setVisibility(View.VISIBLE);
            });
        }

        setupNotifCard(R.id.notif_card_1, R.id.notif_desc_1, R.id.btnDeleteNotif1, list, emptyView, scroll);
        setupNotifCard(R.id.notif_card_2, R.id.notif_desc_2, R.id.btnDeleteNotif2, list, emptyView, scroll);
        setupNotifCard(R.id.notif_card_3, R.id.notif_desc_3, R.id.btnDeleteNotif3, list, emptyView, scroll);
    }

    private void setupNotifCard(int cardId, int descId, int delId, android.widget.LinearLayout list, View empty, View scroll) {
        View card = findViewById(cardId);
        android.widget.TextView desc = findViewById(descId);
        View delete = findViewById(delId);

        if (card != null && desc != null) {
            card.setOnClickListener(v -> {
                if (desc.getMaxLines() == 1) {
                    desc.setMaxLines(10);
                    desc.setEllipsize(null);
                } else {
                    desc.setMaxLines(1);
                    desc.setEllipsize(android.text.TextUtils.TruncateAt.END);
                }
            });
        }

        if (delete != null && card != null) {
            delete.setOnClickListener(v -> {
                card.setVisibility(View.GONE);
                // Check if all are hidden
                boolean allHidden = true;
                for (int i = 0; i < list.getChildCount(); i++) {
                    if (list.getChildAt(i).getVisibility() == View.VISIBLE) {
                        allHidden = false;
                        break;
                    }
                }
                if (allHidden) {
                    if (scroll != null) scroll.setVisibility(View.GONE);
                    if (empty != null) empty.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}