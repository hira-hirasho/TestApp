package com.example.testapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.testapp.model.Professor;
import com.example.testapp.repository.ProfessorMasterRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ProfessorDetailsServiceTest {
    @Autowired
    ProfessorMasterRepository repository;

    @Autowired
    ProfessorDetailsService service;

    @Test
    void ユーザー情報取得テスト() {
        var professor = new Professor();
        professor.setLoginId("professorA");
        professor.setPassword("password");
        professor.setName("生徒A");
        professor.setGender(1);
        repository.save(professor);

        var actual = service.loadUserByUsername("professorA");

        assertEquals(professor.getLoginId(), actual.getUsername());
    }

    @Test
    void 例外スローテスト() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("professorB"));
    }
}
