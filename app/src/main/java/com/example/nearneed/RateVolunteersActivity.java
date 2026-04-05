package com.example.nearneed;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RateVolunteersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Temporary layout
        
        String gigId = getIntent().getStringExtra("gigId");
        Toast.makeText(this, "Rate Volunteers for gig: " + gigId, Toast.LENGTH_SHORT).show();
        
        // TODO: Implement rate volunteers UI
        finish();
    }
}
