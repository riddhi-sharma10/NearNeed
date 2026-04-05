package com.example.nearneed;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.button.MaterialButton;
import android.view.View;

public class GigApplicantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig_applicants);

        // ── Back ──────────────────────────────────────────────────────────────
        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) btnBack.setOnClickListener(v -> finish());

        // ── Rahul S. applicant buttons (EXPANDED) ────────────────────────────
        MaterialButton btnAcceptRahul = findViewById(R.id.btnAcceptRahul);
        MaterialButton btnCounterRahul = findViewById(R.id.btnCounterRahul);
        MaterialButton btnDeclineRahul = findViewById(R.id.btnDeclineRahul);
        MaterialButton btnChatRahul = findViewById(R.id.btnChatRahul);

        if (btnAcceptRahul != null)
            btnAcceptRahul.setOnClickListener(v -> {
                startActivity(new Intent(this, PaymentActivity.class));
            });

        if (btnCounterRahul != null)
            btnCounterRahul.setOnClickListener(v -> showCounterOfferDialog("Rahul S.", 250));

        if (btnDeclineRahul != null)
            btnDeclineRahul.setOnClickListener(v -> {
                CardView cardRahul = findViewById(R.id.cardRahul);
                if (cardRahul != null) {
                    cardRahul.setVisibility(View.GONE);
                }
                Toast.makeText(this, "Declined Rahul's application.", Toast.LENGTH_SHORT).show();
            });

        if (btnChatRahul != null)
            btnChatRahul.setOnClickListener(v -> openChat("Rahul S.", "rahul_id", "+919876543210"));

        // ── Priya M. Card (COLLAPSED PREVIEW) ────────────────────────────────
        CardView cardPriya = findViewById(R.id.cardPriya);
        if (cardPriya != null) {
            cardPriya.setOnClickListener(v -> expandApplicantCard(
                    cardPriya, "Priya M.", 4.5, "priya_id", "+919876543211", 250,
                    "I'm available this weekend and have 5 years of experience with tap installations and repairs."
            ));
        }

        // ── Anika R. Card (COLLAPSED PREVIEW) ────────────────────────────────
        CardView cardAnika = findViewById(R.id.cardAnika);
        if (cardAnika != null) {
            cardAnika.setOnClickListener(v -> expandApplicantCard(
                    cardAnika, "Anika R.", 4.9, "anika_id", "+919876543212", 280,
                    "I'm a certified plumber with 10 years of experience. I can come within the hour and have all necessary equipment."
            ));
        }

        // ── Bottom Navigation ─────────────────────────────────────────────────
        NavbarHelper.setup(this, NavbarHelper.TAB_HOME);
    }

    private void expandApplicantCard(CardView card, String name, double rating, String userId, String userPhone,
                                      int proposedPrice, String fullMessage) {
        // Remove the collapsed preview
        card.removeAllViews();

        // Create expanded content
        LinearLayout expandedContent = new LinearLayout(this);
        expandedContent.setOrientation(LinearLayout.VERTICAL);
        expandedContent.setPadding(48, 48, 48, 48);

        // Avatar + Name + Rating row
        RelativeLayout header = new RelativeLayout(this);
        header.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        ImageView avatar = new ImageView(this);
        avatar.setId(View.generateViewId());
        avatar.setLayoutParams(new RelativeLayout.LayoutParams(80, 80));
        avatar.setBackgroundResource(R.drawable.bg_circle_light_blue);
        avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        header.addView(avatar);
        avatar.setOnClickListener(v -> openUserProfile(name, userId, rating, userPhone));

        LinearLayout nameRatingGroup = new LinearLayout(this);
        nameRatingGroup.setOrientation(LinearLayout.VERTICAL);

        TextView nameView = new TextView(this);
        nameView.setText(name);
        nameView.setTextSize(18);
        nameView.setTextColor(getColor(R.color.text_header));
        nameView.setTypeface(null, android.graphics.Typeface.BOLD);
        nameRatingGroup.addView(nameView);

        LinearLayout ratingRow = new LinearLayout(this);
        ratingRow.setOrientation(LinearLayout.HORIZONTAL);
        ratingRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams ratingRowLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ratingRowLp.topMargin = 4;
        nameRatingGroup.addView(ratingRow, ratingRowLp);

        ImageView starIcon = new ImageView(this);
        starIcon.setLayoutParams(new LinearLayout.LayoutParams(16, 16));
        starIcon.setImageResource(R.drawable.ic_star_yellow);
        ratingRow.addView(starIcon);

        TextView ratingView = new TextView(this);
        ratingView.setText("★ " + rating);
        ratingView.setTextSize(14);
        ratingView.setTextColor(getColor(R.color.brand_warning_solid));
        ratingView.setTypeface(null, android.graphics.Typeface.BOLD);
        LinearLayout.LayoutParams ratingLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        ratingLp.leftMargin = 4;
        ratingRow.addView(ratingView, ratingLp);

        RelativeLayout.LayoutParams groupLp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        groupLp.addRule(RelativeLayout.RIGHT_OF, avatar.getId());
        groupLp.addRule(RelativeLayout.CENTER_VERTICAL);
        groupLp.leftMargin = 20;
        header.addView(nameRatingGroup, groupLp);

        LinearLayout.LayoutParams headerLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        headerLp.bottomMargin = 24;
        expandedContent.addView(header, headerLp);

        // Full message
        TextView messageView = new TextView(this);
        messageView.setText(fullMessage);
        messageView.setTextSize(14);
        messageView.setTextColor(getColor(R.color.text_subheadline));
        messageView.setLineSpacing(1.5f, 1f);
        LinearLayout.LayoutParams messageLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        messageLp.bottomMargin = 24;
        expandedContent.addView(messageView, messageLp);

        // Proposed price chip
        RelativeLayout priceChip = new RelativeLayout(this);
        priceChip.setBackgroundResource(R.drawable.bg_alert_yellow);
        priceChip.setPadding(16, 16, 16, 16);
        LinearLayout.LayoutParams priceLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        priceLp.bottomMargin = 24;
        expandedContent.addView(priceChip, priceLp);

        ImageView priceIcon = new ImageView(this);
        priceIcon.setId(View.generateViewId());
        priceIcon.setLayoutParams(new RelativeLayout.LayoutParams(20, 20));
        priceIcon.setImageResource(R.drawable.ic_cash_icon);
        priceIcon.setColorFilter(getColor(R.color.brand_warning_solid));
        priceChip.addView(priceIcon);

        TextView priceText = new TextView(this);
        priceText.setText("Proposed ₹" + proposedPrice + " (you posted ₹300)");
        priceText.setTextSize(14);
        priceText.setTextColor(getColor(R.color.brand_warning_text));
        priceText.setTypeface(null, android.graphics.Typeface.BOLD);
        RelativeLayout.LayoutParams priceTxtLp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        priceTxtLp.addRule(RelativeLayout.RIGHT_OF, priceIcon.getId());
        priceTxtLp.addRule(RelativeLayout.CENTER_VERTICAL);
        priceTxtLp.leftMargin = 12;
        priceChip.addView(priceText, priceTxtLp);

        // Action buttons row 1: Accept + Counter
        LinearLayout buttonRow1 = new LinearLayout(this);
        buttonRow1.setOrientation(LinearLayout.HORIZONTAL);
        buttonRow1.setWeightSum(2);
        LinearLayout.LayoutParams btnRow1Lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnRow1Lp.bottomMargin = 12;
        expandedContent.addView(buttonRow1, btnRow1Lp);

        MaterialButton acceptBtn = new MaterialButton(this);
        acceptBtn.setText("Accept ₹" + proposedPrice);
        acceptBtn.setTextSize(13);
        acceptBtn.setBackgroundColor(getColor(R.color.brand_success_alt));
        LinearLayout.LayoutParams acceptLp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        acceptLp.setMargins(0, 0, 12, 0);
        buttonRow1.addView(acceptBtn, acceptLp);
        acceptBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Accepted " + name + "'s offer", Toast.LENGTH_SHORT).show();
            card.setVisibility(View.GONE);
        });

        MaterialButton counterBtn = new MaterialButton(this);
        counterBtn.setText("Counter");
        counterBtn.setTextSize(13);
        counterBtn.setBackgroundColor(getColor(R.color.brand_primary));
        LinearLayout.LayoutParams counterLp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        buttonRow1.addView(counterBtn, counterLp);
        counterBtn.setOnClickListener(v -> showCounterOfferDialog(name, proposedPrice));

        // Action buttons row 2: Decline + Chat
        LinearLayout buttonRow2 = new LinearLayout(this);
        buttonRow2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams btnRow2Lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        expandedContent.addView(buttonRow2, btnRow2Lp);

        MaterialButton declineBtn = new MaterialButton(this);
        declineBtn.setText("Decline");
        declineBtn.setTextSize(13);
        declineBtn.setBackgroundColor(getColor(R.color.divider_soft));
        LinearLayout.LayoutParams declineLp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.65f);
        declineLp.setMargins(0, 0, 12, 0);
        buttonRow2.addView(declineBtn, declineLp);
        declineBtn.setOnClickListener(v -> {
            card.setVisibility(View.GONE);
            Toast.makeText(this, "Declined " + name + "'s application.", Toast.LENGTH_SHORT).show();
        });

        MaterialButton chatBtn = new MaterialButton(this);
        chatBtn.setBackgroundColor(getColor(R.color.brand_primary_light));
        chatBtn.setIcon(getDrawable(R.drawable.ic_chat_outline));
        LinearLayout.LayoutParams chatLp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT, 0.35f);
        buttonRow2.addView(chatBtn, chatLp);
        chatBtn.setOnClickListener(v -> openChat(name, userId, userPhone));

        // Add to card
        card.addView(expandedContent);
    }

    private void showCounterOfferDialog(String applicantName, int originalPrice) {
        Dialog dialog = new Dialog(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_counter_offer, null);
        dialog.setContentView(dialogView);

        TextView titleView = dialogView.findViewById(R.id.tvCounterTitle);
        if (titleView != null) {
            titleView.setText("Counter Offer for " + applicantName);
        }

        TextView originalPriceView = dialogView.findViewById(R.id.tvOriginalPrice);
        if (originalPriceView != null) {
            originalPriceView.setText("Original offer: ₹" + originalPrice);
        }

        EditText priceInput = dialogView.findViewById(R.id.etCounterPrice);
        MaterialButton sendBtn = dialogView.findViewById(R.id.btnSendCounterOffer);
        MaterialButton cancelBtn = dialogView.findViewById(R.id.btnCancelCounterOffer);

        if (sendBtn != null) {
            sendBtn.setOnClickListener(v -> {
                String newPrice = priceInput.getText().toString().trim();
                if (newPrice.isEmpty()) {
                    Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Counter offer of ₹" + newPrice + " sent to " + applicantName,
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }

        if (cancelBtn != null) {
            cancelBtn.setOnClickListener(v -> dialog.dismiss());
        }

        dialog.show();
    }

    private void openUserProfile(String name, String userId, double rating, String phone) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        intent.putExtra("userId", userId);
        intent.putExtra("userName", name);
        intent.putExtra("userRating", rating);
        intent.putExtra("userPhone", phone);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void openChat(String name, String userId, String userPhone) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("recipientName", name);
        intent.putExtra("recipientId", userId);
        intent.putExtra("recipientPhone", userPhone);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
