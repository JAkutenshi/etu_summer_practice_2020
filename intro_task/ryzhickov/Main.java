import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static boolean checkExpression(String string) {

        Stack stack = new Stack();

        for (char i : string.toCharArray()) {
            if (i == '(' || i == '{' || i == '[' || i == 'x' || i == '+') {
                stack.push(i);
            } else {
                String newString = "";
                while (true) {
                    char topElement = stack.top();
                    stack.pop();
                    if (topElement == '(' && i == ')' || topElement == '{' && i == '}' || topElement == '[' && i == ']') {
                        if (!checkSubstring(newString)) {
                            return false;
                        }
                        stack.push('x');
                        break;
                    } else {
                        if (stack.getCountStack() == 0) {
                            if (!checkSubstring(newString)) {
                                return false;
                            }
                        }
                    }
                    newString += topElement;
                }
            }
        }

        if (stack.getCountStack() != 0) {
            String newString = "";
            while (stack.getCountStack() != 0) {
                char topElement = stack.top();
                newString += topElement;
                stack.pop();
            }
            if (!checkSubstring(newString)) {
                return false;
            }
        }

        return stack.getCountStack() == 0;
    }

    static boolean checkSubstring(String name) {
        int count = name.length();
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                if (name.charAt(i) != 'x') {
                    return false;
                }
            } else {
                if (i % 2 == 1) {
                    if (name.charAt(i) != '+') {
                        return false;
                    }
                }
            }
        }
        if (count == 0) {
            return false;
        }
        return count % 2 != 0;

    }

    static boolean checkExtraneousSigns(String name) {
        for (char j : name.toCharArray()) {
            if (j != '{' && j != '}' && j != '[' && j != ']' && j != '(' && j != ')' && j != 'x' && j != 'y' && j != 'z' &&
                    j != '+' && j != '-') {
                return false;
            }
        }

        return name.length() != 0;
    }


    public static void main(String[] args) throws IOException {

        int your_choose = 0;

        System.out.println("If you want to enter data from a file, enter \'1\'");
        System.out.println("If you want to enter data manually, enter \'2\'");

        Scanner in = new Scanner(System.in);
        your_choose = in.nextInt();

        if (your_choose == 2) {
            System.out.println("Input string");
            Scanner in2 = new Scanner(System.in);
            String string = in2.nextLine();
            System.out.print("Result: ");
            mainCheck(string);
        }

        if (your_choose == 1) {
            try {
                // нужно указать путь к файлу
                BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Alex\\Desktop\\test.txt"));
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    System.out.print("Test \"" + currentLine + "\" Result: ");
                    mainCheck(currentLine);
                }
                bufferedReader.close();
            } catch (java.io.FileNotFoundException fileInputStream) {
                System.out.println("File not found");
            }
        }
    }

    public static void mainCheck(String name) {
        if (checkExtraneousSigns(name)) {
//            name = name.replace('{', '(');
//            name = name.replace('[', '(');
//            name = name.replace('}', ')');
//            name = name.replace(']', ')');
            name = name.replace('y', 'x');
            name = name.replace('z', 'x');
            name = name.replace('-', '+');
            System.out.println(checkExpression(name));
        } else {
            System.out.println("extraneous signs or empty string");
        }
    }
}
