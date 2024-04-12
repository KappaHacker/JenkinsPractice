package org.example.pages;

import com.codeborne.selenide.SelenideElement;
import org.example.data.RegistrationModel;
import org.example.pages.components.CalendarComponent;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),

            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            submitButton = $("#submit"),
                    bannerRoot = $(".fc-consent-root");



    CalendarComponent calendarComponent = new CalendarComponent();


    public void openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#RightSide_Advertisement').remove()");
        if (bannerRoot.isDisplayed()) {
            bannerRoot.$(byText("Consent")).click();
        }
    }

    public void setFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public void setLastName(String value) {
        lastNameInput.setValue(value);

    }

    public void setEmail(String value) {
        userEmailInput.setValue(value);
    }

    public void setGender(String value) {
        genderWrapper.$(byText(value)).click();
    }

    public RegistrationPage  setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, int year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String[] value) {
        for (String subject : value) {
            subjectsInput.setValue(subject).pressEnter();
        }

        return this;
    }

    public RegistrationPage setHobbies(String[] hobbiesList) {
        for (String hobby : hobbiesList) {
            $(byText(hobby)).parent().click();
        }
        return this;
    }

    public RegistrationPage setPicture(String value) {
        pictureInput.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.click();
        $("#stateCity-wrapper").$(byText(value)).click();

        return this;
    }

    public void setCity(String value) {
        cityInput.click();
        $("#stateCity-wrapper").$(byText(value)).click();
    }

    public void submit() {
        submitButton.click();
    }


    public void fillAllStudentFields(RegistrationModel registrationModel) {
        setFirstName(registrationModel.name);
        setLastName(registrationModel.lastName);
        setEmail(registrationModel.email);
        setGender(registrationModel.gender);
        setUserNumber(registrationModel.phone).
                setDateOfBirth(registrationModel.dayOfBirth, registrationModel.monthOfBirth, registrationModel.yearOfBirth).
                setSubjects(registrationModel.subjects).
                setHobbies(registrationModel.hobbies).
                setPicture(registrationModel.picture).
                setAddress(registrationModel.address).
                setState(registrationModel.state).
                setCity(registrationModel.city);
    }

    public void fillMinStudentFields(RegistrationModel registrationModel) {
        setFirstName(registrationModel.name);
        setLastName(registrationModel.lastName);
        setGender(registrationModel.gender);
        setUserNumber(registrationModel.phone);
               // setDateOfBirth(registrationModel.dayOfBirth, registrationModel.monthOfBirth, registrationModel.yearOfBirth);
    }

}
