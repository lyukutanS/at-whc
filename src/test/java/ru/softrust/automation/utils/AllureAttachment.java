package ru.softrust.automation.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class AllureAttachment {
    @Autowired
    DriverManager driverManager;

    @Attachment
    public void addScreenshot(WebDriver driver, String name) {
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Allure.addAttachment(name, FileUtils.openInputStream(tempFile));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            tempFile.delete();
        }
    }

    @Attachment
    public void logs() {
        Allure.addAttachment("Browser log: ", driverManager.getBrowserLogs());
        addScreenshot(driverManager.getDriver(), "Screenshot-" + new Date());
    }
}
