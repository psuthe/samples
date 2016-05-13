package com.prs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestApp{

    public static void main(String[] args){
//        System.out.println("Enter the fibi iterations:");
//        int iterations = new Scanner(System.in).nextInt();
//        fibiCalc(iterations);
//        System.out.println();
//        fibiCalcII(iterations);
//
//        System.out.println();
//        System.out.println("Enter the filename:" + System.getProperty("user.dir"));
//        String fileName = new Scanner(System.in).nextLine();
//        readFile(fileName);

        System.out.println("Find7--->");
        find7();
    }

    public static void fibiCalc(int iterations){
        for (int i = 0; i < iterations; i++){
            System.out.print(calc(i) + " ");
        }
    }

    public static int calc(int seq){
        if (seq < 2){
            return seq;
        }
        return calc(seq-1) + calc(seq-2);
    }

    public static void fibiCalcII(int iterations){
        int[] fibis = new int[iterations];
        Arrays.fill(fibis, 0);
        for (int i = 1; i < iterations; i++){
            int seq1 = (i == 0) ? 0 : fibis[i-1];
            int seq2 = (i+1 < 3) ? 1 : fibis[i-2];
            fibis[i] = seq1 + seq2;
        }

        Arrays.stream(fibis)
                .forEach(i -> System.out.print(i + " "));
    }

    public static void readFile(String fileName){

        List<String> lines = new ArrayList<>();
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(fileName));
            String lineInput;
            while ((lineInput = buffer.readLine()) != null){
                lines.add(lineInput);
                 System.out.println(lineInput);
            }
            System.out.println("-------------------------------------------------------------");
            int lineCnt = lines.size();
            System.out.println("The number of lines is:" + lineCnt);
            for (int i = lineCnt-1; i > -1; i--){
                 System.out.println(lines.get(i));
            }
        } catch (IOException ioe) {
            System.out.println("FileNotFoundException:" + fileName);
        }

    }

    public static void find7(){
        int a[] = {1,2,3,3};
        int b[] = {2,3,3,4};
        int c[] = {1,2,2,2};
        int n;

        for(int i=0;i < 4; i++){
            for(int j=0;j<4;j++){
                for(int k=0;k<4;k++){
                    n = a[i] + b[j] + c[k];
                    if(n == 7){
                        System.out.printf("[%d,%d,%d]",a[i],b[j],c[k]);
                    }
                }
            }
        }
    }

}

