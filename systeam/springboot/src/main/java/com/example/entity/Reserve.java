package com.example.entity;

import javax.persistence.*;

@Table(name = "reserve")
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="bookId")
    private Integer bookId;
    @Column(name="userId")
    private Integer userId;
    @Column(name="time")
    private String time;

    @Column(name="retime")
    private String retime;

    @Column(name="state")
    private String state;

    @Column(name="late")
    private Integer late;

    @Column(name="back")
    private String back;
    @Transient
    private String bookName;
    @Transient
    private String userName;

    @Transient
    private String nowtime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRetime() {
        return retime;
    }

    public void setRetime(String retime) {
        this.retime = retime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public Integer getLate() {
        return late;
    }

    public void setLate(Integer late) {
        this.late = late;
    }

    public String getNowtime() {
        return nowtime;
    }

    public void setNowtime(String nowtime) {
        this.nowtime = nowtime;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }
}
