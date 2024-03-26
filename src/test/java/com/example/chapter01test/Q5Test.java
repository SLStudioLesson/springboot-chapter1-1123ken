package com.example.chapter01test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.chapter01test.controller.ScheduleController;
import com.example.chapter01test.dto.ScheduleForm;

@ExtendWith(MockitoExtension.class)
public class Q5Test {

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Tag("Q5")
    @Test
    void testRegister() throws Exception {
        mockMvc.perform(get("/question2/register/")) // "/question2/register"へのGETリクエストを模擬
                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                .andExpect(view().name("register")) // ビュー名が"register"であることを検証
                .andExpect(model().attributeExists("scheduleForm")) // モデルに"scheduleForm"属性が存在することを検証
                .andExpect(model().attribute("scheduleForm", instanceOf(ScheduleForm.class))); // "scheduleForm"属性がScheduleFormクラスのインスタンスであるこを検証
    }
}
