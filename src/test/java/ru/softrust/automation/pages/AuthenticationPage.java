package ru.softrust.automation.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationPage extends BasePage {

    public AuthenticationPage() {
    }

    public AuthenticationPage whenOpen() {
        PageFactory.initElements(driver, this);
        return this;
    }

    public AuthenticationPage open() {
        driver.get(baseUrl);
        return whenOpen();
    }

}
