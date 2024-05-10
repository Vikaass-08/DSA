package UnionRankFind;
import java.util.*;

/*
 
DisJoint Set can be used to find if 2 point belong to the same set or not.
It has two important operations. UNION, FIND

Union: In this we connect two nodes to each other based on their rank.
Find: This operation is used to find the absolute parent of the node. i.e. if
      A -> B -> C then the absolute parent of C is A.
      In each time we do a find operation we keep on updating the parent of each node. i.e.
      if we do a find operation on node C then we make sure that every node directly point to their absolute
      parent.


   Time Complexity: O(long(N))

*/

public class DisjointSet {
    public static void main(String[] args) {
        Scanner fs = new Scanner(System.in);
        int noOfNodes = fs.nextInt();
        UnionFind uf = new UnionFind(noOfNodes);
        
        // Add the connections between nodes
        int noOfConnections = fs.nextInt();
        for(int i = 0; i < noOfConnections; i++){
            int u = fs.nextInt();
            int v = fs.nextInt();
            uf.union(u, v);
        }
        
        // Check if the nodes are connected or not
        int a = fs.nextInt();
        int b = fs.nextInt();
        if(uf.find(a) == uf.find(b)){
            System.out.println("Both these nodes belong to the same set");
        }
        else{
            System.out.println("These nodes are not connected, they are in different sets");
        }
    }
}

class UnionFind{
    
    pair[] DSU;
    public UnionFind(int size){
        this.DSU = new pair[size];
        
        // This hold all the nodes in the tree along with their
        // absolute parent and their rank
        for(int i = 0; i < size; i++){
            this.DSU[i] = new pair(-1, 0);
        }
    }
    
    public int find(int u) {
        //Absolute parent
        if(DSU[u].parent == -1) return u;
        
        // Keep updating the absolute parent of every node in the path
        return DSU[u].parent = find(DSU[u].parent); 
    }
    
    public void union(int u, int v) {
        u = find(u);
        v = find(v);
        
        // If u and v are connected then don't add a edge between them 
        // else it will create a cycle.
        if(u == v) return;
        
        if(DSU[u].rank > DSU[v].rank){
            DSU[v].parent = u;
        }
        else if(DSU[v].rank > DSU[u].rank){
            DSU[u].parent = v;
        }
        else {
            DSU[u].parent = v;
            DSU[u].rank++;
        }
    }
}

class pair{
    int parent;
    int rank;
    public pair(int _p, int _r){
        this.parent = _p;
        this.rank = _r;
    }
}
