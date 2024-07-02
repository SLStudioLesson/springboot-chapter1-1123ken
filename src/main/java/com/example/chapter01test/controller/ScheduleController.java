package com.example.chapter01test.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.chapter01test.Schedule;
import com.example.chapter01test.dto.ScheduleForm;

@Controller
@RequestMapping("/question2")
public class ScheduleController {
    public List<Schedule> scheduleList = new ArrayList<>();

    public ScheduleController() {
        scheduleList = new ArrayList<>();
        scheduleList.add(new Schedule(1, "Lunch", LocalDateTime.of(2019, 1, 22, 12, 0), 1.0, "Shibuya"));
        scheduleList.add(new Schedule(2, "Meeting", LocalDateTime.of(2019, 1, 22, 15, 0), 3.0, "Shinjuku"));
        scheduleList.add(new Schedule(3, "Dinner", LocalDateTime.of(2019, 1, 22, 19, 0), 8.0, "Kichijoji"));
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("schedules", scheduleList);
        return "index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@ModelAttribute("id") int id, Model model) {
        Schedule findSchedule = null;
        for (Schedule schedule : scheduleList) {
            if (schedule.getId() == id) {
                findSchedule = schedule;
                break;
            }
        }
        model.addAttribute("schedule", findSchedule);
        return "detail";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "location", required = false) String location,
            Model model) {
        List<Schedule> searchySchedule = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            boolean searchName = (name == null || name.isEmpty() || schedule.getName().contains(name));
            boolean searchLocation = (location == null || location.isEmpty()
                    || schedule.getLocation().contains(location));

            if (searchName && searchLocation) {
                searchySchedule.add(schedule);
            }
        }
        model.addAttribute("schedules", searchySchedule);
        return "result";
    }

    @GetMapping("/register")
    public String register(ScheduleForm scheduleForm) {
        return "register";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("startDateTime") LocalDateTime startDateTime,
            @RequestParam("duration") double duration,
            @RequestParam("location") String location,
            Model model) {
        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setId(id);
        scheduleForm.setName(name);
        scheduleForm.setStartDateTime(startDateTime);
        scheduleForm.setDuration(duration);
        scheduleForm.setLocation(location);

        model.addAttribute("schedule", scheduleForm);

        Schedule addSchedule = new Schedule(id, name, startDateTime, duration, location);
        scheduleList.add(addSchedule);

        return "success";
    }
}
