package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        MaterialButton btnSavePassword = findViewById(R.id.btnSavePassword);
        btnSavePassword.setOnClickListener(v -> {
            // Logic to update password would go here.
            Toast.makeText(this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
            
            // Redirect to Profile Page
            Intent intent = new Intent(ResetPasswordActivity.this, UserProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }
}
