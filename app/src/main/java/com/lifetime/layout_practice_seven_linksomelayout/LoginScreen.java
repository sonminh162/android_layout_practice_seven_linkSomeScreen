package com.lifetime.layout_practice_seven_linksomelayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.lifetime.layout_practice_seven_linksomelayout.Constant.EMAIL;
import static com.lifetime.layout_practice_seven_linksomelayout.Constant.PASSWORD;

public class LoginScreen extends AppCompatActivity {

    TextView signUp;
    Button signIn;
    TextView email;
    TextView password;
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signUp = findViewById(R.id.sign_up);
        signIn = findViewById(R.id.sign_in);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("dataSignUp",MODE_PRIVATE);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean emailValid = email.getText().toString().equals(sharedPreferences.getString(EMAIL,""));
                boolean passwordValid = password.getText().toString().equals(sharedPreferences.getString(PASSWORD,""));
                if(emailValid && passwordValid) {
                    Intent intent = new Intent(LoginScreen.this, WelcomeScreen.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginScreen.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, SignUpScreen.class);
                startActivity(intent);
            }
        });

    }

}
