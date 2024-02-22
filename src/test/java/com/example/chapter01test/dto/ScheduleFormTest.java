package com.example.chapter01test.dto;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class ScheduleFormTest {

    @Tag("Q5")
    @Test
    void testGettersAndSetters() {
        ScheduleForm form = new ScheduleForm();
        form.setId(1);
        form.setName("Test Name");
        form.setStartDateTime(LocalDateTime.of(2023, 1, 1, 10, 0));
        form.setDuration(2.5);
        form.setLocation("Test Location");

        assertThat(form.getId()).isEqualTo(1);
        assertThat(form.getName()).isEqualTo("Test Name");
        assertThat(form.getStartDateTime()).isEqualTo(LocalDateTime.of(2023, 1, 1, 10, 0));
        assertThat(form.getDuration()).isEqualTo(2.5);
        assertThat(form.getLocation()).isEqualTo("Test Location");
    }

}