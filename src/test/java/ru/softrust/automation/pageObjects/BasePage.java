package ru.softrust.automation.pageObjects;

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

@Component
public class BasePage {

    @Autowired
    @Qualifier("webdriver")
    protected WebDriver driver;

    @Value("${doctorLogin}")
    protected String doctorLogin;

    @Value("${terapevtLogin}")
    protected String terapevtLogin;

    @Value("${adminpassword}")
    protected String adminpassword;

    @Value("${loginUrl}")
    protected String loginUrl;

    @Value("${journalPatientUrl}")
    protected String journalPatientUrl;

    @Value("${appointmentRenewalUrl}")
    protected String appointmentRenewalUrl;

    @Autowired
    @Qualifier("timeout")
    protected Integer timeout;

    protected Actions actionBuilder() {
        return new Actions(driver);
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(driver, timeout);
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
            new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                    .until(ExpectedConditions.urlContains(location));
        } catch (TimeoutException ex) {
            throw new NotFoundException("URL текущей страницы не опознан: " + location, ex);
        }
        return this;
    }

    protected BasePage checkVisibilityElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    protected BasePage checkVisibilityElement(List<WebElement> elements) {
        elements.forEach(f -> {
            new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                    .until(ExpectedConditions.visibilityOf(f));
        });

        return this;
    }

    protected BasePage checkInvisibilityElement(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.invisibilityOf(element));
        return this;
    }

    protected BasePage checkVisibilityElementToBeClickable(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.visibilityOf(element));
        new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    protected BasePage checkVisibilityElementIsDisabled(WebElement element) {
        Assert.assertFalse("Элемент '" + element.toString() +
                        "' является доступным, но не должен!",
                new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                        .until(ExpectedConditions.visibilityOf(element)).isEnabled());
        return this;
    }

    protected BasePage moveTo(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        actionBuilder().moveToElement(element).build().perform();
        return this;
    }

    protected BasePage clickWhenReady(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    protected BasePage clickWhenVisibility(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
        element.click();
        return this;
    }

    protected BasePage doubleClickWhenReady(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        actionBuilder().doubleClick(element).build().perform();
        return this;
    }

    protected List<WebElement> getElementsWhenReady(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }

    protected BasePage whenReadyTypeIn(WebElement element, String text) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
        return this;
    }

    @SneakyThrows
    protected BasePage typeAndSelectChooseList(WebElement input, String text, WebElement choose) {
        getWait().until(ExpectedConditions.elementToBeClickable(input));
        input.sendKeys(text);
        clickWhenVisibility(choose);
        return this;
    }

    protected String getTextWhenReady(WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout).getSeconds())
                .until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    @SneakyThrows
    protected void waitElementByConditionOptions(Boolean flag){
        int count = 0;
        do {
            count++;
            Thread.sleep(1000);
            PageFactory.initElements(driver, this);
        } while (flag && count < timeout);
    }
}
