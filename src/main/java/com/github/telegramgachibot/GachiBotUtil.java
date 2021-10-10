package com.github.telegramgachibot;

import java.util.Random;

public class GachiBotUtil {

    public static int getRandomIndexBySize(int size) {

        return Math.abs(new Random().nextInt() % size);
    }
}
