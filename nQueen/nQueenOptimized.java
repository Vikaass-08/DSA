package nQueen;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class nQueenOptimized
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		BitSet col = new BitSet(100);
		BitSet ld = new BitSet(100);
		BitSet rd = new BitSet(100);
		 nQueen(n, 0, col, ld, rd);
		System.out.println(count);
	}
	
	private static int count = 0;
	
	public static void nQueen(int n, int cr, BitSet col, BitSet ld, BitSet rd){
	    if(cr == n){
	        count++;
	        return;
	    }
	    
	    for(int c = 0; c < n; c++){
	        if(!col.get(c) && !ld.get(cr-c+n-1) && !rd.get(cr+c)){
	            col.set(c);
	            ld.set(cr-c+n-1);
	            rd.set(cr + c);
	            nQueen(n, cr + 1, col, ld, rd);
	            col.clear(c);
	            ld.clear(cr-c+n-1);
	            rd.clear(cr + c);
	        }
	    }
	    
	   // return -1;
	}

}
