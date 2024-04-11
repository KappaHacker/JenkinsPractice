package org.example;


import org.aeonbits.owner.ConfigFactory;
import org.example.config.ProjectConfig;
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

    @Tag("simple")
    @Test
    void fillPracticeFormTest() {
        ProjectConfig projectConfig = ConfigFactory.create(ProjectConfig.class);

        step("Open form", () -> {
            open("/automation-practice-form");
            $(".fc-button-label").click();
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('footer').remove()");
        });
        step("Fill form", () -> {
            $("#firstName").setValue(projectConfig.firstName());
            $("#lastName").setValue(projectConfig.lastName());
            $("#userEmail").setValue("ivanov88@mail.ru");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("9119991919");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(7);
            $(".react-datepicker__year-select").selectOption(88);
            $(".react-datepicker__day--023").click();
            $("#subjectsInput").setValue("Arts").pressEnter();
            $("#hobbiesWrapper").$(byText("Music")).click();
            $("#uploadPicture").uploadFromClasspath("screen.jpg");
            $("#currentAddress").setValue("Test");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("Haryana")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Karnal")).click();
            $("#submit").click();
        });
        step("Verify results", () -> {
            $(".modal-header").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text(projectConfig.firstName()));
            $(".table-responsive").shouldHave(text(projectConfig.lastName()));
            $(".table-responsive").shouldHave(text("ivanov88@mail.ru"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("9119991919"));
            $(".table-responsive").shouldHave(text("23 August,1988"));
            $(".table-responsive").shouldHave(text("Test"));
            $(".table-responsive").shouldHave(text("screen.jpg"));
            $(".table-responsive").shouldHave(text("Test"));
            $(".table-responsive").shouldHave(text("Haryana Karnal"));
        });
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