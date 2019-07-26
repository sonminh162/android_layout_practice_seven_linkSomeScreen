package com.lifetime.layout_practice_seven_linksomelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,20}"                 //at least 4 characters
                    );

    SharedPreferences sharedPreferences;
    TextView firstName;
    TextView lastName;
    TextView emailSignUp;
    TextView passwordSignUp;
    Button signUpButton;
//    -- error
    TextView firstNameError;
    TextView lastNameError;
    TextView emailError;
    TextView passwordError;
//    ----------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

//-----------binding data
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        emailSignUp = findViewById(R.id.email_sign_up);
        passwordSignUp = findViewById(R.id.password_sign_up);
        signUpButton = findViewById(R.id.sign_up_button);
//------------binding error
        firstNameError = findViewById(R.id.first_name_error);
        lastNameError = findViewById(R.id.last_name_error);
        emailError = findViewById(R.id.email_error);
        passwordError= findViewById(R.id.password_error);
//-------------------------
        sharedPreferences = getSharedPreferences("dataSignUp",MODE_PRIVATE);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    saveDataToSharedPreferences();
                }
//                else{
//                    setErrorMessage();
//                }
            }
        });
    }

//    private void setErrorMessage(){
//        if(!validateName()){
//
//        }
//        if(!validateEmail()){
//
//        }
//        if(!validatePassword()){
//
//        }
//    }

    private boolean validate(){
        validateName();
        validatePassword();
        validateEmail();
        if(validateName() && validateName() && validateEmail() && validatePassword()){
            return true;
        }
        return false;
    }

    @SuppressLint("SetTextI18n")
    private boolean validateName(){
        boolean flag1=true,flag2=true;
        String getFirstName = firstName.getText().toString().trim();
        String getLastName = lastName.getText().toString().trim();
        if(getFirstName.isEmpty()){
            firstNameError.setText("Field can't be empty");
            flag1 = false;
        }else{
            firstNameError.setText("");
            flag1 = true;
        }
        if(getLastName.isEmpty()){
            lastNameError.setText("Field can't be empty");
            flag2 = false;
        }else{
            lastNameError.setText("");
        }
        if(flag1 && flag2){
            return true;
        }
        return false;
    }
    @SuppressLint("SetTextI18n")
    private boolean validateEmail(){
        String getEmail = emailSignUp.getText().toString().trim();
        if(getEmail.isEmpty()){
            emailError.setText("Field can't be empty");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()){
            emailError.setText("Enter a valid email address");
            return false;
        }else{
            emailError.setText("");
        }
        return true;

    }
    @SuppressLint("SetTextI18n")
    private boolean validatePassword(){
        String getPassword = passwordSignUp.getText().toString().trim();
        if(getPassword.isEmpty()){
            passwordError.setText("Field can't be empty");
            return false;
        }else if(!PASSWORD_PATTERN.matcher(getPassword).matches()){
            passwordError.setText("Password too weak");
            return false;
        }else{
            passwordError.setText("");
        }
        return true;
    }

    private void saveDataToSharedPreferences() {
        String getFirstName = firstName.getText().toString().trim();
        String getLastName = lastName.getText().toString().trim();
        String getEmail = emailSignUp.getText().toString().trim();
        String getPassword = passwordSignUp.getText().toString().trim();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("firstName",getFirstName);
        editor.putString("lastName",getLastName);
        editor.putString("email",getEmail);
        editor.putString("password",getPassword);
        editor.apply();
        openDialog();
    }

    private void openDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Information");
        alertDialog.setMessage("Sign Up Successfully");

        alertDialog.setButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this,TempStep.class);
                startActivity(intent);
            }
        });
        alertDialog.show();
    }
}
