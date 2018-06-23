package com;

import java.util.List;

class Check extends Exception {
    Check() {
        super("Parameters doesn't set"); // Конструктор суперкласса Exception
    }

    Check(List<String> input) {
        if (input.size() > 100) System.out.println("Too much elements: " + input.size());
        else if (input.size() == 0) System.out.println("File is empty");
        else System.out.println("Specified parameters not found");
    }
}
