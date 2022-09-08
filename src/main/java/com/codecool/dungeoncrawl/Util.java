package com.codecool.dungeoncrawl;

import java.util.Random;

public class Util {

    static Random random = new Random();
    public static int generateRandomInteger(int bound){
        int randomInteger = random.nextInt(bound);
        return randomInteger;
    }
}
