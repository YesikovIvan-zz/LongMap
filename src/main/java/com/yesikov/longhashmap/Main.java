package com.yesikov.longhashmap;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        LongMap longMap = new LongMap();
        HashMap hashMap = new HashMap();
        long start, end;

        start = System.nanoTime();
        for (int i=0; i<1000000; i++){
            longMap.put(i,"test - "+i);
        }
        end = System.nanoTime();
        System.out.println("LongMap PUT 1 million objects ==> " +(end-start)/1000000+ " milliseconds");

        start = System.nanoTime();
        for (long i = 0; i<1000000; i++){
            hashMap.put(i,"test - "+i);
        }
        end = System.nanoTime();
        System.out.println("HashMap PUT 1 million objects ==> " +(end-start)/1000000+ " milliseconds");


        System.out.println("******************************************************");


        start = System.nanoTime();
        longMap.values();
        end = System.nanoTime();
        System.out.println("LongMap GET 1 million objects ==> " +(end-start)+ " nanoseconds");

        start = System.nanoTime();
        hashMap.values();
        end = System.nanoTime();
        System.out.println("HashMap GET 1 million objects ==> " +(end-start)+ " nanoseconds");
    }
}
