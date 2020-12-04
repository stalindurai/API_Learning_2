package utils;

import io.cucumber.java.sl.In;

import java.util.Random;

public class Accessories {

    public static void genRandom(){

        Random random = new Random();
        Integer newInt = random.nextInt();
        Double newDBL = random.nextDouble();
        System.out.println("Thisis the new Double "+newInt);
    }

    public static void main(String args[]){

        genRandom();
    }
}
