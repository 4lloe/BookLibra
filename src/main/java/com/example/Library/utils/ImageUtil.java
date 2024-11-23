package com.example.Library.utils;

import java.io.*;
import java.nio.file.*;
import java.util.Base64;

public class ImageUtil {
    public static String encodeImageToBase64(String imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
        return Base64.getEncoder().encodeToString(imageBytes);
    }
}
