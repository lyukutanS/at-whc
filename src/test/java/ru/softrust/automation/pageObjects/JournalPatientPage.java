package ru.softrust.automation.pageObjects;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class JournalPatientPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'patient-journal-header']/h1")
    private WebElement baseHeader;

    @FindBy(xpath = "//div[contains(@class, 'statistic-header')]")
    private List<WebElement> statisticHeader;

    public JournalPatientPage() {
    }

    public JournalPatientPage whenOpen() {
        isLoaded();
        PageFactory.initElements(driver, this);
        return this;
    }

    @SneakyThrows
    public JournalPatientPage smartPageLoadAwaitJournalPatientPage() {
        int count = 0;
        waitElementByConditionOptions(statisticHeader.size() == 0);
        assertTrue(statisticHeader.size() > 0);
        return this;
    }

    public JournalPatientPage checkMoveJournalPatientPage(String item) {
        assertTrue("Заголовок страницы не соответствует ожидаемому", baseHeader.getText().equalsIgnoreCase(item));
        return this;
    }
}
