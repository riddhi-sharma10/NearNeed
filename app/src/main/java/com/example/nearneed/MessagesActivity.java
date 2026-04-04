package com.example.nearneed;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MessagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // ── Bottom Navigation ────────────────────────────────────────────────
        NavbarHelper.setup(this, NavbarHelper.TAB_MESSAGES);

        // ── Conversation click listeners ─────────────────────────────────────
        linkChat(R.id.convRachel, "Rachel");
        linkChat(R.id.convManya, "Manya Awasthi");
        linkChat(R.id.convRahul, "Rahul Singh");
        linkChat(R.id.convRiddhi, "Riddhi Sharma");
        linkChat(R.id.convVishu, "Vishu Singh");
        linkChat(R.id.convAnanya, "Ananya Gupta");
        linkChat(R.id.convKaran, "Karan Mehta");
        linkChat(R.id.convPriya, "Priya Nair");
        linkChat(R.id.convDeepak, "Deepak Verma");
        linkChat(R.id.convMeera, "Meera Iyer");
        linkChat(R.id.convAditya, "Aditya Rao");
        linkChat(R.id.convSneha, "Sneha Patel");
        linkChat(R.id.convArjun, "Arjun Kumar");
    }

    private void linkChat(int resId, String name) {
        android.view.View v = findViewById(resId);
        if (v != null) {
            v.setOnClickListener(view -> {
                android.content.Intent i = new android.content.Intent(this, ChatActivity.class);
                i.putExtra("userName", name);
                startActivity(i);
            });
        }
    }
}
