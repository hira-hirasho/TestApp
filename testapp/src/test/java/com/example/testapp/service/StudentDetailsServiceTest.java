package com.example.testapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;

import com.example.testapp.model.Student;
import com.example.testapp.repository.StudentMasterRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StudentDetailsServiceTest {
    @Autowired
    StudentMasterRepository repository;

    @Autowired
    StudentDetailsService service;

    @Test
    void ユーザー情報取得テスト() {
        var student = new Student();
        student.setLoginId("studentA");
        student.setPassword("password");
        student.setName("生徒A");
        student.setGender(1);
        student.setBirthday(new Date(1653273274));
        repository.save(student);

        var actual = service.loadUserByUsername("studentA");

        assertEquals(student.getLoginId(), actual.getUsername());
    }

    @Test
    void 例外スローテスト() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("studentB"));
    }
}
