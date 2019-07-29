package com.lifetime.layout_practice_seven_linksomelayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TempStep extends AppCompatActivity {

    Button signIn;
    TextView email;
    TextView password;
    String inputEmail;
    String inputPassword;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signIn = findViewById(R.id.sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        inputEmail = email.getText().toString();
        inputPassword = password.getText().toString();

        sharedPreferences = getSharedPreferences("dataSignUp",MODE_PRIVATE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean emailValid = email.getText().toString().equals(sharedPreferences.getString("email","") );
                boolean passwordValid = password.getText().toString().equals(sharedPreferences.getString("password",""));
                if(emailValid && passwordValid) {
                    Intent intent = new Intent(TempStep.this, WelcomeGiaoSu.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(TempStep.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        Intent intent = new Intent(TempStep.this,MainActivity.class);
        startActivity(intent);
    }
}
