package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class activity_kcal extends AppCompatActivity { //음식 검색
    SearchView le_search;
    TextView le_search_1;
    String string_data;
    String foodName = null;

    ImageButton re_list;
    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight, le_diet, le_chart, le_diary, le_goal, le_kcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kcal);

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
                Intent intent = new Intent(activity_kcal.this, MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_kcal.this, activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        le_search_1 = findViewById(R.id.le_search_1);
        le_search = findViewById(R.id.le_search);

        //(쿼리란 데이터베이스나 파일의 내용중 원하는 내용을 검색하기 위하여 몇개의 코드나 키를 기초로 질의 하는것)
        le_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() { //서치뷰
            @Override
            public boolean onQueryTextSubmit(String query) {//텍스트 입력 리스너

                String food = le_search.getQuery().toString(); //입력 받았을때 문자 가져오기
                //foodName = le_search.getQuery().toString();

                new Thread(new Runnable() { //반드시 스레드로 해줘야한다고 한다.
                    @Override
                    public void run() {
                        try {
                            string_data = getData();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                le_search_1.setText(string_data);
                            }
                        });
                    }
                }).start();
                if (food != null || !food.equals("")) { //검색 값 없을때
                    //le_search_1.setText("검색결과 없음");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
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
    String getData() throws UnsupportedEncodingException { //검색하는 데이터

        StringBuffer buffer = new StringBuffer(); //문자열을 추가하거나 변경할때 주로 사용하는 자료형 (여러 메소드 사용가능 그중 여기서는 append사용)

        String str = le_search.getQuery().toString(); //쿼리 문자열을 스트링으로 반환 (쿼리란 데이터베이스나 파일의 내용중 원하는 내용을 검색하기 위하여 몇개의 코드나 키를 기초로 질의 하는것)
        str = str.replaceAll(" ", ""); //정확도를 위해 띄워쓰기 삭제
        //String location = URLEncoder.encode(str);

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/FoodNtrIrdntInfoService1/getFoodNtrItdntList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=ItzgqKGCroSdnxz7Fv%2FqL0XH%2FEghHeBRaE1qaUQfAZumXuPqGrs%2BjZ5phEPJ%2B9irUfLTUAV%2FuFsJ3nRaPEQ4Mg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("desc_kor", "UTF-8") + "=" + URLEncoder.encode(str, "UTF-8")); /*식품이름 검색하기*/

        try {
            URL url = new URL(urlBuilder.toString()); // url객체 생성// 문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is = url.openStream(); // url 위치로 인풋스트림 연결 위 url에서 데이터를 읽어와라

            //xml형식으로 데이터를 가져올 것이기 때문에 xmlPullParser사용
            //안드로이드에서 XML파싱을 할시 이것을 사용하길 권장하고 있다.
            //xmlPullParser객체에 Url에서 받은 InputStream객체를 집어넣어 데이터를 가져온다
            //이벤트 중심 파서이고, xml구성요소를 순서대로 파싱하면서 각이벤트 타입이 발생하고, 발생한 이벤트 타입에 맞는 개발자 코드가 실행
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            // inputstream 으로부터 xml 입력받기
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //받을 문자 타입
            String tag;
            xpp.next(); // 다음 구문 분석 이벤트 가져오라는 메소드
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) { //문서 끝까지 읽으라는 소리
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT: //해당 파서가xml의 맨처음에 표시 파싱 시작단계
                        buffer.append("파싱 시작 단계 \n\n"); //append = 문자열 추가하기
                        break;
                    case XmlPullParser.START_TAG://시작태그
                        tag = xpp.getName(); // 태그 이름 얻어오기
                        if (tag.equals("item")) ; //태그가 아이템 단위로 묶여있기 때문에 아이템 단위로 묶어준다.
                        else if (tag.equals("DESC_KOR")) { //태그별로 값을 가져온다
                            buffer.append("제품 이름 : ");
                            xpp.next(); // 다음 구문 분석 이벤트 가져오라는 메소드
                            // addr 요소의 TEXT 읽어와서 문자열버퍼에 추가
                            buffer.append(xpp.getText());
                            buffer.append("\n"); // 줄바꿈 문자 추가
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT1")) {
                            buffer.append("열량(kcal) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("SERVING_WT")) {
                            buffer.append("1회 제공량(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT2")) {
                            buffer.append("탄수화물(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT3")) {
                            buffer.append("단백질(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT4")) {
                            buffer.append("지방(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT5")) {
                            buffer.append("당류(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT6")) {
                            buffer.append("나트륨(mg) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT7")) {
                            buffer.append("콜레스트롤(mg) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("NUTR_CONT8")) {
                            buffer.append("포화지방산(g) : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("ANIMAL_PLANT")) {
                            buffer.append("가공업체 : ");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                            buffer.append("─────────────");
                            buffer.append("\n");
                        }
                        break;
                    case XmlPullParser.TEXT://xml에 포함된 문자데이터
                        break;
                    case XmlPullParser.END_TAG://끝 태그
                        tag = xpp.getName(); // 태그 이름 얻어오기
                        if (tag.equals("item")) buffer.append("\n"); // 첫번째 검색결과종료 후 줄바꿈
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        buffer.append("             - 검색 결과 끝 - \n");
        return buffer.toString(); // 파싱 다 종료 후 StringBuffer 문자열 객체 반환
    }
}
