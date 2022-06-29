package com.FINALPROJECT.MotoRider.Utils;

import net.bytebuddy.utility.RandomString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private static List<String> tokensCreated = new ArrayList<>();
    private static List<String> codesCreated = new ArrayList<>();


    public static String GenerateToken(int tokenL) {
        String token = "";
        do {
            token = RandomString.make(tokenL);
        } while (tokensCreated.contains(token));
        tokensCreated.add(token);

        return token;
    }


    public static String GenerateCode(int max, int min) {
        int number;
        String numberFinal = "";

        do {
            for (int i = 0; i < 6; i++) {
                number = (int) ((Math.random() * (max - min)) + min);
                numberFinal += number;
            }
        }
        while (codesCreated.contains(numberFinal));
        codesCreated.add(numberFinal);
        return numberFinal;
    }

    public static void DeleteToken(String tokenD) {
        tokensCreated = tokensCreated.stream().filter(token -> token != tokenD).collect(Collectors.toList());
    }



}
