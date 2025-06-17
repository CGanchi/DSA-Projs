// Camron Ganchi
// DSA 2025

import java.util.*;
import java.io.*;
public class MakeOptimalBST {

    static ArrayList<ArrayList<BST>> allOptimals;
    static int numKeys;

    public static ArrayList<BST> makeSingletons(ArrayList<Integer> keys, ArrayList<Double> probs){
        ArrayList<BST> singles = new ArrayList<BST>();
        // create and add all singletons to an array, initialize some vars, and add the singletons to our optimals
        for (int i = 0; i < keys.size(); i++) {
            singles.add(new BST(keys.get(i), probs.get(i)));
        }
        numKeys = singles.size();
        allOptimals = new ArrayList<ArrayList<BST>>();
        allOptimals.add(singles);
        return singles;
    }


        private static BST getOneOptimal(int size, int startPos) {
            //search for the best tree of size size starting from the startPos (looking for lowest cost)
            BST bTree = null;
            double bCost = Double.MAX_VALUE;

            //tries every key as the root

            for (int i = 0; i < size; i++) {
                int rPos = startPos + i;
                BST root = new BST(allOptimals.get(0).get(rPos)); // root singleton BST

                boolean real = true;

                // Deal with Left subtree if it exists
                if (i > 0) {
                    BST lefttree = new BST(allOptimals.get(i - 1).get(startPos));
                    //check if it is a valid BST
                    real = root.addLeft(lefttree) && real;
                }

                // Deal with right subtree if it exists
                if (i < size - 1) {
                    BST rightSubtree = new BST(allOptimals.get(size - i - 2).get(rPos + 1));
                    //check if it is a valid BST
                    real = root.addRight(rightSubtree) && real;
                }

                // if valid BST and cheapest then update best cost and tree
                if (real && root.getCost() < bCost) {
                    bCost = root.getCost();
                    bTree = root;
                }
            }
            return bTree;
        }

    private static ArrayList<BST> getAllOptOfSize(int size) {

        //create all optimal BST that are size size for every start position

        ArrayList<BST> l = new ArrayList<BST>();
        for (int i = 0; i <= numKeys - size; i++) {
            BST best = getOneOptimal(size, i);
            l.add(best);
        }
        allOptimals.add(l);
        return l;
    }


    public static BST getOptimalTree(ArrayList<Integer> keys, ArrayList<Double> probs) {
        //create starting singletons
        makeSingletons(keys, probs);

        //get optimal trees of all sizes up to max size don't need to include size 1
        //because already made the singletons
        for (int i = 2; i <= numKeys; i++) {
            getAllOptOfSize(i);
        }
        // get final optimal tree
        return allOptimals.get(numKeys - 1).get(0);
    }

    /* Do not alter */
    private static ArrayList<Integer> readInKeys(String fileName){
        ArrayList<Integer> keys = new ArrayList<Integer>();
        try {
            String keyText = "";
            Scanner in = new Scanner(new File(fileName));
            keyText = in.nextLine();
            String[] keyStAr = keyText.split(", ");
            keyStAr[0] = keyStAr[0].substring(1);
            int numKeys = keyStAr.length;
            int len = keyStAr[numKeys - 1].length();
            keyStAr[numKeys - 1] = keyStAr[numKeys - 1].substring(0, len - 1);
            for (int i = 0; i < keyStAr.length; i++) {
                keys.add(Integer.valueOf(keyStAr[i]));
            }
            System.out.println(keys);
        }
        catch(Exception e) { System.out.println(e); }
        return keys;
    }
    /* Do not alter */
    private static ArrayList<Double> readInProbs(String fileName){
        ArrayList<Double> probs = new ArrayList<Double>();
        try {
            String probText = "";
            Scanner in = new Scanner(new File(fileName));
            probText = in.nextLine();
            String[] probStAr = probText.split(", ");
            probStAr[0] = probStAr[0].substring(1);
            int numKeys = probStAr.length;
            int len = probStAr[numKeys - 1].length();
            probStAr[numKeys - 1] = probStAr[numKeys - 1].substring(0, len - 1);
            for (int i = 0; i < probStAr.length; i++) {
                probs.add(Double.valueOf(probStAr[i]));
            }
            System.out.println(probs);
        }
        catch(Exception e) { System.out.println(e); }
        return probs;
    }

    /* Do not alter */
    public static void main(String[] args) {
        BST opt = getOptimalTree(readInKeys("Keys0.txt"), readInProbs("Probs0.txt"));
        opt.preOrder();
        System.out.println();
        System.out.println(opt.getCost());

    }


}
