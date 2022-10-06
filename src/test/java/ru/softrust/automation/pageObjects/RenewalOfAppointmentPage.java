package ru.softrust.automation.pageObjects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

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

    @FindBy(xpath = "//div[@id='appointmentRecipe']")
    private List<WebElement> appointmentRecipeBlockList;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private WebElement appointmentAddBlock;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private List<WebElement> appointmentAddBlockList;

    @FindBy(xpath = "//div[@id='appointmentForm']//form//input")
    private List<WebElement> appointmentAddBlockDisabledParameters;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private WebElement appointmentAddBlockDisabledFirstParameter;

    @FindBy(xpath = "//div[@id='appointmentRecipe']//div[contains(@class, 'disabled-element')]")
    private WebElement appointmentRecipeBlockDisabledAddButton;

    @FindBy(xpath = "//button[contains(@class, 'blue-square-clear')]")
    private WebElement closeRequestButton;

    @FindBy(xpath = "//div[contains(@class, 'mat-select-value')]/span")
    private WebElement countPaginationButton;

    @FindBy(xpath = "//span[@class = 'mat-option-text']")
    private List<WebElement> countPaginationList;

    @FindBy(xpath = "//table//tr[contains(@class, 'example-element')]")
    private List<WebElement> rowTableRenewalOfAppointment;


    @FindBy(xpath = "//div[@class='no-data-to-display substrate-block divide-border-bottom ng-star-inserted']")
    private List<WebElement> notRecordElement;

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

    @SneakyThrows
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
                throw new Exception("Некорректно значение параметра Статус: " + status);
        }
        return this;
    }

    public RenewalOfAppointmentPage checkMainFilterStatus(String status) {
        assertEquals("Фильтр статуса " + status + " не выбран, или указан некоррректный фильтр", mainStatusFilterChecker.getText(), status);
        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage fullCheckFilterStatuses(String status) {
        checkVisibilityElementToBeClickable(firstGridLine);
        int count = 0;
        waitElementByConditionOptions(!gridStatusFilterChecker.get(0).getText().equals(status));
        if ((status.equals("Ожидает")) || (status.equals("Одобрен")) || (status.equals("Отклонен"))) {
            for (WebElement statusGrid : gridStatusFilterChecker) {
                assertEquals("Фильтр статуса " + status + " в таблице не соответствует выбранному", statusGrid.getText(), status);
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


    public RenewalOfAppointmentPage selectValuePagination(String count) {
        clickWhenReady(countPaginationButton);
        checkVisibilityElement(countPaginationList);
        WebElement webElement = countPaginationList.stream().filter(f -> f.getText().equals(count)).findFirst().orElse(null);
        assertNotNull("Элемент в списке со значением " + count + " не найден", webElement);
        clickWhenReady(webElement);
        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage checkSizeRecordTableRenewal(String count) {
        int k = 0;
        waitElementByConditionOptions(notRecordElement.size() > 0);
        assertTrue("Количество строк в пагинации не равно: " + count, rowTableRenewalOfAppointment.size() <= Integer.parseInt(count));
        return this;
    }

    public RenewalOfAppointmentPage checkDisabledFieldsOfAppointmentAddBlock() {
        checkVisibilityElementToBeClickable(appointmentAddBlockDisabledFirstParameter);
        for (WebElement element : appointmentAddBlockDisabledParameters) {
            assertFalse("Элемент" + element + "активен, хоть и не должен быть таковым", element.isEnabled());
        }
        return this;
    }

    public RenewalOfAppointmentPage checkDisabledAddRecipeButtonOnRecipeBlock() {
        checkVisibilityElementToBeClickable(appointmentRecipeBlockDisabledAddButton);
        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage closeRequest() {
        clickWhenReady(closeRequestButton);
        PageFactory.initElements(driver, this);
        waitElementByConditionOptions(appointmentAddBlockList.size() != 0);
        waitElementByConditionOptions(appointmentRecipeBlockList.size() != 0);
        return this;
    }
}