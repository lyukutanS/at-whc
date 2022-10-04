package ru.softrust.automation.utils;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StepsListener {
    @Autowired
    private DriverManager driverManager;

    @Pointcut("execution(* ru.softrust.automation.steps.WebElementsInteraction.*(..))")
    public void listener() {
    }

    @AfterThrowing(pointcut = "listener()", throwing = "exception")
    public void afterThrowingListener(Exception exception) {
        driverManager.takeScreenshotToAllure();
    }

    @AfterThrowing(pointcut = "listener()", throwing = "assertionError")
    public void afterThrowingListener(AssertionError assertionError) {
        driverManager.takeScreenshotToAllure();
    }
}
