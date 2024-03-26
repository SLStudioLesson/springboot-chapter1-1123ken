package com.example.chapter01test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;

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
public class Q2Test {

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Tag("Q2")
    @Test
    void testShowSchedule() throws Exception {
        mockMvc.perform(get("/question2/index/")) // "/question2/index"へのGETリクエストを模擬
                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                .andExpect(view().name("index")) // ビュー名が"index"であることを検証
                .andExpect(model().attributeExists("schedules")) // モデルに"schedules"属性が存在することを検証
                .andExpect(model().attribute("schedules", everyItem(allOf(
                        hasProperty("id", anyOf(instanceOf(Integer.class), instanceOf(Long.class))),
                        hasProperty("name", instanceOf(String.class)),
                        hasProperty("startDateTime", instanceOf(LocalDateTime.class)),
                        hasProperty("duration", instanceOf(Double.class)),
                        hasProperty("location", instanceOf(String.class)))))); // "schedules"属性がScheduleクラスのインスタンスのリストであり、各オブジェクトのプロパティが指定された型であることを検証
    }
}
