/* Complete Binary Tree Starter
 * DSA
 */
//I will not discuess this assesment with any other student until the assesment has been returned by my teacher.
//Singatures: Camron Ganchi, Sean Wang, Albert Yao

import java.util.*;

public class CBT{
    private ArrayList tree;

    public CBT(){
        tree = new ArrayList();
    }
    // you may add methods, but they must be private helper methods
    // you may not change the signature of any of the required methods
    // traversals
    public void inOrder(){
        inOrderHelper(0);
        System.out.println();
    }

    private void inOrderHelper(int pos) {
        if (pos >= tree.size() || tree.get(pos) == null) {
            return;
        }
        if(getLeft(pos)!= -1) {
            postOrderHelper(getLeft(pos));
        }
        System.out.print(tree.get(pos) + " ");

        if(getRight(pos)!= -1) {
            postOrderHelper(getRight(pos));
        }

    }
    public void preOrder() {
        preOrderHelper(0);
        System.out.println();
    }

    private void preOrderHelper(int pos) {
        if (pos >= tree.size() || tree.get(pos) == null) {
            return;
        }
        System.out.print(tree.get(pos) + " ");
        if(getLeft(pos)!= -1) {
            postOrderHelper(getLeft(pos));
        }
        if(getRight(pos)!= -1) {
            postOrderHelper(getRight(pos));
        }

    }

    public void postOrder() {
        postOrderHelper(0);
        System.out.println();

    }
    private void postOrderHelper(int pos) {
        if (pos >= tree.size() || tree.get(pos) == null) {
            return;
        }
        if(getLeft(pos)!= -1) {
            postOrderHelper(getLeft(pos));
        }
        if(getRight(pos)!= -1) {
            postOrderHelper(getRight(pos));
        }
        System.out.print(tree.get(pos) + " ");
    }



    public void breadthFirst() {
        String str = "{";
        for(int i = 0; i<tree.size(); i++){
            if(i == tree.size()-1){
                str += tree.get(i) + "}";
            }
            else {
                str += tree.get(i) + ", ";
            }
        }
        System.out.println(str);
    }

    // accessors
    private int getLeft(int pos) {
        if(pos*2 + 1 > tree.size()) {

        return -1;

    }

    else {

        return pos * 2 + 1;

    }}
    private int getRight(int pos) {
        if(pos*2 + 2 > tree.size()) {

            return -1;

        }

        else {

            return pos * 2 + 2 ;

        }
    }
    private int getParent(int pos) {
        if(pos == 0) {

            return 0;

        }

        else {

            return (pos-1)/2;

        }
    }

    // mutators
    public void add(Object o){
        tree.add(o);
    }
    public boolean remove(Object o){
        Object finaleaf = tree.get(tree.size()-1);
        int counter = 0;
        boolean isIn = false;
        for(int i = 0; i<tree.size(); i++){
            if(tree.get(i).equals(o)){
                isIn = true;
                break;
            }
            counter++;
        }
        if(isIn) {
            tree.set(counter, finaleaf);
            tree.remove(tree.size() - 1);
            return true;
        }
        return false;
    }

    public static void main(String[] args){
        CBT Ctree = new CBT();
        Ctree.add("A");
        Ctree.add("B");
        Ctree.add("D");
        Ctree.add("E");
        Ctree.add("H");

        System.out.println(Ctree.getLeft(0));
        System.out.println(Ctree.getRight(0));
        Ctree.breadthFirst();
        Ctree.postOrder();
        Ctree.preOrder();
        Ctree.inOrder();


    }
}
