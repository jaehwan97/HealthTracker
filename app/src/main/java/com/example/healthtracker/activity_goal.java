package com.example.healthtracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.fasterxml.jackson.annotation.Nulls;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executors;

public class activity_goal extends AppCompatActivity {

    ImageButton re_list;
    Button le_bmibtn;
    private EditText le_long,le_weight1,le_age,le_goalw,le_noww,le_ddayw,le_dday,le_nowday,le_d_day;
    private EditText le_man;
    String str,strrr;
    String strr;
    private Spinner spinner;
    String shared ="file";

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight,le_diet,le_chart,le_diary,le_goal,le_kcal;
    TextView le_weater;
    Button goal_button1;
    ImageButton le_X,le_X1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

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
        le_dday=findViewById(R.id.le_dday);
        le_nowday=findViewById(R.id.le_nowday);
        le_d_day=findViewById(R.id.le_d_day);
        le_weater =findViewById(R.id.le_weather);
        le_kcal =findViewById(R.id.le_kcal);
        goal_button1 =findViewById(R.id.goal_button1);
        le_X=findViewById(R.id.le_X);
        le_X1=findViewById(R.id.le_X1);

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
                Intent intent = new Intent(activity_goal.this,MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_goal.this,activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        le_man=(EditText)findViewById(R.id.le_man);
        spinner =(Spinner)findViewById(R.id.spinner);
        le_long = (EditText)findViewById(R.id.le_long);
        le_weight1 = (EditText)findViewById(R.id.le_weight1);
        le_bmibtn=findViewById(R.id.le_bmibtn);
        le_age =(EditText)findViewById(R.id.le_age);
        le_goalw =(EditText)findViewById(R.id.le_goalw); //목표 체중
        le_noww =(EditText)findViewById(R.id.le_noww); //현재 체중
        le_ddayw =(EditText)findViewById(R.id.le_ddayw); //남은 체중
        le_dday =(EditText)findViewById(R.id.le_dday); //목표 날짜
        le_nowday =(EditText)findViewById(R.id.le_nowday); //현재 날짜
        le_d_day =(EditText)findViewById(R.id.le_d_day); //남은 날짜

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        String value1 = sharedPreferences.getString("이재환1",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value2 = sharedPreferences.getString("이재환2",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value3 = sharedPreferences.getString("이재환3",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value4 = sharedPreferences.getString("이재환4",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value5 = sharedPreferences.getString("이재환5",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value6 = sharedPreferences.getString("이재환6",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value7 = sharedPreferences.getString("이재환7",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value8 = sharedPreferences.getString("이재환8",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value9 = sharedPreferences.getString("이재환9",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다
        String value10 = sharedPreferences.getString("이재환10",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다

        le_long.setText(value1);
        le_weight1.setText(value2);
        le_age.setText(value3);
        le_man.setText(value4);
        le_goalw.setText(value5); //목표 체중
        le_noww.setText(value6);
        le_ddayw.setText(value7);
        le_dday.setText(value8);
        le_nowday.setText(value9);
        le_d_day.setText(value10);

        le_noww.addTextChangedListener(new TextWatcher() { //텍스트 변경 감지 필수 메소드 3개
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { //현재 입력된값, 새로추가될 문자열의 시작 위치값, 문자열 길이,문자열길이

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { // 입력 다 끝났을때
                if(le_noww.getText().toString().equals("")) {
                    le_ddayw.setText(""); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                }else{
                    String le_noww1 = le_noww.getText().toString();
                    String le_goalw1 = le_goalw.getText().toString();
                    double le_noww2 = Double.parseDouble(le_noww1);
                    double le_goalw2 = Double.parseDouble(le_goalw1);

                    double goal = le_goalw2 - le_noww2; //스트링 더블로 형변환
                    String goal1 = String.format("%.1f", goal);
                    String goal3 = goal1; // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                    le_ddayw.setText(goal3); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        le_dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        activity_goal.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "/" + month + "/" + dayOfMonth;
                le_dday.setText(date);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                Calendar calendar = Calendar.getInstance(); // 오늘날짜
                String datee = sdf.format(calendar.getTime());
                le_nowday.setText(datee);

                String strStartDate = date; //선택날짜
                String strEndDate = datee; //오늘날짜
                String strFormat = "yyyy/MM/dd";    //strStartDate 와 strEndDate 의 format

                //SimpleDateFormat 을 이용하여 startDate와 endDate의 Date 객체를 생성한다.
                SimpleDateFormat sdff = new SimpleDateFormat(strFormat);
                try {
                    Date startDate = sdff.parse(strStartDate);
                    Date endDate = sdff.parse(strEndDate);

                    //두날짜 사이의 시간 차이(ms)를 하루 동안의 ms(24시*60분*60초*1000밀리초) 로 나눈다.
                    long diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
                    le_d_day.setText(diffDay+1+"일");

                    //메인쪽으로 값 보내기기
                    strrr = le_d_day.getText().toString();

                    SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();//저장할땐 에디터가 필수 에디터 연결한거임
                    editor.putString("key_strrr",strrr); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                    editor.commit();//커밋은 제출이다.

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        //1.값을 가져온다
        //2.클릭을 감지한다
        //3.1번의 값을 다음 액티비티로 넘긴다
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                le_man.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        le_bmibtn.setOnClickListener(new View.OnClickListener() { //bmi로 넘어감
            @Override
            public void onClick(View v) {
                str = le_long.getText().toString();
                strr =le_weight1.getText().toString();
                Intent intent = new Intent(activity_goal.this,activity_bmi.class);
                intent.putExtra("str",str);
                intent.putExtra("strr",strr);
                startActivity(intent);

            }
        });

        goal_button1.setOnClickListener(new View.OnClickListener() { //저장 누르면 데이터 저장
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();//저장할땐 에디터가 필수 에디터 연결한거임
                String value1 = le_long.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환1",value1); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value2 =le_weight1.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환2",value2); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value3 = le_age.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환3",value3); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value4 = le_man.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환4",value4); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value5 = le_goalw.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환5",value5); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value6 = le_noww.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환6",value6); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                if(le_goalw.getText().toString().equals("")) {
                    String value7 = le_ddayw.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                    editor.putString("이재환7", value7); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                }else {
                    double weight1 = Double.parseDouble(value5) - Double.parseDouble(value6); //스트링 더블로 형변환
                    String weight2 = String.format("%.1f", weight1);

                    String value7 = weight2; // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                    editor.putString("이재환7", value7); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                }
                String value8 = le_dday.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환8",value8); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value9 = le_nowday.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환9",value9); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value10 = le_d_day.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환10",value10); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                editor.commit();//커밋은 제출이다.

                Intent intent = new Intent(activity_goal.this,activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_X.setOnClickListener(new View.OnClickListener() { //몸무게 삭제
            @Override
            public void onClick(View v) {
                le_noww.setText("");
                le_ddayw.setText("");
                le_goalw.setText("");
                SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();//저장할땐 에디터가 필수 에디터 연결한거임
                String value5 = le_goalw.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환5",value5); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value6 = le_noww.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환6",value6); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value7 = le_ddayw.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환7", value7); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                editor.commit();//커밋은 제출이다.

            }
        });
        le_X1.setOnClickListener(new View.OnClickListener() { //날짜 삭제
            @Override
            public void onClick(View v) {
                le_dday.setText("");
                le_d_day.setText("");
                le_nowday.setText("");
                SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
                SharedPreferences.Editor editor = sharedPreferences.edit();//저장할땐 에디터가 필수 에디터 연결한거임

                String value8 = le_dday.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환8",value8); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value9 = le_nowday.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환9",value9); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                String value10 = le_d_day.getText().toString(); // 현재 써져 있는 값을 뭔가를 입력했을때 받아옴 스트링형태로.
                editor.putString("이재환10",value10); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다

                strrr = "";

                editor.putString("key_strrr",strrr); //이재환 이란 이름으로 이 밸류 값을 지정을 한다. ,앞에 이름은 별명이다
                editor.commit();//커밋은 제출이다.

                editor.commit();//커밋은 제출이다.

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