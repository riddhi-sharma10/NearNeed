package com.example.nearneed;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ProfileInfoActivity extends AppCompatActivity {

    private EditText etFullName;
    private EditText etBio;
    private TextView tvBioCount;
    private MaterialButton btnContinue;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);

        initViews();
        setupListeners();
    }

    private void initViews() {
        etFullName = findViewById(R.id.etFullName);
        etBio = findViewById(R.id.etBio);
        tvBioCount = findViewById(R.id.tvBioCount);
        btnContinue = findViewById(R.id.btnContinue);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> onBackPressed());

        etBio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s != null ? s.length() : 0;
                tvBioCount.setText(length + "/150");
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        btnContinue.setOnClickListener(v -> {
            String name = etFullName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(this, getString(R.string.txt_please_enter_your_name), Toast.LENGTH_SHORT).show();
                return;
            }
            
            // Note: Since you deleted the other activities like ProfileSetupActivity, 
            // you might want to wait for them to be built before navigating.
            // Navigate to step 2
            startActivity(new android.content.Intent(ProfileInfoActivity.this, ProfileSetupActivity.class));
        });
    }
}
