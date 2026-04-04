package com.example.nearneed;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout llChatContainer;
    private NestedScrollView chatScrollView;
    
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private boolean isRecording = false;
    private String voiceNotePath;
    private static final int REQUEST_MIC = 101;
    private long recordStartTime;
    private ImageButton btnMic;
    private EditText etMessage;
    
    private ImageView currentPlayingIcon = null;
    
    private Handler timerHandler = new Handler(Looper.getMainLooper());
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRecording) {
                long duration = System.currentTimeMillis() - recordStartTime;
                int seconds = (int) (duration / 1000);
                String timeStr = String.format("Recording... %d:%02d", seconds / 60, seconds % 60);
                etMessage.setHint(timeStr);
                timerHandler.postDelayed(this, 1000);
            }
        }
    };
    
    private final ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
            registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                if (uri != null) {
                    addImageMessage(uri, true);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        String userName = getIntent().getStringExtra("userName");
        if (userName == null) userName = "Rachel";

        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvUserStatus = findViewById(R.id.tvUserStatus);
        tvUserName.setText(userName);
        
        if (userName.equals("Rachel") || userName.equals("Sneha Patel") || userName.equals("Riddhi Sharma") || userName.equals("Manya Awasthi")) {
            tvUserStatus.setText("Online");
            tvUserStatus.setTextColor(getResources().getColor(R.color.brand_success));
        } else if (userName.equals("Rahul Singh")) {
            tvUserStatus.setText("Last seen 1 hr ago");
            tvUserStatus.setTextColor(getResources().getColor(R.color.text_muted));
        } else if (userName.equals("Karan Mehta") || userName.equals("Priya Nair")) {
            tvUserStatus.setText("Last seen Mon");
            tvUserStatus.setTextColor(getResources().getColor(R.color.text_muted));
        } else if (userName.equals("Vishu Singh") || userName.equals("Ananya Gupta")) {
            tvUserStatus.setText("Last seen Yesterday");
            tvUserStatus.setTextColor(getResources().getColor(R.color.text_muted));
        } else {
            tvUserStatus.setText("Last seen recently");
            tvUserStatus.setTextColor(getResources().getColor(R.color.text_muted));
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        llChatContainer = findViewById(R.id.llChatContainer);
        chatScrollView = findViewById(R.id.chatScrollView);
        
        chatScrollView.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                chatScrollView.postDelayed(this::scrollToBottom, 50);
            }
        });

        FloatingActionButton btnSend = findViewById(R.id.btnSend);
        etMessage = findViewById(R.id.etMessage);
        btnMic = findViewById(R.id.btnMic);
        ImageButton btnAttach = findViewById(R.id.btnAttach);

        addTextMessage("Hi! Can we connect regarding your post?", false, null);

        btnSend.setOnClickListener(v -> {
            String msg = etMessage.getText().toString().trim();
            if (!msg.isEmpty()) {
                addTextMessage(msg, true, null);
                etMessage.setText("");
            }
        });

        btnMic.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_MIC);
                return;
            }
            if (isRecording) {
                stopRecording();
            } else {
                startRecording();
            }
        });

        btnAttach.setOnClickListener(v -> {
            pickMedia.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });
    }

    private void startRecording() {
        try {
            voiceNotePath = getExternalCacheDir().getAbsolutePath() + "/chat_voice_" + System.currentTimeMillis() + ".3gp";
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(voiceNotePath);
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;

            btnMic.setColorFilter(getResources().getColor(R.color.brand_error_solid)); 
            etMessage.setText("");
            etMessage.setEnabled(false);
            
            recordStartTime = System.currentTimeMillis();
            timerHandler.post(timerRunnable);
            
        } catch (Exception e) {
            Toast.makeText(this, "Could not start recording.", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
        } catch (Exception ignored) {}
        mediaRecorder = null;
        isRecording = false;

        btnMic.clearColorFilter();
        etMessage.setEnabled(true);
        etMessage.setHint("Type a message...");
        timerHandler.removeCallbacks(timerRunnable);

        long duration = System.currentTimeMillis() - recordStartTime;
        int seconds = (int) (duration / 1000);
        String timeStr = String.format("%d:%02d", seconds / 60, seconds % 60);
        
        addAudioMessage(timeStr, true, voiceNotePath);
    }
    
    private void playAudio(String path, ImageView playIcon) {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    if (currentPlayingIcon != null) {
                        currentPlayingIcon.setImageResource(android.R.drawable.ic_media_play);
                    }
                    return; // Pause the audio
                }
                mediaPlayer.release();
                if (currentPlayingIcon != null) {
                    currentPlayingIcon.setImageResource(android.R.drawable.ic_media_play);
                }
            }
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
            
            playIcon.setImageResource(android.R.drawable.ic_media_pause);
            currentPlayingIcon = playIcon;
            
            mediaPlayer.setOnCompletionListener(mp -> {
                playIcon.setImageResource(android.R.drawable.ic_media_play);
                mp.release();
                mediaPlayer = null;
                currentPlayingIcon = null;
            });
            Toast.makeText(this, "Playing message...", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Playback error", Toast.LENGTH_SHORT).show();
            if (playIcon != null) {
                playIcon.setImageResource(android.R.drawable.ic_media_play);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_MIC && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRecording();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            try { mediaRecorder.release(); } catch (Exception ignored) {}
        }
        if (mediaPlayer != null) {
            try { mediaPlayer.release(); } catch (Exception ignored) {}
        }
    }

    private void addAudioMessage(String timeStr, boolean isOutgoing, String audioPath) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 0, 0, dpToPx(16));
        row.setLayoutParams(rowParams);
        row.setGravity(isOutgoing ? Gravity.END : Gravity.START);

        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                dpToPx(180), ViewGroup.LayoutParams.WRAP_CONTENT);
        if (isOutgoing) {
            cardParams.setMargins(dpToPx(60), 0, 0, 0);
        } else {
            cardParams.setMargins(0, 0, dpToPx(60), 0);
        }
        card.setLayoutParams(cardParams);
        card.setRadius(dpToPx(24)); // rounded pill
        card.setCardElevation(dpToPx(1));
        
        if (isOutgoing) {
            card.setCardBackgroundColor(getResources().getColor(R.color.brand_primary));
        } else {
            card.setCardBackgroundColor(getResources().getColor(R.color.white));
            card.setStrokeColor(getResources().getColor(R.color.divider_soft));
            card.setStrokeWidth(dpToPx(1));
        }

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.HORIZONTAL);
        contentLayout.setGravity(Gravity.CENTER_VERTICAL);
        contentLayout.setPadding(dpToPx(12), dpToPx(8), dpToPx(16), dpToPx(8));

        ImageView playIcon = new ImageView(this);
        playIcon.setImageResource(android.R.drawable.ic_media_play);
        LinearLayout.LayoutParams playParams = new LinearLayout.LayoutParams(dpToPx(32), dpToPx(32));
        playIcon.setLayoutParams(playParams);
        if (isOutgoing) {
            playIcon.setColorFilter(getResources().getColor(R.color.white));
        } else {
            playIcon.setColorFilter(getResources().getColor(R.color.brand_primary));
        }

        TextView tvTime = new TextView(this);
        tvTime.setText("Voice Memo [" + timeStr + "]");
        tvTime.setTextSize(14f);
        tvTime.setPadding(dpToPx(8), 0, 0, 0);
        if (isOutgoing) {
            tvTime.setTextColor(getResources().getColor(R.color.white));
        } else {
            tvTime.setTextColor(getResources().getColor(R.color.text_header));
        }

        contentLayout.addView(playIcon);
        contentLayout.addView(tvTime);
        card.addView(contentLayout);

        if (audioPath != null) {
            card.setClickable(true);
            card.setFocusable(true);
            card.setOnClickListener(v -> playAudio(audioPath, playIcon));
        }

        row.addView(card);
        llChatContainer.addView(row);
        
        scrollToBottom();
    }

    private void addTextMessage(String text, boolean isOutgoing, String audioPath) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 0, 0, dpToPx(16));
        row.setLayoutParams(rowParams);
        row.setGravity(isOutgoing ? Gravity.END : Gravity.START);

        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (isOutgoing) {
            cardParams.setMargins(dpToPx(60), 0, 0, 0);
        } else {
            cardParams.setMargins(0, 0, dpToPx(60), 0);
        }
        card.setLayoutParams(cardParams);
        card.setRadius(dpToPx(16));
        card.setCardElevation(dpToPx(1));
        
        if (isOutgoing) {
            card.setCardBackgroundColor(getResources().getColor(R.color.brand_primary));
        } else {
            card.setCardBackgroundColor(getResources().getColor(R.color.white));
            card.setStrokeColor(getResources().getColor(R.color.divider_soft));
            card.setStrokeWidth(dpToPx(1));
        }

        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12));
        tv.setTextSize(14f);
        if (isOutgoing) {
            tv.setTextColor(getResources().getColor(R.color.white));
        } else {
            tv.setTextColor(getResources().getColor(R.color.text_header));
        }

        card.addView(tv);
        row.addView(card);
        llChatContainer.addView(row);
        
        scrollToBottom();
    }

    private void addImageMessage(Uri uri, boolean isOutgoing) {
        LinearLayout row = new LinearLayout(this);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 0, 0, dpToPx(16));
        row.setLayoutParams(rowParams);
        row.setGravity(isOutgoing ? Gravity.END : Gravity.START);

        MaterialCardView card = new MaterialCardView(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                dpToPx(200), dpToPx(200));
        if (isOutgoing) {
            cardParams.setMargins(dpToPx(60), 0, 0, 0);
        } else {
            cardParams.setMargins(0, 0, dpToPx(60), 0);
        }
        card.setLayoutParams(cardParams);
        card.setRadius(dpToPx(16));
        card.setCardElevation(dpToPx(1));
        
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setImageURI(uri);

        card.addView(iv);
        row.addView(card);
        llChatContainer.addView(row);
        
        scrollToBottom();
    }

    private void scrollToBottom() {
        chatScrollView.post(() -> chatScrollView.fullScroll(NestedScrollView.FOCUS_DOWN));
    }

    private int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }
}
