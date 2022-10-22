package ui;

import model.IPSController;
import javax.swing.*;
import java.util.Scanner;

public class IPSManager {

    public static Scanner reader;
    public static IPSController controller;
    public static boolean loadFlag;

    public static void main(String[] args) {
        init();
        showMainMenu();
    } // main

    public static void init() {
        controller = new IPSController();
        reader = new Scanner(System.in);
        loadFlag = false;
    } // init


    public static void showMainMenu(){

        JOptionPane.showMessageDialog(null, "\n|-------------------------------------------|\n| Welcome to the IPS Software  |\n|-------------------------------------------|");

        if(controller.load().equalsIgnoreCase("")){
            loadFlag = true;
        }

        boolean runFlag = true;

        while (runFlag) {
            String menu = "";

            menu+=("\n\n      WHAT DO YOU WANT TO DO?");
            menu+=("\n|----------------------------------------------------");
            menu+=("|\n| 1. Search patient.\n|" +
                    "| 2. Edit patient.\n|" +
                    "| 3. Register patient.\n|" +
                    "| 4. Check-in.\n|" +
                    "| 5. Check-out.\n|" +
                    "| 6. Attention order.\n|"+
                    "| 7. Undo.\n|"+
                    "| 8. Save data.\n|"+
                    "| 9. Load data.\n|"+
                    "| 10. Increase priority to a patient with priority.\n|"+
                    "| 11. Decrease priority to a patient with priority.\n|"+
                    "| 0. Exit.\n|"+
                    "|--------------------------------------"
            );
            int decision0 = Integer.parseInt(JOptionPane.showInputDialog(menu)); // I ask the user what he/she wants to do.

            switch (decision0) {
                case 1:
                    String id = JOptionPane.showInputDialog("\nType the patient's ID");

                    if(controller.search(id)!=null){
                        String search = "";
                        search += ("\nThe patient is in the data base:");
                        search += ("\n - - NAME: "+controller.search(id).getName()+
                                "\n - - ID: "+controller.search(id).getId()+
                                "\n - - AMOUNT OF PRIORITY: "+controller.search(id).getPriority());

                        JOptionPane.showMessageDialog(null,search);

                        int decision = Integer.parseInt(JOptionPane.showInputDialog("Check-in the patient typing 1. If you don't want to, type 0."));

                        if(decision==1){
                            int lab = Integer.parseInt(JOptionPane.showInputDialog("In which lab do you want to entry the patient?\n" +
                                    "1) Hematology.\n" +
                                    "2) General purpose."));

                            if(lab==1){
                                if(controller.entry(id)){
                                    JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                                } else {
                                    JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                                }
                            } else if(lab==2){
                                if(controller.entryGeneralPurpose(id)){
                                    JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                                } else {
                                    JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                                }
                            } else {
                                System.out.println("Please type a valid option.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"\nThe patient isn't in the data base. Please go to REGISTER PATIENT.");                    }

                    break;

                case 2:
                    String id0 = JOptionPane.showInputDialog("\nType the ID of the patient");

                    int change = Integer.parseInt (JOptionPane.showInputDialog("Which information do you want to change? \n" +
                            "1) Name.\n" +
                            "2) Priority."));

                    if(change==1){
                        String newName = JOptionPane.showInputDialog("Type the new name");

                        if(controller.editPatient(id0, 1, newName, 0)){
                            JOptionPane.showMessageDialog(null,"\nThe name of the patient has been edited successfully");
                        } else{
                            JOptionPane.showMessageDialog(null,"\nThe name of the patient couldn't be edited");
                        }
                    } else if(change==2){
                        int newPriority = Integer.parseInt(JOptionPane.showInputDialog(("Type the new priority")));

                        if(controller.editPatient(id0, 2, "", newPriority)){
                            JOptionPane.showMessageDialog(null,"\nThe priority of the patient has been edited successfully");
                        } else{
                            JOptionPane.showMessageDialog(null,"\nThe priority of the patient couldn't be edited");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option");
                    }
                    break;

                case 3:
                    String id2 =  JOptionPane.showInputDialog("\nType the patient's ID");

                    String name = JOptionPane.showInputDialog("Type the patient's name");

                    char genre = JOptionPane.showInputDialog("Which genre is the patient? Type: M for Male | F for Female").charAt(0);

                    String birthDate = JOptionPane.showInputDialog("Type the birth date of the patient (DD/MM/YY)");

                    int priority = Integer.parseInt(JOptionPane.showInputDialog("The patient has any major underlying disease? If yes, type the amount of " +
                            "them. If no, type 0."));

                    if(controller.registerPatient(id2, name, genre, birthDate, priority)){
                        JOptionPane.showMessageDialog(null,"\nThe patient has been registered successfully.");
                        int decision2 = Integer.parseInt(JOptionPane.showInputDialog("\nCheck-in the patient typing 1. If you don't want to, type 0."));

                        if(decision2==1){

                            int lab2 = Integer.parseInt(JOptionPane.showInputDialog("In which lab do you want to entry the patient?\n" +
                                    "1) Hematology.\n" +
                                    "2) General purpose."));

                            if(lab2==1){
                                if(controller.entry(id2)){
                                    JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                                } else {
                                    JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                                }
                            } else if(lab2==2){
                                if(controller.entryGeneralPurpose(id2)){
                                    JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                                } else {
                                    JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                                }
                            } else {
                                System.out.println("Please type a valid option.");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"\nThe patient failed to get registered.");
                    }
                    break;

                case 4:
                    String id3 = JOptionPane.showInputDialog("\nType the ID of the patient");

                    int lab3 = Integer.parseInt(JOptionPane.showInputDialog("In which lab do you want to entry the patient?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose."));

                    if(lab3==1){
                        if(controller.entry(id3)){
                            JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                        } else {
                            JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                        }
                    } else if(lab3==2){
                        if(controller.entryGeneralPurpose(id3)){
                            JOptionPane.showMessageDialog(null,"The patient has been checked-in successfully");

                        } else {
                            JOptionPane.showMessageDialog(null,"The patient failed to get checked-in");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option.");
                    }

                    break;

                case 5:
                    int lab4 = Integer.parseInt (JOptionPane.showInputDialog("\nIn which lab do you want to check out?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose."));

                    if(lab4==1){
                        try {
                            JOptionPane.showMessageDialog(null,"\nThe next PATIENT IS: \n"+controller.out());
                        } catch (NullPointerException nullPointerException){
                            JOptionPane.showMessageDialog(null,"There is no people waiting.");
                        }
                    } else if(lab4==2){
                        try {
                            JOptionPane.showMessageDialog(null,"\nThe next PATIENT IS: \n"+controller.outGeneralPurpose());
                        } catch (NullPointerException nullPointerException){
                            JOptionPane.showMessageDialog(null,"There is no people waiting.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option.");
                    }
                    break;

                case 6:
                    int lab5 = Integer.parseInt (JOptionPane.showInputDialog("\nIn which lab do you want to know the order?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose."));

                    if(lab5==1){
                        if(controller.queueOrder().equals("")){
                            JOptionPane.showMessageDialog(null,"There is no people waiting.");
                        } else{
                            JOptionPane.showMessageDialog(null,"\n| This is the order |\n"+controller.queueOrder());
                        }
                    } else if(lab5==2){
                        if(controller.queueOrderGeneralPurpose().equals("")){
                            JOptionPane.showMessageDialog(null,"There is no people waiting.");
                        } else{
                            JOptionPane.showMessageDialog(null,"\n| This is the order |\n"+controller.queueOrderGeneralPurpose());
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option");
                    }
                    break;

                case 7:
                    String undoMsg = "";

                    try{
                        undoMsg = controller.undo();
                    } catch (NullPointerException nullPointerException){
                        JOptionPane.showMessageDialog(null,"\nThere is no actions");
                        break;
                    }

                    if(undoMsg.equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(null,"The action couldn't be undo.");
                    } else {
                        JOptionPane.showMessageDialog(null,"The action "+undoMsg+" has been undo.");
                    }
                    break;

                case 8:
                    if(controller.save().equalsIgnoreCase("")){
                        JOptionPane.showMessageDialog(null,"The data has been saved successfully");
                    } else {
                        JOptionPane.showMessageDialog(null,"The data couldn't be added");
                    }
                    break;

                case 9:
                    if(loadFlag){
                        JOptionPane.showMessageDialog (null,"\nThe data has been loaded previously.");
                    } else {
                        if(controller.load().equalsIgnoreCase("")){
                            JOptionPane.showMessageDialog(null,"The data has been loaded successfully");
                        } else {
                            JOptionPane.showMessageDialog(null,"The data couldn't be loaded");
                        }
                    }
                    break;

                case 10:
                    int lab9 = Integer.parseInt (JOptionPane.showInputDialog("\nThe patient is in which lab?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose."));

                    if(lab9==1){
                        String id4 = JOptionPane.showInputDialog("Type the ID of the patient.");

                        int newAmount = Integer.parseInt (JOptionPane.showInputDialog("Type the new amount of major underlying diseases."));

                        if(controller.increaseKey(id4, newAmount)){
                            JOptionPane.showMessageDialog(null,"The priority has been updated");
                        } else {
                            JOptionPane.showMessageDialog(null,"The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't major.");
                        }
                    } else if(lab9==2){
                        String id4 = JOptionPane.showInputDialog("Type the ID of the patient.");

                        int newAmount = Integer.parseInt (JOptionPane.showInputDialog("Type the new amount of major underlying diseases."));

                        if(controller.increaseKeyGeneralPurpose(id4, newAmount)){
                            JOptionPane.showMessageDialog(null,"The priority has been updated");
                        } else {
                            JOptionPane.showMessageDialog(null,"The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't major.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option");
                    }
                    break;

                case 11:
                    int lab10 = Integer.parseInt (JOptionPane.showInputDialog("\nThe patient is in which lab?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose."));

                    if(lab10==1){
                        String id5 = JOptionPane.showInputDialog("Type the ID of the patient.");

                        int newAmount2 = Integer.parseInt (JOptionPane.showInputDialog("Type the new amount of major underlying diseases."));

                        if(controller.decreaseKey(id5, newAmount2)){
                            JOptionPane.showMessageDialog(null,"The priority has been updated");
                        } else {
                            JOptionPane.showMessageDialog(null,"The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't minor.");
                        }
                    } else if(lab10==2){
                        String id5 = JOptionPane.showInputDialog("Type the ID of the patient.");

                        int newAmount2 = Integer.parseInt (JOptionPane.showInputDialog("Type the new amount of major underlying diseases."));

                        if(controller.decreaseKeyGeneralPurpose(id5, newAmount2)){
                            JOptionPane.showMessageDialog(null,"The priority has been updated");
                        } else {
                            JOptionPane.showMessageDialog(null,"The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't minor.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"Please type a valid option");
                    }
                    break;

                case 0:
                    JOptionPane.showMessageDialog(null,"\nThank you for using our software. Come back soon!");
                    runFlag = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null,"\nPlease, type a valid option.\n");
                    break;
            } // switch
        } // while
    } // main menu

} // IPS manager
