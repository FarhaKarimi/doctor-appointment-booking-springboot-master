// File: DistrictController.java
package com.nadimnesar.drappointment.controller;

import com.nadimnesar.drappointment.service.SearchElement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictController {

    private final SearchElement searchElement = SearchElement.getInstance();

    @GetMapping("/api/districts/{province}")
    public List<String> getDistricts(@PathVariable String province) {
        return searchElement.getDistrictsByProvince(province);
    }
}