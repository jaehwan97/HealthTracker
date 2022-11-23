package com.example.healthtracker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

//리사이클러뷰에서 보이는 여러개의 아이템은 내부에서 캐시되기 때문에 아이템 개수만큼 객체로 만들어지지는 않는다.
//아이템이 천개라고하더라도 이아티엠을 위해 천개의 뷰 객체가 만들어지지 않고 뷰홀더에 뷰객체를 넣어두어 사용자가 스크롤하여 오비지 않게 된 뷰객체를 새로 재사용하는 효율적인 방법을 사용한다. 이과정에서 뷰홀더가 필요하다
public class CustomAdapter extends RecyclerView.Adapter<com.example.healthtracker.CustomAdapter.CustomViewHolder> { //리사이클러뷰 상속
    //어댑터 필수 메소드 3가지 onCreateViewHolder,onBindViewHolder,getItemCount

    private ArrayList<Dictionary> mList; //전달된 리스트에서 데이터 보관 / 리스트뷰에 아이템들을 담을 배열
    private Context mContext;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private static final String TAG ="MainActivity";


    //리사이클러뷰는 화면에 보이는 아이템뷰 객체를 기억(홀딩)하고 있어야하는 ViewHolder가 필요함
    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener { // 1. 리스너 추가
        protected TextView id; //리스트에 담길 데이터들을 선언해주는 것
        protected TextView english;
        protected TextView korean;
        protected TextView sul;
        protected TextView memo;

        public CustomViewHolder(View view) {
            super(view);
            this.id = (TextView) view.findViewById(R.id.id_listitem);//날짜
            this.english = (TextView) view.findViewById(R.id.english_listitem);//체중
            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
            this.sul = (TextView) view.findViewById(R.id.korean_1);
            this.memo = (TextView) view.findViewById(R.id.korean_2);
            english.setSelected(true);
            memo.setSelected(true); //marquee효과를 주려면 이거 설정 되있어야함,

            view.setOnCreateContextMenuListener(this); //2. 리스너 등록
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가

            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu); //편집 누름
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() { //메뉴 선택 눌렀을때
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) { // 아이템 메뉴 편집과 삭제기능
                    case 1001: //편집 눌렀을때

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        View view = LayoutInflater.from(mContext).inflate(R.layout.edit_box, null, false); //데이터 입력받는 레이아웃
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final TextView editTextID = (TextView) view.findViewById(R.id.edittext_dialog_id);
                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
                        final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);
                        final EditText editTextsul=(EditText) view.findViewById(R.id.edittext_sul);
                        final EditText editTextmemo=(EditText) view.findViewById(R.id.edittext_memo);

                        Spinner spinner = (Spinner)view.findViewById(R.id.spinner); //운동 유무 스피너
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                editTextKorean.setText(parent.getItemAtPosition(position).toString());
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        Spinner spinner1 = (Spinner)view.findViewById(R.id.spinner1); //음주 유무 스피너
                        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                editTextsul.setText(parent.getItemAtPosition(position).toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        editTextID.setOnClickListener(new View.OnClickListener() { //날짜 선택 눌렀을때
                            @Override
                            public void onClick(View v) {
                                Calendar cal =Calendar.getInstance();
                                int year =cal.get(Calendar.YEAR);
                                int month =cal.get(Calendar.MONTH);
                                int day =cal.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog dialog =new DatePickerDialog( // 날짜 선택할때 년 월 일
                                        (com.example.healthtracker.MainActivity) com.example.healthtracker.CustomAdapter.this.mContext,
                                        android.R.style.Theme_Holo_Dialog_MinWidth,
                                        onDateSetListener,
                                        year,month,day);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show();
                            }
                        });
                        onDateSetListener =new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date =year +"/"+month+"/"+dayOfMonth;
                                editTextID.setText(date); //데이트피커로 받은 날짜 설정
                            }
                        };

                        //여기서 뷰에다가 텍스트 써줌
                        editTextID.setText(mList.get(getAdapterPosition()).getId());
                        editTextEnglish.setText(mList.get(getAdapterPosition()).getEnglish());
                        editTextKorean.setText(mList.get(getAdapterPosition()).getKorean());
                        editTextsul.setText(mList.get(getAdapterPosition()).getSul());
                        editTextmemo.setText(mList.get(getAdapterPosition()).getMemo());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() { //수정 버튼
                            public void onClick(View v) { //

                                //뷰에 써져있는 데이터들을 저장
                                String strID = editTextID.getText().toString();
                                String strEnglish = editTextEnglish.getText().toString();
                                String strKorean = editTextKorean.getText().toString();
                                String strsul = editTextsul.getText().toString();
                                String strmemo = editTextmemo.getText().toString();

                                Dictionary dict = new Dictionary(strID, strEnglish, strKorean ,strsul,strmemo);

                                mList.set(getAdapterPosition(), dict);
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss(); //팝업창 닫기
                            }
                        });
                        dialog.show();
                        break;

                    case 1002: //삭제 눌렀을때 지우기
                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());
                        break;
                }
                return true;
            }
        };
    }
    //인터페이스는 클래스가 반드시 해야할 행동 = 규제
    //구현을 나중으로 미루는 것을 추상이라고 한다. 이를 통해 자바의 특징중 하나인 다형성을 구현 가능
   /* 위의 예제처럼 Calculator에서 메소드를 정의하고 CalculatorImpl에서 구현하는거처럼,
   어떠한 행동에 대한 구체적인 구현을 나중으로 미루는것을 추상이라고 합니다.
    다시 정리하자면 추상이런것은 구체적이거나 실체는 없고, 행동(메서드) 정의만 있는 것입니다.
    추상화를 통해 자바의 특징중 하나인 다형성(Polymorphism)을 구현할 수 있습니다.
    추상화된 interface 구현을 어떻게 하느냐에 따라서 메소드내의 코드 또는 반환값을 다르게 만들 수 있습니다.
    결국 같은 interface를 구현하더라도 다른 형태를 가질수 있기 때문에 이를 다형성이라 합니다.*/
    public interface OnItemClick {// 인터페이스 정의 나중에 다른데서 구현해야 사용가능
        void onClick(String Value);
    }

    public CustomAdapter(Context context, ArrayList<Dictionary> list) { //생성자
        mList = list;
        mContext = context;
    }

    //onCreateViewHolder(): 리사이클러뷰는 뷰홀더를 새로 만들어야 할 때마다 이 메서드를 호출한다.
    // 이 메서드는 위에서 정의한 아이템 뷰 레이아웃을 이용해 뷰 객체를 만든다. 그리고 뷰 객체를 새로 만든 뷰홀더 객체에 담아 반환한다.
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) { //뷰홀더를 만들어서 리턴해준다

        View view = LayoutInflater.from(viewGroup.getContext()) //item_list 레이아웃가져옴
                .inflate(R.layout.item_list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    //onBindViewHolder(): 리사이클러뷰는 뷰홀더를 데이터와 연결할 때 이 메서드를 호출한다.
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) { //해당되는 포지션의 데이터를 뷰홀더에 바인딩시켜줌

        //바인딩시킬때 텍스트 사이즈
        viewholder.id.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.english.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.korean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.sul.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.memo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        //바인딩 시킬때 중력
        viewholder.id.setGravity(Gravity.CENTER);
        viewholder.english.setGravity(Gravity.CENTER);
        viewholder.korean.setGravity(Gravity.CENTER);
        viewholder.sul.setGravity(Gravity.CENTER);
        viewholder.memo.setGravity(Gravity.CENTER);

        //set 텍스트 통해서 바로 바인딩시켜줌
        viewholder.id.setText(mList.get(position).getId());
        viewholder.english.setText(mList.get(position).getEnglish());
        viewholder.korean.setText(mList.get(position).getKorean());
        viewholder.sul.setText(mList.get(position).getSul());
        viewholder.memo.setText(mList.get(position).getMemo());

    }

    //getItemCount(): 어댑터에서 관리하는 아이템의 개수를 반환한다. 이 메서드는 리싸이클러뷰에서 어댑터가 관리하는 아이템의 개수를 알아야 할 때 사용된다.
    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    } //리스트 개수 반환

    public boolean moveItem(int fromPosition, int toPosition){ //아이템이동 어디서 어디로 이동할지
        Dictionary text = mList.get(fromPosition); //데이터를 잠깐 담아두는곳
        mList.remove(fromPosition);
        mList.add(toPosition,text);
        notifyItemMoved(fromPosition, toPosition); //이동
        return true;
    }
    public void removeItem(int position){ //스와이프 할때 데이터 삭제기능
        mList.remove(position); //지워
        notifyItemRemoved(position);
    }
}