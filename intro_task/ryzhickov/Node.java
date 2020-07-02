public class Node {
    private char data;
    private Node next;
    private Node prev;

    public char getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    Node(char i){
        data = i;
        next = null;
        prev = null;
    }
}
