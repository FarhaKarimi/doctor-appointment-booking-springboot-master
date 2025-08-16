// File: SearchElement.java
package com.nadimnesar.drappointment.service;

import java.util.*;

public class SearchElement {
    private static SearchElement instance;

    // نگهداری شهرستان‌ها بر اساس استان
    private final Map<String, List<String>> provinceDistrictMap = new HashMap<>();

    private final List<String> specialtyList = List.of("قلب", "اطفال", "جراحی", "داخلی", "زنان", "پوست", "عصب و روان");

    private final List<String> divisionList = List.of("تهران", "اصفهان", "شیراز");

    private SearchElement() {
        initializeDistricts();
    }

    private void initializeDistricts() {
        provinceDistrictMap.put("تهران", Arrays.asList(
                "منطقه ۱", "منطقه ۲", "منطقه ۳", "منطقه ۴", "منطقه ۵", "ملارد", "اسلامشهر"
        ));
        provinceDistrictMap.put("اصفهان", Arrays.asList(
                "مرکز اصفهان", "خمینی‌شهر", "فلاورجان", "شهرضا"
        ));
        provinceDistrictMap.put("شیراز", Arrays.asList(
                "مرکز شیراز", "زاهدشهر", "خاوران", "جهرم"
        ));
    }

    public static SearchElement getInstance() {
        if (instance == null) {
            instance = new SearchElement();
        }
        return instance;
    }

    public List<String> getDivisionList() {
        return new ArrayList<>(divisionList); // بازگرداندن کپی ایمن
    }

    public List<String> getSpecialtyList() {
        return new ArrayList<>(specialtyList);
    }

    /**
     * بازگرداندن شهرستان‌های مربوط به یک استان
     */
    public List<String> getDistrictsByProvince(String province) {
        if (province == null || province.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return provinceDistrictMap.getOrDefault(province, Collections.emptyList());
    }
}