package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FK1 {

    private static Pattern pNumeric = Pattern.compile("-?\\d+(\\.\\d+)?");
    private static int intCheck = -1;

    static String DH;
    static String UI;
    static String FK;

    static String command;

    static List<Immobilie> immobilien;
    static List<Owner> owner;

    static int id_immobilie;
    static String address;
    static String type;
    static int owner_id;

    static int id_owner;
    static String first;
    static String last;


    static void initializeProgramm(String UIfromMain, String FKfromMain, String DHfromMain){
        UI = UIfromMain;
        FK = FKfromMain;
        DH = DHfromMain;
    }

    static void prepareUI() {
        if (UI.startsWith("TUI")) {
            TUI.main(null);
            command = TUI.greeting();

            switch (command){
                case "exit":
                    System.exit(0);
                case "list immobilien":
                    listImmobilien();
                    break;
                case "list owner":
                    listOwner();
                    break;
                case "get immobilie":
                    id_immobilie = TUI.getInput_Int("Please type in the immobilien id.");
                    getImmobilie(id_immobilie);
                    break;
                case "get owner":
                    id_owner = TUI.getInput_Int("Please type in the owner id.");
                    getOwner(id_owner);
                    break;
                case "create immobilie":
                    address = TUI.getNoEmptyInput_String("please enter addresse");
                    type = TUI.getNoEmptyInput_String("please enter type");

                    while (intCheck < 0){
                        owner_id = TUI.getInput_Int("please enter owner_id");
                        intCheck = owner_id;
                    }

                    createImmobilie(address, type, owner_id);
                    immobilien = DBUtils.getImmobilienData();

                    TUI.displayValue("created immobilie");
                    TUI.space();
                    displayObject(immobilien.get(immobilien.size()-1).toString());
                    break;
                case "create owner":
                    first = TUI.getInput_String("please enter first");
                    if (first.equals("")){
                        first = TUI.getInput_String("please enter first");
                    }
                    last = TUI.getInput_String("please enter last");
                    if (last.equals("")){
                        last = TUI.getInput_String("please enter last");
                    }

                    createOwner(first, last);

                    owner = DBUtils.getOwnerData();

                    TUI.displayValue("created owner");
                    TUI.space();
                    displayObject(owner.get(owner.size()-1).toString());
                    break;
                case "update immobilie":
                    id_immobilie = TUI.getInput_Int("please enter the immobilien id") -1;
                    address = TUI.getInput_String("please enter address or press enter to keep the old one");
                    type = TUI.getInput_String("please enter type  or press enter to keep the old one");

                    updateImmobilie(id_immobilie, address, type);

                    immobilien = DBUtils.getImmobilienData();

                    TUI.displayValue("updated immobilie");
                    TUI.space();
                    displayObject(immobilien.get(id_immobilie).toString());
                    break;
                case "update owner":
                    id_owner = TUI.getInput_Int("please enter the owner id") -1;
                    first = TUI.getInput_String("please enter the first name or press enter to keep the old one");
                    last = TUI.getInput_String("please enter the last name or press enter to keep the old one");

                    updateOwner(id_owner, first, last);

                    owner = DBUtils.getOwnerData();

                    TUI.displayValue("updated owner");
                    TUI.space();
                    displayObject(owner.get(id_owner).toString());
                    break;
                case "delete immobilie":
                    id_immobilie = TUI.getInput_Int("please enter the immobilien id");
                    deleteImmobilie(id_immobilie);
                    immobilien = DBUtils.getImmobilienData();
                    break;
                case "delete owner":
                    id_owner = TUI.getInput_Int("please enter the owner id");
                    deleteOwner(id_owner);
                    owner = DBUtils.getOwnerData();
                    break;
                case "get owner of":
                    id_immobilie = TUI.getInput_Int("Please type in the immobilien id.");
                    getOwnerOf(id_immobilie);
                    break;
                case "get immobilien of":
                    id_owner = TUI.getInput_Int("Please type in the owner id.");
                    getImmobilienOf(id_owner);
                    break;
                default:
                    prepareUI();
                    break;
            }
            prepareUI();
        }
        if (UI.startsWith("GUI")) {
            // GUI preparation here !!!
        }
    }

    static void prepareDataStorage() {
        if (DH == "DH1") {
            DBUtils.PrepareDB();
            immobilien = DBUtils.getImmobilienData();
            owner = DBUtils.getOwnerData();
            System.out.println("Prepare Data...");
        }
        if (DH == "DH2") {
            // DH2 preperation here !!!
        }
    }

    static void displayObject(String data){
        if (UI == "TUI"){
            TUI.displayValue(data);
        }
        if (UI == "GUI"){
            // GUI display data command
        }
    }

    static void listImmobilien(){
        int size = immobilien.size();
        int i = 0;
        while (i < size) {
            displayObject(immobilien.get(i).toString());
            i++;
        }
    }

    static void listOwner(){
        int size = owner.size();
        int i = 0;
        while (i < size) {
            displayObject(owner.get(i).toString());
            i++;
        }
    }

    static void getImmobilie(int id) {
        int i = 0;
        while(i < immobilien.size()){
            if (immobilien.get(i).getId() == id){
                displayObject(immobilien.get(i).toString());
                i = immobilien.size();
            }
            i++;
        }
    }

    static void getOwner(int id) {
        int i = 0;
        while(i < owner.size()){
            if (owner.get(i).getId() == id){
                displayObject(owner.get(i).toString());
                i = owner.size();
            }
            i++;
        }
    }

    static void createImmobilie(String address, String type, int owner_id){
        if (DH.startsWith("DH1")){
            DBUtils.addImmobilie(address, type, owner_id);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void createOwner(String first, String last){
        if (DH.startsWith("DH1")){
            DBUtils.addOwner(first, last);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void updateImmobilie(int id, String address, String type) {
        if (DH.startsWith("DH1")){
            DBUtils.updateImmobilie(id, address, type);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void updateOwner(int id, String first, String last) {
        if (DH.startsWith("DH1")){
            DBUtils.updateOwner(id, first, last);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void deleteImmobilie(int id){
        if (DH.startsWith("DH1")){
            DBUtils.deleteImmobilie(id);
            TUI.displayValue("deleted immobilie with ID: " + id);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void deleteOwner(int id){
        if (DH.startsWith("DH1")){
            DBUtils.deleteOwner(id);
            TUI.displayValue("deleted owner with ID: " + id);
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void getOwnerOf(int id){
        if (DH.startsWith("DH1")){
            owner_id = DBUtils.getImmobilie(id).getOwner_id() + 1;
            TUI.displayValue("owner of immobilie with id: " + id);
            displayObject(DBUtils.getOwner(owner_id).toString());
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    static void getImmobilienOf(int id){
        if (DH.startsWith("DH1")){
            int i = 0;
            boolean b = false;
            while (i < immobilien.size()){
                owner_id = immobilien.get(i).getOwner_id();
                if (owner_id == id) {
                    displayObject(immobilien.get(i).toString());
                    b = true;
                }
                i++;
            }
            if (!b)
                TUI.displayValue("this owner doesn't own any immobilien.");
        }
        if (DH.startsWith("DH2")){
            // DH2
        }
    }

    public static boolean isNumeric(String strNum){
        return pNumeric.matcher(strNum).matches();
    }

    public static void main(String DH, String UI, String FK) {
        initializeProgramm(UI, FK, DH);
        prepareDataStorage();
        prepareUI();
    }
}
