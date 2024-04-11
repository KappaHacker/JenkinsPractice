package org.example;



import org.example.data.RegistrationData;
import org.example.data.RegistrationModel;
import org.example.helpers.Attach;
import org.example.pages.RegistrationPage;
import org.example.pages.components.ModalWindowComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class RegistrationTest extends BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    ModalWindowComponent modalWindowComponent = new ModalWindowComponent();

    @BeforeEach
    public void beforeEach() {
        step("Open form", () -> {
            registrationPage.openPage();
        });
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }


    @Test
    void successfulRegisterFullDataTest() {
        RegistrationModel registrationFullData = RegistrationData.generateFullRegistrationData();
        step("Fill form", () -> {
            registrationPage.fillAllStudentFields(registrationFullData);
            registrationPage.submit();
        });
        step("Verify results", () -> {
            modalWindowComponent.checkAllStudentFields(registrationFullData);
        });

    }

    @Test
    void successfulRegisterMinDataTest() {
        RegistrationModel registrationMinData = RegistrationData.generateMinimumRegistrationData();
        step("Fill form", () -> {
            registrationPage.fillMinStudentFields(registrationMinData);
            registrationPage.submit();
        });
        step("Verify results", () -> {
            modalWindowComponent.checkMinStudentFields(registrationMinData);

        });
    }
}