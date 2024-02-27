package com.demoautomationproject.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
    private static final String CONFIG_FILE_PATH = "Configuration/Config.properties";
    private static Properties configProperties;

    static {
        loadConfigProperties();
    }

    private static void loadConfigProperties() {
        configProperties = new Properties();
        try (FileInputStream configInputStream = new FileInputStream(CONFIG_FILE_PATH)) {
            configProperties.load(configInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load configuration properties from " + CONFIG_FILE_PATH, e);
        }
    }

    public static String getProperty(String key) {
        return configProperties.getProperty(key);
    }

    public static int getWaitTime() {
        return Integer.parseInt(getProperty("waitTime"));
    }
}
