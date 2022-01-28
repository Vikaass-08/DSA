package Dikstra;
import java.util.*;

/*

   Dikstra Algo is used to find the minimum distance of all the nodes from the src Node in a weighted undirected  acyclic graph

*/

public class Dikstra {
    
    public static void main(String[] args) {
        Scanner fs = new Scanner(System.in);
        
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
        
        // Dikstra Algo with O(V^2) complexity
        DikstraNSquare nSquareComplexity = new DikstraNSquare(adjMatrix, adjMatrix.length);
        nSquareComplexity.DikstraAlgo(src, adjMatrix.length);
        
        
        
        
        
        // Dikstra Algo with O( (V + E) * log(V) ) using priority Queue
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
        
        DikstraPrioriyQueue dpq = new DikstraPrioriyQueue(vertices, adjList);
        
        System.out.println("Print the dikstra's Algo version of Priority Queue: ");
        dpq.dijkstra(src);
    }
    
}


class DikstraNSquare{
    
    int[][] adj;
    int vertices;
    
    // Create a graph
    public DikstraNSquare(int[][] graph, int v){
        this.adj = graph;
        this.vertices = v;
    }
    
    public void DikstraAlgo(int src, int n){
        // Array to store the min distance of every node from the source
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        
        // Array to keep track of the visited node
        boolean visited[] = new boolean[n];
        Arrays.fill(visited, false);
        distance[src] = 0;
        
        for(int i = 0; i < n - 1; i++){
            
            // Find the node which is not visited and is closest to the src node
            int u = findMin(distance, visited);
            visited[u] = true;
            
            for(int v = 0; v < n; v++){
               
                // update the distance of all the node connected to "u" which are not visited && whose distance from src is greater than the
                // distance of "u" from the src + adj[u][v]
                if(!visited[v] && adj[u][v] != 0 && distance[u] != Integer.MAX_VALUE && distance[u] + adj[u][v] < distance[v]){
                    distance[v] = distance[u] + adj[u][v];
                }
            }
        }
        print(distance, src);
    }   
    
    // Find the node with min distance from the src and which is not visited yet.
    public int findMin(int[] distance, boolean visited[]){
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i = 0; i < distance.length; i++){
            if(min >= distance[i] && !visited[i]){
                min = distance[i];
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    // print the distance of all the node from the src
    public void print(int[] distance, int src){
        System.out.println("Distance of every node from source (" + src + ")");
        for(int i = 0; i < distance.length; i++){
            System.out.println(i + " => " + distance[i]);
        }
    }
}



class DikstraPrioriyQueue{
    
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V;
    private List<List<Node> > adj;
  
    public DikstraPrioriyQueue(int V, List<List<Node>> adj) {
        this.adj = adj;
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>((a, b) -> Integer.compare(a.cost, b.cost));
    }
    
    public void dijkstra(int src) {
        this.adj = adj;
        for (int i = 0; i < V; i++)
            dist[i] = Integer.MAX_VALUE;
  
        pq.add(new Node(src, 0));
        dist[src] = 0;
  
        while (settled.size() != V && !pq.isEmpty()) {
            int u = pq.remove().node;
            if(settled.contains(u)) continue;
            settled.add(u);
            e_Neighbours(u);
        }
        
        printDistance(src);
    }
    
    public void e_Neighbours(int u) {
  
        int edgeDistance = -1;
        int newDistance = -1;
  
        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);
  
            // If current node hasn't already been processed
            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;
  
                // If new distance is cheaper in cost
                if (newDistance < dist[v.node])
                    dist[v.node] = newDistance;
  
                // Add the current node to the queue
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }
    
    public void printDistance(int src){
        for(int i = 0; i < dist.length; i++){
            System.out.println(src + " -> " + i + " = " + dist[i]);
        }
    }
}


class Node{
    
    public int node;
    public int cost;
    public Node() {}

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }
}
