package SegmentTree;


/*

We Use Lazy Propagation in segment Tree when we want to update multiple nodes at the same time.

We are given an array of integers and we q queries, in each query we have to do oen of the following operations.
1) Add value x in all the nodes in range (i, j)
2) get the sum of all the elements in the idx range [i, j]: getSum(i, j);



*/


class Construct {
  public static void main(String[] args) {
    int arr[] = {1, 3, 5, 7, 9, 11};
    int n = arr.length;
    LazyPropagation tree = new LazyPropagation(4 * n + 1);
    tree.buildTree(arr, 0, n - 1, 0);

    System.out.println("Sum of values in given range = " +
            tree.getSum(0, n - 1, 1, 3, 0));

    tree.updateRange(0, n - 1, 1, 5, 10, 0);

    System.out.println("Updated sum of values in given range = " +
            tree.getSum(0, n - 1, 1, 3, 0));

  }
}


class LazyPropagation {
  private int[] segTree, lazyTree;

  public LazyPropagation(int n) {
    this.segTree = new int[n];
    this.lazyTree = new int[n];
  }

  public void buildTree(int[] input, int start, int end, int idx) {
    if(start > end) return;
    else if(start == end) {
      this.segTree[idx] = input[start];
      return;
    }

    int mid = (start + end) / 2;
    buildTree(input, start, mid, 2 * idx + 1);
    buildTree(input, mid + 1, end, 2 * idx + 2);

    this.segTree[idx] = this.segTree[2 * idx + 1] +  this.segTree[2 * idx + 2];
  }

  public void updateRange(int start, int end, int rangeStart, int rangeEnd, int updateVal, int idx){

    // Process the lazy tree
    if(this.lazyTree[idx] != 0) {
      this.segTree[idx] = (end - start + 1) * this.lazyTree[idx];

      if(start != end) {
        this.lazyTree[2 * idx + 1] += this.lazyTree[idx];
        this.lazyTree[2 * idx + 2] += this.lazyTree[idx];
      }

      this.lazyTree[idx] = 0;
    }

    // Out of range
    if(start > end || start > rangeEnd || end < rangeStart) return;

    // range query lies inside the range
    if(start >= rangeStart && end <= rangeEnd) {
      this.segTree[idx] += (end - start + 1) * updateVal;

      if(start != end) {
        this.lazyTree[2 * idx + 1] += updateVal;
        this.lazyTree[2 * idx + 2] += updateVal;
      }

      return;
    }

    // range query overlaps with the range
    int mid = (start + end) / 2;

    updateRange(start, mid, rangeStart, rangeEnd, updateVal, 2 * idx + 1);
    updateRange(mid + 1, end, rangeStart, rangeEnd, updateVal, 2 * idx + 2);

    this.segTree[idx] = this.segTree[2 * idx + 1] + this.segTree[2 * idx + 2];
  }

  public int getSum(int start, int end, int rangeStart, int rangeEnd, int idx) {
    // Process the lazy tree
    if(this.lazyTree[idx] != 0) {
      this.segTree[idx] += (end - start + 1) * this.lazyTree[idx];

      if(start != end) {
        this.lazyTree[2 * idx + 1] += this.lazyTree[idx];
        this.lazyTree[2 * idx + 2] += this.lazyTree[idx];
      }

      this.lazyTree[idx] = 0;
    }

    // Out of range
    if(start > end || start > rangeEnd || end < rangeStart) return 0;

    // range query lies inside the range
    if(start >= rangeStart && end <= rangeEnd) return this.segTree[idx];

    // range query overlaps with the range
    int mid = (start + end) / 2;

    return getSum(start, mid, rangeStart, rangeEnd, 2 * idx + 1) +
            getSum(mid + 1, end, rangeStart, rangeEnd, 2 * idx + 2);

  }
}
