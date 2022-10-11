package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import static com.codeborne.selenide.Selenide.$;

@Slf4j
@Component
public class AuthorizationPage extends BasePage {

    @FindBy(name = "organisationInput")
    private SelenideElement organisation;

    @FindBy(xpath = "//span[@class = 'mat-option-text']")
    private SelenideElement overlay;

    @FindBy(name = "loginInput")
    private SelenideElement loginInput;

    @FindBy(name = "passwordInput")
    private SelenideElement passwordInput;

    @FindBy(className = "login-form__button")
    private SelenideElement loginButton;

    @FindBy(className = "version-info-number")
    private SelenideElement versionNumber;

    @FindBy(className = "authorization-header")
    private SelenideElement authHeader;

    public AuthorizationPage() {

    }

    public AuthorizationPage whenOpen() {
        isLoaded();
        //PageFactory.initElements(driver, this);
        return this;
    }

    public AuthorizationPage openAuth() {
        driver.get(loginUrl);
        return whenOpen();
    }

    public AuthorizationPage authenticationInSystem(String organisationAuth) {
        log.info("Авторизация с " + doctorLogin + " логином" + " и " + adminpassword + " паролем");
        whenReadyTypeIn(loginInput, doctorLogin)
                .whenReadyTypeIn(passwordInput, adminpassword)
                .typeAndSelectChooseList(organisation, organisationAuth, overlay)
                .clickWhenReady(loginButton)
                .shouldLocate(journalPatientUrl);
        return this;
    }

}
