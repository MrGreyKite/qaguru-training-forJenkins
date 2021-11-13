package ru.rbagmet.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.rbagmet.TestBase;
import ru.rbagmet.TestData;
import ru.rbagmet.pages.RegistrationFormPage;
import ru.rbagmet.pages.components.Modal;

import static ru.rbagmet.Attachments.addScreenshotAs;

@Tag("demoqa")
public class AutomationPracticeFormTest extends TestBase {

    @Test
    @DisplayName("Тест на заполнение формы целиком")
    @Owner("Rudolf Bagmet")
    void formFilled() {

        TestData data = new TestData();
        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        Modal registrationModal = new Modal();

        registrationFormPage.openPage();
        addScreenshotAs("After opening page");
        registrationFormPage.calendar.setDate(data.getDateOfBirth());
        addScreenshotAs("After setting a date");
        registrationFormPage.uploadPicture("src/test/resources/img/" + data.getPictureName());
        addScreenshotAs("After picture upload");
        registrationFormPage.insertFirstName(data.getFirstName())
                .insertLastName(data.getLastName())
                .insertEmail(data.getEmail())
                .chooseGender(data.getGender())
                .insertPhoneNumber(data.getUserNumber())
                .chooseSubject(data.getSubject1())
                .chooseHobby(data.getHobby1())
                .chooseHobby(data.getHobby2())
                .insertAddress(data.getCurrentAddress())
                .chooseState(data.getState())
                .chooseCity(data.getCity());
        registrationFormPage.submitForm();
        addScreenshotAs("After submitting the form");

        registrationModal.checkModalHeaderTitle(registrationModal.getRegistrationModalTitle());

        registrationFormPage.massCheckResults(data);
        addScreenshotAs("After results check");

    }
}
