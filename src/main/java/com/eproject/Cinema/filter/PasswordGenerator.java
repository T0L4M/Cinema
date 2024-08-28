package com.eproject.Cinema.filter;

import java.security.SecureRandom;



public class PasswordGenerator {
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$&?";
    private static final String ALL_CHARS = LOWER_CASE + UPPER_CASE + DIGITS + SPECIAL_CHARS;

    private static final int PASSWORD_LENGTH = 8;

     public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        
        // Ensure at least one character from each category
        StringBuilder password = new StringBuilder();
        password.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));
        password.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // Fill the rest of the password length with random characters from all categories
        for (int i = password.length(); i < PASSWORD_LENGTH; i++) {
            password.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }

        // Shuffle the characters to ensure randomness
        StringBuilder shuffledPassword = new StringBuilder();
        while (password.length() > 0) {
            int index = random.nextInt(password.length());
            shuffledPassword.append(password.charAt(index));
            password.deleteCharAt(index);
        }

        return shuffledPassword.toString();
    }
}
