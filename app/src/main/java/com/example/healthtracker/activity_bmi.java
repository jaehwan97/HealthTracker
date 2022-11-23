package com.example.healthtracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class activity_bmi extends AppCompatActivity {
    ImageButton re_list;
    Button le_bmii,le_btn;
    TextView le_result;
    EditText le_long,le_weight1;

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight,le_diet,le_chart,le_diary,le_goal,le_kcal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        re_list = findViewById(R.id.re_list);
        le_bmii = findViewById(R.id.le_bmii);
        le_result=(TextView)findViewById(R.id.le_result);
        le_long=(EditText)findViewById(R.id.le_long);
        le_weight1=(EditText)findViewById(R.id.le_weight1);
        le_btn=findViewById(R.id.le_btn);

        Intent intent =getIntent();
        String str = intent.getStringExtra("str");
        String strr = intent.getStringExtra("strr");
        le_long.setText(str);
        le_weight1.setText(strr);



        //1.값을 가져온다
        //2.클릭을 감지한다
        //3.1번의 값을 다음 액티비티로 넘긴다

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

        re_list = findViewById(R.id.re_list);
        re_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this,activity_drawer.class); //어디서 어디로 이동할지 설정
                //startActivity(intent); //액티비티 실제 이동구간
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
                Intent intent = new Intent(activity_bmi.this,MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_bmi.this,activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        le_bmii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url ="https://namu.wiki/w/BMI";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });

        le_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strNum = le_long.getText().toString(); //키

                double height = Double.parseDouble(strNum);

                strNum = le_weight1.getText().toString(); //몸무게

                double weight = Double.parseDouble(strNum);

                double result = weight / height / height * 10000; //BMI 구하는 공식

                //strNum = Double.toString(result);

                if(result <= 25) {
                    strNum = String.format("당신의 BMI 지수는 %.2f 입니다.\n" +
                            "당신은 정상 체중입니다.", result);
                }
                else if(result <=29.9){
                    strNum = String.format("당신의 BMI 지수는 %.2f 입니다.\n" +
                            "당신은 과체중(1도 비만)입니다.", result);
                }
                else if(result <=40){
                    strNum = String.format("당신의 BMI 지수는 %.2f 입니다.\n" +
                            "당신은 비만(2도 비만)입니다.", result);
                }
                else if(result < 40){
                    strNum = String.format("당신의 BMI 지수는 %.2f 입니다.\n" +
                            "당신은 고도비만입니다.", result);
                }
                else{
                    strNum = String.format("당신의 BMI 지수는 %.2f 입니다.", result);
                }
                le_result.setText(strNum);
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
}
