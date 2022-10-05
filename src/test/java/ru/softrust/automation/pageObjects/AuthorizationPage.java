package ru.softrust.automation.pageObjects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationPage extends BasePage {

    @FindBy(name = "organisationInput")
    private WebElement organisation;

    @FindBy(xpath = "//span[@class = 'mat-option-text']")
    private WebElement overlay;

    @FindBy(name = "loginInput")
    private WebElement loginInput;

    @FindBy(name = "passwordInput")
    private WebElement passwordInput;

    @FindBy(className = "login-form__button")
    private WebElement loginButton;

    @FindBy(className = "version-info-number")
    private WebElement versionNumber;

    @FindBy(className = "authorization-header")
    private WebElement authHeader;

    public AuthorizationPage() {

    }

    public AuthorizationPage whenOpen() {
        isLoaded();
        PageFactory.initElements(driver, this);
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
