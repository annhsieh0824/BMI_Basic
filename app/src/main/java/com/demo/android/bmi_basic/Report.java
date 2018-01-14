package com.demo.android.bmi_basic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        showBMI(calcBMI());
    }


    Double calcBMI(){
        Intent it= getIntent();
        Bundle bd= it.getExtras();
        double h = Double.parseDouble(bd.getString("KEY_HEIGHT"))/100;
        double w= Double.parseDouble(bd.getString("KEY_WEIGHT"));
        double BMI= w/(h*h) ;
        return BMI;

    }

    void showBMI(double BMI){
        TextView result= findViewById(R.id.result);
        TextView suggest=findViewById(R.id.suggest);
        DecimalFormat df= new DecimalFormat("0.00");
        result.setText("Your BMI is " +df.format(BMI));

        if (BMI>25)
            suggest.setText(R.string.bmi_heavy);
        else if (BMI<20)
            suggest.setText(R.string.bmi_light);
        else
            suggest.setText(R.string.bmi_average);

    }
}
