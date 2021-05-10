package com.example.analogclock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    /*define clock hand views*/
    ImageView secondh;
    ImageView minuteh;
    ImageView hourh;

    /*second and minute hands movement*/
    static float perSM = 360 / 60;

    /*move for hourhand per minute*/
    static float HperM = 0.5f;

    /*starting angles for hands*/
    static float startSA = -180.0f;
    static float startMA = -180.0f;
    static float startHA = -180.0f;

    /*create new angles*/
    static float newSA;
    static float newMA;
    static float newHA;

    /*starting values for pieces of time*/
    static float startS = 0.0f;
    static float startM = 0.0f;
    static float startH = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*tie in clock hands*/
        setContentView(R.layout.activity_main);
        secondh = findViewById(R.id.tv_secondh);
        minuteh = findViewById(R.id.tv_minuteh);
        hourh = findViewById(R.id.tv_hourh);

        /*pivot points for clock hands*/
        secondh.setPivotX(0);
        secondh.setPivotY(0);
        minuteh.setPivotX(0);
        minuteh.setPivotY(0);
        hourh.setPivotX(0);
        hourh.setPivotY(0);



        /*get time and assign variables*/
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String time = (sdf.format(cal.getTime()));
        /*breaking time up into chunks and making it a value*/
        startS = (Integer.parseInt(time.substring(4)));
        startM = (Integer.parseInt(time.substring(2, 4)));
        startH = (Integer.parseInt(time.substring(0, 2))) % 12;//mod is to deal with 24hour times which my computer and phone use


        /*get starting angles for hands*/
        startSA += startS * perSM;
        startMA += startM * perSM;
        startHA += startH * 30 + startM * HperM;

        /*set new angles*/
        newSA = startSA;
        newMA = startMA;
        newHA = startHA;

        /*set hand angles*/
        secondh.setRotation(startSA);
        minuteh.setRotation(startMA);
        hourh.setRotation(startHA);
/*TESTING
        System.out.println();
        System.out.println("###################################################################################################################################");
        System.out.println("Time is: Hours: " + startH + " minutes " + startM + " seconds: " + startS);
        System.out.println("###################################################################################################################################");
        System.out.println();
        System.out.println();
        System.out.println("###################################################################################################################################");
        System.out.println("Y is: " + secondh.getMeasuredHeight());
        System.out.println("###################################################################################################################################");
        System.out.println();
//*/
        /*define a view for the timer to run on*/
        TextView timerView = findViewById(R.id.textView);
        timerView.postDelayed(timerTick, 1000);
    }


    // method to update the clock each second
    Runnable timerTick = new Runnable() {
        @Override
        public void run() {
            update();
            TextView timerView = findViewById(R.id.textView);
            timerView.postDelayed(this, 1000);
        }
    };


    /*method to update the clock face*/
    void update() {
        /*create new seconds measurement and set it*/
        newSA += perSM;
        secondh.setRotation(newSA);

        /*new minutes measurement and set it*/
        /*the +180 degrees is to counteract the -180 in the beginning; which made the "zero" for the hands the top of the clock
        * instead of the bottom of it. */
        /*every time the second hand moves past the top, one more perSM will be added to newMA*/
        newMA = (startMA + (((int) ((newSA + 180) / 360)) * perSM));
        minuteh.setRotation(newMA);

        /*the hour hand will move half of a degree (HperM) every time the minute hand passes the top of the clock/makes a loop*/
        newHA = (startHA + (((int) ((newMA + 180) / 360)) * HperM));
        hourh.setRotation(newHA);
    }


}
