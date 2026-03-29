package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResponseSentActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private final long TOTAL_TIME = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response_sent);

        findViewById(R.id.btnClose).setOnClickListener(v -> goHome());
        findViewById(R.id.btnBackToFeed).setOnClickListener(v -> goHome());

        startAutoRedirect();
    }

    private void startAutoRedirect() {
        TextView tvAutoRedirect = findViewById(R.id.tvAutoRedirect);
        ProgressBar pbAutoRedirect = findViewById(R.id.pbAutoRedirect);

        if (tvAutoRedirect == null || pbAutoRedirect == null) {
            // Fallback in case views are missing
            new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(this::goHome, TOTAL_TIME);
            return;
        }

        countDownTimer = new CountDownTimer(TOTAL_TIME, 30) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) Math.ceil(millisUntilFinished / 1000.0);
                tvAutoRedirect.setText("auto-redirect in " + secondsLeft + "s");

                int progress = (int) ((millisUntilFinished * 100) / TOTAL_TIME);
                pbAutoRedirect.setProgress(progress);
            }

            @Override
            public void onFinish() {
                pbAutoRedirect.setProgress(0);
                tvAutoRedirect.setText("auto-redirect in 0s");
                goHome();
            }
        }.start();
    }

    private void cancelTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    private void goHome() {
        cancelTimer();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
