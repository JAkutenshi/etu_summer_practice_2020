import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        HierarchyList list1;
        HierarchyList list2;

        System.out.println("Enter 2 hierarchy lists in form (a(bc)d)");

        list1 = readList();
        list2 = readList();

        System.out.println(list1.equals(list2)
                            ? "Lists are identical."
                            : "Lists are NOT identical.");
    }

    private static HierarchyList readList() {
        Scanner scanner = new Scanner(System.in);
        String line;

        for (;;) {
            System.out.print("Enter list: ");
            line = scanner.nextLine();
            if (HierarchyList.isValidList(line)) {
                return new HierarchyList(line);
            }
            System.out.println("Invalid list!");
        }
    }
}
