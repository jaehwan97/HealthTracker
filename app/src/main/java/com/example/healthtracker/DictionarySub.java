package com.example.healthtracker;

public class DictionarySub {//리스트(아이템뷰)에 들어갈 데이터(모델)들을 선언
    private String day;
    private String kcal1;
    private String kcal2;
    private String kcal3;
    private String kcal4;

    //클래스를 만들어서 어레이 리스트에서 관리한다. 그걸위한 클래스스
    //데이터를 담아주기위해 겟과 셋
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getKcal1() {
        return kcal1;
    }

    public void setKcal1(String kcal1) {
        this.kcal1 = kcal1;
    }

    public String getKcal2() {
        return kcal2;
    }

    public void setKcal2(String kcal2) {
        this.kcal2 = kcal2;
    }

    public String getKcal3() {
        return kcal3;
    }

    public void setKcal3(String kcal3) {
        this.kcal3 = kcal3;
    }

    public String getKcal4() {
        return kcal4;
    }

    public void setKcal4(String kcal4) {
        this.kcal4 = kcal4;
    }

    public DictionarySub(String day, String kcal1, String kcal2, String kcal3, String kcal4) {//생성자 추가
        this.day = day;
        this.kcal1 = kcal1;
        this.kcal2 = kcal2;
        this.kcal3 = kcal3;
        this.kcal4 = kcal4;
    }
}
