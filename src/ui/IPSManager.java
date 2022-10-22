package ui;

import model.IPSController;

import java.util.Scanner;

public class IPSManager {

    public static Scanner reader;
    public static IPSController controller;
    public static boolean loadFlag;

    public static void main(String[] args) {
        init();
        showMainMenu();
    } // main ///////////////////////////////

    public static void init() {
        controller = new IPSController();
        reader = new Scanner(System.in);
        loadFlag = false;
    } // init ////////////////////////////////////////////////////


    public static void showMainMenu(){

        System.out.println("\n|-----------------------------|");
        System.out.println("| Welcome to the IPS Software |");
        System.out.println("|-----------------------------|");
        if(controller.load().equalsIgnoreCase("")){
            loadFlag = true;
        }


        boolean runFlag = true;

        while (runFlag) {

            System.out.println("\n\n      WHAT DO YOU WANT TO DO?");
            System.out.println("|-------------------------------------");
            System.out.println("|| 1. Search patient.\n|" +
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
            int decision0 = reader.nextInt(); // I ask the user what he/she wants to do.

            switch (decision0) {
                case 1:
                    System.out.println("\nType the patient's ID");
                    String id = reader.next();

                    if(controller.search(id)!=null){
                        System.out.println("\nThe patient is in the data base:");
                        System.out.println("\n - - NAME: "+controller.search(id).getName()+
                                "\n - - ID: "+controller.search(id).getId()+
                                "\n - - AMOUNT OF PRIORITY: "+controller.search(id).getPriority());
                        System.out.println("\n\n-----------------------------------------------" +
                                "\nCheck-in the patient typing 1. If you don't want to, type 0.");
                        int decision = reader.nextInt();

                        if(decision==1){

                            System.out.println("In which lab do you want to entry the patient?\n" +
                                    "1) Hematology.\n" +
                                    "2) General purpose.");
                            int lab = reader.nextInt();

                            if(lab==1){
                                if(controller.entry(id)){
                                    System.out.println("The patient has been checked-in successfully");

                                } else {
                                    System.out.println("The patient failed to get checked-in");
                                }
                            } else if(lab==2){
                                if(controller.entryGeneralPurpose(id)){
                                    System.out.println("The patient has been checked-in successfully");

                                } else {
                                    System.out.println("The patient failed to get checked-in");
                                }
                            } else {
                                System.out.println("Please type a valid option.");
                            }
                        }
                    } else {
                        System.out.println("\nThe patient isn't in the data base. Please go to REGISTER PATIENT");
                    }

                    break;

                case 2:
                    System.out.println("\nType the ID of the patient");
                    String id0 = reader.next();

                    System.out.println("Which information do you want to change? \n" +
                            "1) Name.\n" +
                            "2) Priority.");
                    int change = reader.nextInt();

                    if(change==1){
                        System.out.println("Type the new name");
                        reader.nextLine();
                        String newName = reader.nextLine();

                        if(controller.editPatient(id0, 1, newName, 0)){
                            System.out.println("\nThe name of the patient has been edited successfully");
                        } else{
                            System.out.println("\nThe name of the patient couldn't be edited");
                        }
                    } else if(change==2){
                        System.out.println("Type the new priority");
                        int newPriority = reader.nextInt();

                        if(controller.editPatient(id0, 2, "", newPriority)){
                            System.out.println("\nThe priority of the patient has been edited successfully");
                        } else{
                            System.out.println("\nThe priority of the patient couldn't be edited");
                        }
                    } else {
                        System.out.println("Please type a valid option");
                    }
                    break;

                case 3:
                    System.out.println("\nType the patient's ID");
                    String id2 = reader.next();

                    System.out.println("Type the patient's name");
                    reader.nextLine();
                    String name = reader.nextLine();

                    System.out.println("Which genre is the patient? Type: M for Male | F for Female");
                    char genre = reader.next().charAt(0);

                    System.out.println("Type the birth date of the patient (DD/MM/YYYY)");
                    String birthDate = reader.next();

                    System.out.println("The patient has any major underlying disease? If yes, type the amount of " +
                            "them. If no, type 0.");
                    int priority = reader.nextInt();

                    if(controller.registerPatient(id2, name, genre, birthDate, priority)){
                        System.out.println("\nThe patient has been registered successfully.");
                        System.out.println("\n\n-----------------------------------------------" +
                                "\nCheck-in the patient typing 1. If you don't want to, type 0.");
                        int decision2 = reader.nextInt();

                        if(decision2==1){

                            System.out.println("In which lab do you want to entry the patient?\n" +
                                    "1) Hematology.\n" +
                                    "2) General purpose.");
                            int lab2 = reader.nextInt();

                            if(lab2==1){
                                if(controller.entry(id2)){
                                    System.out.println("The patient has been checked-in successfully");
                                } else {
                                    System.out.println("The patient failed to get checked-in");
                                }
                            } else if(lab2==2){
                                if(controller.entryGeneralPurpose(id2)){
                                    System.out.println("The patient has been checked-in successfully");
                                } else {
                                    System.out.println("The patient failed to get checked-in");
                                }
                            } else {
                                System.out.println("Please type a valid option.");
                            }
                        }

                    } else {
                        System.out.println("\nThe patient failed to get registered.");
                    }
                    break;

                case 4:
                    System.out.println("\nType the ID of the patient");
                    String id3 = reader.next();

                    System.out.println("In which lab do you want to entry the patient?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose.");
                    int lab3 = reader.nextInt();

                    if(lab3==1){
                        if(controller.entry(id3)){
                            System.out.println("The patient has been checked-in successfully");

                        } else {
                            System.out.println("The patient failed to get checked-in");
                        }
                    } else if(lab3==2){
                        if(controller.entryGeneralPurpose(id3)){
                            System.out.println("The patient has been checked-in successfully");

                        } else {
                            System.out.println("The patient failed to get checked-in");
                        }
                    } else {
                        System.out.println("Please type a valid option");
                    }

                    break;

                case 5:
                    System.out.println("\nIn which lab do you want to check out?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose.");
                    int lab4 = reader.nextInt();

                    if(lab4==1){
                        try {
                            System.out.println("\nThe next PATIENT IS: ");
                            System.out.println(controller.out());
                        } catch (NullPointerException nullPointerException){
                            System.out.println("There is no people waiting.");
                        }
                    } else if(lab4==2){
                        try {
                            System.out.println("\nThe next PATIENT IS: ");
                            System.out.println(controller.outGeneralPurpose());
                        } catch (NullPointerException nullPointerException){
                            System.out.println("There is no people waiting.");
                        }
                    } else {
                        System.out.println("Please type a valid option.");
                    }
                    break;

                case 6:
                    System.out.println("\nIn which lab do you want to know the order?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose.");
                    int lab5 = reader.nextInt();

                    if(lab5==1){
                        if(controller.queueOrder().equals("")){
                            System.out.println("There is no people waiting.");
                        } else{
                            System.out.println("\n| This is the order |\n"+controller.queueOrder());
                        }
                    } else if(lab5==2){
                        if(controller.queueOrderGeneralPurpose().equals("")){
                            System.out.println("There is no people waiting.");
                        } else{
                            System.out.println("\n| This is the order |\n"+controller.queueOrderGeneralPurpose());
                        }
                    } else {
                        System.out.println("Please type a valid option");
                    }
                    break;

                case 7:
                    String undoMsg = "";

                    try{
                        undoMsg = controller.undo();
                    } catch (NullPointerException nullPointerException){
                        System.out.println("\nThere is no actions");
                        break;
                    }

                    if(undoMsg.equalsIgnoreCase("")){
                        System.out.println("The action couldn't be undo.");
                    } else {
                        System.out.println("The action "+undoMsg+" has been undo.");
                    }
                    break;

                case 8:
                    if(controller.save().equalsIgnoreCase("")){
                        System.out.println("\nThe data has been saved successfully");
                    } else {
                        System.out.println("The data couldn't be added");
                    }
                    break;

                case 9:
                    if(loadFlag){
                        System.out.println("\nThe data has been loaded previously.");
                    } else {
                        if(controller.load().equalsIgnoreCase("")){
                            System.out.println("The data has been loaded successfully");
                        } else {
                            System.out.println("The data couldn't be loaded");
                        }
                    }
                    break;

                case 10:
                    System.out.println("\nThe patient is in which lab?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose.");
                    int lab9 = reader.nextInt();

                    if(lab9==1){
                        System.out.println("Type the ID of the patient.");
                        String id4 = reader.next();

                        System.out.println("Type the new amount of major underlying diseases.");
                        int newAmount = reader.nextInt();

                        if(controller.increaseKey(id4, newAmount)){
                            System.out.println("The priority has been updated");
                        } else {
                            System.out.println("The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't major.");
                        }
                    } else if(lab9==2){
                        System.out.println("Type the ID of the patient.");
                        String id4 = reader.next();

                        System.out.println("Type the new amount of major underlying diseases.");
                        int newAmount = reader.nextInt();

                        if(controller.increaseKeyGeneralPurpose(id4, newAmount)){
                            System.out.println("The priority has been updated");
                        } else {
                            System.out.println("The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't major.");
                        }
                    } else {
                        System.out.println("Please type a valid option");
                    }
                    break;

                case 11:
                    System.out.println("\nThe patient is in which lab?\n" +
                            "1) Hematology.\n" +
                            "2) General purpose.");
                    int lab10 = reader.nextInt();

                    if(lab10==1){
                        System.out.println("Type the ID of the patient.");
                        String id5 = reader.next();

                        System.out.println("Type the new amount of major underlying diseases.");
                        int newAmount2 = reader.nextInt();

                        if(controller.decreaseKey(id5, newAmount2)){
                            System.out.println("The priority has been updated");
                        } else {
                            System.out.println("The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't minor.");
                        }
                    } else if(lab10==2){
                        System.out.println("Type the ID of the patient.");
                        String id5 = reader.next();

                        System.out.println("Type the new amount of major underlying diseases.");
                        int newAmount2 = reader.nextInt();

                        if(controller.decreaseKeyGeneralPurpose(id5, newAmount2)){
                            System.out.println("The priority has been updated");
                        } else {
                            System.out.println("The priority hasn't been updated. The patient isn't in the priority queue or the new" +
                                    " amount isn't minor.");
                        }
                    } else {
                        System.out.println("Please type a valid option");
                    }
                    break;

                case 0:
                    System.out.println("\nThank you for using our software. Come back soon!");
                    runFlag = false;
                    break;

                default:
                    System.out.println("\nPlease, type a valid option.\n");
                    break;
            } // switch
        } // while
    } // main menu

} // IPS manager
