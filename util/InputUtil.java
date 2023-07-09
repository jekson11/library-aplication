package javadasar.studycase.perpusoop.util;

import java.util.Scanner;

public class InputUtil {

    static Scanner scanner = new Scanner(System.in);
    public static String input(String inputUser){

        System.out.print(inputUser + ": " );
        return scanner.nextLine();
    }

    public static String next(){
        String next = input("Continue? (y/n)");
        if (next.equalsIgnoreCase("y")){
            return next;
        } else if (next.equalsIgnoreCase("n")) {
            return next;
        }
        return "\ncommand not understandable";
    }

    public static boolean keepAddingM(){
        while(true){
            String keepAdding = InputUtil.input("Keep adding books? (y/n)");
            if (keepAdding.equalsIgnoreCase("y")){
                return true;
            }else if (keepAdding.equalsIgnoreCase("n")){
                return false;
            }else {
                System.out.println("\ncommand not understandable");
            }
        }
    }
}
