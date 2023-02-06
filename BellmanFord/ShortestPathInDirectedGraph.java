package BellmanFord;
import java.util.*;

/*
   Bellman Ford is used to find the minimum distance of every node from the source node in the directed weighted graph.
   It works even if we have negative weights, where Dikstra Algorithm fails.
   
Disadvantages: 
  It will not work if we have a cycle with overall negative weights.

  for ex: (0, -1) -> (1, 30) -> (2, -5) -> (0, -40)   => (node, weights)
  so overall weight in this cycle = -1 + 30 - 5 - 40 < 0 , so bellman algo fails here.

  The bellman ford algo can be used to detect if there is a negative weighted cycle present in the directed graph or not.

 Time Complexity = O(EV)
*/

public class ShortestPathInDirectedGraph {
    private static List<List<Node>> adj;
    private static int[] dist;
    private static boolean[] visited;
    
    public static void main(String[] args) {
        Scanner fs = new Scanner(System.in);
        int vertices = fs.nextInt();
        int edges = fs.nextInt();
        adj = new ArrayList<>();
        for(int i = 0; i < vertices; i++) adj.add(new ArrayList<>());
        
        for(int i = 0; i < edges; i++){
            int u = fs.nextInt();
            int v = fs.nextInt();
            int dist = fs.nextInt();
            adj.get(u).add(new Node(v, dist));
        }
        
        int src = fs.nextInt();
        
        if(BellmanFord(src, vertices, edges)){
            System.out.println("Found a negative weighted cycle in the graph");
        }
        else{
            for(int i = 0; i < vertices; i++){
                System.out.println(src + " -> " + i + " = " + dist[i]);
            }
        }
    }
    
    
    private static boolean BellmanFord(int src, int vertices, int edges){
        dist = new int[vertices];
        visited = new boolean[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        
        // We go through all the edges present in the graph, (N - 1) times and relax them.
        // We do this (N - 1) times cozz the max distance of any node from the source can be (N - 1)
        // 
        for(int i = 0; i < vertices - 1; i++){
            Arrays.fill(visited, false);
            dfs(src);
        }
        
        Arrays.fill(visited, false);
        
        // After relaxing the nodes (n - 1) times we should get the minimum dist of each nodes but
        // If we do one more traversal and found any changes, then that means there is a negative cycle in the graph
        if(dfs(src)) return true;
        
        return false;
    }
    
    private static boolean dfs(int src){
        visited[src] = true;
        boolean changes = false;
        for(Node node: adj.get(src)){
            
            // Relaxation Step
            if(dist[src] + node.dist < dist[node.v]){
                changes = true;
                dist[node.v] = dist[src] + node.dist;
            }
            if(!visited[node.v]) {
                boolean k = dfs(node.v);
                if(k) changes = true;
            }
        }
        
        // changes variable is true if we made any changes in the distance of the node
        return changes;
    }
}

class Node{
    int v;
    int dist;
    public Node(int v, int dist){
        this.dist  = dist;
        this.v = v;
    }
}
