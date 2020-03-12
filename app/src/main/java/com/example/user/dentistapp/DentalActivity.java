package com.example.user.dentistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class DentalActivity extends AppCompatActivity {
        private Button account;
        private Button register;
        private Button chat;
        private Button blog;
        private Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dental);
        chat=(Button) findViewById(R.id.btn_chat);
        register=(Button) findViewById(R.id.btn_reg);
       // account=(Button) findViewById(R.id.btn_CAccount);
        blog=(Button) findViewById(R.id.id_post);
        settings=(Button) findViewById(R.id.btn_settings);
      /*  account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DentalActivity.this, LoginActivity.class));
            }
        });*/
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DentalActivity.this, SignupActivity.class));
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DentalActivity.this, ForumActivity.class));
            }
        });
        blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DentalActivity.this, FAQActivity.class));
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DentalActivity.this, MainActivity.class));
            }
        });

    }
}
