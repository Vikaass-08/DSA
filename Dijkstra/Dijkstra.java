package Dijkstra;
import java.util.*;

/*

   Dijkstra Algo is used to find the minimum distance of all the nodes from the src Node in a weighted acyclic graph
   Dijkstra Algo fails when there is negative weights in the graph.

   When we have negative edges we use Bellman Ford algorithm

*/

public class Dijkstra {
    
    public static void main(String[] args) {

        int[][] adjMatrix = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } 
                          };
        int src = 0;
        
        // Dijkstra Algo with O(V^2) complexity
        DijkstraNSquare nSquareComplexity = new DijkstraNSquare(adjMatrix, adjMatrix.length);
        nSquareComplexity.DijkstraAlgo(src, adjMatrix.length);
        
        
        
        
        
        // Dijkstra Algo with O( (V + E) * log(V) ) using priority Queue
        int vertices = 5;
        List<List<Node>> adjList = new ArrayList<>();
        for(int i = 0; i < vertices; i++) adjList.add(new ArrayList<>());
        
        adjList.get(0).add(new Node(1, 2));
        adjList.get(1).add(new Node(0, 2));

        adjList.get(1).add(new Node(2, 4));
        adjList.get(2).add(new Node(1, 4));

        adjList.get(0).add(new Node(3, 1));
        adjList.get(3).add(new Node(0, 1));

        adjList.get(3).add(new Node(2, 3));
        adjList.get(2).add(new Node(3, 3));

        adjList.get(1).add(new Node(4, 5));
        adjList.get(4).add(new Node(1, 5));

        adjList.get(2).add(new Node(4, 1));
        adjList.get(4).add(new Node(2, 1));

        DijkstraPriorityQueueSolution dpq = new DijkstraPriorityQueueSolution(vertices, adjList);
        
        System.out.println("Print the Dijkstra Algo version of Priority Queue: ");
        dpq.dijkstra(src);
    }
    
}

