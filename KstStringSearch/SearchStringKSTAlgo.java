package KstStringSearch;

import java.util.*;

public class SearchStringKSTAlgo {
    public static void main(String[] args) {
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        boolean result = KMP(str.toCharArray(), subString.toCharArray());
        System.out.println(result ? "It is a substring" : "No, It is not a substring");
    }
    
    public static boolean KMP(char[] str, char[] subString){
        int lps[] = computeTemporaryArray(subString);
        int i = 0;
        int j = 0;
        while(i < str.length && j < subString.length){
            if(str[i] == str[j]){
                i++;
                j++;
            }
            else{
                if(j != 0){
                    j = lps[j - 1];
                }
                else{
                    i++;
                }
            }
        }
        if(j == subString.length) return true;
        return false;
    }
    
    public static int[] computeTemporaryArray(char[] pattern){
        int[] lps = new int[pattern.length];
        int index = 0;
        for(int i = 1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }
            else{
                if(index != 0){
                    index = lps[index - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
}
