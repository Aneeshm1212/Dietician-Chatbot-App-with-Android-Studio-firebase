package com.example.ft.aidt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmi extends AppCompatActivity {
  private TextView resd;
  private EditText hei,wei;
  private Button calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        resd=(TextView) findViewById(R.id.textView8);
        hei=(EditText) findViewById(R.id.editText9);
        wei =(EditText) findViewById(R.id.editText10);
        calc = (Button) findViewById(R.id.button6);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float height = Float.parseFloat(hei.getText().toString());
                float weight = Float.parseFloat(wei.getText().toString());
                float heigh = height/100;
                float bmi =  weight / (heigh*heigh);
               if(bmi <16 && bmi>15 )
               {
                   resd.setText("Result: Severely Underweight"+String.valueOf(bmi));
               }
                if(bmi <18.5 && bmi>16 )
                {
                    resd.setText("Result:  Underweight"+String.valueOf(bmi));
                }
                if(bmi <25 && bmi>18.5 )
                {
                    resd.setText("Result: Normal"+String.valueOf(bmi));
                }
                if(bmi <30 && bmi>25 )
                {
                    resd.setText("Result: Overweight"+String.valueOf(bmi));
                }
                if(bmi <35 && bmi>30 )
                {
                    resd.setText("Result: Moderately Obesse"+String.valueOf(bmi));
                }
                if(bmi <40 && bmi>35 )
                {
                    resd.setText("Result: Severely Obesse"+String.valueOf(bmi));
                }
                if(bmi >40 )
                {
                    resd.setText("Result: very Severely Obesse"+String.valueOf(bmi));
                }

            }
        });
    }
}
