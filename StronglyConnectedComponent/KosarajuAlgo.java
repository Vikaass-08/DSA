package StronglyConnectedComponent;
import java.util.*;

/*

Strongly connected components in the directed graph are the set of nodes such that if we take any node from that set and 
perform a traversal then we can visit each and every node in that set.


To check SSCs:

1) find the topological order of the graph and store it into a stack. 0(V + E)
2) create a new graph that is a copy of the previous but all the edges in reverse direction. 0(V + E)
3) pop nodes one by one from the topological stack and perform a dfs on that node ( this will give all the connected components of that node).
4) keep repeating the 3rd step until the stack is empty. O(V + E)


Time complexity: O(V + E)
Space complexity: O(V + E)

*/

public class KosarajuAlgo {
    public static void main(String[] args) {
        int n = 5;
        ArrayList<ArrayList<Integer> > adj = new ArrayList<>();
		
        for (int i = 0; i < n; i++) 
            adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(1).add(3);
        adj.get(3).add(4);
        adj.get(4).add(1);


        Graph g = new Graph(); 
        g.kosaRaju(adj, n); 
    }
}

class Graph{
    
    public void dfs(int node, Stack<Integer> topo, ArrayList<ArrayList<Integer>> adj, int vis[]) {
        vis[node] = 1;
        for(Integer it : adj.get(node)) {
                if(vis[it] == 0) {
                        dfs(it, topo, adj, vis); 
                }
        }
        topo.push(node); 
    }
    
    // Print the SSCs of the graph
    private void SCCs(int node, ArrayList<ArrayList<Integer>> transpose, int vis[]) {
        vis[node] = 1;
        System.out.print(node + " "); 
        for(Integer it : transpose.get(node)) {
                if(vis[it] == 0) {
                        SCCs(it, transpose, vis); 
                }
        }
    }
    
    void kosaRaju(ArrayList<ArrayList<Integer>> adj, int n)
    {
        int vis[] = new int[n]; 
        Stack<Integer> topo = new Stack<>(); 
        
        //Find the topological order of the graph
        for(int i = 0;i<n;i++) {
            if(vis[i] == 0) {
                dfs(i, topo, adj, vis); 
            }
        }

        ArrayList<ArrayList<Integer> > transpose = new ArrayList<>();
		
        for (int i = 0; i < n; i++) 
            transpose.add(new ArrayList<>());

        // Create a new graph with the existing with with all the edges directions reversed
        for(int i = 0;i<n;i++) {
            vis[i] = 0; 
            for(int it: adj.get(i)) {
                transpose.get(it).add(i); 
            }
        }

        // Use dfs on the topo sort and new graph to find that if the nodes are connected or not
        // this will give us the strongly connected components
        while(topo.size() > 0) {
            int node = topo.peek(); 
            topo.pop(); 
            if(vis[node] == 0) {
                System.out.print("SCC: "); 
                SCCs(node, transpose, vis);
                System.out.println(); 
            }
        }

    }
    
}
