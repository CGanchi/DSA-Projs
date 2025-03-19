import java.io.File;
import java.util.*;
// Topological Sort, Traversals

public class UnweightedGraph {
    private int[][] graph;
    private int numVerts;

    public UnweightedGraph() {
        int[][] adj =  {{0,1,1,1,0,0,0}, //1
                {0,0,0,1,1,0,0}, //2
                {0,0,0,0,0,1,0}, //3
                {0,0,1,0,0,1,1},
                {0,0,0,1,0,0,1},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0}};

        graph = adj;
        numVerts = adj.length;
    }


    private ArrayList<ArrayList<Integer>> adjacencyListIncoming(){
        ArrayList<ArrayList<Integer>> incoming = new ArrayList<ArrayList<Integer>>();
        for (int j = 0; j < numVerts; j++) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < numVerts; i++) {
                if (graph[i][j] == 1) { temp.add(i); }
            }
            incoming.add(temp);
        }
        return incoming;
    }

    private int getFirstNoIncoming(ArrayList<ArrayList<Integer>> incoming, ArrayList<Integer> sort) {
        for (int i = 0; i < numVerts; i++) {
            if(incoming.get(i).size()==0 && !sort.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> topologicalSort(){
        ArrayList<ArrayList<Integer>> incoming = adjacencyListIncoming();
        ArrayList<Integer> sort = new ArrayList<Integer>();
        //find vertex
        while(sort.size()<numVerts) {
            System.out.println(sort);
            System.out.println(incoming);
            int next = getFirstNoIncoming(incoming, sort);
            sort.add(next);
            for(int i = 0; i<numVerts; i++) {
                incoming.get(i).remove((Integer) next);
            }
        }
        return sort;

    }

    public void depthFirstTraversal() {
        System.out.println(depthFirstHelper(0, new ArrayList<Integer>()));
    }
    private ArrayList<Integer> depthFirstHelper(int start, ArrayList<Integer> visited) {
        visited.add(start);
        for (int j = 0; j < numVerts; j++) {
            if (graph[start][j] == 1 && !visited.contains(j)) {
                depthFirstHelper(j, visited);
            }
        }
        return visited;
    }

    public void breadthFirstTraversal(){
        ArrayList<Integer> visited = new ArrayList<>();
        ArrayList<Integer> toVisit = new ArrayList<>();

        // Start from the first vertex (0)
        toVisit.add(0);

        while (!toVisit.isEmpty()) {
            int current = toVisit.remove(0); // Remove from front like dequeue
            if (!visited.contains(current)) {
                visited.add(current);
                for (int j = 0; j < numVerts; j++) { //checking through adj matrix to find connections to add to the queue
                    if (graph[current][j] == 1 && !visited.contains(j) && !toVisit.contains(j)) {
                        toVisit.add(j);
                    }
                }
            }
        }

        System.out.println(visited);
    }

    public static void main(String[] args) {
        UnweightedGraph g = new UnweightedGraph();
        g.breadthFirstTraversal();

    }
}
