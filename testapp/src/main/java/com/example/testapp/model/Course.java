package com.example.testapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COURSE_MASTER")
public class Course {
    // 主キー 講義ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    // 講義名
    @Column(name = "name")
    private String name;

    // 担当教授
    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "professor_id")
    private Professor professor;

    // 講義を受講している生徒のリスト
    @ManyToMany(mappedBy = "courseList")
    List<Student> studentList = new ArrayList<>();
}
