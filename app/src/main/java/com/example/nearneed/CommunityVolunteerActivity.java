package com.example.nearneed;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class CommunityVolunteerActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private Handler updateHandler;
    private long postTimestamp;
    private final double userLatitude = 28.6139;  // Default: Delhi coordinates
    private final double userLongitude = 77.2090;
    private final double postLatitude = 28.6139;   // Default: Same location
    private final double postLongitude = 77.2090;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_volunteer);

        ImageButton btnBack = findViewById(R.id.btnBack);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Share button functionality
        ImageButton btnShare = findViewById(R.id.btnShare);
        if (btnShare != null) {
            btnShare.setOnClickListener(v -> {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Community Volunteer Opportunity");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this volunteer opportunity on NearNeed: Emergency food support!");
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            });
        }

        // More options menu functionality
        ImageButton btnOptions = findViewById(R.id.btnOptions);
        if (btnOptions != null) {
            btnOptions.setOnClickListener(v -> {
                androidx.appcompat.widget.PopupMenu popup = new androidx.appcompat.widget.PopupMenu(this, v);
                popup.getMenuInflater().inflate(R.menu.menu_volunteer_more, popup.getMenu());
                popup.setOnMenuItemClickListener(item -> {
                    int id = item.getItemId();
                    if (id == R.id.menu_save) {
                        // Handle Save action
                        android.widget.Toast.makeText(this, "Post Saved", android.widget.Toast.LENGTH_SHORT).show();
                        return true;
                    } else if (id == R.id.menu_help) {
                        startActivity(new Intent(this, HelpSupportActivity.class));
                        return true;
                    } else if (id == R.id.menu_report) {
                        android.widget.Toast.makeText(this, "Report Submitted", android.widget.Toast.LENGTH_SHORT).show();
                        return true;
                    } else if (id == R.id.menu_settings) {
                        startActivity(new Intent(this, SettingsActivity.class));
                        return true;
                    }
                    return false;
                });
                popup.show();
            });
        }

        // Map preview redirects to full map view
        findViewById(R.id.cardMapPreview).setOnClickListener(v -> {
            startActivity(new Intent(this, DiscoveryMapActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // "I'll Volunteer" bottom CTA launches Step 2 Activity
        findViewById(R.id.btnVolunteerSticky).setOnClickListener(v -> {
            startActivity(new Intent(CommunityVolunteerActivity.this, CommunityVolunteerStep2Activity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        // Setup real-time updates and audio player
        postTimestamp = System.currentTimeMillis() - 600000; // 10 minutes ago
        setupRealTimeUpdates();
        setupAudioPlayer();
        setupAuthorProfileInteractions();
    }

    private void setupRealTimeUpdates() {
        updateHandler = new Handler(Looper.getMainLooper());
        updateTimeAndDistance();
        updateHandler.postDelayed(this::updateTimeAndDistance, 60000);
    }

    private void updateTimeAndDistance() {
        TextView tvTimeAgo = findViewById(R.id.tvTimeAgo);
        TextView tvDistance = findViewById(R.id.tvDistance);

        if (tvTimeAgo != null) {
            long elapsedTime = System.currentTimeMillis() - postTimestamp;
            tvTimeAgo.setText(getRelativeTimeString(elapsedTime));
        }

        if (tvDistance != null) {
            double distance = calculateDistance(userLatitude, userLongitude, postLatitude, postLongitude);
            tvDistance.setText(String.format(Locale.US, "%.1f km", distance));
        }

        if (updateHandler != null) {
            updateHandler.postDelayed(this::updateTimeAndDistance, 60000);
        }
    }

    private String getRelativeTimeString(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (seconds < 60) {
            return "just now";
        } else if (minutes < 60) {
            return minutes + "m ago";
        } else if (hours < 24) {
            return hours + "h ago";
        } else {
            return days + "d ago";
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Radius in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }

    private void setupAudioPlayer() {
        ImageView ivPlayPauseButton = findViewById(R.id.ivPlayPauseButton);
        if (ivPlayPauseButton != null) {
            ivPlayPauseButton.setOnClickListener(v -> toggleAudioPlayPause());
        }
    }

    private void toggleAudioPlayPause() {
        ImageView ivPlayPauseButton = findViewById(R.id.ivPlayPauseButton);
        if (!isPlaying) {
            try {
                if (mediaPlayer == null) {
                    mediaPlayer = new MediaPlayer();
                }
                // Load audio from assets (place audio file in res/raw/)
                mediaPlayer = MediaPlayer.create(this, R.raw.sample_audio);
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    isPlaying = true;
                    if (ivPlayPauseButton != null) {
                        ivPlayPauseButton.setImageDrawable(android.content.res.Resources.getSystem().getDrawable(android.R.drawable.ic_media_pause));
                    }
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
            }
        } else {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                isPlaying = false;
                if (ivPlayPauseButton != null) {
                    ivPlayPauseButton.setImageDrawable(android.content.res.Resources.getSystem().getDrawable(android.R.drawable.ic_media_play));
                }
            }
        }
    }

    private void setupAuthorProfileInteractions() {
        ImageButton btnCallUser = findViewById(R.id.btnCallUser);
        ImageButton btnMessageUser = findViewById(R.id.btnMessageUser);

        if (btnCallUser != null) {
            btnCallUser.setOnClickListener(v -> callUser());
        }

        if (btnMessageUser != null) {
            btnMessageUser.setOnClickListener(v -> messageUser());
        }
    }

    private void callUser() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(android.net.Uri.parse("tel:+919876543210")); // Replace with actual phone
        startActivity(intent);
    }

    private void messageUser() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(android.net.Uri.parse("smsto:+919876543210")); // Replace with actual phone
        intent.putExtra("sms_body", "Hi, I'm interested in this volunteer opportunity.");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (updateHandler != null) {
            updateHandler.removeCallbacksAndMessages(null);
        }
    }
}
