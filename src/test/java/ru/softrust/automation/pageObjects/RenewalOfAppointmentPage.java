package ru.softrust.automation.pageObjects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class RenewalOfAppointmentPage extends BasePage {

    @FindBy(xpath = "//h1[@class = 'base-header']")
    private WebElement baseHeader;

    @FindBy(xpath = "//mat-radio-button")
    private List<WebElement> statusFilterButtons;

    @FindBy(xpath = "//mat-radio-button[contains(@class, 'mat-radio-checked')]")
    private WebElement mainStatusFilterChecker;

    @FindBy(xpath = "//td[contains(@class, 'cdk-cell cdk-column-state')]")
    private List<WebElement> gridStatusFilterChecker;

    @FindBy(xpath = "//td[contains(@class, 'cdk-cell cdk-column-state')]")
    private WebElement gridFirstStatusFilterChecker;

    @FindBy(xpath = "//tr[contains(@class, 'mat-row')]")
    private WebElement firstGridLine;

    @FindBy(xpath = "//div[@id='appointmentRecipe']")
    private WebElement appointmentRecipeBlock;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private WebElement appointmentAddBlock;


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
                throw new NullPointerException("Некорректно значение параметра Статус: " + status);
        }
        return this;
    }

    public RenewalOfAppointmentPage checkMainFilterStatus(String status) {
        assertEquals("Фильтр статуса " + status +
                " не выбран, или указан некоррректный фильтр", mainStatusFilterChecker.getText(), status);

        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage fullCheckFilterStatuses(String status) {
        checkVisibilityElementToBeClickable(firstGridLine);
        int count = 0;
        do {
            count++;
            Thread.sleep(1000);
            PageFactory.initElements(driver, this);
        } while (!gridStatusFilterChecker.get(0).getText().equals(status) && count < timeout);
        if ((status.equals("Ожидает")) || (status.equals("Одобрен")) || (status.equals("Отклонен"))) {
            for (WebElement statusGrid :
                    gridStatusFilterChecker) {
                assertEquals("Фильтр статуса " + status +
                        " в таблице не соответствует выбранному", statusGrid.getText(), status);
            }
        } else {
            throw new NullPointerException("Некорректно значение параметра Статус: " + status);
        }
        return this;
    }

    public RenewalOfAppointmentPage checkAppointmentAddAndRecipeBlocks() {
        clickWhenReady(firstGridLine);

        checkVisibilityElement(appointmentAddBlock);
        checkVisibilityElement(appointmentRecipeBlock);

        return this;
    }

}
