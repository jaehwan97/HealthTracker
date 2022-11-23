package com.example.healthtracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SubActivity extends AppCompatActivity {

    ImageButton re_list;//메뉴
    private ArrayList<DictionarySub> mmArrayList;//아이템 리스트 담는 배열 Dictionary(리스트에 들어갈 데이터모델)는 클래스에서 따로 선언
    private CustomAdapterSub mmAdapter; //커스텀 어댑터

    private DrawerLayout drawerLayout;
    private View drawerView;
    Button le_schedule;
    Button le_bmi;
    Button le_question;
    Button le_weight, le_diet, le_chart, le_diary, le_goal, le_kcal;

    private DatePickerDialog.OnDateSetListener onDateSetListener2;//날짜선택기

    //main 함수같은 애플리케이션의 진입 지점이 따로 없음
    @Override
    protected void onCreate(Bundle savedInstanceState) {//액티비티가 생성될때 시작되는 콜백함수, 여기서 데이터 초기화(연결,바인딩), 이벤트 처리
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);//findViewById 이게 바인딩으로 뷰객체 만들어 주는거다
        drawerView = (View) findViewById(R.id.drawer);//R은 리소스들을전체적으로 관리하는 전역 변수
        le_schedule = findViewById(R.id.le_schedule);
        le_bmi = findViewById(R.id.le_bmi);
        le_question = findViewById(R.id.le_question);
        le_weight = findViewById(R.id.le_weight);
        le_diet = findViewById(R.id.le_diet);
        le_chart = findViewById(R.id.le_chart);
        le_diary = findViewById(R.id.le_diary);
        le_goal = findViewById(R.id.le_goal);
        le_kcal = findViewById(R.id.le_kcal);


        re_list = findViewById(R.id.re_list); //메뉴 버튼
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
                Intent intent = new Intent(SubActivity.this, MainActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, SubActivity.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_chart.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_diary.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_goal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_youtube.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_bmi.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, activity_question.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });
        le_kcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this,activity_kcal.class); //어디서 어디로 이동할지 설정
                startActivity(intent); //액티비티 실제 이동구간
            }
        });


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_listt);//리사이클러뷰 추가
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);//레이아웃 매니저 리니어 레이아웃은 항목을 1차원 목록으로 정렬해준다.
        mRecyclerView.setLayoutManager(mLinearLayoutManager); //리사이클러뷰랑 연결


        loadData();//메모데이터를 작성하여 앱이 시작될때 데이터 호출 리스트에 JSON데이터 뿌림
        mmAdapter = new CustomAdapterSub(this, mmArrayList);

        mRecyclerView.setAdapter(mmAdapter); //어뎁터 연결


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation()); // 레이아웃 매니저 지정하기 여기가 선 표시해줌
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        Button buttonInsert = (Button) findViewById(R.id.button_main_insertt);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());

                Calendar calendar = Calendar.getInstance(); // 오늘날짜

                final String date = sdf.format(calendar.getTime());

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);
                View view = LayoutInflater.from(SubActivity.this)
                        .inflate(R.layout.edit_box1, null, false);
                builder.setView(view);
                final Button ButtonSubmitt = (Button) view.findViewById(R.id.button_dialog_submitt); //버튼
                final TextView editTextDAY = (TextView) view.findViewById(R.id.edittext_dialog_id2); //edit_box에있는 날짜
                final EditText editTextKCAL1 = (EditText) view.findViewById(R.id.edittext_1); //아침
                final EditText editTextKCAL2 = (EditText) view.findViewById(R.id.edittext_2); //점심
                final EditText editTextKCAL3 = (EditText) view.findViewById(R.id.edittext_3); //저녁
                final EditText editTextKCAL4 = (EditText) view.findViewById(R.id.edittext_4); //간식
                editTextDAY.setText(date);

                ButtonSubmitt.setText("삽입");

                editTextDAY.setOnClickListener(new View.OnClickListener() { //날짜 선택하는 부분
                    @Override
                    public void onClick(View v) {
                        Calendar cal =Calendar.getInstance();
                        int year =cal.get(Calendar.YEAR);
                        int month =cal.get(Calendar.MONTH);
                        int day =cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog =new DatePickerDialog(
                                SubActivity.this,
                                android.R.style.Theme_Holo_Dialog_MinWidth,
                                onDateSetListener2,
                                year,month,day);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                onDateSetListener2 =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        String date =year +"/"+month+"/"+dayOfMonth;
                        editTextDAY.setText(date);

                    }
                };

                final AlertDialog dialog = builder.create();
                ButtonSubmitt.setOnClickListener(new View.OnClickListener() { //최종 버튼 누를때
                    public void onClick(View v) {

                        String X=""; //아무값도 없는 경우 X처리해줌
                        String strDAY = editTextDAY.getText().toString(); //각각의 뷰에서 써져있는 글자 읽어옴
                        String strKCAL1 = editTextKCAL1.getText().toString();
                        String strKCAL2 = editTextKCAL2.getText().toString();
                        String strKCAL3 = editTextKCAL3.getText().toString();
                        String strKCAL4 = editTextKCAL4.getText().toString();
                        if(strKCAL1.equals(X)){
                            strKCAL1="x";
                        }
                        if(strKCAL2.equals(X)){
                            strKCAL2="x";
                        }
                        if(strKCAL3.equals(X)){
                            strKCAL3="x";
                        }
                        if(strKCAL4.equals(X)){
                            strKCAL4="x";
                        }

                        DictionarySub dictt = new DictionarySub(strDAY, strKCAL1, strKCAL2, strKCAL3, strKCAL4);


                        mmArrayList.add(0, dictt); //첫 줄에 삽입
                        //mArrayList.add(dict); //마지막 줄에 삽입
                        mmAdapter.notifyDataSetChanged(); //변경된 데이터를 화면에 반영

                        dialog.dismiss(); //팝업창 닫기
                    }
                });

                dialog.show();
            }
        });
        //리사이클러뷰 드래그 기능
        //안드로이드에서는 글자가 써내려가는 방향이나 반대방향으로 지정하는 방식으로 좌우 판단함
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.START | ItemTouchHelper.END) { //심플 콜백은 드래그 디렉션과 스와이프디렉션을 생성자로 받음 드래그할 방향과 스와이프할 방향
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return mmAdapter.moveItem(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mmAdapter.removeItem(viewHolder.getAdapterPosition());
            }

            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) { //드래그 상태인지 스와이프 상태인지 구분이 가능함
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onResume() {
        saveData();
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveData();
    }

    private void saveData() { //데이터 저장하고
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);//문자열 형식으로 이름을 전달 MODE_PRIVATE 해당앱 내에서만 사용가능
        SharedPreferences.Editor editor = sharedPreferences.edit();//에디터 연결 이걸통해서 출력, 삭제 ,변경 관리함
        Gson gson = new Gson(); // 변수 없이 간단히 리스트만 json으로 만드는 경우이다.
        String json = gson.toJson(mmArrayList); // 리스트에 담긴걸 자동으로 JSON객체로 바꿔준다.
        editor.putString("task list", json);
        editor.apply();  // 여기서 이 메서드를 호출하면 적용 할 수 있다고 생각하면 예제가 저장된다.
    }

    private void loadData() {//데이터를 띄운다
        SharedPreferences sharedPreferences = getSharedPreferences("shared", MODE_PRIVATE);//문자열 형식으로 이름을 전달
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", "");//작업 목록기술을 전달하고 기본값의 기본값은 이경우 null이므로 모든 목록에 저장하지 않으면 아무것도 얻지 못합니다.
        //위에 "" 안에 값이 원래 null이었다 이러면 한칸이라도 비면 저장이 안된다.
        Type type = new TypeToken<ArrayList<DictionarySub>>() {
        }.getType();//타입에 자바 도트길이가 없다
        mmArrayList = gson.fromJson(json, type); //리스트를 풀어준다 JSON타입으로

        //메서드를 사용하여 예제 목록을 로드한다 그러나 공유환경 설정에서 벗어나지만 저장하지 않은 경우 null이 될 수도 있기 때문에 리스트 예제가 지금 같은지 여기에서 확인한다.
        if (mmArrayList == null) {
            mmArrayList = new ArrayList<>();
        }

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