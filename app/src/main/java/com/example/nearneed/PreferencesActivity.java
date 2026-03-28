package com.example.nearneed;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.LinkedHashMap;
import java.util.Map;

public class PreferencesActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private MaterialButton btnEnter;
    private ChipGroup cgNeeds, cgOffers;

    // Define all available needs and their corresponding base icon
    private final Map<String, String> needsData = new LinkedHashMap<String, String>() {{
        put("Moving Help", "truck");
        put("Plumbing", "wrench");
        put("Electrical", "plug");
        put("Cleaning", "broom");
        put("Cooking", "oven");
        put("Gardening", "plant");
        put("Pet Sitting", "paw");
    }};

    // Define all available offers and their corresponding base icon
    private final Map<String, String> offersData = new LinkedHashMap<String, String>() {{
        put("Moving Help", "truck");
        put("Plumbing", "wrench");
        put("Electrical", "plug");
        put("Cleaning", "broom");
        put("Cooking", "oven");
        put("IT Support", "laptop");
        put("Car Wash", "car");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        btnBack = findViewById(R.id.btnBack);
        btnEnter = findViewById(R.id.btnEnter);
        cgNeeds = findViewById(R.id.cgNeeds);
        cgOffers = findViewById(R.id.cgOffers);

        setupListeners();
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        btnEnter.setOnClickListener(v -> {
            Intent intent = new Intent(this, CommunityPreferencesActivity.class);
            startActivity(intent);
        });
    }
}
