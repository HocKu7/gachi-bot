package com.github.telegramgachibot;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Files;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class TestUtil {

    @SneakyThrows
    public static MultipartFile getTestFile() {
        File file = new File("src/test/resources/test.mp3");
        byte[] allBytes = Files.readAllBytes(file.toPath());
        return new MockMultipartFile("test.file", allBytes);
    }
}
