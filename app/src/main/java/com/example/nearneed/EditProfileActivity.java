package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Back action
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        
        Chip btnAddService = findViewById(R.id.btnAddService);
        ChipGroup cgServices = findViewById(R.id.cgServices);
        
        // Initialize existing chips
        if (cgServices != null) {
            for (int i = 0; i < cgServices.getChildCount(); i++) {
                View child = cgServices.getChildAt(i);
                if (child instanceof Chip && child.getId() != R.id.btnAddService) {
                    Chip existingChip = (Chip) child;
                    existingChip.setOnCloseIconClickListener(v -> cgServices.removeView(existingChip));
                }
            }
        }

        // Add Service Action (Open Popup)
        if (btnAddService != null) {
            btnAddService.setOnClickListener(v -> showServiceSelectionPopup(cgServices));
        }

        // Forgot Password Action (Open OTP Popup)
        TextView btnForgotPassword = findViewById(R.id.btnForgotPassword);
        if (btnForgotPassword != null) {
            btnForgotPassword.setOnClickListener(v -> showOtpPopup());
        }

        // Save action
        findViewById(R.id.btnSave).setOnClickListener(v -> {
            Toast.makeText(this, "Profile changes saved successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void showServiceSelectionPopup(ChipGroup parentGroup) {
        BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_services, null);
        dialog.setContentView(dialogView);

        ChipGroup cgAllServices = dialogView.findViewById(R.id.cgAllServices);
        
        dialogView.findViewById(R.id.btnAddSelected).setOnClickListener(v -> {
            for (int i = 0; i < cgAllServices.getChildCount(); i++) {
                Chip chip = (Chip) cgAllServices.getChildAt(i);
                if (chip.isChecked()) {
                    addNewServiceChip(parentGroup, chip.getText().toString());
                }
            }
            dialog.dismiss();
        });

        dialog.show();
    }

    private void addNewServiceChip(ChipGroup parent, String serviceName) {
        // Prevent duplicate chips
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            if (child instanceof Chip && ((Chip) child).getText().toString().equalsIgnoreCase(serviceName)) {
                return;
            }
        }

        Chip chip = new Chip(this);
        chip.setText(serviceName);
        chip.setCloseIconVisible(true);
        chip.setChipBackgroundColorResource(R.color.white);
        chip.setChipStrokeColorResource(R.color.brand_primary);
        chip.setChipStrokeWidth(1);
        chip.setTextColor(getResources().getColor(R.color.brand_primary));
        chip.setCloseIconTintResource(R.color.brand_primary);
        
        chip.setOnCloseIconClickListener(v -> parent.removeView(chip));
        
        // Add before the "Add More" button
        int count = parent.getChildCount();
        parent.addView(chip, count - 1);
    }

    private void showOtpPopup() {
        BottomSheetDialog dialog = new BottomSheetDialog(this, R.style.BottomSheetDialogTheme);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_otp_verify, null);
        dialog.setContentView(dialogView);

        EditText otp1 = dialogView.findViewById(R.id.otp1);
        EditText otp2 = dialogView.findViewById(R.id.otp2);
        EditText otp3 = dialogView.findViewById(R.id.otp3);
        EditText otp4 = dialogView.findViewById(R.id.otp4);

        setupOtpAutoFollow(otp1, otp2);
        setupOtpAutoFollow(otp2, otp3);
        setupOtpAutoFollow(otp3, otp4);

        dialogView.findViewById(R.id.btnVerifyOtp).setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(EditProfileActivity.this, ResetPasswordActivity.class));
        });

        dialog.show();
    }

    private void setupOtpAutoFollow(final EditText current, final EditText next) {
        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    next.requestFocus();
                }
            }
        });
    }
}
