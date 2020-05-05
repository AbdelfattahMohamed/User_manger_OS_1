/*
Task_OS-1
Requirments: 


    - Add / Delete Users
    - Add / Delete groups
    - Change information of users
    - Change account information e.g. password expiration
    - Assign specific users to specific groups

*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import task_os.User_Manager_class;

/**
 *
 * @author AbdelfattahMohamed
 * 
 */



public class Task_OS {
    public static void main(String[] args) {
        // Object from User_Manager_class
        User_Manager_class um = new User_Manager_class();
        // Path in hidden || Bin File
        String hpath = "simpleGui/gui.txt";
        
        
        if (args.length != 0 && (args[0].equals("-h") ||args[0].equals("--h") ||
                args[0].equals("--simpleGui"))){

            System.out.println(um.simpleGui(hpath));

        }
        
        else {

            String userName, password, postalCode, fullName, TaskToDo, AddressNum, groupName;
            ArrayList<String> groups = new ArrayList<>();

            String choices;
            Scanner input = new Scanner(System.in);

            System.out.println("-----------------------------------------------HELLO THIS IS USER MANAGER SYSTEM-----------------------------------------------");

            do {

                System.out.println(
                        "\nEnter      A   to add a new user\n" +
                        "Enter      B   to delete user.\n" +
                        "Enter      C   to assign a specific user to specific groups.\n" +
                        "Enter      D   to Change information of user or change account inforamtion.\n" +
                        "Enter      E   to Create group.\n" +
                        "Enter      F   to delete group.\n" +
                        "Enter     end to end the Program .\n");
                // Validation upper or lower
                choices = input.next().toUpperCase();

                switch (choices) {
// Add new USER
                    case "A":
                        System.out.println("Enter *  1  * to create user without group name:");
                        System.out.println("Enter *  2  * to create user to specific group:");
                        int option = input.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("\n Enter Your Username");
                                userName = input.next();
                                System.out.println("\n" + um.addUser(userName));
                                break;

                            case 2:
                                System.out.println("\nEnter Your Username");
                                userName = input.next();
                                System.out.println("\nEnter Your Group Name");
                                groupName = input.next();
                                System.out.println("\n" + um.createUserWithgroupName(userName, groupName));
                                break;

                            default:
                                System.out.println("OPS! Error!");
                        }
                        break;
// Delete USER
                    case "B":
                        System.out.println("\nEnter your Username :");
                        userName = input.next();
                        System.out.println("\n" + um.deleteUser(userName));
                        break;
// assign a specific user to specific groups
                    case "C":
                        System.out.println("\nEnter the number of groups:");
                        int g = input.nextInt();

                        System.out.println("\nEnter group name or names if more than 1:");
                        while (g > 0) {
                            groups.add(input.next());
                            g--;
                        }

                        System.out.println("\nEnter your Username:");
                        userName = input.next();
                        System.out.println("\n" + um.assignUserToSpecificGroup(userName, groups));
                        break;
// Change information 
                    case "D":
                        System.out.println("Enter *  1  * to change Full Name");
                        System.out.println("Enter *  2  * to change Address Number ");
                        System.out.println("Enter *  3  * to change postalCode ");
                        System.out.println("Enter *  4  * to change the number of Task to Do");
                        System.out.println("Enter *  5  * to change password ");
                        option = input.nextInt();

                        switch (option) {
                            case 1:
                                System.out.println("Enter userName: ");
                                userName = input.next();
                                System.out.println("Enter a new fullName: ");
                                fullName = input.next();
                                System.out.println(um.changeFullName(userName, fullName));
                                break;

                            case 2:
                                System.out.println("Enter userName: ");
                                userName = input.next();
                                System.out.println("Enter a new Address Number: ");
                                AddressNum = input.next();
                                System.out.println(um.changeAddressNumber(userName, AddressNum));
                                break;

                            case 3:
                                System.out.println("Enter userName: ");
                                userName = input.next();
                                System.out.println("Enter a new postalCode: ");
                                postalCode = input.next();
                                System.out.println(um.changePostalCode(userName, postalCode));
                                break;

                            case 4:
                                System.out.println("Enter userName: ");
                                userName = input.next();
                                System.out.println("Enter a new work number: ");
                                TaskToDo = input.next();
                                System.out.println(um.changeTaskToDo(userName, TaskToDo));
                                break;

                            case 5:
                                System.out.println("Enter userName: ");
                                userName = input.next();
                                System.out.println("Enter a new password number: ");
                                password = input.next();
                                System.out.println(um.changePassword(userName, password));
                                break;


                            default:
                                System.out.println("\nError!");
                        }
                        break;
//CREATE GROUP
                    case "E":
                        System.out.println("\nEnter the group name you wanna to create !");
                        groupName = input.next();
                        System.out.println("\n" + um.createGroup(groupName));
                        break;
// Delete GROUP
                    case "F":
                        System.out.println("\nEnter the group name that you want to delete it:");
                        groupName = input.next();
                        System.out.println("\n" + um.deleteGroup(groupName));
                        break;

                    case "end":
                        break;

                    default:
                        System.out.println("\nError!");
                }
            }
// END DO           
            
        while (!choices.equals("end"));

        }
// END DO WHILE
    }
}
