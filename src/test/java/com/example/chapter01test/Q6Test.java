package com.example.chapter01test;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

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
public class Q6Test {

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
    }

    @Tag("Q6")
    @Test
    void testAddScheduleToList() throws Exception {
        // ScheduleControllerのインスタンスを取得または作成
        ScheduleController controller = this.scheduleController;
        List<Schedule> scheduleList = controller.scheduleList;
        int initialSize = scheduleList.size();

        mockMvc.perform(post("/question2/add") // "/question2/add"へのPOSTリクエストを模擬し、パラメータを設定
                .param("id", "4")
                .param("name", "Dinner")
                .param("startDateTime", "2022-10-05T20:00:00")
                .param("duration", "5.0")
                .param("location", "Ikebukuro"))
                .andExpect(status().isOk())
                .andExpect(view().name("success"));

        assertThat(scheduleList).hasSize(initialSize + 1);
        Schedule addedSchedule = scheduleList.get(initialSize); // 新しく追加されたスケジュールを取得
        assertThat(addedSchedule.getId()).isEqualTo(4);
        assertThat(addedSchedule.getName()).isEqualTo("Dinner");
        assertThat(addedSchedule.getStartDateTime()).isEqualTo(LocalDateTime.parse("2022-10-05T20:00:00"));
        assertThat(addedSchedule.getDuration()).isEqualTo(5.0);
        assertThat(addedSchedule.getLocation()).isEqualTo("Ikebukuro");
    }
}
