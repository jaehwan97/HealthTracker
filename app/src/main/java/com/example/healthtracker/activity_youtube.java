package com.example.healthtracker;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class activity_youtube extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    ImageButton re_list;
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight,le_diet,le_chart,le_diary,le_goal,le_kcal;

    //스트레칭
    ImageView youtubeC_1_1;
    TextView youtubeC_1;
    LinearLayout youtube_1;
    LinearLayout youtube_2;
    boolean youtu1=true; // 이걸로 조절
    TextView youtube_intent_1; //검색어 링크

    //3대운동
    ImageView youtubeC_2_1;
    TextView youtubeC_2;
    LinearLayout youtube2_1;
    boolean youtu2=true; // 이걸로 조절
    TextView youtube_intent_2; //검색어 링크

    //어깨
    ImageView youtubeC3_0_1;
    TextView youtubeC3_0;
    LinearLayout youtube3_0;
    boolean youtu3_0=true; // 이걸로 조절
    TextView youtube_intent_3; //검색어 링크

    //가슴
    ImageView youtubeC3_1_1;
    TextView youtubeC3_1;
    LinearLayout youtube3_1;
    boolean youtu3_1=true; // 이걸로 조절
    TextView youtube_intent_4; //검색어 링크

    //등
    ImageView youtubeC3_2_1;
    TextView youtubeC3_2;
    LinearLayout youtube3_2;
    boolean youtu3_2=true; // 이걸로 조절
    TextView youtube_intent_5; //검색어 링크

    //복근
    ImageView youtubeC3_3_1;
    TextView youtubeC3_3;
    LinearLayout youtube3_3;
    boolean youtu3_3=true; // 이걸로 조절
    TextView youtube_intent_6; //검색어 링크

    //팔
    ImageView youtubeC3_4_1;
    TextView youtubeC3_4;
    LinearLayout youtube3_4;
    boolean youtu3_4=true; // 이걸로 조절
    TextView youtube_intent_7; //검색어 링크

    //하체
    ImageView youtubeC3_5_1;
    TextView youtubeC3_5;
    LinearLayout youtube3_5;
    boolean youtu3_5=true; // 이걸로 조절
    TextView youtube_intent_8; //검색어 링크

    //종아리
    ImageView youtubeC3_6_1;
    TextView youtubeC3_6;
    LinearLayout youtube3_6;
    boolean youtu3_6=true; // 이걸로 조절
    TextView youtube_intent_9; //검색어 링크

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        le_schedule = findViewById(R.id.le_schedule);
        le_bmi = findViewById(R.id.le_bmi);
        le_question = findViewById(R.id.le_question);
        le_weight = findViewById(R.id.le_weight);
        le_diet = findViewById(R.id.le_diet);
        le_chart = findViewById(R.id.le_chart);
        le_diary = findViewById(R.id.le_diary);
        le_goal = findViewById(R.id.le_goal);
        le_kcal = findViewById(R.id.le_kcal);

        youtubeC_1_1 = findViewById(R.id.youtubeC_1_1); //스트레칭 화살표
        youtubeC_1 = findViewById(R.id.youtubeC_1); //운동전 스트레칭
        youtube_1=findViewById(R.id.youtube_1); //상체
        youtube_2=findViewById(R.id.youtube_2);//하체

        youtubeC_2_1 = findViewById(R.id.youtubeC_2_1); //3대 화살표
        youtubeC_2 = findViewById(R.id.youtubeC_2);//3대
        youtube2_1=findViewById(R.id.youtube2_1);//리니어

        //화살표
        youtubeC3_0_1 = findViewById(R.id.youtubeC3_0_1);//어깨 화살표
        youtubeC3_1_1 = findViewById(R.id.youtubeC3_1_1);//가슴
        youtubeC3_2_1 = findViewById(R.id.youtubeC3_2_1);//등
        youtubeC3_3_1 = findViewById(R.id.youtubeC3_3_1);//복근
        youtubeC3_4_1 = findViewById(R.id.youtubeC3_4_1);//팔
        youtubeC3_5_1 = findViewById(R.id.youtubeC3_5_1);//하체
        youtubeC3_6_1 = findViewById(R.id.youtubeC3_6_1);//종아리

        //레이아웃
        youtube3_0=findViewById(R.id.youtube3_0); //리니어 어깨
        youtube3_1=findViewById(R.id.youtube3_1);//가슴
        youtube3_2=findViewById(R.id.youtube3_2);//등
        youtube3_3=findViewById(R.id.youtube3_3);//복근
        youtube3_4=findViewById(R.id.youtube3_4);//팔
        youtube3_5=findViewById(R.id.youtube3_5);//하체
        youtube3_6=findViewById(R.id.youtube3_6);//종아리

        //텍스트
        youtubeC3_0=findViewById(R.id.youtubeC3_0);//어깨
        youtubeC3_1=findViewById(R.id.youtubeC3_1);//가슴
        youtubeC3_2=findViewById(R.id.youtubeC3_2);//등
        youtubeC3_3=findViewById(R.id.youtubeC3_3);//복근
        youtubeC3_4=findViewById(R.id.youtubeC3_4);//팔
        youtubeC3_5=findViewById(R.id.youtubeC3_5);//하체
        youtubeC3_6=findViewById(R.id.youtubeC3_6);//종아리

        //유튜브 링크
        youtube_intent_1=findViewById(R.id.youtube_intent_1);//스트레칭
        youtube_intent_2=findViewById(R.id.youtube_intent_2);//3대
        youtube_intent_3=findViewById(R.id.youtube_intent_3);//어깨
        youtube_intent_4=findViewById(R.id.youtube_intent_4);//가슴
        youtube_intent_5=findViewById(R.id.youtube_intent_5);//등
        youtube_intent_6=findViewById(R.id.youtube_intent_6);//복근
        youtube_intent_7=findViewById(R.id.youtube_intent_7);//팔
        youtube_intent_8=findViewById(R.id.youtube_intent_8);//하체
        youtube_intent_9=findViewById(R.id.youtube_intent_9);//종아리



        re_list = findViewById(R.id.re_list);
        re_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(MainActivity.this,activity_drawer.class); //어디서 어디로 이동할지 설정
                // startActivity(intent); //액티비티 실제 이동구간
                drawerLayout.openDrawer(drawerView);
            }
        });
        drawerLayout.addDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        le_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this, com.example.healthtracker.MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this, com.example.healthtracker.SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_youtube.this,activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        youtubeC_1.setOnClickListener(new View.OnClickListener() { //스트레칭
            @Override
            public void onClick(View v) {
                if (youtu1 == true) {
                    youtube_1.setVisibility(VISIBLE);
                    youtube_2.setVisibility(VISIBLE);
                    youtubeC_1_1.setImageResource(R.drawable.up_x);
                    youtu1=false;
                }
                else{
                    youtube_1.setVisibility(GONE);
                    youtube_2.setVisibility(GONE);
                    youtubeC_1_1.setImageResource(R.drawable.down_x);
                    youtu1=true;
                }
            }
        });
        youtubeC_1_1.setOnClickListener(new View.OnClickListener() { //스트레칭
            @Override
            public void onClick(View v) {
                if (youtu1 == true) {
                    youtube_1.setVisibility(VISIBLE);
                    youtube_2.setVisibility(VISIBLE);
                    youtubeC_1_1.setImageResource(R.drawable.up_x);
                    youtu1=false;
                }
                else{
                    youtube_1.setVisibility(GONE);
                    youtube_2.setVisibility(GONE);
                    youtubeC_1_1.setImageResource(R.drawable.down_x);
                    youtu1=true;
                }
            }
        });

        youtubeC_2.setOnClickListener(new View.OnClickListener() { //3대
            @Override
            public void onClick(View v) {
                if (youtu2 == true) {
                    youtube2_1.setVisibility(VISIBLE);
                    youtubeC_2_1.setImageResource(R.drawable.up_x);
                    youtu2=false;
                }
                else{
                    youtube2_1.setVisibility(GONE);
                    youtubeC_2_1.setImageResource(R.drawable.down_x);
                    youtu2=true;
                }
            }
        });
        youtubeC_2_1.setOnClickListener(new View.OnClickListener() { //3대
            @Override
            public void onClick(View v) {
                if (youtu2 == true) {
                    youtube2_1.setVisibility(VISIBLE);
                    youtubeC_2_1.setImageResource(R.drawable.up_x);
                    youtu2=false;
                }
                else{
                    youtube2_1.setVisibility(GONE);
                    youtubeC_2_1.setImageResource(R.drawable.down_x);
                    youtu2=true;
                }
            }
        });

        youtubeC3_0.setOnClickListener(new View.OnClickListener() {//어깨
            @Override
            public void onClick(View v) {
                if (youtu3_0 == true) {
                    youtube3_0.setVisibility(VISIBLE);
                    youtubeC3_0_1.setImageResource(R.drawable.up_x);
                    youtu3_0=false;
                }
                else{
                    youtube3_0.setVisibility(GONE);
                    youtubeC3_0_1.setImageResource(R.drawable.down_x);
                    youtu3_0=true;
                }
            }
        });
        youtubeC3_0_1.setOnClickListener(new View.OnClickListener() {//어깨 화살표
            @Override
            public void onClick(View v) {
                if (youtu3_0 == true) {
                    youtube3_0.setVisibility(VISIBLE);
                    youtubeC3_0_1.setImageResource(R.drawable.up_x);
                    youtu3_0=false;
                }
                else{
                    youtube3_0.setVisibility(GONE);
                    youtubeC3_0_1.setImageResource(R.drawable.down_x);
                    youtu3_0=true;
                }
            }
        });
        youtubeC3_1.setOnClickListener(new View.OnClickListener() {//가슴
            @Override
            public void onClick(View v) {
                if (youtu3_1 == true) {
                    youtube3_1.setVisibility(VISIBLE);
                    youtubeC3_1_1.setImageResource(R.drawable.up_x);
                    youtu3_1=false;
                }
                else{
                    youtube3_1.setVisibility(GONE);
                    youtubeC3_1_1.setImageResource(R.drawable.down_x);
                    youtu3_1=true;
                }
            }
        });
        youtubeC3_1_1.setOnClickListener(new View.OnClickListener() {//가슴
            @Override
            public void onClick(View v) {
                if (youtu3_1 == true) {
                    youtube3_1.setVisibility(VISIBLE);
                    youtubeC3_1_1.setImageResource(R.drawable.up_x);
                    youtu3_1=false;
                }
                else{
                    youtube3_1.setVisibility(GONE);
                    youtubeC3_1_1.setImageResource(R.drawable.down_x);
                    youtu3_1=true;
                }
            }
        });
        youtubeC3_2.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                if (youtu3_2 == true) {
                    youtube3_2.setVisibility(VISIBLE);
                    youtubeC3_2_1.setImageResource(R.drawable.up_x);
                    youtu3_2=false;
                }
                else{
                    youtube3_2.setVisibility(GONE);
                    youtubeC3_2_1.setImageResource(R.drawable.down_x);
                    youtu3_2=true;
                }
            }
        });
        youtubeC3_2_1.setOnClickListener(new View.OnClickListener() {//
            @Override
            public void onClick(View v) {
                if (youtu3_2 == true) {
                    youtube3_2.setVisibility(VISIBLE);
                    youtubeC3_2_1.setImageResource(R.drawable.up_x);
                    youtu3_2=false;
                }
                else{
                    youtube3_2.setVisibility(GONE);
                    youtubeC3_2_1.setImageResource(R.drawable.down_x);
                    youtu3_2=true;
                }
            }
        });
        youtubeC3_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_3 == true) {
                    youtube3_3.setVisibility(VISIBLE);
                    youtubeC3_3_1.setImageResource(R.drawable.up_x);
                    youtu3_3=false;
                }
                else{
                    youtube3_3.setVisibility(GONE);
                    youtubeC3_3_1.setImageResource(R.drawable.down_x);
                    youtu3_3=true;
                }
            }
        });
        youtubeC3_3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_3 == true) {
                    youtube3_3.setVisibility(VISIBLE);
                    youtubeC3_3_1.setImageResource(R.drawable.up_x);
                    youtu3_3=false;
                }
                else{
                    youtube3_3.setVisibility(GONE);
                    youtubeC3_3_1.setImageResource(R.drawable.down_x);
                    youtu3_3=true;
                }
            }
        });
        youtubeC3_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_4 == true) {
                    youtube3_4.setVisibility(VISIBLE);
                    youtubeC3_4_1.setImageResource(R.drawable.up_x);
                    youtu3_4=false;
                }
                else{
                    youtube3_4.setVisibility(GONE);
                    youtubeC3_4_1.setImageResource(R.drawable.down_x);
                    youtu3_4=true;
                }
            }
        });
        youtubeC3_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_4 == true) {
                    youtube3_4.setVisibility(VISIBLE);
                    youtubeC3_4_1.setImageResource(R.drawable.up_x);
                    youtu3_4=false;
                }
                else{
                    youtube3_4.setVisibility(GONE);
                    youtubeC3_4_1.setImageResource(R.drawable.down_x);
                    youtu3_4=true;
                }
            }
        });
        youtubeC3_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_5 == true) {
                    youtube3_5.setVisibility(VISIBLE);
                    youtubeC3_5_1.setImageResource(R.drawable.up_x);
                    youtu3_5=false;
                }
                else{
                    youtube3_5.setVisibility(GONE);
                    youtubeC3_5_1.setImageResource(R.drawable.down_x);
                    youtu3_5=true;
                }
            }
        });
        youtubeC3_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_5 == true) {
                    youtube3_5.setVisibility(VISIBLE);
                    youtubeC3_5_1.setImageResource(R.drawable.up_x);
                    youtu3_5=false;
                }
                else{
                    youtube3_5.setVisibility(GONE);
                    youtubeC3_5_1.setImageResource(R.drawable.down_x);
                    youtu3_5=true;
                }
            }
        });
        youtubeC3_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_6 == true) {
                    youtube3_6.setVisibility(VISIBLE);
                    youtubeC3_6_1.setImageResource(R.drawable.up_x);
                    youtu3_6=false;
                }
                else{
                    youtube3_6.setVisibility(GONE);
                    youtubeC3_6_1.setImageResource(R.drawable.down_x);
                    youtu3_6=true;
                }
            }
        });
        youtubeC3_6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (youtu3_6 == true) {
                    youtube3_6.setVisibility(VISIBLE);
                    youtubeC3_6_1.setImageResource(R.drawable.up_x);
                    youtu3_6=false;
                }
                else{
                    youtube3_6.setVisibility(GONE);
                    youtubeC3_6_1.setImageResource(R.drawable.down_x);
                    youtu3_6=true;
                }
            }
        });

        youtube_intent_1.setOnClickListener(new View.OnClickListener() { //스트레칭 방법 링크
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EC%9A%B4%EB%8F%99%EC%A0%84+%EC%8A%A4%ED%8A%B8%EB%A0%88%EC%B9%AD+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_2.setOnClickListener(new View.OnClickListener() {//3대
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=3%EB%8C%80+%EC%9A%B4%EB%8F%99+%EB%B0%A9%EB%B2%95"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_3.setOnClickListener(new View.OnClickListener() {//어깨
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EC%96%B4%EA%B9%A8+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_4.setOnClickListener(new View.OnClickListener() { //가슴
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EA%B0%80%EC%8A%B4+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_5.setOnClickListener(new View.OnClickListener() {//등
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EB%93%B1+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_6.setOnClickListener(new View.OnClickListener() { //복근
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EB%B3%B5%EA%B7%BC+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_7.setOnClickListener(new View.OnClickListener() { //팔
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%ED%8C%94+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_8.setOnClickListener(new View.OnClickListener() {//하체
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%ED%95%98%EC%B2%B4+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
        youtube_intent_9.setOnClickListener(new View.OnClickListener() {//종아리
            @Override
            public void onClick(View v) {
                Intent intentUrl=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=%EC%A2%85%EC%95%84%EB%A6%AC+%EC%9A%B4%EB%8F%99+%EC%B6%94%EC%B2%9C"));
                startActivity(intentUrl);
            }
        });
    }

    DrawerLayout.DrawerListener listener =new DrawerLayout.DrawerListener() { //만들어 놓으면 좋아서 만들어놓은거임
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) { //슬라이드했을때

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}