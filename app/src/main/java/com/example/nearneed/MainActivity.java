package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    private FrameLayout cardGig1, cardCommunity1, cardGig2;
    private ChipGroup cgFilters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        cardGig1 = findViewById(R.id.cardGig1);
        cardCommunity1 = findViewById(R.id.cardCommunity1);
        cardGig2 = findViewById(R.id.cardGig2);
        cgFilters = findViewById(R.id.cgFilters);

        cgFilters.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.isEmpty()) return;
            
            int id = checkedIds.get(0);
            
            // Note: Since IDs are not strictly mapped in ChipGroup, we inspect the chip child index
            int idx = group.indexOfChild(findViewById(id));
            
            if (idx == 0) {
                // All
                cardGig1.setVisibility(View.VISIBLE);
                cardGig2.setVisibility(View.VISIBLE);
                cardCommunity1.setVisibility(View.VISIBLE);
            } else if (idx == 1) {
                // Gigs
                cardGig1.setVisibility(View.VISIBLE);
                cardGig2.setVisibility(View.VISIBLE);
                cardCommunity1.setVisibility(View.GONE);
            } else if (idx == 2) {
                // Community
                cardGig1.setVisibility(View.GONE);
                cardGig2.setVisibility(View.GONE);
                cardCommunity1.setVisibility(View.VISIBLE);
            } else {
                // Near By
                cardGig1.setVisibility(View.VISIBLE);
                cardGig2.setVisibility(View.VISIBLE);
                cardCommunity1.setVisibility(View.VISIBLE);
            }
        });
    }
}