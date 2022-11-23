package com.example.healthtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class activity_diary extends AppCompatActivity {
    ImageButton re_list;
    Button button;     //저장버튼
    DatePicker datePicker;  // 날짜를 선택하는 위젯
    EditText editText;  // 글을 기록하는 부분
    String filename;  //파일 입출력을 위해 저장할 파일이름

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight, le_diet, le_chart, le_diary, le_goal, le_kcal;

    private Thread thread_adver; // 광고
    private Boolean timer = true; // 정해진 시간동안 광고 다시 안뜨게하기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

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
                Intent intent = new Intent(activity_diary.this, MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_diary.this, activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        setTitle("간단 일기장");

        button = findViewById(R.id.button); // 저장하기 버튼
        datePicker = findViewById(R.id.datepicker);
        editText = findViewById(R.id.edit_text);

        // datePicker를 현재날짜로 초기화해주기 위해 오늘의 년, 월, 일을 받아온다.
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        // datepicker를 오늘의 날짜로 초기값을 정해준다.
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            // datepicker에서 날짜가 바뀔때마다 파일 이름을 정해준다.
            // readDiary메소드를 통해 파일이 존재하면 파일의 내용을 가져오고
            // 그렇지 않다면 null을 가져온다.
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

                filename = Integer.toString(i) + "_" + Integer.toString(i1 + 1) + "_" + Integer.toString(i2);
                String str = readDiary(filename); //filename으로 readDiary 메소드 사용
                editText.setText(str); //일기란에 텍스트 써줌
                button.setEnabled(true); //setEnabled = 버튼 클릭 활성화 기본 xml에서 false로 설정되어 있음
            }
        });

        //파일 생성, 쓰기단계
        //editText에 적혀있는 내용의 byte값을 가져와
        //파일에 쓰기한다.
        button.setOnClickListener(new Button.OnClickListener() { //저장버튼
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outFs = openFileOutput(filename, Context.MODE_PRIVATE); // 파일 비공개로 앱에 전달 생성된파일은 호출 어플리케이션에서만 사용가능
                    String str = editText.getText().toString();
                    outFs.write(str.getBytes()); //스트링 값으로 이제 써준다
                    outFs.close(); //닫아요
                    Toast.makeText(activity_diary.this, filename + "이 저장", Toast.LENGTH_SHORT).show();
                    button.setText("수정하기");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    // 파일 읽기 단계
    // 기록을 하지 않은 경우도 있으니 파일이 없는 경우도 있다.
    // try catch를 통해 파일이 없는경우는 Hint를 띄워준다.
    String readDiary(String filename) {
        String diaryStr = null;

        //파일입출력용 스트림, 바이트 데이터를 읽거나 파일에 바이트 데이터를 저장할 수 있다.
        FileInputStream inFs;//파일에서 바이트데이터를 읽기위한 함수 제공
        try {  //파일이 있는경우
            inFs = openFileInput(filename);

            byte[] txt = new byte[500]; //500바이트까지
            inFs.read(txt); //read()함수를 통해서 파일 끝까지 1바이트씩 읽어내고 텍스트 읽어옴
            inFs.close(); //열면 항상 닫아야 한다.
            diaryStr = (new String(txt)).trim(); //여기 값이 입력됨 마지막에 이걸 반환
            button.setText("수정하기"); //데이터가 이미 들어가있으니까 수정하기로 변경한다.

        } catch (IOException e) {  // 파일이 없는 경우
            editText.setHint("일기 없음");
            button.setText("새로 저장");
        }
        return diaryStr;//위에서 받은 값
    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() { //만들어 놓으면 좋아서 만들어놓은거임
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

