package com.example.ft.aidt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import  com.google.firebase.auth.FirebaseAuth;
import  com.google.firebase.auth.AuthResult;
public class LoginActivity extends AppCompatActivity {
   private Button log;
   private ProgressBar progressBar;
   private EditText usern , paswor;
   private TextView siup,fuup;
   private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      siup = (TextView) findViewById(R.id.textView2);
      fuup = (TextView) findViewById(R.id.textView3);
      auth = FirebaseAuth.getInstance();
      if(auth.getCurrentUser()!=null)
      {
          startActivity(new Intent(LoginActivity.this,Actual.class));
      }
      siup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              signup();
          }
      });
      fuup.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              forgot();
          }
      });
      usern = (EditText) findViewById(R.id.editText);
      paswor =(EditText) findViewById(R.id.editText2);
      progressBar =(ProgressBar) findViewById(R.id.progressBar);
      log = (Button) findViewById(R.id.button2);
      auth = FirebaseAuth.getInstance();
      log.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          String email =usern.getText().toString();
          final String password = paswor.getText().toString();
          if(TextUtils.isEmpty(email))
          {
              Toast.makeText(getApplicationContext(),"Enter Your Email!!!",Toast.LENGTH_SHORT).show();
              return;
          }
          if(TextUtils.isEmpty(password))
          {
              Toast.makeText(getApplicationContext(),"Enter your password!!!",Toast.LENGTH_SHORT).show();
           return;
          }
          progressBar.setVisibility(View.VISIBLE);
          auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  progressBar.setVisibility(View.GONE);
                  if(!task.isSuccessful())
                  {
                      if(password.length()<6)
                      {
                          Toast.makeText(getApplicationContext(),"Password should be greater than 6 characters!",Toast.LENGTH_SHORT).show();
                      }
                      else
                      {
                          Toast.makeText(LoginActivity.this,"Authentication error!",Toast.LENGTH_LONG).show();
                      }
                  }
                  else
                  {
                      Intent intent = new Intent(LoginActivity.this,Actual.class);
                      startActivity(intent);
                   finish();
                  }
              }
          });
          }
      });



    }
    public  void signup()
    {
        Intent intent  = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivity(intent);
    }
    public void forgot()
    {
        Intent intent  = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
        startActivity(intent);

    }
}
