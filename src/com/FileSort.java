package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class FileSort {
    private List<String> input;
    private String[] args;
    private boolean num, reverse, line;

    FileSort(String[] args) throws IOException, Check {
        if (args.length == 4) {
            input = Files.readAllLines(Paths.get(args[0]));
            if (input.size() > 0 && input.size() <= 100) {
                this.args = args;
                if (args[2].equals("-i") && args[3].equals("-a")) num = true;
                else if (args[2].equals("-i") && args[3].equals("-d")) reverse = true;
                else if (args[2].equals("-s") && args[3].equals("-a")) line = true;
            } else throw new Check(input);
        } else throw new Check();
    }

    private void num() {
        for (int i = 1; i < input.size(); i++) {
            for (int j = i; j > 0 && Integer.parseInt(input.get(j - 1)) > Integer.parseInt((input.get(j))); j--) {
                String tmp = input.get(j - 1);
                input.set(j - 1, input.get(j));
                input.set(j, tmp);
            }
        }
    }

    private void reverse_num() {
        for (int i = input.size() - 2; i >= 0; i--) {
            for (int j = i; j < input.size() - 1 && Integer.parseInt(input.get(j + 1)) > Integer.parseInt((input.get(j))); j++) {
                String tmp = input.get(j + 1);
                input.set(j + 1, input.get(j));
                input.set(j, tmp);
            }
        }
    }

    private void lexicography() {
        String temp;
        int j;
        for (int i = 0; i < input.size() - 1; i++) {
            int res = input.get(i).compareTo(input.get(i + 1));
            if (res != 0 && res > 0) {
                temp = input.get(i + 1);
                input.set(i + 1, input.get(i));
                j = i;
                while (j > 0 && temp.compareTo(input.get(j - 1)) < 0) {
                    input.set(j, input.get(j - 1));
                    j--;
                }
                input.set(j, temp);
            }
        }
    }

    private void emit() throws IOException {
        if (Files.exists(Paths.get(args[1]))) {
            Files.delete(Paths.get(args[1]));
            Files.write(Paths.get(args[1]), input, Charset.forName("UTF-8"));
        } else Files.write(Paths.get(args[1]), input, Charset.forName("UTF-8"));
    }

    void sort() throws IOException, Check {
        if (num) {
            num();
            emit();
            System.out.println("The file " + args[0] + " was sorted with -i -a parameters");
        } else if (reverse) {
            reverse_num();
            emit();
            System.out.println("The file " + args[0] + " was sorted with -i -d parameters");
        } else if (line) {
            lexicography();
            emit();
            System.out.println("The file " + args[0] + " was sorted with -s -a parameters");
        } else throw new Check(input);
    }

    List<String> getInput() {
        return input;
    }
}