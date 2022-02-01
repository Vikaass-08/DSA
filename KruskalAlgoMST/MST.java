package KruskalAlgoMST;
import java.util.*; 

/*

 Krushkal algorithm says that take all edges and sort them according to the weights.
 Then go through every edges and make a connection if it doesn't form a cycle.

 It work even if the graph is not connected
 If the graph is not connected then it will find the mst of every subgraph separately.

  Time complexity: O(E*log(E))
  Space complexity: O(E)
*/

public class MST {
    public static void main(String[] args) {
        int n = 5;
        ArrayList<Node> adj = new ArrayList<>();
        adj.add(new Node(0, 1, 2));
        adj.add(new Node(0, 3, 6));
        adj.add(new Node(1, 3, 8));
        adj.add(new Node(1, 2, 3));
        adj.add(new Node(1, 4, 5));
        adj.add(new Node(2, 4, 7));


        Krushal obj = new Krushal(); 
        obj.KruskalAlgo(adj, n);
    }
}


class Krushal{
    
    // O(log(E))
    public int findPar(int u, int parent[]) {
        if(u==parent[u]) return u;
        return parent[u] = findPar(parent[u], parent); 
    }
    
    // O(E*(4*alpha)) == O(E * log(E))
    public void union(int u, int v, int parent[], int rank[]) {
        // Find the parent of both the node
        u = findPar(u, parent); 
        v = findPar(v, parent);
        
        // add the larger subtree to the smaller so that minimum time is taken to find them.
        if(rank[u] < rank[v]) {
            parent[u] = v;
        }
        else if(rank[v] < rank[u]) {
            parent[v] = u; 
        }
        else {
            parent[v] = u;
            rank[u]++; 
        }
    }
    
    public void KruskalAlgo(ArrayList<Node> adj, int N) {
        // Sort all the edges
        Collections.sort(adj, (a, b) -> Integer.compare(a.w, b.w));
        int parent[] = new int[N]; 
        int rank[] = new int[N];

        // make every nodes parent of itself and make their rank to 0
        for(int i = 0;i<N;i++) {
            parent[i] = i; 
            rank[i] = 0; 
        }

        int costMst = 0;
        ArrayList<Node> mst = new ArrayList<Node>();
        
        // Loop through all the edges
        for(Node it: adj) {
            // check if these nodes are already connected in the disjoint set or not
            // If both has same parent means they belong to same subset
            // If both has different parent then add a pair between them in disjoint set
            if(findPar(it.u, parent) != findPar(it.v, parent)) {
                costMst += it.w; 
                mst.add(it); 
                union(it.u, it.v, parent, rank); 
            }
        } 
        
        // print the minimum weights and edges edges needed to connect all the graph
        System.out.println(costMst);
        for(Node it: mst) {
            System.out.println(it.u + " " +it.v); 
        }
    }
    
}

class Node{
    int u;
    int v;
    int w;
    public Node(int _u, int _v, int _w){
        this.u = _u;
        this.v = _v;
        this.w = _w;
    }
}