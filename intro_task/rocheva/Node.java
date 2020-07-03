package com.company;

public class Node {
    private char value;
    private Node left;
    private Node right;
    private boolean existFlag;
    private boolean isVisit;

    Node(char value){
        this.value = value;
        left = null;
        right = null;
        isVisit = false;
    }

    public void setVisit(){
        isVisit = true;
    }

    public boolean isVisited(){
        return isVisit;
    }

    public boolean isExistFlag(){
        return existFlag;
    }

    public char getValue(){
        return value;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
    }

}
