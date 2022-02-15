package BinaryLifting;
import java.util.*;

/*
1) It is used to find the kth ancestor of a node in a tree.
2) If there are Q queries and for every query we need to find the kth ancestor of node x.
   Then, we can't use normal way to find the ancestor i.e moving k step upward of node x.
   At worst case this could take O(N*Q) complexity, we need to find the ancestor in log(N).
3) We can precompute and store the parents of every node. Only store parent at the pos 2^k.
4) we can form any number using the sumation of 2^k, so we are going to use this fact and store  
   only ancestors of the node x which are at 2^k postion up from the node x.
   EX. 4 = 2^2, 5 = 2^2 + 2^0
   so if we want 5th ancestor of any node x, then we will say that get node y which is the 2^2 ancestor
   of node x. After we get to y we will say get your 2^0 ancestor.

EX: 1 -> 3 -> 4 -> 5
    3rd ancestor of 5 is 3
    1st ancestor of 5 is 4
    2nd ancestor of 4 is 1
*/

public class BinaryLifting {
    
    static int[][] dp;  // dp[i][j] means 2^j ancestor of node i.
    static int MAX;    // MAX no of ancestor in term of 2^k
    
    public static void main(String[] args) {
        Scanner fs = new Scanner(System.in);
        int nodes = fs.nextInt();
        int[] parent = new int[nodes];
        for(int i = 0; i < nodes; i++){
            // parent[i] stores the parent of the ith node.
            parent[i] = fs.nextInt();
        }
        
        // Build the tree for the binary lifting
        TreeAncestor(nodes, parent);
        
        int n = fs.nextInt();
        int k = fs.nextInt();
        
        // Get the kth ancestor of the node x.
        int ancestor = getKthAncestor(n, k);
        
        System.out.println("The Kth ancestor of " + n + " = " + ancestor);
    }
    
    private static void TreeAncestor(int n, int[] parent){
        MAX = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
        dp = new int[n][MAX];
        for(int[] x: dp){
            Arrays.fill(x, -1);
        }
        
        for(int i = 1; i < parent.length; i++){
            preprocessing(parent[i], i);
        }
    }
    
    private static void preprocessing(int u, int v){
        // Store ancestor of node v at 2^0
        dp[v][0] = u;
        for(int i = 1; i <= MAX; i++){
            // Store ancestor at position 2^i
            dp[v][i] = dp[dp[v][i - 1]][i - 1];
            
            // Return when parent is at at the top i.e it is the root node.
            if(dp[v][i] == -1) return;
        }
    }
    
    private static int getKthAncestor(int node, int k){
        for(int i = 0; i < MAX; i++){
            if((k & (1<<i)) > 0){
                node = dp[node][i];
                if(node == -1) return -1;
            }
        }
        return node;
    }
}
