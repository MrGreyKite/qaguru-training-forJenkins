package ru.rbagmet.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ru.rbagmet.pages.components.Calendar;
import ru.rbagmet.pages.components.Modal;
import ru.rbagmet.TestData;

import java.io.File;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    private final String pageTitle = "Student Registration Form";

    private final SelenideElement firstNameField = $("#firstName");
    private final SelenideElement lastNameField = $("#lastName");
    private final SelenideElement emailField = $("#userEmail");
    private final SelenideElement phoneNumberField = $("#userNumber");
    private final SelenideElement addressField = $("#currentAddress");
    private final SelenideElement subjectField = $("#subjectsInput");
    private final SelenideElement pictureUploader = $("#uploadPicture");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement stateSelector = $("#state");
    private final SelenideElement citySelector = $("#city");

    private final SelenideElement formTitle = $(".practice-form-wrapper");

    public Calendar calendar = new Calendar();

    @Step("Открыть страницу")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        formTitle.shouldBe(visible);
        formTitle.shouldHave(text(pageTitle));
        return this;
    }

    @Step("Загрузить картинку")
    public void uploadPicture(String pathname) {
        pictureUploader.uploadFile(new File(pathname));
    }

    @Step("Ввести имя")
    public RegistrationFormPage insertFirstName(String value){
        firstNameField.setValue(value);
        return this;
    }

    @Step("Ввести фамилию")
    public RegistrationFormPage insertLastName(String value) {
        lastNameField.setValue(value);
        return this;
    }

    @Step("Ввести email")
    public RegistrationFormPage insertEmail(String value) {
        emailField.setValue(value);
        return this;
    }

    @Step("Выбрать пол")
    public RegistrationFormPage chooseGender(String value) {
        $$("div.custom-radio label").findBy(text(value)).click();
        return this;
    }

    @Step("Ввести номер телефона")
    public RegistrationFormPage insertPhoneNumber(String value) {
        phoneNumberField.setValue(value);
        return this;
    }

    @Step("Выбрать предмет")
    public RegistrationFormPage chooseSubject(String value) {
        subjectField.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать хобби")
    public RegistrationFormPage chooseHobby(String value) {
        $$("div.custom-checkbox label").findBy(text(value)).click();
        return this;
    }

    @Step("Ввести адрес")
    public RegistrationFormPage insertAddress(String value) {
        addressField.setValue(value);
        return this;
    }

    @Step("Выбрать штат")
    public RegistrationFormPage chooseState(String value) {
        stateSelector.click();
        $("#react-select-3-input").setValue(value).pressEnter();
        return this;
    }

    @Step("Выбрать город")
    public RegistrationFormPage chooseCity(String value) {
        citySelector.click();
        $("#react-select-4-input").setValue(value).pressEnter();
        return this;
    }

    @Step("Отправить форму")
    public Modal submitForm() {
        submitButton.scrollIntoView(true).click();
        return new Modal();
    }

    @Step("Проверить результат для {key} и {value}")
    public void checkResults(String key, String value) {
        $$("div.table-responsive td").findBy(text(key))
                .sibling(0).shouldHave(text(value));
    }

    @Step("Проверить результаты")
    public void massCheckResults(TestData data) {

        HashMap<String, String> registrationResults = new HashMap<>();
        registrationResults.put("Student Name", data.getFirstName() + " " + data.getLastName());
        registrationResults.put("Student Email", data.getEmail());
        registrationResults.put("Gender", data.getGender());
        registrationResults.put("Mobile", data.getUserNumber());
        registrationResults.put("Date of Birth", data.getDateOfBirthFormatted());
        registrationResults.put("Subjects", data.getSubject1());
        registrationResults.put("Hobbies", data.getHobby1());
        registrationResults.put("Picture", data.getPictureName());
        registrationResults.put("Address", data.getCurrentAddress());
        registrationResults.put("State and City", data.getState() + " " + data.getCity());

        registrationResults.forEach((label, value) ->
                $$("div.table-responsive td").findBy(text(label))
                        .sibling(0).shouldHave(text(value))
                                     );
    }
}
