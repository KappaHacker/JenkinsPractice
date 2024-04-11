package org.example.data;

public class RegistrationModel {

    public final String name;
    public final String lastName;
    public final String email;
    public final String gender;
    public final String phone;
    public final int yearOfBirth;
    public final String monthOfBirth;
    public final String dayOfBirth;
    public final String[] subjects;
    public final String[] hobbies;
    public final String picture;
    public final String address;
    public final String state;
    public final String city;

    public RegistrationModel(String name, String lastName, String email, String gender, String phone, int yearOfBirth,
                             String monthOfBirth, String dayOfBirth, String[] subjects, String[] hobbies, String picture,
                             String address, String state, String city) {

        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.subjects = subjects;
        this.hobbies = hobbies;
        this.picture = picture;
        this.address = address;
        this.state = state;
        this.city = city;
    }



}