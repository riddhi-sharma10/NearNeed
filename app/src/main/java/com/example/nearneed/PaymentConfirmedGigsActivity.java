package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class PaymentConfirmedGigsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirmed_gigs);

        ImageView ivClose = findViewById(R.id.iv_close);
        if (ivClose != null) {
            ivClose.setOnClickListener(v -> finish());
        }

        AppCompatButton btnRate = findViewById(R.id.btn_rate);
        if (btnRate != null) {
            btnRate.setOnClickListener(v -> {
                Intent intent = new Intent(this, RatingScreenGigActivity.class);
                startActivity(intent);
                finish();
            });
        }

        TextView tvRateLater = findViewById(R.id.tv_rate_later);
        if (tvRateLater != null) {
            tvRateLater.setOnClickListener(v -> {
                android.content.Intent intent = new android.content.Intent(this, MainActivity.class);
                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP | android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            });
        }
    }
}
