package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

@Component
public class BasePage {

    @Value("${doctorLogin}")
    protected String doctorLogin;

    @Value("${terapevtLogin}")
    protected String terapevtLogin;

    @Value("${adminpassword}")
    protected String adminpassword;

    @Value("${loginUrl}")
    protected static String loginUrl;

    @Value("${journalPatientUrl}")
    protected String journalPatientUrl;

    @Value("${appointmentRenewalUrl}")
    protected String appointmentRenewalUrl;

    @Autowired
    @Qualifier("timeout")
    protected Integer timeout;

    protected Actions actionBuilder() {
        return new Actions(getAndCheckWebDriver());
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds());
    }

    protected void isLoaded() {
        ExpectedCondition expectedCondition = driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .toString()
                .equals("complete");
        getWait().until(expectedCondition);
    }

    protected BasePage shouldLocate(String location) {
        try {
            new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                    .until(ExpectedConditions.urlContains(location));
        } catch (TimeoutException ex) {
            throw new NotFoundException("URL текущей страницы не опознан: " + location, ex);
        }
        return this;
    }

    protected BasePage checkVisibilityElement(SelenideElement element) {
        new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    protected BasePage checkVisibilityElement(List<SelenideElement> elements) {
        elements.forEach(f -> {
            new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                    .until(ExpectedConditions.visibilityOf(f));
        });

        return this;
    }

    protected BasePage checkInvisibilityElement(SelenideElement element) {
        new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.invisibilityOf(element));
        return this;
    }

    protected BasePage checkVisibilityElementToBeClickable(SelenideElement element) {
        new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.visibilityOf(element));
        new WebDriverWait(getAndCheckWebDriver(), Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }



    protected BasePage clickWhenReady(SelenideElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    protected BasePage clickWhenVisibility(SelenideElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
        return this;
    }


    protected BasePage whenReadyTypeIn(SelenideElement element, String text) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
        return this;
    }

    @SneakyThrows
    protected BasePage typeAndSelectChooseList(SelenideElement input, String text, SelenideElement choose) {
        getWait().until(ExpectedConditions.elementToBeClickable(input));
        input.sendKeys(text);
        clickWhenVisibility(choose);
        return this;
    }


    @SneakyThrows
    protected void waitElementByConditionOptions(Boolean flag) {
        int count = 0;
        do {
            count++;
            Thread.sleep(1000);
        } while (flag && count < timeout);
    }
}
