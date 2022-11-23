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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

//리사이클러뷰에서 보이는 여러개의 아이템은 내부에서 캐시되기 때문에 아이템 개수만큼 객체로 만들어지지는 않는다.
//아이템이 천개라고하더라도 이아티엠을 위해 천개의 뷰 객체가 만들어지지 않고 뷰홀더에 뷰객체를 넣어두어 사용자가 스크롤하여 오비지 않게 된 뷰객체를 새로 재사용하는 효율적인 방법을 사용한다. 이과정에서 뷰홀더가 필요하다
public class CustomAdapterSub extends RecyclerView.Adapter<com.example.healthtracker.CustomAdapterSub.ViewHolder>{
    //어댑터 필수 메소드 3가지 onCreateViewHolder,onBindViewHolder,getItemCount

    private ArrayList<DictionarySub> mList;//전달된 리스트에서 데이터 보관 / 리스트뷰에 아이템들을 담을 배열
    private Context mContext;
    private DatePickerDialog.OnDateSetListener onDateSetListener2;


    //리사이클러뷰는 화면에 보이는 아이템뷰 객체를 기억(홀딩)하고 있어야하는 ViewHolder가 필요함
    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener { // 1. 리스너 추가
        protected TextView day; //리스트에 담길 데이터들을 선언해주는 것
        protected TextView kcal1;
        protected TextView kcal2;
        protected TextView kcal3;
        protected TextView kcal4;


        public ViewHolder(View view) { //변수에 할당하는 부분
            super(view);
            this.day = (TextView) view.findViewById(R.id.id_day);
            this.kcal1 = (TextView) view.findViewById(R.id.id_kcal1);
            this.kcal2 = (TextView) view.findViewById(R.id.id_kcal2);
            this.kcal3 = (TextView) view.findViewById(R.id.id_kcal3);
            this.kcal4 = (TextView) view.findViewById(R.id.id_kcal4);
            kcal1.setSelected(true);//marquee효과를 주려면 이거 설정 되있어야함,
            kcal2.setSelected(true);
            kcal3.setSelected(true);
            kcal4.setSelected(true);

            view.setOnCreateContextMenuListener(this); //2. 리스너 등록
        }



        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {  // 3. 메뉴 추가


            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집"); //케이스로 구분해줌
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);

        }

        // 4. 캔텍스트 메뉴 클릭시 동작을 설정
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() { //메뉴선택
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                switch (item.getItemId()) {
                    case 1001: //편집

                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext); //팝업창 띄움
                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box1, null, false); //데이터 입력받는 레이아웃
                        builder.setView(view);
                        final Button ButtonSubmitt = (Button) view.findViewById(R.id.button_dialog_submitt);//뷰객체 사용할 수 있게 만들어줌
                        final TextView editTextDAY = (TextView) view.findViewById(R.id.edittext_dialog_id2); //날짜
                        final EditText editTextKCAL1 = (EditText) view.findViewById(R.id.edittext_1);//아침
                        final EditText editTextKCAL2 = (EditText) view.findViewById(R.id.edittext_2);//점심
                        final EditText editTextKCAL3=(EditText) view.findViewById(R.id.edittext_3);//저녁
                        final EditText editTextKCAL4=(EditText) view.findViewById(R.id.edittext_4);//간식

                        editTextDAY.setOnClickListener(new View.OnClickListener() {//날짜 누르면
                            @Override
                            public void onClick(View v) {
                                Calendar cal =Calendar.getInstance();
                                int year =cal.get(Calendar.YEAR);
                                int month =cal.get(Calendar.MONTH);
                                int day =cal.get(Calendar.DAY_OF_MONTH);

                                DatePickerDialog dialog =new DatePickerDialog(
                                        (SubActivity)CustomAdapterSub.this.mContext, // 날짜 선택할때 년 월 일
                                        android.R.style.Theme_Holo_Dialog_MinWidth,
                                        onDateSetListener2,
                                        year,month,day);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show(); //팝업창 띄움
                            }
                        });
                        onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                month = month+1;
                                String date =year +"/"+month+"/"+dayOfMonth;
                                editTextDAY.setText(date); //데이트피커로 받은 날짜 설정

                            }
                        };

                        //여기서 뷰에다가 텍스트 써줌
                        editTextDAY.setText(mList.get(getAdapterPosition()).getDay());
                        editTextKCAL1.setText(mList.get(getAdapterPosition()).getKcal1());
                        editTextKCAL2.setText(mList.get(getAdapterPosition()).getKcal2());
                        editTextKCAL3.setText(mList.get(getAdapterPosition()).getKcal3());
                        editTextKCAL4.setText(mList.get(getAdapterPosition()).getKcal4());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmitt.setOnClickListener(new View.OnClickListener() { //수정 버튼
                            public void onClick(View v) {

                                //뷰에 써져있는 데이터들을 저장
                                String strDAY = editTextDAY.getText().toString();
                                String strKCAL1 = editTextKCAL1.getText().toString();
                                String strKCAL2 = editTextKCAL2.getText().toString();
                                String strKCAL3 = editTextKCAL3.getText().toString();
                                String strKCAL4 = editTextKCAL4.getText().toString();

                                DictionarySub dictt = new DictionarySub(strDAY, strKCAL1, strKCAL2 ,strKCAL3,strKCAL4);

                                mList.set(getAdapterPosition(), dictt);
                                notifyItemChanged(getAdapterPosition());

                                dialog.dismiss(); //팝업창 닫기
                            }
                        });

                        dialog.show();

                        break;

                    case 1002://삭제 눌렀을때 지우기

                        mList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());

                        break;

                }
                return true;
            }
        };
    }
    public CustomAdapterSub(Context context, ArrayList<DictionarySub> list) { //생성자
        mList = list;
        mContext = context;

    }
    //onCreateViewHolder(): 리사이클러뷰는 뷰홀더를 새로 만들어야 할 때마다 이 메서드를 호출한다.
    // 이 메서드는 위에서 정의한 아이템 뷰 레이아웃을 이용해 뷰 객체를 만든다. 그리고 뷰 객체를 새로 만든 뷰홀더 객체에 담아 반환한다.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) { //뷰홀더 생성 시점에 자동 호출됨 각각 아이템을 위한 레이아웃을 인플레이션 한후 뷰홀더에서 리턴해줌
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list1, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    //onBindViewHolder(): 리사이클러뷰는 뷰홀더를 데이터와 연결할 때 이 메서드를 호출한다.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {

        //바인딩시킬때 텍스트 사이즈
        viewholder.day.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.kcal1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.kcal2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.kcal3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.kcal4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        //바인딩시킬때 중력
        viewholder.day.setGravity(Gravity.CENTER);
        viewholder.kcal1.setGravity(Gravity.CENTER);
        viewholder.kcal2.setGravity(Gravity.CENTER);
        viewholder.kcal3.setGravity(Gravity.CENTER);
        viewholder.kcal4.setGravity(Gravity.CENTER);

        //글자 바인딩
        viewholder.day.setText(mList.get(position).getDay());
        viewholder.kcal1.setText(mList.get(position).getKcal1());
        viewholder.kcal2.setText(mList.get(position).getKcal2());
        viewholder.kcal3.setText(mList.get(position).getKcal3());
        viewholder.kcal4.setText(mList.get(position).getKcal4());

    }
    //getItemCount(): 어댑터에서 관리하는 아이템의 개수를 반환한다. 이 메서드는 리싸이클러뷰에서 어댑터가 관리하는 아이템의 개수를 알아야 할 때 사용된다.
    @Override
    public int getItemCount() { //안에 들어가는 아이템 개수가 몇개임?

        return (null != mList ? mList.size() : 0);
    }
    public boolean moveItem(int fromPosition, int toPosition){//아이템이동 어디서 어디로 이동할지
        DictionarySub text = mList.get(fromPosition);//데이터를 잠깐 담아두는곳
        mList.remove(fromPosition);
        mList.add(toPosition,text);
        notifyItemMoved(fromPosition, toPosition);//이동
        return true;
    }
    public void removeItem(int position){//스와이프 할때 데이터 삭제기능
        mList.remove(position);//지워
        notifyItemRemoved(position);
    }
}