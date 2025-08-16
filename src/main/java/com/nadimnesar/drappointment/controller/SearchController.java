// File: SearchController.java
package com.nadimnesar.drappointment.controller;

import com.nadimnesar.drappointment.model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    private final List<Doctor> allDoctors = createDoctorList(); // دیتای تستی

    @GetMapping("/search")
    public String search(
            @RequestParam(required = false) String division,
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String specialty,
            Model model) {

        List<Doctor> results = new ArrayList<>();

        for (Doctor doctor : allDoctors) {
            boolean match = true;

            if (division != null && !division.isBlank()) {
                if (!matchesDivision(doctor, division)) match = false;
            }
            if (district != null && !district.isBlank()) {
                if (!matchesDistrict(doctor, district)) match = false;
            }
            if (specialty != null && !specialty.isBlank()) {
                if (!doctor.getSpecialty().equals(specialty)) match = false;
            }

            if (match) {
                results.add(doctor);
            }
        }

        model.addAttribute("division", division);
        model.addAttribute("district", district);
        model.addAttribute("specialty", specialty);
        model.addAttribute("results", results);

        return "search";
    }

    // مثلاً فرض کنیم هر پزشک فقط در یک استان کار می‌کنه
    private boolean matchesDivision(Doctor doctor, String division) {
        return switch (division) {
            case "تهران" -> doctor.getAddress().contains("تهران");
            case "اصفهان" -> doctor.getAddress().contains("اصفهان");
            case "شیراز" -> doctor.getAddress().contains("شیراز");
            default -> true;
        };
    }

    // فعلاً همون آدرس رو چک می‌کنه
    private boolean matchesDistrict(Doctor doctor, String district) {
        return doctor.getAddress().contains(district);
    }

    // دیتای تستی
    private List<Doctor> createDoctorList() {
        List<Doctor> list = new ArrayList<>();
        list.add(new Doctor("دکتر محمدی", "قلب", "تهران، خیابان آزادی", "مطب سلامت", "/img/doctors/dr1.jpg"));
        list.add(new Doctor("دکتر احمدی", "اطفال", "تهران، منطقه ۲", "کلینیک کودکان آرمان", "/img/doctors/dr2.jpg"));
        list.add(new Doctor("دکتر رضایی", "جراحی", "اصفهان، میدان انقلاب", "بیمارستان فجر", "/img/doctors/dr3.jpg"));
        list.add(new Doctor("دکتر کاظمی", "زنان", "شیراز، خیابان زینب", "مطب زینبیه", "/img/doctors/dr4.jpg"));
        return list;
    }
}