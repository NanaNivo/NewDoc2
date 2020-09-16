package com.example.newdoc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.newdoc2.FirstActivity.db;


public class regester extends AppCompatActivity {
  private Button btnLogin;

  private EditText inputEmail;
  private EditText inputPassword,inputRepass;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_regester);

    inputEmail = (EditText) findViewById(R.id.usernameReg);
    inputPassword = (EditText) findViewById(R.id.passwordReg);
    inputRepass = (EditText) findViewById(R.id.Repassword);
    btnLogin = (Button) findViewById(R.id.btnReg);

    btnLogin.setOnClickListener(new View.OnClickListener() {

      public void onClick(View view) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String Repassword = inputRepass.getText().toString().trim();

        // Check for empty data in the form
        if (!email.isEmpty() && !password.isEmpty()&&!Repassword.isEmpty()) {
          // login user
          //   userLogin(email, password);
          if(password.equals(Repassword)&&!db.checFromReg(email,password))
          {
            db.insertDataToReg(email,password);
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
          }
          else if(!password.equals(Repassword))
          {
            Toast.makeText(getApplicationContext(),
                    "the password isnt correct", Toast.LENGTH_LONG)
                    .show();
          }
          else if(db.checFromReg(email,password))
          {
            Toast.makeText(getApplicationContext(),
                    "the username already exist", Toast.LENGTH_LONG)
                    .show();
          }



        } else {
          // Prompt user to enter credentials
          Toast.makeText(getApplicationContext(),
                  "Please enter the credentials!", Toast.LENGTH_LONG)
                  .show();

        }
      }

    });

  }
}

