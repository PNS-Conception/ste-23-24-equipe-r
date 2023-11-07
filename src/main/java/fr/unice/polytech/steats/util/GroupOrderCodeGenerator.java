package fr.unice.polytech.steats.util;

import java.security.SecureRandom;

public class GroupOrderCodeGenerator {

    private static final int CODE_LENGTH = 8; // Choose the length you prefer
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom rnd = new SecureRandom();

    public static String generate() {
        return generateRandomComponent(CODE_LENGTH);
    }

    private static String generateRandomComponent(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(rnd.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}
