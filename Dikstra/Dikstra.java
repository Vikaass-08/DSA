package Dikstra;

import java.util.*;

//Dikstra algorith if use to traverse the weighted graph with non negative weight.
public class Dikstra {
    
    private static int[][] adj;
    public static void main(String[] args) {
        adj = new int[][]{ { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        int src = 0;
        DikstraAlgo(src, adj.length);
        
    }
    
    public static void DikstraAlgo(int src, int n){
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
    public static int findMin(int[] distance, boolean visited[]){
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
    public static void print(int[] distance, int src){
        System.out.println("Distance of every node from source (" + src + ")");
        for(int i = 0; i < distance.length; i++){
            System.out.println(i + " => " + distance[i]);
        }
    }
}
