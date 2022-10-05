package ru.softrust.automation.steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.softrust.automation.pages.AuthenticationPage;
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
    private AuthenticationPage authenticationPageObject;

    @Дано("^переходим на страницу Авторизация$")
    public void goToAuthorisationPage() {
        authenticationPageObject.open();
    }

    @И("^переходим в Основное меню$")
    public void moveToMainMenu() {

    }

}
