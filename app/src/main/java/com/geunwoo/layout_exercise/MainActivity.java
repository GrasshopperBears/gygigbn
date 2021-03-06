package com.geunwoo.layout_exercise;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    //
//    NotificationManager noti;
    ArrayList<TripUser> applicant_list = new ArrayList<>();
    User a = new User("doli", "1234", "Kim", "01090", "23", 1, "먹방, 사진");
    TripUser e = new TripUser(a, new Double[]{3.5,4.5}, new Double[]{4.5,5.7}, 1, 20, "");
    static int GET_SID = 1;
    private String CURSID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Button register = findViewById(R.id.Register);
        Button join = findViewById(R.id.join);
        Button login = findViewById(R.id.Login);
        Button board = findViewById(R.id.board);

//        applicant_list.add(e);
//        applicant_list.add(e);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CURSID.length() == 0)
                    Toast.makeText(getApplicationContext(), "로그인 후 이용해주세요", Toast.LENGTH_LONG).show();
                else{

                    Intent intent = new Intent(MainActivity.this, Registration.class);
                    intent.putExtra("CURSID", CURSID);
                    startActivity(intent);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, myLogin.class);
                    startActivityForResult(intent, GET_SID);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signIn.class);
                startActivity(intent);
            }
        });

        board.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {

                if(CURSID.length() == 0)
                    Toast.makeText(getApplicationContext(), "회원만 이용 가능합니다", Toast.LENGTH_LONG).show();
                else{
                    Intent intent = new Intent(MainActivity.this, board.class);
                    intent.putExtra("CURSID", CURSID);
                    startActivity(intent);
                }




//                PendingIntent pendingIntent  = PendingIntent.getActivity(MainActivity.this,
//                        0, new Intent(getApplicationContext(),Registration.class),
//                        PendingIntent.FLAG_UPDATE_CURRENT);
//
//                noti = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                Notification.Builder nott = new Notification.Builder(MainActivity.this)
//                        .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
//                        .setContentTitle("노티 제목")
//                        .setContentText("매칭 사항을 확인하세요.")
//                        .setDefaults(Notification.DEFAULT_SOUND)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                        .setAutoCancel(true)
//                        .setContentIntent(pendingIntent);
//
//                noti.notify(0, nott.build());
            }
        });

//        Intent cc = getIntent();
//        TripUser finishedUser = (TripUser) cc.getSerializableExtra("완료된 매칭");
//        if(finishedUser != null)
//            applicant_list.remove(finishedUser);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GET_SID && resultCode == Activity.RESULT_OK){
            CURSID = data.getStringExtra("result");
            Toast.makeText(getApplicationContext(), "당신의 아이디는 " + CURSID, Toast.LENGTH_LONG).show();
        }

    }

}
