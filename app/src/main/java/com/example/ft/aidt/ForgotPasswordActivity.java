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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private EditText ema;
    private TextView login,sign;
    private Button recover;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
         progressBar =(ProgressBar) findViewById(R.id.progressBar);
         login= (TextView) findViewById(R.id.textView7);
         login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
             }
         });
         sign=(TextView) findViewById(R.id.textView6);
         sign.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(ForgotPasswordActivity.this,SignUpActivity.class));
             }
         });
         ema= (EditText) findViewById(R.id.editText4);
         auth=FirebaseAuth.getInstance();
         ema.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String eml = ema.getText().toString().trim();
                 if (TextUtils.isEmpty(eml)) {
                     Toast.makeText(getApplicationContext(), "Enter your email to rcover password!!!", Toast.LENGTH_SHORT).show();
                 }
                 progressBar.setVisibility(View.VISIBLE);
                 auth.sendPasswordResetEmail(eml).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if (task.isSuccessful()) {
                             Toast.makeText(ForgotPasswordActivity.this, "Recovery options have been sent to your email address!!!", Toast.LENGTH_LONG).show();
                         } else {
                             Toast.makeText(ForgotPasswordActivity.this, "Failed to reset email or you havent registered wih us yet!!!", Toast.LENGTH_LONG).show();
                         }
                         progressBar.setVisibility(View.GONE);
                     }
                 });
             }
             });
    }
}
