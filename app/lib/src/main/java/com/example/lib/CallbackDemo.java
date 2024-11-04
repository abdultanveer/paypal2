package com.example.lib;

public class CallbackDemo {
    public static void main(String[] args) {
        System.out.println("hello world");
        Abdul ansarisPhno = new Abdul();
        SanngeethAbiram studennt = new SanngeethAbiram();
        new Thread(){
            @Override
            public void run() {
                super.run();
                System.out.println("addition result a--"+ studennt.add(10,20));
                studennt.multiply(5,4,ansarisPhno);
            }
        }.start();

        System.out.println("leave for airport");
    }
}
