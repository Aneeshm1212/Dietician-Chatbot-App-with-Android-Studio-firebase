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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
   private Button up;
   private TextView loginb;
   private EditText name,em,pas,dob,no;
  private   FirebaseAuth auth;
  private ProgressBar progressBar;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
      loginb = (TextView) findViewById(R.id.textView5);
       auth = FirebaseAuth.getInstance();
      loginb.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              back();
          }
      });
      name=(EditText) findViewById(R.id.editText8);
        em=(EditText) findViewById(R.id.editText3);
        pas=(EditText) findViewById(R.id.editText5);
        no=(EditText) findViewById(R.id.editText6);
        dob=(EditText) findViewById(R.id.editText7);
        progressBar =(ProgressBar) findViewById(R.id.progressBar);
        up=(Button) findViewById(R.id.button3);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= name.getText().toString().trim();
                String email =em.getText().toString().trim();
                String password = pas.getText().toString().trim();
                String number = no.getText().toString().trim();
                String date_0f_birth = dob.getText().toString().trim();
                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(getApplicationContext(),"You cnnot leave this blank!!!",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(getApplicationContext(),"You cannot leave this blank!!!",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(getApplicationContext(),"You cannot leave this blank!!!",Toast.LENGTH_LONG).show();
                }
                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(SignUpActivity.this,"Ceate user complete"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this,"SIgn up Failed",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            startActivity(new Intent(SignUpActivity.this,Actual.class));
                         finish();
                        }
                    }
                });
            }
        });
   }
public  void back()
{
    Intent intent  = new Intent(SignUpActivity.this,LoginActivity.class);
    startActivity(intent);
}
}
