package com.company;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static com.company.FK1.isNumeric;

public class TUI {

    private static String emptyString;

    static List<Immobilie> immobilien;

    static String FK;

    static String command;
    static String validCommands = "\n   help\n   list immobilien\n   list owner\n   get immobilie\n   get owner\n   create immobilie\n   create owner\n   update immobilie\n   update owner\n   delete immobilie\n   delete owner\n   get owner of\n   exit\n   ";

    static void initializeProgramm(String FKfromFK){
        FK = FKfromFK;
    }

    static void displayValue(String data) {
        System.out.println(data);
    }

    static void displayObject(String data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        displayValue(json);
    }

    static String getInput_String(String inputInfo){
        System.out.println(inputInfo);
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        return s;
    }

    static String getNoEmptyInput_String(String inputInfo){
        emptyString = TUI.getInput_String(inputInfo);
        while (emptyString.equals("")){
            emptyString = TUI.getInput_String(inputInfo);
        }
        return emptyString;
    }

    static int getInput_Int(String inputInfo){
        displayValue(inputInfo);
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        if (s.equals("") || !isNumeric(s)){
            return -1;
        }

        int i = Integer.parseInt(s);
        return i;
    }

    static void space() {
        displayValue("\n");
    }

    static String greeting(){
        space();
        displayValue("Please type in a viable command.");
        space();
        command = getInput_String("Type 'help' to display a list of all viable commands.");
        if (command.startsWith("help")){
            displayValue(validCommands);}
        return command;
    }

    static void main(String FK) {
        initializeProgramm(FK);
    }
}
