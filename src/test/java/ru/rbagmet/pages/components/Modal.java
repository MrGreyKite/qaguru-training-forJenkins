package ru.rbagmet.pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Modal {

    private final String registrationModalTitle = "Thanks for submitting the form";

    @Step("Проверить заголовок модального окна")
    public void checkModalHeaderTitle(String title) {
        $(".modal-title").shouldHave(text(title));
    }

    public void checkModalBodyText(String text) {
        $(".modal-body").shouldHave(text(text));
    }

    public void checkIfModalBodyHaveTable() {
        $(".table-responsive").shouldBe(visible);
    }

    public void closeLargeModalWithButton() {
        $("#closeLargeModal").click();
    }

    public void closeSmallModalWithButton() {
        $("#closeSmallModal").click();
    }

    public String getRegistrationModalTitle() {
        return registrationModalTitle;
    }
}
