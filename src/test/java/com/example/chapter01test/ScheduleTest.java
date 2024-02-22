package com.example.chapter01test;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ScheduleTest {

    @Tag("Q2")
    @Test
    void testScheduleObjectCreation() {
        LocalDateTime startDateTime = LocalDateTime.of(2022, 10, 5, 14, 30);
        Schedule schedule = new Schedule(1, "Meeting", startDateTime, 2.0, "Conference Room");

        assertThat(schedule).isNotNull();
        assertThat(schedule.getId()).isEqualTo(1);
        assertThat(schedule.getName()).isEqualTo("Meeting");
        assertThat(schedule.getStartDateTime()).isEqualTo(startDateTime);
        assertThat(schedule.getDuration()).isEqualTo(2.0);
        assertThat(schedule.getLocation()).isEqualTo("Conference Room");
    }

    @Tag("Q2")
    @Test
    void testScheduleSetters() {
        LocalDateTime newStartDateTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        Schedule schedule = new Schedule(0, "", LocalDateTime.now(), 0.0, "");

        schedule.setId(2);
        schedule.setName("Workshop");
        schedule.setStartDateTime(newStartDateTime);
        schedule.setDuration(3.0);
        schedule.setLocation("Main Hall");

        assertThat(schedule.getId()).isEqualTo(2);
        assertThat(schedule.getName()).isEqualTo("Workshop");
        assertThat(schedule.getStartDateTime()).isEqualTo(newStartDateTime);
        assertThat(schedule.getDuration()).isEqualTo(3.0);
        assertThat(schedule.getLocation()).isEqualTo("Main Hall");
    }
}
