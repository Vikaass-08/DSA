package MST;

import java.util.*;

public class PrimAlgo {
    static void primsAlgo(ArrayList<ArrayList<Node>> adj, int N)  {
        // Key array is used to store the min path weight
        int key[] = new int[N];
        
        // parent array is used to keep the track of connection that have been made to form a MST
        int parent[] = new int[N]; 
        
        // mstSet array is used to keep the track of node that are added to the Mst
        boolean mstSet[] = new boolean[N]; 
        for(int i = 0;i<N;i++) {
            key[i] = 100000000; 
            mstSet[i] = false; 
        }

        // pq is used to get the minimum weight node that has not been added to the mst yet.
        PriorityQueue<Node> pq = new PriorityQueue<Node>(N, new Node());

        // start from first node so its distance will be 0, and it will have no parent, since it is the start node.
        key[0] = 0;
        parent[0] = -1; 
        
        // add this node to the pq with weight as 0
        pq.add(new Node(key[0], 0)); 
        
        while(!pq.isEmpty()) {
            
            // poll the minimum weighted node from pq and change its state in mstSet.
            int u = pq.poll().getV();
            mstSet[u] = true;

            // get all the adjacent node of 'u' and 
            // check if it has been added to mst or not 
            // if not, then check if their weight from 'u' is less than that of it weight stored in the key array
            // if yes, then change its parent to 'u' and update it's new value in the key array and
            // add this node to the pq.
            for(Node it: adj.get(u)) {
                if(mstSet[it.getV()] == false && it.getWeight() < key[it.getV()]) {
                    parent[it.getV()] = u; 
                    key[it.getV()] = it.getWeight(); 
                    pq.add(new Node(it.getV(), key[it.getV()]));
                }
            }
        }
          
        System.out.println(" Edge " + "  weight");
        for(int i = 1;i<N;i++) {
            System.out.println(parent[i] + " - " + i + " => " + adj.get(i).get(parent[i]).getWeight()); 
        }
    }
    
    public static void main(String args[]) {
        int n = 5;
        ArrayList<ArrayList<Node> > adj = new ArrayList<ArrayList<Node> >();
        for (int i = 0; i < n; i++) 
            adj.add(new ArrayList<Node>());

        adj.get(0).add(new Node(1, 2));
        adj.get(1).add(new Node(0, 2));

        adj.get(1).add(new Node(2, 3));
        adj.get(2).add(new Node(1, 3));

        adj.get(0).add(new Node(3, 6));
        adj.get(3).add(new Node(0, 6));

        adj.get(1).add(new Node(3, 8));
        adj.get(3).add(new Node(1, 8));

        adj.get(1).add(new Node(4, 5));
        adj.get(4).add(new Node(1, 5));

        adj.get(2).add(new Node(4, 7));
        adj.get(4).add(new Node(2, 7));

        primsAlgo(adj, n); 
		
    }
}
