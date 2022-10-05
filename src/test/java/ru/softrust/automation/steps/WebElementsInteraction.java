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
import ru.softrust.automation.pageObjects.AuthorizationPage;
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

    @Дано("^переходим на страницу Авторизация$")
    public void goToAuthorisationPage() {
        authorisationPageObject.openAuth();
    }

    @Когда("^авторизуемся в системе с огранизацией \"(.*)\"$")
    public void authenticationInSystem(String organisationAuth) {
        authorisationPageObject.authenticationInSystem(organisationAuth);

    }

    @И("^переходим в Основное меню$")
    public void moveToMainMenu() {

    }

}
