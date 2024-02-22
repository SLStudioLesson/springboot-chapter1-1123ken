package com.example.chapter01test.controller;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.allOf;
// import static org.hamcrest.Matchers.allOf;
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

import com.example.chapter01test.Schedule;
import com.example.chapter01test.dto.ScheduleForm;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

        @InjectMocks
        private ScheduleController scheduleController;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp() {
                mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();
        }

        @Tag("Q2")
        @Test
        void scheduleListInitializationTest() {
                // ScheduleControllerのインスタンスを作成
                ScheduleController controller = new ScheduleController();

                // scheduleListが期待通りに初期化されているかを検証
                assertThat(controller.scheduleList).isNotNull().hasSize(3);

                // 最初のスケジュールの詳細を検証
                Schedule firstSchedule = controller.scheduleList.get(0);
                assertThat(firstSchedule.getId()).isEqualTo(1);
                assertThat(firstSchedule.getName()).isEqualTo("Lunch");
                assertThat(firstSchedule.getStartDateTime()).isEqualTo(LocalDateTime.parse("2019-01-22T12:00:00"));
                assertThat(firstSchedule.getDuration()).isEqualTo(1.0);
                assertThat(firstSchedule.getLocation()).isEqualTo("Shibuya");

                // 2番目のスケジュールの詳細を検証
                Schedule secondSchedule = controller.scheduleList.get(1);
                assertThat(secondSchedule.getId()).isEqualTo(2);
                assertThat(secondSchedule.getName()).isEqualTo("Meeting");
                assertThat(secondSchedule.getStartDateTime()).isEqualTo(LocalDateTime.parse("2019-01-22T15:00:00"));
                assertThat(secondSchedule.getDuration()).isEqualTo(3.0);
                assertThat(secondSchedule.getLocation()).isEqualTo("Shinjuku");

                // 3番目のスケジュールの詳細を検証
                Schedule thirdSchedule = controller.scheduleList.get(2);
                assertThat(thirdSchedule.getId()).isEqualTo(3);
                assertThat(thirdSchedule.getName()).isEqualTo("Dinner");
                assertThat(thirdSchedule.getStartDateTime()).isEqualTo(LocalDateTime.parse("2019-01-22T19:00:00"));
                assertThat(thirdSchedule.getDuration()).isEqualTo(8.0);
                assertThat(thirdSchedule.getLocation()).isEqualTo("Kichijoji");
        }

        @Tag("Q2")
        @Test
        void testShowSchedule() throws Exception {
                mockMvc.perform(get("/question2/index/")) // "/question2/index"へのGETリクエストを模擬
                                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                                .andExpect(view().name("index")) // ビュー名が"index"であることを検証
                                .andExpect(model().attributeExists("schedules")) // モデルに"schedules"属性が存在することを検証
                                .andExpect(model().attribute("schedules", hasItem(allOf(
                                                hasProperty("id", is(1)),
                                                hasProperty("name", is("Lunch")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T12:00:00"))),
                                                hasProperty("duration", is(1.0)),
                                                hasProperty("location", is("Shibuya"))))))
                                .andExpect(model().attribute("schedules", hasItem(allOf(
                                                hasProperty("id", is(2)),
                                                hasProperty("name", is("Meeting")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T15:00:00"))),
                                                hasProperty("duration", is(3.0)),
                                                hasProperty("location", is("Shinjuku"))))))
                                .andExpect(model().attribute("schedules", hasItem(allOf(
                                                hasProperty("id", is(3)),
                                                hasProperty("name", is("Dinner")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T19:00:00"))),
                                                hasProperty("duration", is(8.0)),
                                                hasProperty("location", is("Kichijoji")))))); // "schedules"属性がScheduleクラスのインスタンスのリストであり、各オブジェクトのフィールド値が期待通りであることを検証
        }

        @Tag("Q3")
        @Test
        void testShowDetail() throws Exception {
                mockMvc.perform(get("/question2/detail/1")) // "/question2/detail/1"へのGETリクエストを模擬
                                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                                .andExpect(view().name("detail")) // ビュー名が"detail"であることを検証
                                .andExpect(model().attributeExists("schedule")) // モデルに"schedule"属性が存在することを検証
                                .andExpect(model().attribute("schedule", allOf(
                                                instanceOf(Schedule.class),
                                                hasProperty("id", is(1)),
                                                hasProperty("name", is("Lunch")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T12:00:00"))),
                                                hasProperty("duration", is(1.0)),
                                                hasProperty("location", is("Shibuya"))))); // "schedules"属性がScheduleクラスのインスタンスのリストであり、オブジェクトのフィールド値が期待通りであることを検証
        }

        @Tag("Q4")
        @Test
        void testSearch() throws Exception {
                mockMvc.perform(get("/question2/search").param("name", "Dinner").param("location", "Kichijoji")) // "/question2/search"へのGETリクエストを模擬し、パラメータを設定
                                .andExpect(status().isOk()) // ステータスコードが200であることを検証
                                .andExpect(view().name("result")) // ビュー名が"result"であることを検証
                                .andExpect(model().attributeExists("schedules")) // モデルに"schedules"属性が存在することを検証
                                .andExpect(model().attribute("schedules", everyItem(allOf(
                                                instanceOf(Schedule.class),
                                                hasProperty("id", is(3)),
                                                hasProperty("name", is("Dinner")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T19:00:00"))),
                                                hasProperty("duration", is(8.0)),
                                                hasProperty("location", is("Kichijoji")))))); // "schedules"属性がScheduleクラスのインスタンスのリストであり、オブジェクトのフィールド値が期待通りであることを検証
        }

        @Tag("Q4")
        @Test
        void testSearchOnlyName() throws Exception {
                mockMvc.perform(get("/question2/search").param("name", "Lunch")) // パラメータがnameのみの場合を検証
                                .andExpect(status().isOk())
                                .andExpect(view().name("result"))
                                .andExpect(model().attributeExists("schedules"))
                                .andExpect(model().attribute("schedules", everyItem(allOf(
                                                instanceOf(Schedule.class),
                                                hasProperty("id", is(1)),
                                                hasProperty("name", is("Lunch")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T12:00:00"))),
                                                hasProperty("duration", is(1.0)),
                                                hasProperty("location", is("Shibuya"))))));
        }

        @Tag("Q4")
        @Test
        void testSearchOnlyLocation() throws Exception {
                mockMvc.perform(get("/question2/search").param("location", "Shinjuku")) // パラメータがlocationのみの場合を検証
                                .andExpect(status().isOk())
                                .andExpect(view().name("result"))
                                .andExpect(model().attributeExists("schedules"))
                                .andExpect(model().attribute("schedules", everyItem(allOf(
                                                instanceOf(Schedule.class),
                                                hasProperty("id", is(2)),
                                                hasProperty("name", is("Meeting")),
                                                hasProperty("startDateTime",
                                                                is(LocalDateTime.parse("2019-01-22T15:00:00"))),
                                                hasProperty("duration", is(3.0)),
                                                hasProperty("location", is("Shinjuku"))))));
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