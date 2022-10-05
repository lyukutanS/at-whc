package ru.softrust.automation.pageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
@Component
public class MenuPage extends BasePage {

    @FindBy(xpath = "//div[@class = 'menu']/div[contains(@class, 'btn')]")
    private WebElement leftMenuButton;

    @FindBy(xpath = "//div[contains(@id, 'ui-id')]/ul/li")
    private List<WebElement> leftMenuItemList;

    public MenuPage(){
    }

    public MenuPage whenOpen() {
        isLoaded();
        PageFactory.initElements(driver, this);
        return this;
    }

    public MenuPage chooseFromListInLeftMenu(String item) {
        isLoaded();
        if (leftMenuButton.getAttribute("class").contains("--close")){
            clickWhenReady(leftMenuButton);
        }
        WebElement webElement = leftMenuItemList
                .stream()
                .filter(f -> f.getText().equalsIgnoreCase(item))
                .findFirst()
                .orElse(null);
        assertThat("Элемент с текстом \"" + item + "\" в боковом меню - не найден", webElement != null);
        clickWhenVisibility(webElement);

        return this;
    }

}
