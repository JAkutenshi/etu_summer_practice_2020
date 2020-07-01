package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter tree:");
        Scanner scanner = new Scanner(System.in);
        String treeString = scanner.nextLine();

        if (checkString(treeString) == 0){
            System.out.println("Invalid tree");
            return;
        }

        Tree tree = new Tree(treeString);
        if (!tree.isValid()){
            System.out.println("Invalid tree");
            return;
        }

        System.out.println(tree.findRep());

        System.out.println("Your tree:");
        tree.print();
    }

    public static int checkString(String string){
        int size = string.length();
        size++;
        if (size > 0 && ((size & (size-1))) == 0)
            return 1;
        else
            return 0;
    }

}
