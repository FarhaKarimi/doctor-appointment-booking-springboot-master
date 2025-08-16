// File: AppointmentController.java
package com.nadimnesar.drappointment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AppointmentController {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final List<String> timeSlots = Arrays.asList(
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "14:00", "14:30", "15:00", "15:30", "16:00"
    );

    @GetMapping("/appointment")
    public String showAppointmentPage(
            @RequestParam String doctor,
            Model model) {

        // تاریخ‌های قابل انتخاب: امروز + ۷ روز آینده
        List<String> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate date = LocalDate.now().plusDays(i);
            dates.add(date.format(formatter));
        }

        model.addAttribute("doctorName", doctor);
        model.addAttribute("availableDates", dates);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("today", LocalDate.now().format(formatter));

        return "appointment"; // appointment.html
    }
}