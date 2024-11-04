package com.example.lib;


public class SanngeethAbiram {

    int add(int a, int b){
        return a + b;
    }


    int multiply(int a, int b){
        try {
            Thread.sleep(10000);
            int res = a * b;
            return  res;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
