package ru.softrust.automation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class JournalPatientPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'patient-journal-header']/h1")
    private WebElement baseHeader;

    public JournalPatientPage() {
    }

    public JournalPatientPage whenOpen() {
        isLoaded();
        PageFactory.initElements(driver, this);
        return this;
    }

    public JournalPatientPage checkMoveJournalPatientPage(String item) {
        assertTrue("Заголовок страницы не соответствует ожидаемому", baseHeader.getText().equalsIgnoreCase(item));
        return this;
    }
}
