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
public class Q4Test {

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Tag("Q4")
    @Test
    void testSearch() throws Exception {
        mockMvc.perform(get("/question2/search").param("name", "x").param("location", "y")) // "/question2/search"へのGETリクエストを模擬し、パラメータを設定
                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                .andExpect(view().name("result")) // ビュー名が"result"であることを検証
                .andExpect(model().attributeExists("schedules")) // モデルに"schedules"属性が存在することを検証
                .andExpect(model().attribute("schedules", everyItem(allOf( // "schedules"属性がScheduleクラスのインスタンスのリストであり、オブジェクトのフィールド値が期待通りであることを検証
                        instanceOf(Schedule.class), // Scheduleクラスのインスタンスであることを検証
                        hasProperty("name", containsString("x")), // "name"プロパティが"x"を含むことを検証
                        hasProperty("location", containsString("y")))))); // "location"プロパティが"y"を含むことを検証
    }

    @Tag("Q4")
    @Test
    void testSearchOnlyName() throws Exception {
        mockMvc.perform(get("/question2/search").param("name", "x")) // パラメータがnameのみの場合を検証
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("schedules"))
                .andExpect(model().attribute("schedules", everyItem(allOf(
                        instanceOf(Schedule.class),
                        hasProperty("name", containsString("x"))))));
    }

    @Tag("Q4")
    @Test
    void testSearchOnlyLocation() throws Exception {
        mockMvc.perform(get("/question2/search").param("location", "y")) // パラメータがlocationのみの場合を検証
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attributeExists("schedules"))
                .andExpect(model().attribute("schedules", everyItem(allOf(
                        instanceOf(Schedule.class),
                        hasProperty("location", containsString("y"))))));
    }
}
