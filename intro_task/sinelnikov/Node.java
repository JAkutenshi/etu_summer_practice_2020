public class Node {
    private long key;
    private int left;
    private int right;
    public long getKey(){
        return this.key;
    }
    public int getRight(){
        return this.right;
    }
    public int getLeft(){
        return this.left;
    }
    public void setKey(long key){
        this.key = key;
    }
    public void setLeft(int left){
        this.left = left;
    }
    public void setRight(int right){
        this.right = right;
    }
}
