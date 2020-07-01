import java.io.IOException;
import java.util.Scanner;

public class binTreeProgram {
    public static void main(String[] args) throws IOException {
        int number_of_nodes = 0;
        Scanner scanner = new Scanner(System.in);
        number_of_nodes = scanner.nextInt();
        Node[] nodes = new Node[number_of_nodes];
        initialization(nodes,number_of_nodes,scanner);
        if(checkOnCorrectness(nodes))
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");
    }

    public static void initialization(Node[] nodes,int number_of_nodes,Scanner scanner) throws IOException {
        long key = 0;
        int left,right;
        for(int i = 0;i < number_of_nodes;i++){
            key = scanner.nextInt();
            left = scanner.nextInt();
            right = scanner.nextInt();
            nodes[i] = new Node();
            nodes[i].setKey(key);
            nodes[i].setLeft(left);
            nodes[i].setRight(right);
        }
    }

    public static boolean checkOnCorrectness(Node[] nodes){
        binTree b = new binTree();
        if(nodes.length != 0){
            b = b.enterBT(b,nodes,0);
            long[] keys = new long[nodes.length];
            if(!b.inOrder(b,keys))
                return false;
            long prev = -2147483648;
            for (int i = 0; i < keys.length; i++) {
                if (keys[i] < prev) {
                    return false;
                }
                prev = keys[i];
            }
            return true;
        }
        else
            return true;
    }
}
