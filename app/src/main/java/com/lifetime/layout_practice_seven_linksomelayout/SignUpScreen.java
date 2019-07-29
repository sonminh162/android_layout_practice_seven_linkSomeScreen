package com.lifetime.layout_practice_seven_linksomelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

import static com.lifetime.layout_practice_seven_linksomelayout.Constant.emailValue;
import static com.lifetime.layout_practice_seven_linksomelayout.Constant.firstNameValue;
import static com.lifetime.layout_practice_seven_linksomelayout.Constant.lastNameValue;
import static com.lifetime.layout_practice_seven_linksomelayout.Constant.passwordValue;

public class SignUpScreen extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
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
        passwordError = findViewById(R.id.password_error);
//-------------------------
        sharedPreferences = getSharedPreferences("dataSignUp", MODE_PRIVATE);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    saveDataToSharedPreferences();
                    openDialogShowResult();
                }
            }
        });
    }

    private boolean validate() {
        validateName();
        validatePassword();
        validateEmail();
        return validateName() && validateName() && validateEmail() && validatePassword();
    }

    private boolean validateName() {
        boolean flag1 = true, flag2 = true;
        String getFirstName = firstName.getText().toString();
        String getLastName = lastName.getText().toString();

        if (getFirstName.isEmpty()) {
            firstNameError.setText(R.string.empty_error);
            flag1 = false;
        } else {
            firstNameError.setText(R.string.empty);
        }

        if (getLastName.isEmpty()) {
            lastNameError.setText(R.string.empty_error);
            flag2 = false;
        } else {
            lastNameError.setText(R.string.empty);
        }

        return flag1 && flag2;
    }

    private boolean validateEmail() {
        String getEmail = emailSignUp.getText().toString().trim();
        if (getEmail.isEmpty()) {
            emailError.setText(R.string.empty_error);
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {
            emailError.setText(R.string.valid_email_error);
            return false;
        } else {
            emailError.setText(R.string.empty);
        }
        return true;

    }

    private boolean validatePassword() {
        String getPassword = passwordSignUp.getText().toString().trim();
        if (getPassword.isEmpty()) {
            passwordError.setText(R.string.empty_error);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(getPassword).matches()) {
            passwordError.setText(R.string.valid_password_error);
            return false;
        } else {
            passwordError.setText(R.string.empty);
        }
        return true;
    }

    private void saveDataToSharedPreferences() {
        String getFirstName = firstName.getText().toString();
        String getLastName = lastName.getText().toString();
        String getEmail = emailSignUp.getText().toString();
        String getPassword = passwordSignUp.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(firstNameValue, getFirstName);
        editor.putString(lastNameValue, getLastName);
        editor.putString(emailValue, getEmail);
        editor.putString(passwordValue, getPassword);
        editor.apply();
    }

    private void openDialogShowResult() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.title_dialog);
        alertDialog.setMessage(getResources().getString(R.string.message_dialog));

        alertDialog.setButton(getResources().getString(R.string.next_activity), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(SignUpScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });
        alertDialog.show();
    }
}
