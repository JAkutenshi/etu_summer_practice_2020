import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Enter postfix notation for calculation result:");
        Scanner str = new Scanner(System.in);
        String input = str.nextLine();
        int len = input.length();
        Stack head= new Stack(len);

        for(int i = 0; i < len; i++ ){
            int sum = 0;
            if(Character.isDigit(input.charAt(i)))
                head.push(input.charAt(i) - 48);
            else {
                if(head.getCount() < 2){
                    System.out.println("There is something wrong with entered postfix notation, can't calculate result");
                    return;
                }
                switch (input.charAt(i)) {
                    case '+': {
                        sum += head.pop();
                        sum += head.pop();
                        head.push(sum);
                        break;
                    }
                    case '-': {
                        sum += head.pop();
                        sum = -1 * sum + head.pop();
                        head.push(sum);
                        break;
                    }
                    case '/': {
                        sum += head.pop();
                        sum = head.pop() / sum;
                        head.push(sum);
                        break;
                    }
                    case '*': {
                        sum += head.pop();
                        sum = sum * head.pop();
                        head.push(sum);
                        break;
                    }

                }
            }
        }

        if(head.getCount() > 1){

            System.out.println("There is something wrong with entered postfix notation, can't calculate result");
        }
        else {
            System.out.println(head.getHead());
        }



    }
}
