// File: Doctor.java
package com.nadimnesar.drappointment.model;

public class Doctor {
    private String name;
    private String specialty;
    private String address;
    private String clinic;
    private String imageUrl; // مثلاً /img/doctors/dr1.jpg

    public Doctor(String name, String specialty, String address, String clinic, String imageUrl) {
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.clinic = clinic;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getAddress() { return address; }
    public String getClinic() { return clinic; }
    public String getImageUrl() { return imageUrl; }
}