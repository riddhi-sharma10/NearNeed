package com.example.nearneed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nearneed.R;
import com.example.nearneed.MessagesActivity;
import com.google.android.material.button.MaterialButton;

public class VolunteerResponsesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_responses);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        ImageButton btnOptions = findViewById(R.id.btnOptions);
        if (btnOptions != null) {
            btnOptions.setOnClickListener(this::showOptionsMenu);
        }

        // Buttons for Anjali
        MaterialButton btnAcceptAnjali = findViewById(R.id.btnAcceptAnjali);
        MaterialButton btnMsgAnjali = findViewById(R.id.btnMsgAnjali);

        if (btnAcceptAnjali != null) {
            btnAcceptAnjali.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Anjali's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgAnjali != null) {
            btnMsgAnjali.setOnClickListener(v -> openMessages("Anjali M."));
        }

        // Buttons for Rahul
        MaterialButton btnAcceptRahul = findViewById(R.id.btnAcceptRahul);
        MaterialButton btnMsgRahul = findViewById(R.id.btnMsgRahul);

        if (btnAcceptRahul != null) {
            btnAcceptRahul.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Rahul's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgRahul != null) {
            btnMsgRahul.setOnClickListener(v -> openMessages("Rahul S."));
        }

        // Buttons for Sarah
        MaterialButton btnAcceptSarah = findViewById(R.id.btnAcceptSarah);
        MaterialButton btnMsgSarah = findViewById(R.id.btnMsgSarah);

        if (btnAcceptSarah != null) {
            btnAcceptSarah.setOnClickListener(v -> {
                Toast.makeText(this, "Accepted Sarah's response!", Toast.LENGTH_SHORT).show();
            });
        }
        if (btnMsgSarah != null) {
            btnMsgSarah.setOnClickListener(v -> openMessages("Sarah K."));
        }
    }

    private void openMessages(String userName) {
        Intent intent = new Intent(this, MessagesActivity.class);
        intent.putExtra("USER_NAME", userName);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showOptionsMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenu().add("View Post Details");
        popup.getMenu().add("Share Responses Summary");
        popup.getMenu().add("Report an Issue");
        popup.setOnMenuItemClickListener(item -> {
            Toast.makeText(this, item.getTitle() + " feature coming soon", Toast.LENGTH_SHORT).show();
            return true;
        });
        popup.show();
    }
}
