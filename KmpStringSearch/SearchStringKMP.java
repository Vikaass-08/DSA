package KmpStringSearch;

/*


Time Complexity: O(M + N)
Space Complexity: O(M)



For the pattern “ABCDE”, 
lps[] is [0, 0, 0, 0, 0]

For the pattern “AABAACAABAA”, 
lps[] is [0, 1, 0, 1, 2, 0, 1, 2, 3, 4, 5]

For the pattern “AAACAAAAAC”, 
lps[] is [0, 1, 2, 0, 1, 2, 3, 3, 3, 4] 


*/

public class SearchStringKMP {
    public static void main(String[] args) {
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        boolean result = KMP(str.toCharArray(), subString.toCharArray());
        System.out.println(result ? "It is a substring" : "No, It is not a substring");
    }
    
    public static boolean KMP(char[] str, char[] subString){
        int lps[] = new int[subString.length];
        computeTemporaryArray(subString, lps);
        int i = 0;
        int j = 0;
        while(i < str.length && j < subString.length){
            if(str[i] == subString[j]){
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
    
    public static void computeTemporaryArray(char[] pattern, int[] lps){
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
        
    }
}
