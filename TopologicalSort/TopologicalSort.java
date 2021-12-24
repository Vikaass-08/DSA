package TopologicalSort;

import java.util.*;
import java.io.*;


/*
    topological Sort: 1) acyclic,  2) directed graph,  3) It should follow ancestor-predeccor
    
    Topological sort is not uniqe
    All the parent should come before the child.

      a
    b   c
      d
    e   f

   Various valid topo sort : abcdef or abcdfe or acbdef or acbdfe     In these all parent node comes before the child node

   Time Complexity: O(V + E),  Space Complexity: O(V)
*/

public class TopologicalSort {
    static ArrayList<ArrayList<Integer>> adj;
    static boolean[] visited;
    static Stack<Integer> topoSort;
    
    public static void main(String[] args) {
        int v = 6;
        adj = new ArrayList<>();
        for(int i = 0; i < v; i++) adj.add(new ArrayList<>());
        addEdge(5, 2);
        addEdge(5, 0);
        addEdge(4, 0);
        addEdge(4, 1);
        addEdge(2, 3);
        addEdge(3, 1);
        topologicalSort(v);
    }
    
    public static void addEdge(int v, int w) { adj.get(v).add(w); }
    
    public static void topologicalSort(int v){
        topoSort = new Stack<>();
        visited = new boolean[v];
        for (int i = 0; i < v; i++)
            visited[i] = false;
 
        for (int i = 0; i < v; i++)
            if (visited[i] == false)
                topologicalSortUtil(i);
 
        while (topoSort.empty() == false)
            System.out.print(topoSort.pop() + " ");
    }
    
    public static void topologicalSortUtil(int v){
        visited[v] = true;
        Integer i;
 
        Iterator<Integer> it = adj.get(v).iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i])
                topologicalSortUtil(i);
        }
 
        topoSort.push(v);
    }
}
