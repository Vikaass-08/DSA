package DetectCycleDirectedGraphDFS;
import java.util.*;

/*

To detect cycle in directed graph we need an extra array (dfsVisited) to
keep the track to the node visited during the curr traversal

ex:
   1 -> 2
   2 -> 3
   3 -> 4 
   4 -> 5
   3 -> 6
   6 -> 5

If we traverse this using normal dfs traversal for undirected graph we 
will get wrong ans

so we need an extra array to keep the track of all the nodes visited during
current traversal

*/

public class DirectedCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int V = sc.nextInt();
        int E = sc.nextInt();
        for(int i = 0; i < V+1; i++)
            list.add(i, new ArrayList<Integer>());
        for(int i = 0; i < E; i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            list.get(u).add(v);
        }
        if(new Graph().isCyclic(V, list) == true)
            System.out.println("1");
        else System.out.println("0");

    }
}

class Graph{
    private boolean checkCycle(int node,  ArrayList<ArrayList<Integer>> adj, int vis[], int dfsVis[]) {
        vis[node] = 1; 
        dfsVis[node] = 1; 
        
        for(Integer it: adj.get(node)) {
            if(vis[it] == 0) {
                if(checkCycle(it, adj, vis, dfsVis)) {
                    return true; 
                }
            } 
            // If the node has been visited and it is a part of current traversal
            else if(dfsVis[it] == 1) {
                return true; 
            }
        }
        // Marked dfsVis[node] = false because we have completed the subtree traversal
        dfsVis[node] = 0;
        return false; 
    }
    public boolean isCyclic(int N, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[N]; 
        int dfsVis[] = new int[N]; 
        
        for(int i = 0;i<N;i++) {
            if(vis[i] == 0) {
                if(checkCycle(i, adj, vis, dfsVis) == true) return true; 
            }
        }
        return false; 
    }
}
