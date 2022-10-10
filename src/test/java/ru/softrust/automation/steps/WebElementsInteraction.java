package ru.softrust.automation.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.softrust.automation.pageObjects.*;
import ru.softrust.automation.utils.AllureAttachment;
import ru.softrust.automation.utils.DriverManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@CucumberContextConfiguration
public class WebElementsInteraction {

    @Autowired
    private AllureAttachment allure;

    @Autowired
    private DriverManager driverManager;

    @Qualifier("scenario")
    private Scenario scenario;
    @Autowired
    private AuthorizationPage authorisationPageObject;

    @Autowired
    private MenuPage menuPage;

    @Autowired
    private RenewalOfAppointmentPage renewalOfAppointmentPage;

    @Autowired
    private JournalPatientPage journalPatientPage;

    @Autowired
    private HeaderPage headerPage;

    @Дано("^переходим на страницу Авторизация$")
    public void goToAuthorisationPage() {
        authorisationPageObject.whenOpen()
                .openAuth();
    }

    @Когда("^авторизуемся в системе с огранизацией \"(.*)\"$")
    public void authenticationInSystem(String organisationAuth) {
        authorisationPageObject.authenticationInSystem(organisationAuth);
    }

    @И("^переходим в Основное меню и выбираем пункт \"(.*)\"$")
    public void moveToMainMenu(String item) {
        menuPage.whenOpen()
                .chooseFromListInLeftMenu(item);
    }

    @И("^проверяем, что мы находимся на странице \"(.*)\"$")
    public void checkMovePage(String item) {
        switch (item) {
            case "Продление назначений":
                renewalOfAppointmentPage.whenOpen()
                        .checkMoveRenewalOfAppointmentPage(item);
                headerPage.whenOpen()
                        .checkBreadCrumbs(item);
                break;
            case "Журнал пациентов":
                journalPatientPage.whenOpen()
                        .smartPageLoadAwaitJournalPatientPage()
                        .checkMoveJournalPatientPage(item);
                headerPage.whenOpen()
                        .checkBreadCrumbs(item);
                break;
            default:
                throw new NullPointerException("Переданный пункт меню - не существует");
        }
    }

    @И("^нажимаем на логотип SofTrust$")
    public void clickLogoApp() {
        headerPage.whenOpen()
                .clickLogo();
    }

    @И("^устанавливаем фильтр по полю Статус со значением \"(.*)\"$")
    public void setFilterStatus(String status) {
        renewalOfAppointmentPage.whenOpen()
                .setFilterStatus(status)
                .checkMainFilterStatus(status)
                .fullCheckFilterStatuses(status);
    }

    @И("^открываем карточку-запрос пациента и проверяем блок-назначение и блок-рецепт$")
    public void openPatientCardAndCheckAppointmentAddAndRecipeBlocks() {
        renewalOfAppointmentPage.whenOpen()
                .checkAppointmentAddAndRecipeBlocks();
    }

    @И("^выбираем значение пагинации \"(.*)\"$")
    public void selectValuePagination(String count) {
        renewalOfAppointmentPage.whenOpen()
                .selectValuePagination(count)
                .checkSizeRecordTableRenewal(count);
    }

    @И("^проверяем, что поля блока Назначение недоступны для редактирования$")
    public void checkDisabledFieldsOfAppointmentAddBlock() {
        renewalOfAppointmentPage.whenOpen()
                .checkDisabledFieldsOfAppointmentAddBlock();
    }

    @И("^проверяем, что в блоке \"(.*)\" кнопка \"(.*)\" задизейблена$")
    public void checkDisabledButtonOnBlocks(String blockName, String buttonName) {
        renewalOfAppointmentPage.whenOpen();
        if (blockName.equals("Рецепты") && buttonName.equals("Добавить")) {
            renewalOfAppointmentPage.checkDisabledAddRecipeButtonOnRecipeBlock();
        }
    }

    @И("^сворачиваем заявку$")
    public void closeRequest() {
        renewalOfAppointmentPage.whenOpen()
                .closeRequest();
    }
}
