package nKings;

import java.util.*;
import java.lang.*;

class NKings {
  public static void main (String[] args) throws java.lang.Exception {

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();

    boolean[][] grid = new boolean[n][n];
    for(boolean[] row : grid){
      Arrays.fill(row, false);
    }

    nKings(grid, n, 0, 0, 0);
    System.out.println(ans);
  }
  private static int ans = 0;
  private static void nKings(boolean[][] grid, int n, int r, int c, int count){
    if(count == n){
      ans++;
      // display(grid, n);
      return;
    }
    for(int i = r; i < n; i++){
      for(int j = (i == r)? c : 0; j < n; j++){
        if(isItSafe(grid, i, j, n)){
          grid[i][j] = true;
          nKings(grid, n, i, j+2, count+1);
          grid[i][j] = false;
        }
      }
    }
  }

  private static void display(boolean[][] grid, int n){
    for(int i=0;i<n;i++){
      for(int j=0;j<n;j++){
        System.out.print(grid[i][j] ? "K " : ". ");
      }
      System.out.println();
    }
    System.out.println("\n \n");
  }

  private static boolean isItSafe(boolean[][] grid, int r, int c, int n){
    if(c - 1 >= 0 && grid[r][c-1]) return false;
    if(c - 1 >= 0 && r - 1 >= 0 && grid[r-1][c-1]) return false;
    if(r - 1 >= 0 && grid[r -1][c]) return false;
    if(c + 1 < n && r - 1 >= 0 && grid[r-1][c+1]) return false;
    if(c + 1 < n && grid[r][c+1]) return false;
    if(c + 1 < n && r + 1 < n && grid[r+1][c+1]) return false;
    if(r + 1 < n && grid[r+1][c]) return false;
    if(c - 1 >= 0 && r + 1 < n && grid[r+1][c-1]) return false;
    return true;
  }
}

