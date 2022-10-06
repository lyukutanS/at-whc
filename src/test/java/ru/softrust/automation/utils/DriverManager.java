package ru.softrust.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class DriverManager {

    @Autowired
    private AllureAttachment allureAttachment;

    @Qualifier("webdriver")
    private WebDriver driver;

    @Autowired
    private String browser;

    @Bean(value = "webdriver")
    @Scope("singleton")
    @Step("Инициализация веб-драйвера, подготовка к запуску тестов...")
    public WebDriver getDriver() {
        log.info("Инициализация веб-драйвера, подготовка к запуску тестов...");
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.addArguments("--headless", "--window-size=1920,1200");
                    chromeOptions.setCapability("acceptInsecureCerts", true);
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "internet explorer":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
        log.info("Открытие браузера " + browser);
        driver.manage().window().maximize();
        return driver;
    }

    @Bean(value = "webdriver")
    @Scope("singleton")
    @Step("Завершение работы, закрытие веб-драйвера")
    public void quitDriver() {
        log.info("Завершение работы, закрытие веб-драйвера");
        driver.close();
        driver.quit();
    }

    @Bean(value = "webdriver")
    @Step("Создание скриншота")
    public void takeScreenshotToAllure() {
        allureAttachment.addScreenshot(driver, "Скриншот c ошибкой");
    }

    @Bean(value = "webdriver")
    public String getBrowserLogs() {
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        StringBuilder logs = new StringBuilder();
        for (org.openqa.selenium.logging.LogEntry entry : logEntries) {
            logs.append(new Date(entry.getTimestamp()))
                    .append(" ")
                    .append(entry.getLevel())
                    .append(" ")
                    .append(entry.getMessage());
            logs.append(System.lineSeparator());
        }
        return String.valueOf(logs);
    }
}
