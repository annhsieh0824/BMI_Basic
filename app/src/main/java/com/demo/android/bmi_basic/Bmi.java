package com.demo.android.bmi_basic;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

//Method2: 具名listener
//public class Bmi extends AppCompatActivity  implements View.OnClickListener{

//Method3: 匿名listener
public class Bmi extends AppCompatActivity{
    Button button;
    EditText height,weight;
    TextView result,suggest;
    String test;
    private static final String TAG="LifeCycle";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Bmi- onCreate");
        //show linearlayout
        setContentView(R.layout.linearlayout);

        //show framelayout
//        setContentView(R.layout.framelayout);

        //Method3: 匿名listener
        findViews();
        setListeners();


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"Bmi- onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Bmi- onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Bmi- onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Bmi- onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"Bmi- onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"Bmi- onRestart");
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
//                double BMI=calcBMI();
//                showBMI(BMI);
//                openOptionDialog();

                //switch to report
//                Intent intent= new Intent();
//                intent.setClass(Bmi.this,Report.class);
                // intent 2 in 1
                Intent intent = new Intent(Bmi.this, Report.class);

                Bundle bundle= new Bundle();
                bundle.putString("KEY_HEIGHT", height.getText().toString());
                bundle.putString("KEY_WEIGHT", weight.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);

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
//        result.setText("Your BMI is " +df.format(BMI));
        result.setText(getResources().getString(R.string.bmi_result)+df.format(BMI));

        if (BMI>25)
            suggest.setText(R.string.bmi_heavy);
        else if (BMI<20)
            suggest.setText(R.string.bmi_light);
        else
            suggest.setText(R.string.bmi_average);

    }


    void openOptionDialog(){
        //method1
//        new AlertDialog.Builder(Bmi.this).setTitle("About BMI").setMessage("Android BMI calc")
//                .setPositiveButton("Confirm",dialogListener)
//                .setNegativeButton("Cancel",null)
//                .show();
        //method2
        AlertDialog.Builder ab= new AlertDialog.Builder(Bmi.this);
        ab.setTitle("About BMI");
        ab.setMessage("Android BMI calc")
                .setPositiveButton("Confirm",dialogListener)
                .setNegativeButton("Cancel",dialogListenerToaster)
                .show();
    }

    DialogInterface.OnClickListener dialogListener= new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
//            System.out.println("Pressed Confirm in Dialog");
            Uri uri= Uri.parse("http://google.com");
            Intent intent= new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    };

    DialogInterface.OnClickListener dialogListenerToaster= new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(Bmi.this,"Pressed Cancel",Toast.LENGTH_LONG).show();
        }
    };





}
