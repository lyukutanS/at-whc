package ru.softrust.automation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@Component
public class RenewalOfAppointmentPage extends BasePage {

    @FindBy(xpath = "//h1[@class = 'base-header']")
    private WebElement baseHeader;

    public RenewalOfAppointmentPage() {
    }

    public RenewalOfAppointmentPage whenOpen() {
        isLoaded();
        shouldLocate(appointmentRenewalUrl);
        PageFactory.initElements(driver, this);
        return this;
    }
    public RenewalOfAppointmentPage checkMoveRenewalOfAppointmentPage(String item) {
        assertThat("Заголовок страницы не соответствует ожидаемому", baseHeader.getText().equalsIgnoreCase(item));
        return this;
    }
}
