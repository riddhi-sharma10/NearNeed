package com.example.nearneed;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ReportPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Temporary layout
        
        String postType = getIntent().getStringExtra("postType");
        String postId = getIntent().getStringExtra("postId");
        Toast.makeText(this, "Reporting " + postType + ": " + postId, Toast.LENGTH_SHORT).show();
        
        // TODO: Implement report post UI
        finish();
    }
}
