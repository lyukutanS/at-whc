package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.assertTrue;

@Slf4j
@Component
public class JournalPatientPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'patient-journal-header']/h1")
    private SelenideElement baseHeader;

    @FindBy(xpath = "//div[contains(@class, 'statistic-header')]")
    private List<SelenideElement> statisticHeader;

    public JournalPatientPage() {
    }


    @SneakyThrows
    public JournalPatientPage smartPageLoadAwaitJournalPatientPage() {
        int count = 0;
        waitElementByConditionOptions(statisticHeader.size() == 0);
        assertTrue(statisticHeader.size() > 0);
        return this;
    }

    public JournalPatientPage checkMoveJournalPatientPage(String item) {
        assertTrue("Заголовок страницы не соответствует ожидаемому",
                baseHeader.getText().equalsIgnoreCase(item));
        return this;
    }
}
