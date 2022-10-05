package ru.softrust.automation.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ScenarioNameMap {
    ConcurrentHashMap<String, String> objectsHashCode = new ConcurrentHashMap<>();

    public void SetHashOfScenario(String scenarioName) {
        objectsHashCode.put(scenarioName, UUID.randomUUID().toString());
    }

    public String ReturnHashOfScenario(String scenarioName) {
        return objectsHashCode.get(scenarioName);
    }
}
