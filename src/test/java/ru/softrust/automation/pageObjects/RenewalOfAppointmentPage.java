package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Slf4j
@Component
public class RenewalOfAppointmentPage extends BasePage {

    @FindBy(xpath = "//h1[@class = 'base-header']")
    private SelenideElement baseHeader;

    @FindBy(xpath = "//mat-radio-button")
    private List<SelenideElement> statusFilterButtons;

    @FindBy(xpath = "//mat-radio-button[contains(@class, 'mat-radio-checked')]")
    private SelenideElement mainStatusFilterChecker;

    @FindBy(xpath = "//td[contains(@class, 'cdk-cell cdk-column-state')]")
    private List<SelenideElement> gridStatusFilterChecker;

    @FindBy(xpath = "//td[contains(@class, 'cdk-cell cdk-column-state')]")
    private SelenideElement gridFirstStatusFilterChecker;

    @FindBy(xpath = "//tr[contains(@class, 'mat-row')]")
    private SelenideElement firstGridLine;

    @FindBy(xpath = "//div[@id='appointmentRecipe']")
    private SelenideElement appointmentRecipeBlock;

    @FindBy(xpath = "//div[@id='appointmentRecipe']")
    private List<SelenideElement> appointmentRecipeBlockList;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private SelenideElement appointmentAddBlock;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private List<SelenideElement> appointmentAddBlockList;

    @FindBy(xpath = "//div[@id='appointmentForm']//form//input")
    private List<SelenideElement> appointmentAddBlockDisabledParameters;

    @FindBy(xpath = "//div[@class='appointment-add']")
    private SelenideElement appointmentAddBlockDisabledFirstParameter;

    @FindBy(xpath = "//div[@id='appointmentRecipe']//div[contains(@class, 'disabled-element')]")
    private SelenideElement appointmentRecipeBlockDisabledAddButton;

    @FindBy(xpath = "//button[contains(@class, 'blue-square-clear')]")
    private SelenideElement closeRequestButton;

    @FindBy(xpath = "//div[contains(@class, 'mat-select-value')]/span")
    private SelenideElement countPaginationButton;

    @FindBy(xpath = "//span[@class = 'mat-option-text']")
    private List<SelenideElement> countPaginationList;

    @FindBy(xpath = "//table//tr[contains(@class, 'example-element')]")
    private List<SelenideElement> rowTableRenewalOfAppointment;


    @FindBy(xpath = "//div[@class='no-data-to-display substrate-block divide-border-bottom ng-star-inserted']")
    private List<SelenideElement> notRecordElement;


    public RenewalOfAppointmentPage checkMoveRenewalOfAppointmentPage(String item) {
        assertTrue("Заголовок страницы не соответствует ожидаемому",
                baseHeader.getText().equalsIgnoreCase(item));
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
        assertEquals("Фильтр статуса " + status + " не выбран, или указан некоррректный фильтр",
                mainStatusFilterChecker.getText(),
                status);
        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage fullCheckFilterStatuses(String status) {
        checkVisibilityElementToBeClickable(firstGridLine);
        int count = 0;
        waitElementByConditionOptions(!gridStatusFilterChecker.get(0).getText().equals(status));
        if ((status.equals("Ожидает")) || (status.equals("Одобрен")) || (status.equals("Отклонен"))) {
            for (SelenideElement statusGrid : gridStatusFilterChecker) {
                assertEquals("Фильтр статуса " + status + " в таблице не соответствует выбранному",
                        statusGrid.getText(),
                        status);
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
        SelenideElement SelenideElement = countPaginationList.stream()
                .filter(f -> f.getText().equals(count))
                .findFirst()
                .orElse(null);
        assertNotNull("Элемент в списке со значением " + count + " не найден", SelenideElement);
        clickWhenReady(SelenideElement);
        return this;
    }

    @SneakyThrows
    public RenewalOfAppointmentPage checkSizeRecordTableRenewal(String count) {
        int k = 0;
        waitElementByConditionOptions(notRecordElement.size() > 0);
        assertTrue("Количество строк в пагинации не равно: " + count,
                rowTableRenewalOfAppointment.size() <= Integer.parseInt(count));
        return this;
    }

    public RenewalOfAppointmentPage checkDisabledFieldsOfAppointmentAddBlock() {
        checkVisibilityElementToBeClickable(appointmentAddBlockDisabledFirstParameter);
        for (SelenideElement element : appointmentAddBlockDisabledParameters) {
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
        waitElementByConditionOptions(appointmentAddBlockList.size() != 0);
        waitElementByConditionOptions(appointmentRecipeBlockList.size() != 0);
        return this;
    }
}