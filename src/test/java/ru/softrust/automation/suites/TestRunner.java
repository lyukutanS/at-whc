package ru.softrust.automation.suites;

import courgette.api.CourgetteOptions;
import courgette.api.CourgetteRunLevel;
import courgette.api.CucumberOptions;
import courgette.api.junit.Courgette;
import org.junit.runner.RunWith;

@RunWith(Courgette.class)
@CourgetteOptions(
        threads = 1,
        runLevel = CourgetteRunLevel.SCENARIO,
        rerunFailedScenarios = false,
        showTestOutput = true,
        reportTargetDir = "reports",
        cucumberOptions = @CucumberOptions(
                features = "src/test/resources/features",
                tags = {"@all"},
                glue = {"ru.softrust.automation.steps"},
                plugin = {
                        "pretty",
//                        "json:build/cucumber-report/cucumber.json",
//                        "html:build/cucumber-report/cucumber.html",
//                        "junit:build/cucumber-report/cucumber.xml",
                        "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"}
        ))
public class TestRunner {
}