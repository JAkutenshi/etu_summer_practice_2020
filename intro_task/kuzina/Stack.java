


public class Stack{
    private int values[];
    private int count;

    public Stack(int num){
        values = new int[num];
        count = 0;
    }

    public int push(int i){
        values[count] = i;
        count++;
        return 1;
    }

    public int pop(){
        int t = values[count - 1];
        values[count - 1] = 0;
        count--;
        return t;
    }

    public int getCount(){
        return count;

    }

    public int getHead(){
        return values[0];

    }
}

