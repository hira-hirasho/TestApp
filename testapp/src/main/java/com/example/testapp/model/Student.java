package com.example.testapp.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "STUDENT_MASTER")
public class Student {
    // 主キー 学籍番号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    // 生徒のログインID
    @Column(name = "login_id")
    private String loginId;

    // 生徒のログインパスワード
    @Column(name = "password")
    private String password;

    // 生徒の名前
    @Column(name = "name")
    private String name;

    // 生徒の性別
    @Column(name = "gender")
    private Integer gender;

    // 生徒の誕生日
    @Column(name = "birthday")
    private Date birthday;

    // 受講している講義のリスト
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "STUDENT_TO_COURSE", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "course_id"))
    List<Course> courseList = new ArrayList<>();
}
