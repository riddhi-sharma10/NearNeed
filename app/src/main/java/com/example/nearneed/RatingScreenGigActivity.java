package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

public class RatingScreenGigActivity extends AppCompatActivity {

    private ImageView[] stars = new ImageView[5];
    private boolean[] tagSelected = new boolean[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_screen_gig);

        ImageView btnBack = findViewById(R.id.iv_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        AppCompatButton btnSubmitRating = findViewById(R.id.btn_submit);
        if (btnSubmitRating != null) {
            btnSubmitRating.setOnClickListener(v -> {
                Intent intent = new Intent(RatingScreenGigActivity.this, RatingConfirmedGigsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            });
        }

        setupStars();
        setupTags();
    }

    private void setupStars() {
        stars[0] = findViewById(R.id.iv_star1);
        stars[1] = findViewById(R.id.iv_star2);
        stars[2] = findViewById(R.id.iv_star3);
        stars[3] = findViewById(R.id.iv_star4);
        stars[4] = findViewById(R.id.iv_star5);

        for (int i = 0; i < stars.length; i++) {
            final int rating = i;
            if (stars[i] != null) {
                stars[i].setOnClickListener(v -> setRating(rating));
            }
        }
    }

    private void setRating(int ratingIndex) {
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (i <= ratingIndex) {
                    stars[i].setImageResource(R.drawable.ic_star_blue_filled);
                } else {
                    stars[i].setImageResource(R.drawable.ic_star_blue_outline);
                }
            }
        }
    }

    private void setupTags() {
        TextView tagReliable = findViewById(R.id.tv_tag_reliable);
        TextView tagExcellent = findViewById(R.id.tv_tag_excellent_work);
        TextView tagHireAgain = findViewById(R.id.tv_tag_hire_again);
        TextView tagArrivedTime = findViewById(R.id.tv_tag_arrived_on_time);

        TextView[] tags = {tagReliable, tagExcellent, tagHireAgain, tagArrivedTime};
        
        // Initial setup based on XML
        tagSelected[0] = true; 
        tagSelected[1] = false;
        tagSelected[2] = false;
        tagSelected[3] = false;

        for (int i = 0; i < tags.length; i++) {
            final int index = i;
            if (tags[i] != null) {
                tags[i].setOnClickListener(v -> {
                    tagSelected[index] = !tagSelected[index];
                    updateTagVisuals(tags[index], tagSelected[index]);
                });
            }
        }
    }

    private void updateTagVisuals(TextView tag, boolean isSelected) {
        if (isSelected) {
            tag.setBackgroundResource(R.drawable.bg_chip_selected);
            tag.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        } else {
            tag.setBackgroundResource(R.drawable.bg_chip_blue_translucent);
            tag.setTextColor(ContextCompat.getColor(this, R.color.brand_primary));
        }
    }
}
