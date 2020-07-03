public class Stack {
    private int count;
    private Node lastElement;

    Stack() {
        count = 0;
        lastElement = new Node(' ');
    }

    public void push(char i){
        count++;
        Node node = new Node(i);
        node.setPrev(lastElement);
        lastElement.setNext(new Node(i));
        lastElement = node;
    }

    public void pop(){
        if(count > 0){
            count--;
            lastElement = lastElement.getPrev();
        }
    }

    public int getCountStack(){
        return count;
    }

    char top(){
        return lastElement.getData();
    }
}
