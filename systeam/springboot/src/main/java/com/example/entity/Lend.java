package com.example.entity;

import cn.hutool.core.annotation.Alias;

import javax.persistence.*;

@Table(name = "lend")
public class Lend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role")
    private String role;

    @Column(name = "num")
    private Integer num;

    @Column(name = "day")
    private Integer day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
