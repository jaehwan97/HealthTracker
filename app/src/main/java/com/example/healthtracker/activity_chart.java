package com.example.healthtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static java.lang.Long.parseLong;
import static java.lang.Long.valueOf;

public class activity_chart extends AppCompatActivity {

    ImageButton re_list;
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight,le_diet,le_chart,le_diary,le_goal,le_kcal;

    private LineChart lineChart;
    Thread thread_4; // 4d = 차트변경 7일
    Thread thread_5; // 5d = 차트변경 30일
    Thread thread_6; // 6d = 차트변경 90일
    Thread thread_7; // 7d = 차트변경 180일

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

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
                Intent intent = new Intent(activity_chart.this, MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_chart.this, activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        lineChart = (LineChart) findViewById(R.id.chart);

        Spinner chart_spinner1 = findViewById(R.id.chart_spinner);

        chart_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerS = parent.getSelectedItem().toString();
                String chart7 = "7일";
                String chart30 = "30일";
                String chart90 = "90일";
                String chart180 = "180일";
                if(spinnerS.equals(chart7)==true){ //자바에서 String은 객체 취급이라 equals를 사용해야 비교가 된다.
                    thread_4 = new Thread() {
                        public void run() {
                            handlr.sendEmptyMessage(1);
                        }
                    };
                    thread_4.start();
                }
                else if(spinnerS.equals(chart30) == true){
                    thread_5 = new Thread() {
                        public void run() {
                            handlrr.sendEmptyMessage(1);
                        }
                    };
                    thread_5.start();
                }
                else if(spinnerS.equals(chart90) == true){
                    thread_6 = new Thread() {
                        public void run() {
                            handlrrr.sendEmptyMessage(1);
                        }
                    };
                    thread_6.start();
                }
                else if(spinnerS.equals(chart180) == true){
                    thread_7 = new Thread() {
                        public void run() {
                            handlrrrr.sendEmptyMessage(1);
                        }
                    };
                    thread_7.start();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Toast.makeText(getApplicationContext(),"가장 최근 기록된 7일간의 체중변화 입니다.",Toast.LENGTH_SHORT).show();

        ArrayList<Entry> entries = new ArrayList<>(); //Entry 는 차트에 존재하는 클래스이다. x,y값을 입력 받는다.
        SharedPreferences sharedPreferences = getSharedPreferences("sharedd", MODE_PRIVATE);//문자열 형식으로 이름을 전달
        String sharedPreferencesString = sharedPreferences.getString("task listt", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
       //데이터를 풀어줌
        JSONObject jsonObject = new JSONObject(); //JSON형태의 데이터를 관리해주는 메서드
        try {
            JSONArray jsonArray = new JSONArray(); //JSONObject가 들어가는 배열
            jsonObject = new JSONObject(sharedPreferencesString); //JSONObject String 값 집어넣어서 생성해줌
            String arry = jsonObject.getString("key");
            jsonArray = new JSONArray(arry);
            if(jsonArray.length()>=7) { //7일 이상의 데이터가 들어있는 경우
                for (int i = jsonArray.length() - 1, j = 0; i >= jsonArray.length() - 7; i--, j++) {
                    JSONObject jsonObj = (JSONObject) jsonArray.get(6 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                    String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                    String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                    day = day.replaceAll("/", "");

                    float d_day = Float.valueOf((String) day);
                    float weigh_t = Float.valueOf((String) weight);
                    int dd_q = Integer.valueOf((int) d_day);
                    entries.add(new Entry(j + 1, weigh_t)); //j+1 리스트에 쭉담아줌
                }
            }
            else{ //데이터가 부족한 경우 있는거 다 가져온다.
                for (int i = jsonArray.length() - 1, j = 0; i >= 0; i--, j++) {
                    JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                    String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                    String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                    day = day.replaceAll("/", "");

                    float d_day = Float.valueOf((String) day);
                    float weigh_t = Float.valueOf((String) weight);
                    int dd_q = Integer.valueOf((int) d_day);
                    entries.add(new Entry(j + 1, weigh_t)); //j+1
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        try {
            jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String arry = null;
        try {
            arry = jsonObject.getString("key");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonArray = new JSONArray(arry);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LineDataSet lineDataSet = null;  //밑에 차트 이름
        try {
            JSONObject jsonObj = (JSONObject) jsonArray.get(6); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
            String day = jsonObj.getString("day"); // 스트링으로 되어있다.
            lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lineDataSet.setLineWidth(1);                                               //차트 선 두께
        lineDataSet.setCircleRadius(6);                                            //원 반지름 크기
        lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));      //데이터 원 색상
        lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));            //차트 색상
        lineDataSet.setDrawCircleHole(true);                                       //데이터 원 안의 색 채울지 여부
        lineDataSet.setDrawCircles(true);                                          //데이터 원 표시 유무
        lineDataSet.setCircleHoleColor(Color.WHITE);                               //데이터 원 홀 색상
        //lineDataSet.setCircleHoleRadius(3);                                      //데이터 원 홀 키기
        lineDataSet.setValueTextSize(10);                                          //데이터 숫자 크기
        lineDataSet.setValueTextColor(Color.BLACK);                                //글자 색상

        lineDataSet.setDrawHorizontalHighlightIndicator(false);
        lineDataSet.setDrawHighlightIndicators(true);                              //십자가 표시 유무
        lineDataSet.setDrawValues(false);
        lineDataSet.setHighLightColor(Color.parseColor("#E43535"));     //십자가 색상

       // lineDataSet.setHighlightEnabled(true);

        LineData lineData = new LineData(lineDataSet); // 라인데이터셋을 담는 그릇

        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();  //x축 설정

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) { // 날짜 달아주기

                String svalue =String.valueOf((int)value)+"day";
                return svalue;
            }
        });//데이터 다시설정

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //x축 데이터 표시 위치

        xAxis.setTextColor(Color.BLACK);
        xAxis.setLabelCount(7, true); // x축 데이터 표시 개수 7개로 제한
        //xAxis.enableGridDashedLine(8, 24, 0);

        YAxis yLAxis = lineChart.getAxisLeft();
        yLAxis.setTextColor(Color.BLACK);
        YAxis yRAxis = lineChart.getAxisRight();

        yRAxis.setGranularity(0.1f); //소수축
        yRAxis.setGranularityEnabled(true);
        yRAxis.setDrawLabels(false); //오른쪽에 데이터 노출할지 안할지 왼쪽만 표시함
        yRAxis.setDrawAxisLine(false);
        yRAxis.setDrawGridLines(false);

        Description description = new Description();
        description.setText("");

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() { // 차트 클릭시 이벤트들
            @Override
            public void onValueSelected(Entry e, Highlight h) {  // 차트 클릭시 이벤트
                String AAA=null;
                AAA = String.valueOf(e); // 값 받아서 재수정 해서 토스로 띄움
                String AA = AAA.replaceAll("Entry,", ""); //여기 y있어서 이거까지 바꿔줌
                String A = AA.replaceAll("x", "날짜");
                String b = A.replaceAll("y", "몸무게");
                int idx =b.indexOf("몸"); //몸 기준으로 글짜 자름 인덱스 구하기
                String Weight=b.substring(idx);// 구한 인덱스 기준으로 자름
                Toast.makeText(getApplicationContext(),Weight ,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected() { // 차트 클릭 안할시 이벤트
            }
        });

        lineChart.setDoubleTapToZoomEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDescription(description);

        lineChart.invalidate();          //차트 초기화
        lineChart.setData(lineData);     //차트에 라인데이터 추가

    }


    private Handler handlr = new Handler() {
       /* class MyxAxisValue extends ValueFormatter implements com.example.dietview.MyxAxisValue {
            public String getXValue(String dateInMillisecons, int index, ViewPortHandler viewPortHandler) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM dd");
                    return sdf.format(new Date(parseLong(dateInMillisecons)));
                } catch (Exception e) {
                    return dateInMillisecons;
                }
            }
        } //핸들러에서 메시지를 받으면 작동한다.*/

        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(),"가장 최근 기록된 7일간의 체중변화 입니다.",Toast.LENGTH_SHORT).show();

            ArrayList<Entry> entries = new ArrayList<>();
            SharedPreferences sharedPreferences = getSharedPreferences("sharedd", MODE_PRIVATE);//문자열 형식으로 이름을 전달
            String sharedPreferencesString = sharedPreferences.getString("task listt", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
            JSONObject jsonObject = new JSONObject();

            try {
                JSONArray jsonArray = new JSONArray();
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
                String arry = jsonObject.getString("key");
                jsonArray = new JSONArray(arry);
                //  for (int i = jsonArray.length()-1,j=0; i >= jsonArray.length()-7; i--,j++) {
                if(jsonArray.length()>=7) {
                    for (int i = jsonArray.length() - 1, j = 0; i >= jsonArray.length() - 7; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(6 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
                else{
                    for (int i = jsonArray.length() - 1, j = 0; i >= 0; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray();
            try {
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String arry = null;
            try {
                arry = jsonObject.getString("key");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonArray = new JSONArray(arry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LineDataSet lineDataSet = null;  //밑에 차트 이름
            try {
                JSONObject jsonObj = (JSONObject) jsonArray.get(6); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            } catch (JSONException e) {
                JSONObject jsonObj = null; //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                try {
                    jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                String day = null; // 스트링으로 되어있다.
                try {
                    day = jsonObj.getString("day");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            }
            lineDataSet.setLineWidth(1);                                               //차트 선 두께
            lineDataSet.setCircleRadius(6);                                            //원 반지름 크기
            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));      //데이터 원 색상
            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));                                           //차트 색상
            lineDataSet.setDrawCircleHole(true);                                       //데이터 원 안의 색 채울지 여부
            lineDataSet.setDrawCircles(true);                                          //데이터 원 표시 유무
            lineDataSet.setCircleHoleColor(Color.WHITE);                                 //데이터 원 홀 색상
            //lineDataSet.setCircleHoleRadius(3);                                        //데이터 원 홀 키기
            lineDataSet.setValueTextSize(10);                                          //데이터 숫자 크기
            lineDataSet.setValueTextColor(Color.BLACK);                                //글자 색상

            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setDrawValues(false);
            lineDataSet.setHighLightColor(Color.parseColor("#E43535"));     //십자가 색상

            LineData lineData = new LineData(lineDataSet);

            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();  //x축 설정

            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {

                    String svalue =String.valueOf((int)value)+"day";
                    return svalue;
                }
            });//데이터 다시설정
            // lineData.setValueFormatter(new MyValueFormatter()); // 추가함

            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setTextColor(Color.BLACK);
            xAxis.setLabelCount(7, true);
            //xAxis.enableGridDashedLine(8, 24, 0);

            YAxis yLAxis = lineChart.getAxisLeft();
            yLAxis.setTextColor(Color.BLACK);

            YAxis yRAxis = lineChart.getAxisRight();

            yRAxis.setGranularity(0.1f); //소수축
            yRAxis.setGranularityEnabled(true);

            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

           /* yRAxis.setGranularity(1.0f);
            yRAxis.setGranularityEnabled(true);*/

            Description description = new Description();
            description.setText("");

            //lineChart.getAxisLeft (). setInverted (true);
            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription(description);
            //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
            lineChart.invalidate();          //차트 초기화
            lineChart.setData(lineData);     //차트에 라인데이터 추가
        }

    };
    private Handler handlrr = new Handler() { //핸들러에서 메시지를 받으면 작동한다.
        @Override
        public void handleMessage(@NonNull Message msg) {

            Toast.makeText(getApplicationContext(),"가장 최근 기록된 30일간의 체중변화 입니다.",Toast.LENGTH_SHORT).show();

            ArrayList<Entry> entries = new ArrayList<>();
            SharedPreferences sharedPreferences = getSharedPreferences("sharedd", MODE_PRIVATE);//문자열 형식으로 이름을 전달
            String sharedPreferencesString = sharedPreferences.getString("task listt", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
            JSONObject jsonObject = new JSONObject();

            try {
                JSONArray jsonArray = new JSONArray();
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
                String arry = jsonObject.getString("key");
                jsonArray = new JSONArray(arry);
                if(jsonArray.length()>=30) {
                    for (int i = jsonArray.length() - 1, j = 0; i >= jsonArray.length() - 30; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(29 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
                else{
                    for (int i = jsonArray.length() - 1, j = 0; i >= 0; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray();
            try {
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String arry = null;
            try {
                arry = jsonObject.getString("key");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonArray = new JSONArray(arry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LineDataSet lineDataSet = null;  //밑에 차트 이름
            try {
                JSONObject jsonObj = (JSONObject) jsonArray.get(29); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            } catch (JSONException e) {
                JSONObject jsonObj = null; //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                try {
                    jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                String day = null; // 스트링으로 되어있다.
                try {
                    day = jsonObj.getString("day");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            }

            lineDataSet.setLineWidth(1);                                               //차트 선 두께
            lineDataSet.setCircleRadius(5);                                            //원 반지름 크기
            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));      //데이터 원 색상
            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));                                           //차트 색상
            lineDataSet.setDrawCircleHole(true);                                       //데이터 원 안의 색 채울지 여부
            lineDataSet.setDrawCircles(true);                                          //데이터 원 표시 유무
            lineDataSet.setCircleHoleColor(Color.WHITE);                                 //데이터 원 홀 색상
            //lineDataSet.setCircleHoleRadius(3);                                        //데이터 원 홀 키기
            lineDataSet.setValueTextSize(10);                                          //데이터 숫자 크기
            lineDataSet.setValueTextColor(Color.BLACK);                                //글자 색상

            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setDrawValues(false);
            lineDataSet.setHighLightColor(Color.parseColor("#E43535"));     //십자가 색상

            LineData lineData = new LineData(lineDataSet);

            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();  //x축 설정

            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {

                    String svalue =String.valueOf((int)value)+"day";
                    return svalue;
                }
            });//데이터 다시설정
            // lineData.setValueFormatter(new MyValueFormatter()); // 추가함


            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setTextColor(Color.BLACK);
            xAxis.setLabelCount(7, true);
            //xAxis.enableGridDashedLine(8, 24, 0);

            YAxis yLAxis = lineChart.getAxisLeft();
            yLAxis.setTextColor(Color.BLACK);

            YAxis yRAxis = lineChart.getAxisRight();

            yRAxis.setGranularity(0.1f); //소수축
            yRAxis.setGranularityEnabled(true);

            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

           /* yRAxis.setGranularity(1.0f);
            yRAxis.setGranularityEnabled(true);*/

            Description description = new Description();
            description.setText("");

            //lineChart.getAxisLeft (). setInverted (true);
            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription(description);
            //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
            lineChart.invalidate();          //차트 초기화
            lineChart.setData(lineData);     //차트에 라인데이터 추가
        }

    };
    private Handler handlrrr = new Handler() { //핸들러에서 메시지를 받으면 작동한다.
        @Override
        public void handleMessage(@NonNull Message msg) {

            Toast.makeText(getApplicationContext(),"가장 최근 기록된 90일간의 체중변화 입니다.",Toast.LENGTH_SHORT).show();

            ArrayList<Entry> entries = new ArrayList<>();
            SharedPreferences sharedPreferences = getSharedPreferences("sharedd", MODE_PRIVATE);//문자열 형식으로 이름을 전달
            String sharedPreferencesString = sharedPreferences.getString("task listt", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
            JSONObject jsonObject = new JSONObject();

            try {
                JSONArray jsonArray = new JSONArray();
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
                String arry = jsonObject.getString("key");
                jsonArray = new JSONArray(arry);
                if(jsonArray.length()>=90) {
                    for (int i = jsonArray.length() - 1, j = 0; i >= jsonArray.length() - 90; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(89 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
                else{
                    for (int i = jsonArray.length() - 1, j = 0; i >= 0; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray();
            try {
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String arry = null;
            try {
                arry = jsonObject.getString("key");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonArray = new JSONArray(arry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LineDataSet lineDataSet = null;  //밑에 차트 이름
            try {
                JSONObject jsonObj = (JSONObject) jsonArray.get(89); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            } catch (JSONException e) {
                JSONObject jsonObj = null; //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                try {
                    jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                String day = null; // 스트링으로 되어있다.
                try {
                    day = jsonObj.getString("day");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            }

            lineDataSet.setLineWidth(1);                                               //차트 선 두께
            lineDataSet.setCircleRadius(4);                                            //원 반지름 크기
            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));      //데이터 원 색상
            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));                                           //차트 색상
            lineDataSet.setDrawCircleHole(true);                                       //데이터 원 안의 색 채울지 여부
            lineDataSet.setDrawCircles(true);                                          //데이터 원 표시 유무
            lineDataSet.setCircleHoleColor(Color.WHITE);                                 //데이터 원 홀 색상
            //lineDataSet.setCircleHoleRadius(3);                                        //데이터 원 홀 키기
            lineDataSet.setValueTextSize(10);                                          //데이터 숫자 크기
            lineDataSet.setValueTextColor(Color.BLACK);                                //글자 색상

            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setDrawValues(false);
            lineDataSet.setHighLightColor(Color.parseColor("#E43535"));     //십자가 색상

            LineData lineData = new LineData(lineDataSet);

            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();  //x축 설정


            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {

                    String svalue =String.valueOf((int)value)+"day";
                    return svalue;
                }
            });//데이터 다시설정
            // lineData.setValueFormatter(new MyValueFormatter()); // 추가함

            xAxis.setLabelCount(50);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setTextColor(Color.BLACK);
            xAxis.setLabelCount(7, true);
            //xAxis.enableGridDashedLine(8, 24, 0);

            YAxis yLAxis = lineChart.getAxisLeft();
            yLAxis.setTextColor(Color.BLACK);

            YAxis yRAxis = lineChart.getAxisRight();

            yRAxis.setGranularity(0.1f); //소수축
            yRAxis.setGranularityEnabled(true);

            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

           /* yRAxis.setGranularity(1.0f);
            yRAxis.setGranularityEnabled(true);*/

            Description description = new Description();
            description.setText("");

            //lineChart.getAxisLeft (). setInverted (true);
            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription(description);
            //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
            lineChart.invalidate();          //차트 초기화
            lineChart.setData(lineData);     //차트에 라인데이터 추가
        }

    };
    private Handler handlrrrr = new Handler() { //핸들러에서 메시지를 받으면 작동한다.
        @Override
        public void handleMessage(@NonNull Message msg) {

            Toast.makeText(getApplicationContext(),"가장 최근 기록된 180일간의 체중변화 입니다.",Toast.LENGTH_SHORT).show();

            ArrayList<Entry> entries = new ArrayList<>();
            SharedPreferences sharedPreferences = getSharedPreferences("sharedd", MODE_PRIVATE);//문자열 형식으로 이름을 전달
            String sharedPreferencesString = sharedPreferences.getString("task listt", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
            JSONObject jsonObject = new JSONObject();

            try {
                JSONArray jsonArray = new JSONArray();
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
                String arry = jsonObject.getString("key");
                jsonArray = new JSONArray(arry);
                if(jsonArray.length()>=180) {
                    for (int i = jsonArray.length() - 1, j = 0; i >= jsonArray.length() - 180; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(179 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
                else{
                    for (int i = jsonArray.length() - 1, j = 0; i >= 0; i--, j++) {
                        JSONObject jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1 - j); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                        String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                        String weight = jsonObj.getString("weight"); // 내가 필요한 데이터

                        day = day.replaceAll("/", "");
                        //String bday = day.substring(4, 7);

                        // long da_y = parseLong(bday); // da_y는 매개변수 x로 바로 전달되면 안된다고 한다.

                        float d_day = Float.valueOf((String) day);
                        float weigh_t = Float.valueOf((String) weight);
                        int dd_q = Integer.valueOf((int) d_day);
                        entries.add(new Entry(j + 1, weigh_t)); //j+1
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray jsonArray = new JSONArray();
            try {
                jsonObject = new JSONObject(sharedPreferencesString); // 지금 여기 안에 값이 0이다 수정완료
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String arry = null;
            try {
                arry = jsonObject.getString("key");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonArray = new JSONArray(arry);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            LineDataSet lineDataSet = null;  //밑에 차트 이름
            try {
                JSONObject jsonObj = (JSONObject) jsonArray.get(179); //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                String day = jsonObj.getString("day"); // 스트링으로 되어있다.
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            } catch (JSONException e) {
                JSONObject jsonObj = null; //이거 단순 어레이 배열이 아니다 착각하지 말자 순서다 i에서 j로 하면 차트 반대됨
                try {
                    jsonObj = (JSONObject) jsonArray.get(jsonArray.length()-1);
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                String day = null; // 스트링으로 되어있다.
                try {
                    day = jsonObj.getString("day");
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                lineDataSet = new LineDataSet(entries, "Start Day: "+ day);
            }

            lineDataSet.setLineWidth(1);                                               //차트 선 두께
            lineDataSet.setCircleRadius(3);                                            //원 반지름 크기
            lineDataSet.setCircleColor(Color.parseColor("#FFA1B4DC"));      //데이터 원 색상
            lineDataSet.setColor(Color.parseColor("#FFA1B4DC"));                                           //차트 색상
            lineDataSet.setDrawCircleHole(true);                                       //데이터 원 안의 색 채울지 여부
            lineDataSet.setDrawCircles(true);                                          //데이터 원 표시 유무
            lineDataSet.setCircleHoleColor(Color.WHITE);                                 //데이터 원 홀 색상
            //lineDataSet.setCircleHoleRadius(3);                                        //데이터 원 홀 키기
            lineDataSet.setValueTextSize(10);                                          //데이터 숫자 크기
            lineDataSet.setValueTextColor(Color.BLACK);                                //글자 색상

            lineDataSet.setDrawHorizontalHighlightIndicator(false);
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setDrawValues(false);
            lineDataSet.setHighLightColor(Color.parseColor("#E43535"));     //십자가 색상

            LineData lineData = new LineData(lineDataSet);

            lineChart.setData(lineData);

            XAxis xAxis = lineChart.getXAxis();  //x축 설정

            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {

                    String svalue =String.valueOf((int)value)+"day";
                    return svalue;
                }
            });//데이터 다시설정
            // lineData.setValueFormatter(new MyValueFormatter()); // 추가함

            xAxis.setLabelCount(50);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            xAxis.setTextColor(Color.BLACK);
            xAxis.setLabelCount(7, true);
            //xAxis.enableGridDashedLine(8, 24, 0);

            YAxis yLAxis = lineChart.getAxisLeft();
            yLAxis.setTextColor(Color.BLACK);

            YAxis yRAxis = lineChart.getAxisRight();

            yRAxis.setGranularity(0.1f); //소수축
            yRAxis.setGranularityEnabled(true);

            yRAxis.setDrawLabels(false);
            yRAxis.setDrawAxisLine(false);
            yRAxis.setDrawGridLines(false);

           /* yRAxis.setGranularity(1.0f);
            yRAxis.setGranularityEnabled(true);*/

            Description description = new Description();
            description.setText("");

            //lineChart.getAxisLeft (). setInverted (true);
            lineChart.setDoubleTapToZoomEnabled(false);
            lineChart.setDrawGridBackground(false);
            lineChart.setDescription(description);
            //lineChart.animateY(2000, Easing.EasingOption.EaseInCubic);
            lineChart.invalidate();          //차트 초기화
            lineChart.setData(lineData);     //차트에 라인데이터 추가
        }

    };
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
};


