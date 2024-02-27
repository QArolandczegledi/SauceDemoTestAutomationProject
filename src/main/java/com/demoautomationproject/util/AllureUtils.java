package com.demoautomationproject.util;

import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class AllureUtils {

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshotToAllure(File screenshot) {
        try {
            return FileUtils.readFileToByteArray(screenshot);
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
