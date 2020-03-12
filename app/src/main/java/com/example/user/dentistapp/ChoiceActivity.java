package com.example.user.dentistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.dentistapp.R;

public class ChoiceActivity extends AppCompatActivity {
     private Button btn_consulter;
     private Button btn_contacter;
     private Button btn_account;
     private Button btn_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        btn_consulter =(Button)findViewById(R.id.btn_blog);
        btn_account=(Button)findViewById(R.id.btn_account);
        btn_contacter=(Button)findViewById(R.id.btn_contact);

        btn_contacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceActivity.this, ForumActivity.class));
            }
        });
        btn_consulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceActivity.this, FAQActivity.class));
            }
        });
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChoiceActivity.this, MainActivity.class));
            }
        });




    }
}
