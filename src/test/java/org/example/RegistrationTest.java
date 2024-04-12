package org.example;



import com.codeborne.selenide.SelenideElement;
import org.aeonbits.owner.ConfigFactory;
import org.example.config.ProjConfig;
import org.example.data.RegistrationData;
import org.example.data.RegistrationModel;
import org.example.helpers.Attach;
import org.example.pages.RegistrationPage;
import org.example.pages.components.ModalWindowComponent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.appear;
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

    @Tag("steam_tests")
    @Test
    void minimalRegistrationTest() {
        ProjConfig projConfig = ConfigFactory.create(ProjConfig.class);
        registrationPage.openPage();
        SelenideElement bannerRoot = $(".fc-consent-root");
        if (bannerRoot.isDisplayed()) {
            bannerRoot.$(byText("Consent")).click();
        }
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        registrationPage.setFirstName(projConfig.firstName());
        registrationPage.setLastName(projConfig.lastName());
        registrationPage.setGender("Other");
        registrationPage.setUserNumber("1234567890");
        registrationPage.submit();

        $(".modal-dialog").should(appear);
        modalWindowComponent.checkMinStudentFields(new RegistrationModel( projConfig.firstName(), projConfig.lastName(), "Other", "1234567890"));

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