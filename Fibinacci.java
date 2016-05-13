package com.prs;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by peter on 5/5/16.
 */
public class Fibinacci {


    public static void main (String[] args){

        System.out.print("Enter iterations-->");
        int iterations = new Scanner(System.in).nextInt();
        for (int i = 0; i < iterations; i++){
            System.out.print(fibiRecursive(i) + " ");
        }

        System.out.println("");

        int[] fibisFromLoop = fibiLoop(iterations);
        Arrays.stream(fibisFromLoop)
                .forEach(f -> System.out.print(f + " "));

    }

    private static int fibiRecursive(int value){
        if (value < 2){
            return value;
        }
        return fibiRecursive(value-1) + fibiRecursive(value-2);
    }

    private static int[] fibiLoop(int iterations){
        int[] fibis = new int[iterations];
        for (int i = 0; i < iterations; i++){
            fibis[i] = (i < 2) ? i : fibis[i-1] + fibis[i-2];
        }
        return fibis;
    }

}
