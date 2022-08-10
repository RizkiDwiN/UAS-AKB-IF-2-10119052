package com.rizki.catkur;
/**
 * Tanggal Pengerjaan : 10 AGUSTUS 2022 , 13.56 WIB
 * NIM : 10119052
 * Nama : Rizki Dwi Nugraha
 * Kelas : IF-2
 **/
public class Catkur {
    private long ID;
    private String title;
    private String content;
    private String date;
    private String time;

    Catkur(){}
    Catkur(String title, String content, String date, String time){
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }
    Catkur(long id,String title, String content, String date, String time){
        this.ID = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID= ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
