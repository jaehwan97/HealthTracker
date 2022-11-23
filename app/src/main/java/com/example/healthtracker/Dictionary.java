package com.example.healthtracker;

public class Dictionary { //리스트(아이템뷰)에 들어갈 데이터(모델)들을 선언
    private String id;
    private String English;
    private String Korean;
    private String sul;
    private String memo;

    //클래스를 만들어서 어레이 리스트에서 관리한다. 그걸위한 클래스스
   //데이터를 담아주기위해 겟과 셋
    public String getId() { //자동으로 입력 받았음
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish() {
        return English;
    }

    public void setEnglish(String english) {
        English = english;
    }

    public String getKorean() {
        return Korean;
    }

    public void setKorean(String korean) {
        Korean = korean;
    }


    public String getSul() {
        return sul;
    }

    public void setSul(String sul) {
        this.sul = sul;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Dictionary(String id, String english, String korean, String sul, String memo) { //생성자 추가
        this.id = id;
        this.English = english;
        this.Korean = korean;
        this.sul = sul;
        this.memo = memo;
    }
}
