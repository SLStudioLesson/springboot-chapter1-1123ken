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

@ExtendWith(MockitoExtension.class)
public class Q3Test {

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Tag("Q3")
    @Test
    void testShowDetail() throws Exception {
        int testId = 1; // テスト対象のIDを設定
        mockMvc.perform(get("/question2/detail/" + testId)) // "/question2/detail/{testId}"へのGETリクエストを模擬
                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                .andExpect(view().name("detail")) // ビュー名が"detail"であることを検証
                .andExpect(model().attributeExists("schedule")) // モデルに"schedule"属性が存在することを検証
                .andExpect(model().attribute("schedule",
                        hasProperty("id", anyOf(instanceOf(Integer.class), instanceOf(Long.class))))); // "schedule"属性の"id"プロパティがtestIdであることを検証
    }
}
