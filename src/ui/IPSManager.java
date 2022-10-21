package ui;

import model.IPSController;

import java.util.Scanner;

public class IPSManager {

    public static Scanner reader;
    public static IPSController controller;

    public static void main(String[] args) {
        init();
        showMainMenu();
    } // main ///////////////////////////////

    public static void init() {
        controller = new IPSController();
        reader = new Scanner(System.in);

    } // init ////////////////////////////////////////////////////


    public static void showMainMenu(){

        System.out.println("\n|-----------------------------|");
        System.out.println("| Welcome to the IPS Software |");
        System.out.println("|-----------------------------|");

        boolean runFlag = true;

        while (runFlag) {

            System.out.println("\n\n      WHAT DO YOU WANT TO DO?");
            System.out.println("|-------------------------------------");
            System.out.println("|| 1. Search patient.\n|" +
                    "| 2. Register patient\n|" +
                    "| 3. Check-in.\n|" +
                    "| 4. Check-out.\n|" +
                    "| 5. Attention order\n|" +
                    "| 6. Undo.\n"+
                    "|--------------------------------------"
            );
            int decision0 = reader.nextInt(); // I ask the user what he/she wants to do.

            switch (decision0) {
                case 1:
                    System.out.println("Type the patient's ID");
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
                            if(controller.entry(id)){
                                System.out.println("The patient has been checked-in successfully");

                            } else {
                                System.out.println("The patient failed to get checked-in");
                            }
                        }
                    } else {
                        System.out.println("\nThe patient isn't in the data base. Please go to REGISTER PATIENT");
                    }

                    break;

                case 2:
                    System.out.println("Type the patient's ID");
                    String id2 = reader.next();

                    System.out.println("Type the patient's name");
                    reader.nextLine();
                    String name = reader.nextLine();

                    System.out.println("Which genre is the patient? Type: M for Male | F for Female");
                    char genre = reader.next().charAt(0);

                    System.out.println("Type the birth date of the patient (DD/MM/YY)");
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
                            if(controller.entry(id2)){
                                System.out.println("The patient has been checked-in successfully");
                            } else {
                                System.out.println("The patient failed to get checked-in");
                            }
                        }

                    } else {
                        System.out.println("\nThe patient failed to get registered.");
                    }
                    break;

                case 3:
                    System.out.println("Type the ID of the patient");
                    String id3 = reader.next();

                    if(controller.entry(id3)){
                        System.out.println("The patient has been checked-in successfully");

                    } else {
                        System.out.println("The patient failed to get checked-in");
                    }
                    break;

                case 4:
                    try {
                        System.out.println("\nThe next PATIENT IS: ");
                        System.out.println(controller.out());
                    } catch (NullPointerException nullPointerException){
                        System.out.println("There is no people waiting.");
                    }
                    break;

                case 5:
                    if(controller.queueOrder().equals("")){
                        System.out.println("There is no people waiting.");
                    } else{
                        System.out.println("\n| This is the order |\n"+controller.queueOrder());
                    }
                    break;

                case 6:

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
