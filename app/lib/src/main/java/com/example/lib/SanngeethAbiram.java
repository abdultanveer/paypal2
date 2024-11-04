package com.example.lib;


public class SanngeethAbiram {

    int add(int a, int b){
        return a + b;
    }

//suspendable function -- funnction which you anticipate would take more time to execute
    //compared to other functions eg n/w call, db call, io call


    void multiply(int a, int b,MobilePhone phoneno){
        try {
            Thread.sleep(10000);
            int res = a * b;
             phoneno.multiplicationResult(res);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
