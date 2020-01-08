package com.company;

import java.util.Scanner;

public class Main {

    static String UI;
    static String FK;
    static String DH;

    static String Choose(String option1, String option2) {
        System.out.println("Please choose between '" + option1 + "' or '" + option2 + "'.");
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.toUpperCase();

        if (s.startsWith(option1)) {
            System.out.println(option1 + " chosen.");
            return option1;
        } else if (s.startsWith(option2)) {
            System.out.println(option2 + " chosen.");
            return option2;
        } else {
            Choose(option1, option2);
        }
        return null;
    }

    static void startProgramm(String UI, String FK, String DH) {
        if (FK == "FK1") {
            //start FK1 and pass UI and DHq
            FK1.main(DH, UI, FK);
        }
        if (FK == "FK2") {
            // FK2 and pass UI and DH
        }
    }



    public static void main(String[] args) {
        UI = Choose("TUI", "GUI");
        FK = Choose("FK1", "FK2");
        DH = Choose("DH1", "DH2");
        startProgramm(UI, FK, DH);
    }
}