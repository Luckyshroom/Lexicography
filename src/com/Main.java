package com;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileSort file = new FileSort(args);
            file.sort();
            System.out.println(file.getInput());
            System.in.read();
        } catch (IOException | Check e) {
            e.printStackTrace();
        }
    }
}
