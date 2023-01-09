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

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

@Slf4j
@Component
public class DriverManager {

    @Autowired
    private String browser;

    public WebDriver getDriver() {
        log.info("Открытие браузера " + browser);
        return getAndCheckWebDriver();
    }

    @Bean(value = "webdriver")
    public String getBrowserLogs() {
        LogEntries logEntries = getDriver().manage().logs().get(LogType.BROWSER);
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
