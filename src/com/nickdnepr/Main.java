package com.nickdnepr;

public class Main {

    Boolean b;

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("aaa");
        builder.insert(1, "k");
        System.out.println(builder.toString());
        Main m = new Main();
    }
}
