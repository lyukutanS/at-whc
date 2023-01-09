package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class HeaderPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'text-information')]")
    private SelenideElement breadCrumbs;

    @FindBy(xpath = "//img[contains(@class, 'logo')]")
    private SelenideElement logoSofTrust;


    public HeaderPage checkBreadCrumbs(String item) {
        assertTrue("Модуль " + item + " не отображается в хэдере (хлебные крошки = " + breadCrumbs.getText() + ")",
                breadCrumbs.getText().contains(item));
        return this;
    }

    public HeaderPage clickLogo() {
        clickWhenReady(logoSofTrust);
        isLoaded();
        return this;
    }
}
