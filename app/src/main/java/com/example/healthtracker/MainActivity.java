package com.example.healthtracker;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity { //컴포넌트 중 엑티비티 상속

    ImageButton re_list; //메뉴
    private ArrayList<Dictionary> mArrayList; //아이템 리스트 담는 배열 Dictionary(리스트에 들어갈 데이터모델)는 클래스에서 따로 선언
    private CustomAdapter mAdapter; //데이터목록을 아이템단위의 뷰로 구성하여 화면에 표시하기위한 어댑터

    private DatePickerDialog.OnDateSetListener onDateSetListener; //날짜선택기

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight,le_diet,le_chart,le_diary,le_goal,le_kcal;

    Thread thread; // 목표
    String shared ="file";

    //main 함수같은 애플리케이션의 진입 지점이 따로 없음
    @Override
    protected void onCreate(Bundle savedInstanceState) { //액티비티가 생성될때 시작되는 콜백함수, 여기서 데이터 초기화(연결,바인딩), 이벤트 처리
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout); //findViewById 이게 바인딩으로 뷰객체 만들어 주는거다
        drawerView = (View) findViewById(R.id.drawer); //R은 리소스들을전체적으로 관리하는 전역 변수
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


        thread =new Thread() {
            public void run() { //목표날짜 띄우기
                try {
                    sleep(3000);
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
                handlerr.sendEmptyMessage(1); // 몇초있다가 핸들러에 메시지를 보내게된다. 그럼 핸들러가 작동한다.
            }
        };
        thread.start();

        re_list.setOnClickListener(new OnClickListener() { //메뉴 버튼
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
                Intent intent = new Intent(MainActivity.this,MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list); //리사이클러뷰 추가
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this); //레이아웃 매니저 리니어 레이아웃은 항목을 1차원 목록으로 정렬해준다.
        mRecyclerView.setLayoutManager(mLinearLayoutManager); //리사이클러뷰랑 연결

        mArrayList = new ArrayList<>();

        loadData(); //메모데이터를 작성하여 앱이 시작될때 데이터 호출 리스트에 JSON데이터 뿌림

        mAdapter = new CustomAdapter(this, mArrayList);
        mRecyclerView.setAdapter(mAdapter); //어뎁터 연결

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation()); // 레이아웃 매니저 지정하기 여기가 구분선 표시해줌
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        Button buttonInsert = (Button) findViewById(R.id.button_main_insert); //기록추가하기 버튼
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.edit_box, null, false); //팝업창에 뜰 레이아웃
                builder.setView(view);
                //팝업창 안에 있는 데이터들을 이곳에서 사용할 수 있게 뷰객체 만들어 주는거다
                final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                final TextView editTextID = (TextView) view.findViewById(R.id.edittext_dialog_id); //edit_box에 있는 날짜
                final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);//edit_box에 있는 체중
                final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);//edit_box에있는 운동
                final EditText editTextsul=(EditText) view.findViewById(R.id.edittext_sul);//edit_box에있는 음주
                final EditText editTextmemo=(EditText) view.findViewById(R.id.edittext_memo);//edit_box에있는 메모

                Spinner spinner = (Spinner)view.findViewById(R.id.spinner);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //운동여부 스피너
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        editTextKorean.setText(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


                Spinner spinner1 = (Spinner)view.findViewById(R.id.spinner1);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { //음주여부 스피너
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        editTextsul.setText(parent.getItemAtPosition(position).toString());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

                ButtonSubmit.setText("추가하기"); //원래 수정으로 되어있음

                editTextID.setOnClickListener(new View.OnClickListener() { //edit_box에 있는 날짜선택
                    @Override
                    public void onClick(View v) {
                        Calendar cal =Calendar.getInstance();
                        int year =cal.get(Calendar.YEAR);
                        int month =cal.get(Calendar.MONTH);
                        int day =cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog =new DatePickerDialog(
                                MainActivity.this,
                                android.R.style.Theme_Holo_Dialog_MinWidth,
                                onDateSetListener,
                                year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                onDateSetListener =new DatePickerDialog.OnDateSetListener() { //날짜 선택 데이트픽커
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date =year +"/"+month+"/"+dayOfMonth;
                        editTextID.setText(date);
                    }
                };

                final AlertDialog dialog = builder.create(); //
                ButtonSubmit.setOnClickListener(new View.OnClickListener() { //삽입 버튼을 클릭했을때
                    public void onClick(View v) {

                        String strID = editTextID.getText().toString();
                        String strEnglish = editTextEnglish.getText().toString();
                        String strKorean = editTextKorean.getText().toString();
                        String strsul = editTextsul.getText().toString();
                        String strmemo = editTextmemo.getText().toString();

                        // Dictionary 생성자를 사용하여 ArrayList에 삽입할 데이터를 만듭니다.
                        Dictionary dict = new Dictionary(strID, strEnglish, strKorean, strsul, strmemo);

                        mArrayList.add(0, dict); //첫 줄에 삽입

                        mAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영

                        dialog.dismiss();//팝업창 닫기
                    }
                });

                dialog.show();
            }
        });
        //리사이클러뷰 드래그 기능
        //안드로이드에서는 글자가 써내려가는 방향이나 반대방향으로 지정하는 방식으로 좌우 판단함
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END) { //심플 콜백은 드래그 디렉션과 스와이프디렉션을 생성자로 받음 드래그할 방향과 스와이프할 방향
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) { //
                return mAdapter.moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) { //밀어서 지우기
                mAdapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) { //드래그 상태인지 스와이프 상태인지 구분이 가능함
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) { //원래대로 돌려주는 곳
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() { //엑티비티가 사용자와 상호작용하는 상태, 어떤 이벤트가 발생하여 앱에서 포커스가 떠날때까지 앱이 이상태에 머무름, 사용자에게 ui가 보여지는 단계
        saveData();
        super.onResume();
    }

    @Override
    protected void onStop() { //엑티비티가 사용자에게 보이지 않는 단계
        saveData();
        super.onStop();
    }

    private void saveData() { //데이터 저장하고
        //JSON을  SharedPreferences에 저장해서 사용
        SharedPreferences sharedPreferences = getSharedPreferences("sharedd",MODE_PRIVATE);//문자열 형식으로 이름을 전달 MODE_PRIVATE 해당앱 내에서만 사용가능
        SharedPreferences.Editor editor =sharedPreferences.edit(); //에디터 연결 이걸통해서 출력,삭제 ,변경 관리함
        JSONObject jObject = new JSONObject(); //JSON형태의 데이터를 관리해주는 메서드
        JSONArray JArray =new JSONArray(); //JSONObject가 들어가는 배열
        try {
            for (int i = 0; i < mArrayList.size(); i++) {
                JSONObject jsonObject = new JSONObject(); // jsonobject 객체 생성 및 데이터 삽입 실시
                jsonObject.put("day", mArrayList.get(i).getId());
                jsonObject.put("weight", mArrayList.get(i).getEnglish());//이 데이터가 차트에 필요해서 이런식으로 저장해줌
                jsonObject.put("health", mArrayList.get(i).getKorean());
                jsonObject.put("sul", mArrayList.get(i).getSul());
                jsonObject.put("memo", mArrayList.get(i).getMemo());
                JArray.put(jsonObject); //JSONObject 넣어줌
            }
            jObject.put("key",JArray);
        }catch (JSONException e) {
            e.printStackTrace();
        }
        editor.putString("task listt",jObject.toString());// 스트링 값으로 만든값으로 만든걸 리턴을 받는거임.
        editor.apply();  // 여기서 이 메서드를 호출하면 적용 할 수 있다고 생각하면 예제가 저장된다.
    }

    private void loadData() {//데이터를 띄운다
        SharedPreferences sharedPreferences = getSharedPreferences("sharedd",MODE_PRIVATE);//문자열 형식으로 이름을 전달
        String sharedPreferencesString = sharedPreferences.getString("task listt","");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.

        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            jsonObject =new JSONObject(sharedPreferencesString); //JSONObject String 값 집어넣어서 생성해줌
            String arry =jsonObject.getString("key");
            jsonArray = new JSONArray(arry);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObj = (JSONObject)jsonArray.get(i);

                String day = jsonObj.getString("day");
                String weight = jsonObj.getString("weight"); // 내가 필요한 데이터
                String health = jsonObj.getString("health");
                String sul = jsonObj.getString("sul");
                String memo = jsonObj.getString("memo");
                Dictionary dictionary =new Dictionary(day,weight,health,sul,memo);
                mArrayList.add(dictionary); //Dictionary에 생성자 있음
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (mArrayList == null) {
            mArrayList = new ArrayList<>();
        }
    }

    private Handler handlerr = new Handler(){ //핸들러에서 메시지를 받으면 작동한다.
        @Override
        public void handleMessage(@NonNull Message msg) { //목표날짜 핸들러

            SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
            String strrr = sharedPreferences.getString("key_strrr",""); //꺼내오는거라 빈값을 설정 키 ,그리고 벨류(값)다

            if(strrr==""){ //값이 있는 경우만 출력

        }
            else{
                Toast.makeText(getApplicationContext(),"목표로 하신 날짜까지 "+strrr+" 남았습니다!",Toast.LENGTH_LONG).show();
            }
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

}