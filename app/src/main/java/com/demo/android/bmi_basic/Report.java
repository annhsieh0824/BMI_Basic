package com.demo.android.bmi_basic;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Report extends AppCompatActivity {
    private static final String TAG="LifeCycle";
 

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        showBMI(calcBMI());
        Log.i(TAG,"Report- onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Report- onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Report- onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Report- onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Report- onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Report- onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Report- onRestart");
    }

    Double calcBMI(){
        Intent it= getIntent();
        Bundle bd= it.getExtras();
        double h = Double.parseDouble(bd.getString("KEY_HEIGHT"))/100;
        double w= Double.parseDouble(bd.getString("KEY_WEIGHT"));
        double BMI= w/(h*h) ;
        return BMI;

    }

    DecimalFormat df= new DecimalFormat("0.00");
    void showBMI(double BMI){
        TextView result= findViewById(R.id.result);
        TextView suggest=findViewById(R.id.suggest);
//        DecimalFormat df= new DecimalFormat("0.00");
//        result.setText(String.valueOf(R.string.bmi_result) +df.format(BMI));

        result.setText(getResources().getString(R.string.bmi_result)+df.format(BMI));

        if (BMI>25) {
            suggest.setText(R.string.bmi_heavy);
            //notification
            showNotification(BMI);
        }
        else if (BMI<20)
            suggest.setText(R.string.bmi_light);
        else
            suggest.setText(R.string.bmi_average);

    }

    protected void showNotification(double BMI){
        NotificationManager barManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        PendingIntent pi= PendingIntent.getActivity(Report.this,
                0,
                new Intent(Report.this,Bmi.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification barMsg= new Notification.Builder(Report.this).setContentTitle("Your BMI is too high")
                .setContentText("BMI="+df.format(BMI)+", Please notify your doctor")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pi)
                .build();

        barManager.notify(0,barMsg);

    }

}
