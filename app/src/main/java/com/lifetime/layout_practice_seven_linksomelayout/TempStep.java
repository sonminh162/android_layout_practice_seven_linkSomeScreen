package com.lifetime.layout_practice_seven_linksomelayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TempStep extends AppCompatActivity {

    TextView signUpUseCase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signUpUseCase = findViewById(R.id.sign_up);
    }

    public void onClick(View view) {
        Intent intent = new Intent(TempStep.this,MainActivity.class);
        startActivity(intent);
    }
}
