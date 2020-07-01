package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Tree {
    private Node root;
    private int size;
    private boolean valid;

    Tree(String string){
        root = new Node(string.charAt(0));
        size = 1;
        valid = true;
        int i = 1;
        while(i < string.length()){
            for (int j = 0; j < (int)(Math.pow(2, size)/2); j++){
                Node t = takeNode(j);
                if (t.getValue() == '#' && (string.charAt(i) != '#' || string.charAt(i+1) != '#')){
                    valid = false;
                    return;
                }
                t.setLeft(new Node(string.charAt(i)));
                i++;
                t.setRight(new Node(string.charAt(i)));
                i++;
            }
            size++;
        }
    }

    public void print(){
        recursivePrint(root, 0);
    }

    public int getSize(){
        return size;
    }

    public Node getRoot(){
        return root;
    }

    public boolean isValid(){
        return valid;
    }

    private void recursivePrint(Node node, int size){
        for (int i = 0; i < size; i++)
            System.out.print("\t");
        if (node.getValue() == '#'){
            System.out.println("<>");
        }
        else {
            System.out.println("<" + node.getValue() + ">");
        }
        if (node.getLeft() != null)
            recursivePrint(node.getLeft(), size+1);
        if (node.getRight() != null)
            recursivePrint(node.getRight(), size+1);
    }

    public Node takeNode(int j){
        Node n = root;
        int oldSize = size;

        while (size > 1){
            if (Math.pow(2, size-1)/2 > j){
                n = n.getLeft();
            }
            else {
                n = n.getRight();
                j -= Math.pow(2, size-1)/2;
            }
            size--;
        }

        size = oldSize;
        return n;
    }

    public String findRep(){

        HashMap<Character, Integer> result = new HashMap<Character, Integer>();
        rep(result, root);

        String resultString = "";
        for (Map.Entry<Character, Integer> entry: result.entrySet()){
            if (entry.getKey().equals('#'))
                continue;
            if (entry.getValue() > 1){
                resultString += entry.getKey() + " (" + entry.getValue() + ")\n";
            }
        }

        if (resultString.equals("")){
            resultString += "No such objects";
        }

        return resultString;

    }

    private void rep(HashMap<Character, Integer> result, Node node){
        if (!node.isVisited()){
            if (!result.containsKey(node.getValue())){
                result.put(node.getValue(), 0);
            }
            result.put(node.getValue(), result.get(node.getValue())+1);
            node.setVisit();
        }
        if (node.getLeft() != null){
            rep(result, node.getLeft());
        }
        if (node.getRight() != null){
            rep(result, node.getRight());
        }
    }

}
// abcdef#ddeeff##ddddeeeeffff####

