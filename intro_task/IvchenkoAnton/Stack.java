class Stack {
	private int mSize; 
    private boolean[] stackArray;
    private int top;
 
    public Stack(int m) {
        this.mSize = m;
        stackArray = new boolean[mSize];
        top = -1;
    }
    public void addElement(boolean element) {
        stackArray[++top] = element;
    }
    public boolean deleteElement() {
        return stackArray[top--];
    }
    public boolean readTop() {
        return stackArray[top];
    }
    public boolean isEmpty() {
        return (top == -1);
    }
    public boolean isFull() {
        return (top == mSize - 1);
    }
}
