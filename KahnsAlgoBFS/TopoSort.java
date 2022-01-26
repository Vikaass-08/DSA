package KahnsAlgoBFS;
import java.util.*;

/*

   This Algorithm can be used to find the topo sort or cyclic directed graph

 InDegree Array stores the total number of incoming edges to that node

 Topo sort is the arrangement of the nodes in increasing order of their indgree

  Time Complexity: O(V + E)
  Space Complexity: O(V)

*/

public class TopoSort {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
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
                System.out.println("It contains Cycle");
            else System.out.println("No cycles are present");
        }
    }
}

class Graph{
     public boolean isCyclic(int N, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> topo = new ArrayList<>(); 
        int indegree[] = new int[N]; 
        for(int i = 0;i<N;i++) {
            for(Integer it: adj.get(i)) {
                indegree[it]++; 
            }
        }
        
        Queue<Integer> q = new LinkedList<Integer>(); 
        for(int i = 0;i<N;i++) {
            if(indegree[i] == 0) {
                q.add(i); 
            }
        }
        int cnt = 0;
        while(!q.isEmpty()) {
            Integer node = q.poll(); 
            cnt++; 
            topo.add(node);
            for(Integer it: adj.get(node)) {
                indegree[it]--; 
                if(indegree[it] == 0) {
                    q.add(it); 
                }
            }
        }
        
        // cnt == N means their are no cycles in the directed graph and the topo sort is correct
        if(cnt == N) {
            return false;
        } 
        return true; 
    }
}
