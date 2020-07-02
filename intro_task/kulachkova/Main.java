import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String tree1 = in.nextLine();
        Tree first = new Tree(tree1);
        String tree2 = in.nextLine();
        Tree second = new Tree(tree2);
        if (Tree.similar(first, second)) {
            System.out.println("Бинарные деревья подобны");
        } else {
            System.out.println("Бинарные деревья не подобны");
        }
    }
}
