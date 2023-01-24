package org.example;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static AtomicInteger three = new AtomicInteger();
    static AtomicInteger four = new AtomicInteger();
    static AtomicInteger five = new AtomicInteger();

    public static void main(String[] args) {

        Random random = new Random();
        String[] texts = new String[100000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread isPalindrome = new Thread(() -> {
            for (String text : texts) {
                for (int i = 0; i < (text.length() / 2); i++) {
                    if (text.charAt(i) == text.charAt(text.length() - i - 1)) {
                        counter(text.length());
                    }
                }
            }
        });
        isPalindrome.start();

        Thread sameLetters = new Thread(() -> {
            for (String text : texts) {
                for (int i = 1; i < text.length(); i++) {
                    if (text.charAt(i) != text.charAt(i - 1)) {
                        counter(text.length());
                    }
                }
            }
        });
        sameLetters.start();

        Thread upLetters = new Thread(() -> {
            for (String text : texts) {
                for (int i = 1; i < text.length(); i++) {
                    if (text.charAt(i) > text.charAt(i - 1)) {
                        counter(text.length());
                    }
                }
            }
        });
        upLetters.start();

        System.out.println("Красивых слов с длиной 3: " + three + " шт.");
        System.out.println("Красивых слов с длиной 4: " + four + " шт.");
        System.out.println("Красивых слов с длиной 5: " + five + " шт.");
    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }


    public static void counter(int textLength) {
            switch (textLength) {
                case (3):
                        three.getAndIncrement();
                    break;
                case (4):
                        four.getAndIncrement();
                    break;
                case (5):
                        five.getAndIncrement();
                    break;

        }
    }
}
