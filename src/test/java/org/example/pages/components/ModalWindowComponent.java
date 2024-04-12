package org.example.pages.components;

import org.example.data.RegistrationModel;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalWindowComponent {
    public ModalWindowComponent checkSubmitForm (String fieldName, String value) {
        $(".table-responsive table").$(byText(fieldName)).parent().shouldHave(text(value));
        return this;
    }



    public void checkAllStudentFields(RegistrationModel student) {
        checkSubmitForm("Student Name", student.name + " " + student.lastName).
                checkSubmitForm("Student Email", student.email).
                checkSubmitForm("Gender", student.gender).
                checkSubmitForm("Mobile", student.phone).
                checkSubmitForm("Date of Birth",
                        student.dayOfBirth + " " + student.monthOfBirth + "," + student.yearOfBirth).
                checkSubmitForm("Subjects", String.join(", ", student.subjects)).
                checkSubmitForm("Hobbies", String.join(", ", student.hobbies)).
                checkSubmitForm("Picture", student.picture).
                checkSubmitForm("Address", student.address).
                checkSubmitForm("State and City", student.state + " " + student.city);
    }
    public void checkMinStudentFields(RegistrationModel student) {
        checkSubmitForm("Student Name", student.name + " " + student.lastName).
                checkSubmitForm("Gender", student.gender).
                checkSubmitForm("Mobile", student.phone).
                checkSubmitForm("Date of Birth",
                        student.dayOfBirth + " " + student.monthOfBirth + "," + student.yearOfBirth);
    }


}