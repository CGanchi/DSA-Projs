// Camron Ganchi
// DSA 2025
// Min Spanning Trees

import java.util.*;
import java.io.*;
public class WUGraph {
    private int[][] adjMatrix;
    private int numVerts;

    public WUGraph(String fileName, int verts) {
        File f = new File(fileName);
        numVerts = verts;
        adjMatrix = new int[verts][verts];
        try {
            Scanner in = new Scanner(f);
            for (int i = 0; i < verts; i++) {
                for (int j = 0; j < verts; j++) {
                    adjMatrix[i][j] = in.nextInt();
                }
            }
            in.close();
        }
        catch(Exception E) { System.out.println(E); }
    }


    public void printGraph() {
        for (int i = 0; i < numVerts; i++) {
            for (int j = 0; j < numVerts; j++) {
                System.out.print(adjMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int[][] Prims() {
        int[][] mst = new int[numVerts][numVerts]; // This will store the MST
        boolean[] inMST = new boolean[numVerts]; // Keeps track of which vertices are in the MST
        int[] key = new int[numVerts]; // Stores the smallest weight edge for each vertex
        int[] parent = new int[numVerts]; // Stores the parent of each vertex in the MST

        // Set all keys to a large number (infinity) and mark all vertices as not in MST
        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1; // The first vertex has no parent

        // Loop to add vertices to the MST one by one
        for (int count = 0; count < numVerts - 1; count++) {
            int u = findMinKeyVertex(key, inMST);
            inMST[u] = true; // Add vertex with smallest weight edge to the MST

            // Update the key values and parent for adjacent vertices
            for (int v = 0; v < numVerts; v++) {
                if (adjMatrix[u][v] != 0 && !inMST[v] && adjMatrix[u][v] < key[v]) { //if a new shortest path to a vertex is found and
                    // its not already in the MST and its not 0 then we can update its key and parent
                    key[v] = adjMatrix[u][v];
                    parent[v] = u;
                }
            }
        }

        // Build the MST adjacency matrix
        for (int i = 1; i < numVerts; i++) {
            mst[parent[i]][i] = adjMatrix[parent[i]][i];
            mst[i][parent[i]] = adjMatrix[i][parent[i]];
        }

        return mst;
    }

    // Helper function to find the vertex with the smallest key value
    private int findMinKeyVertex(int[] key, boolean[] inMST) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVerts; v++) {
            if (!inMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        WUGraph graph = new WUGraph("graph1.txt", 7);
        System.out.println("OG Graph:");
        graph.printGraph();

        int[][] mst = graph.Prims();

        System.out.println("MST:");
        for (int i = 0; i < mst.length; i++) {
            for (int j = 0; j < mst.length; j++) {
                System.out.print(mst[i][j] + " ");
            }
            System.out.println();
        }
    }


}
