package SegmentTree;

/*

We are given an array of integers and we q queries, in each query we have to do one of the following operations.
1) update the element at ith index with the given value:  update(i, val);
2) get the sum of all the elements in the idx range [i, j]: getSum(i, j);



 Segment Tree is a tree like structure in which each node store the answer of some part
 > In the above question the segment tree will store the sum of element in the particular range

Eg: arr = [1, 3, 6, 0, 10, 7]

Segment Tree:
node : val(start idx, end idx)
if(start idx == end idx) -> It means that it is the leaf node at position idx in the array

                               27 (0, 5)

                      10(0, 2)            17(3, 5)

               4(0, 1)    6(2, 2)     10(3, 4)     7(5, 5)



*/


public class SegmentTree {
    private static int[] segTree;
    public static void main(String[] args) {
        int[] arr = {1, 3, 6, 0, 10, 7};
        int n = arr.length;
        segTree = new int[4 * n];
        
        // Build a Segment Tree
        buildSegmentTree(arr, 0, n - 1, 0);
        
        // Get sum of numbers in range 2 to 4 which are present in the array
        int getSumInRange = getSum(0, n - 1, 2, 4, 0);
        System.out.println(getSumInRange);
        
        // Update the value at idx 1 and change it to 5
        update(0, n - 1, 5, 1, 0);
        
        getSumInRange = getSum(0, n - 1, 0, 2, 0);
        System.out.println(getSumInRange);
        
    }
    
    public static void buildSegmentTree(int[] arr, int s, int e, int np){
        // If we reach leaf Node
        if(s == e){
            segTree[np] = arr[s];
            return;
        }
        
        int mid = (s + e) / 2;
        
        // Build left child
        buildSegmentTree(arr, s, mid, 2 * np + 1);
        
        // Build right child
        buildSegmentTree(arr, mid + 1, e, 2 * np + 2);
        
        // Current value is addition of left side and right side
        segTree[np] = segTree[2 * np + 1] + segTree[2 * np + 2];
    }
    
    public static int getSum(int ss, int se, int queryStart, int queryEnd, int si){
        // Complete overlap
        if(queryStart <= ss && queryEnd >= se){
            return segTree[si];
        }
        
        // NO overlap
        if(se < queryStart || ss > queryEnd){
            return 0;
        }
        
        // Get the overlap from left part and the overlap from right part
        int mid = (ss + se) / 2;
        return getSum(ss, mid, queryStart, queryEnd, 2 * si + 1) +
                getSum(mid + 1, se, queryStart, queryEnd, 2 * si + 2);
    }
    
    public static void update(int ss, int se, int val, int idx, int np){
        
        // If the idx is out of the range of the array then no changes are done
        if(idx < ss || idx > se) return;
        
        // Found the idx 
        if(ss == se) {
            segTree[np] = val;
            return;
        }
        
        int mid = (ss + se) / 2;
        
        // Check if idx > mid || idx <= mid and move accordingly into left child or right child
        if(idx > mid){
            update(mid + 1, se, val, idx, 2 * np + 2);
        }
        else {
            update(ss, mid, val, idx, 2 * np + 1);
        }
        
        // After changing the value at idx, while coming back to the top we keep on updating
        // the node vale with the sum of left and right child
        segTree[np] = segTree[2 * np + 1] + segTree[2 * np + 2];
    }
}