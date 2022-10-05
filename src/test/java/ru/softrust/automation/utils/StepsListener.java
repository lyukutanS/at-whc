package ru.softrust.automation.utils;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StepsListener {
    @Autowired
    private DriverManager driverManager;

    @Autowired
    AllureAttachment allureAttachment;

    @Pointcut("execution(* ru.softrust.automation.steps.WebElementsInteraction.*(..))")
    public void listener() {
    }

    @After("execution(* ru.softrust.automation.steps.WebElementsInteraction.*(..))")
    public void logger() {
        allureAttachment.logs();
    }
}
