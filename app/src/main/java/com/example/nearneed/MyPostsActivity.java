package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MyPostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Card 1: Emergency Food Delivery (Community)
        findViewById(R.id.cardPost1).setOnClickListener(v -> {
            Intent intent = new Intent(this, MyCommunityPostDetailActivity.class);
            intent.putExtra("post_title", "Emergency Food Delivery");
            startActivity(intent);
        });

        // Card 2: Plumbing Repair (Paid Gig)
        findViewById(R.id.cardPost2).setOnClickListener(v -> {
            Intent intent = new Intent(this, GigDetailActivity.class);
            intent.putExtra(GigDetailActivity.EXTRA_TITLE, "Plumbing Repair");
            intent.putExtra(GigDetailActivity.EXTRA_PRICE, "₹400 budget");
            intent.putExtra(GigDetailActivity.EXTRA_DESC, "Need a professional plumber to fix a leaking tap and shower head. Tools provided if needed.");
            intent.putExtra(GigDetailActivity.EXTRA_DISTANCE, "Local • Block C");
            intent.putExtra(GigDetailActivity.EXTRA_IS_OWNER, true);
            startActivity(intent);
        });

        // Card 3: Garden Drive Block B (Community)
        findViewById(R.id.cardPost3).setOnClickListener(v -> {
            Intent intent = new Intent(this, MyCommunityPostDetailActivity.class);
            intent.putExtra("post_title", "Garden Drive Block B");
            startActivity(intent);
        });

        // Card 4: Dog Walking (Paid Gig)
        findViewById(R.id.cardPost4).setOnClickListener(v -> {
            Intent intent = new Intent(this, GigDetailActivity.class);
            intent.putExtra(GigDetailActivity.EXTRA_TITLE, "Dog Walking (Eve)");
            intent.putExtra(GigDetailActivity.EXTRA_PRICE, "₹200/walk");
            intent.putExtra(GigDetailActivity.EXTRA_DESC, "Looking for a dog lover to walk my Golden Retriever every evening for 30 minutes.");
            intent.putExtra(GigDetailActivity.EXTRA_DISTANCE, "Local • Block D");
            intent.putExtra(GigDetailActivity.EXTRA_IS_OWNER, true);
            startActivity(intent);
        });

        findViewById(R.id.btnCreatePost).setOnClickListener(v -> {
            startActivity(new Intent(this, CreatePostActivity.class));
        });
    }
}
