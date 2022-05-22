package com.example.testapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "PROFESSOR_MASTER")
public class Professor {
    // 主キー 教授ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Integer professorId;

    // 教授のログインID
    @Column(name = "login_id")
    private String loginId;

    // 教授のログインパスワード
    @Column(name = "password")
    private String password;

    // 教授名
    @Column(name = "name")
    private String name;

    // 教授の性別
    @Column(name = "gender")
    private Integer gender;

    // 担当している講義のリスト
    @OneToMany(mappedBy = "professor")
    List<Course> courseList = new ArrayList<>();
}
