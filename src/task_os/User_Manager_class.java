package task_os;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author AbdelfattahMohamed
 */
public class User_Manager_class {
    
    private final Runtime runtime;
    private Process process;
    private BufferedReader input;
    private String result;
    private String line;

    public User_Manager_class() {
        runtime = Runtime.getRuntime();
        process = null;
        line = null;
        input = null;
    }

    public String addUser(String name) {
        if (!searchInFile(name, "users.txt")) {
            try {
                writeInTextFile(name, "users.txt");
                process = runtime.exec("sudo adduser " + name);
                result = "\n\nThe user is created _____SUCCESSFULLY____\n\n";
            } catch (IOException e) {
                result = "ERROR REPEAT AGAIN";
                return result;
            }

            return result;
        } else
            return "The user is exsited !!";
    }

    public String createUserWithgroupName(String userName, String groupName) {

        if (!searchInFile(userName, "users.txt") &&  searchInFile(groupName,"groups.txt")) {
            try {
                writeInTextFile(userName, "users.txt");
                process = runtime.exec("sudo useradd -G "+ groupName + " "+ userName);
                result = "\n\n The user is created With a specific Group _____SUCCESSFULLY____ \n\n";
            } catch (IOException e) {
                result = "ERROR REPEAT AGAIN!";
                return result;
            }

            return result;
        } else
            return "\n\tERROR!\n the user is exsited before or the group does not exist\n";
    }

    public String deleteUser(String userName) {

        if(searchInFile(userName,"users.txt")) {
            try {
                removeLine(userName, "users.txt");
                process = runtime.exec("sudo deleteUser " + userName);
                result = "\n The user is deleted _____SUCCESSFULLY____ \n";
            } catch (IOException e) {
                result = "ERROR REPEAT AGAIN!";
                return result;
            }
            return result;
        }else
            return "\n ERROR! \n User does not exist to delete \n";
    }

    public String assignUserToSpecificGroup(String userName, ArrayList<String> groups) {
        try {
            String groupName = " ";

            for (int i = 0; i < groups.size(); i++) {
                groupName = groupName + groups.get(i);

                if (i < groups.size() - 1)
                    groupName += ",";
            }
            process = runtime.exec("sudo usermod -a -G" + groupName + " " + userName);
            result = " \n The user is addedd _____SUCCESSFULLY____ to " + groupName + " group \n";
        } catch (Exception e) {
            result = "ERROR!";
            return result;
        }
        return result;
    }

    public String createGroup(String groupName) {
        if (!searchInFile(groupName, "groups.txt")) {
            try {
                writeInTextFile(groupName, "groups.txt");
                process = runtime.exec("sudo groupadd " + groupName);
                result = "\nThe group is created _____SUCCESSFULLY____\n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        } else
            return "\n ERROR! the group is exsited before \n";

    }

    public String deleteGroup(String groupName) {

        if(searchInFile(groupName,"groups.txt")) {
            try {
                removeLine(groupName,"groups.txt");
                process = runtime.exec("sudo deleteGroup " + groupName);
                result = "\n The group is deleted _____SUCCESSFULLY____ \n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        }else
            return "\n ERROR! group doesn't exist to delete \n";
    }


    public String changePassword(String userName, String password) {
        if(searchInFile(userName,"users.txt")) {
            try {
                process = runtime.exec("echo -e \'" + password + "\\n" + password + "\' | passwod " + userName);
                result = "\nThe password is updated \n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        }else
            return "\n ERROR! user doesn't exist ";
    }

    public String changeFullName(String userName, String fullname) {
        if(searchInFile(userName,"users.txt")) {
            try {
                process = runtime.exec("sudo chfn -f " + fullname + " " + userName);
                result = "\n Fullname is updated _____SUCCESSFULLY____ \n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        }else
            return "\n ERROR user doesn't exist ";
    }

    public String changeAddressNumber(String userName, String AddressNum) {
        if (searchInFile(userName, "users.txt")) {
            try {
                process = runtime.exec("sudo chfn -h " + AddressNum + " " + userName);
                result = "\nHome number is updated _____SUCCESSFULLY____ \n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        }else
            return "-------- Sorry user doesn't exist ";
    }


    public String changePostalCode(String userName, String roomnum) {

        try {
            process = runtime.exec("sudo chfn -r " + roomnum + " " + userName);
            result = "\nRoom number is updated _____SUCCESSFULLY____ \n";
        } catch (IOException e) {
            result = "ERROR!";
            return result;
        }
        return result;
    }

    public String changeTaskToDo(String userName, String TaskToDo) {
        if (searchInFile(userName, "users.txt")) {
            try {
                process = runtime.exec("sudo chfn -w " + TaskToDo + " " + userName);
                result = "\nWork number is updated _____SUCCESSFULLY____ \n";
            } catch (IOException e) {
                result = "ERROR!";
                return result;
            }
            return result;
        }
        else
            return "\n ERROR! user doesn't exist ";
    }


    public String simpleGui(String fpath) {
        try {
            process = runtime.exec("less " + fpath);

            input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            result = "";
            while ((line = input.readLine()) != null)
                result += "\n" + line;

        } catch (IOException e) {
        }
        if (result.equals(""))
            result = "no such directory";

        return result;
    }


    public static boolean writeInTextFile(String userName, String path) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter((new FileWriter(new File(path), true)));
            writter.println(userName);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            writter.close();
        }

    }

    public static ArrayList<String> readFile(String path) {
        ArrayList<String> infile = new ArrayList<>();
        Scanner reader = null;
        try {
            reader = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            return infile;
        }
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            infile.add(line);
        }
        reader.close();
        return infile;
    }

    public static boolean searchInFile(String data, String path) {
        ArrayList<String> infile = null;
        infile = readFile(path);
        for (int i = 0; i < infile.size(); i++) {
            if (infile.get(i).equals(data))
                return true;
        }
        return false;
    }

    public static boolean removeLine(String data, String path) {
        Scanner reader = null;
        PrintWriter writter = null;
        File file = new File(path);
        String line;
        ArrayList<String> infile = new ArrayList<>();

        try {
            reader = new Scanner(file);

            while (reader.hasNext()) {

                line = reader.next();
                if (line.equals(data)){
                    continue;
                }
                infile.add(line);

            }
            reader.close();
            writeInTextFileAfterRemove(infile, path);
            return true;
        } catch (IOException e) {
            return false;
        }
    }private static void writeInTextFileAfterRemove(ArrayList<String> infile, String path) {
        PrintWriter writter = null;
        try {
            writter = new PrintWriter(new FileWriter(new File(path), false));
            for (int i = 0; i < infile.size(); i++) {
                writter.println(infile.get(i));
            }
        } catch (IOException e) {
        } finally {
            writter.close();
        }
    }


}




