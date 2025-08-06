package com.example;

import jakarta.persistence.*;

@Entity
public class FormEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int year;
    private int month;
    private int day;
    private int age;
    private String email;
    private String callnumber;
    private String live;
    private String finalbackground;
    private String skills;
    private String job;
    private String PR;
    private String motivation;

    // --- ゲッター & セッター ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // year
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // month
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }

    // day
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    // age
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    // callnumber
    public String getCallnumber() {
        return callnumber;
    }
    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }   
    
    // live
    public String getLive() {
        return live;
    }
    public void setLive(String live) {
        this.live = live;
    }       
    
    //　finalbackground
    public String getFinalbackground() {
        return finalbackground;
    }
    public void setFinalbackground(String finalbackground) {
        this.finalbackground = finalbackground;
    }           

    // skills
    public String getSkills() {
        return skills;
    }
    public void setSkills(String skills) {
        this.skills = skills;
    }

    // job
    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    // PR
    public String getPR() {
        return PR;
    }
    public void setPR(String PR) {
        this.PR = PR;
    }

    // motivation
    public String getMotivation() {
        return motivation;
    }
    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }
}