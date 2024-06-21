package com.example.entity;

import javax.persistence.*;

@Table(name = "imsingle")
public class ImSingle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="fromuser")
    private String fromuser;
    @Column(name="touser")
    private String touser;
    @Column(name="content")
    private String content;

    @Column(name="readed")
    private Integer readed;

    @Column(name="fromavatar")
    private String fromavatar;

    @Column(name="toavatar")
    private String toavatar;

    @Column(name="time")
    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromuser() {
        return fromuser;
    }

    public void setFromuser(String fromuser) {
        this.fromuser = fromuser;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReaded() {
        return readed;
    }

    public void setReaded(Integer readed) {
        this.readed = readed;
    }

    public String getFromavatar() {
        return fromavatar;
    }

    public void setFromavatar(String fromavatar) {
        this.fromavatar = fromavatar;
    }

    public String getToavatar() {
        return toavatar;
    }

    public void setToavatar(String toavatar) {
        this.toavatar = toavatar;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
