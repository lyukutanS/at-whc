package ru.softrust.automation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class RenewalOfAppointmentPage extends BasePage {

    @FindBy(xpath = "//h1[@class = 'base-header']")
    private WebElement baseHeader;

    @FindBy(xpath = "//mat-radio-button")
    private List<WebElement> statusFilterButtons;

    @FindBy(xpath = "//mat-radio-button[@class='mat-radio-button mat-accent mat-radio-checked']//div[@class='mat-radio-label-content']")
    private WebElement mainStatusFilterChecker;

    public RenewalOfAppointmentPage() {
    }

    public RenewalOfAppointmentPage whenOpen() {
        isLoaded();
        shouldLocate(appointmentRenewalUrl);
        PageFactory.initElements(driver, this);
        return this;
    }

    public RenewalOfAppointmentPage checkMoveRenewalOfAppointmentPage(String item) {
        assertTrue("Заголовок страницы не соответствует ожидаемому", baseHeader.getText().equalsIgnoreCase(item));
        return this;
    }

    public RenewalOfAppointmentPage setFilterStatus(String status) {
        switch (status) {
            case "Ожидает":
                clickWhenReady(statusFilterButtons.get(0));
                break;
            case "Одобрен":
                clickWhenReady(statusFilterButtons.get(1));
                break;
            case "Отклонен":
                clickWhenReady(statusFilterButtons.get(2));
                break;
            case "Все":
                clickWhenReady(statusFilterButtons.get(3));
                break;
            default:
                throw new NullPointerException("Некорректно значение параметра Статус");
        }
        return this;
    }

    public RenewalOfAppointmentPage сheckMainFilterStatus(String status) {
        Assert.assertEquals(getTextWhenReady(mainStatusFilterChecker), status);

        return this;
    }

    public RenewalOfAppointmentPage fullCheckFilterStatuses(String status) {


        return this;
    }


}
