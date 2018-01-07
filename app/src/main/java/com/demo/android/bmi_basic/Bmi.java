package com.demo.android.bmi_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

//Method2: 具名listener
//public class Bmi extends AppCompatActivity  implements View.OnClickListener{

//Method3: 匿名listener
public class Bmi extends AppCompatActivity{
    Button button;
    EditText height,weight;
    TextView result,suggest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //show linearlayout
        setContentView(R.layout.linearlayout);

        //show framelayout
//        setContentView(R.layout.framelayout);

        //Method3: 匿名listener
        findViews();
        setListeners();

    }

    //Method1: OnClick from xml
//    public void onClick(View view) {
//        System.out.println("pressed button!");
//    }

    //Method2: 具名listener
//    @Override
//    public void onClick(View v) {
//        System.out.println("pressed button by 具名listener");
//
//    }

    void findViews(){
        button=findViewById(R.id.submit);
        height= findViewById(R.id.height);
        weight= findViewById(R.id.weight);
        result = findViewById(R.id.result);
        suggest = findViewById(R.id.suggest);
    }

    void setListeners(){
        button.setOnClickListener(listener);
    }


    //Method3: 匿名listener
    View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            System.out.println("pressed button by 匿名listener!");
            try {
                double BMI=calcBMI();
                showBMI(BMI);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // other actions here


        }
    };

    Double calcBMI(){


        double h = Double.parseDouble(height.getText()+"")/100;
        double w= Double.parseDouble(weight.getText()+"");
        double BMI= w/(h*h) ;
        return BMI;

    }

    void showBMI(double BMI){
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
