package ru.softrust.automation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@Component
public class HeaderPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'text-information')]")
    private WebElement breadCrumbs;

    public HeaderPage(){
    }
    public HeaderPage whenOpen() {
        isLoaded();
        PageFactory.initElements(driver, this);
        return this;
    }
    public HeaderPage checkBreadCrumbs(String item) {
        assertThat("", breadCrumbs.getText().contains(item));
        return this;
    }
}
