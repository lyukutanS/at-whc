package ru.softrust.automation.pageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@Component
public class MenuPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'menu']/div[contains(@class, 'btn')]")
    private SelenideElement leftMenuButton;

    @FindBy(xpath = "//div[contains(@id, 'ui-id')]/ul/li")
    private List<SelenideElement> leftMenuItemList;

    public MenuPage() {
    }

    public MenuPage whenOpen() {
        isLoaded();
        //PageFactory.initElements(driver, this);
        return this;
    }

    public MenuPage chooseFromListInLeftMenu(String item) {
        isLoaded();
        if (Objects.requireNonNull(leftMenuButton.getAttribute("class")).contains("--close")) {
            clickWhenReady(leftMenuButton);
        }
        SelenideElement SelenideElement = leftMenuItemList
                .stream()
                .filter(f -> f.getText().equalsIgnoreCase(item))
                .findFirst()
                .orElse(null);
        assertThat("Элемент с текстом \"" + item + "\" в боковом меню - не найден", SelenideElement != null);
        clickWhenVisibility(SelenideElement);
        return this;
    }

}
