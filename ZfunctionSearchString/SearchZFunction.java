package ZfunctionSearchString;

/*

Time Complexity: O(M + N)
Space Complexity: O(M + N)

 
Count the number of times pattern is present in the original String.
Ex: str = "ababcdsdabbdabyu"
    pattern = "ab"
    
output: pattern(ab) occurs 4 times in the str


Example of z function output:

c[]  = "aaaaaa"
Z[]  = {x, 5, 4, 3, 2, 1}

c[] = "aabaacd"
Z[] = {x, 1, 0, 2, 1, 0, 0}

c[] = "abababab"
Z[] = {x, 0, 6, 0, 4, 0, 2, 0}


*/

public class SearchZFunction {
    public static void main(String[] args) {
        String str = "abcxabcdabcdabcy";
        String pattern = "ab";
        String searchPattern = pattern + "$" + str;
        int len = searchPattern.length();
        int[] z = calculateZFunction(searchPattern.toCharArray());
        int foundPattern = 0;
        for(int i = 0; i < len; i++){
            if(z[i] == pattern.length()) {
                // i the the start position where pattern match start
                foundPattern++;
            }
        }
        System.out.println("Total Number of times pattern was found in the str: " + foundPattern);
    }
    
    public static int[] calculateZFunction(char[] c){
        int n = c.length;
        
        // Z stores the count of elements from ith index that has a matching prefix from the start
        int[] z = new int[n];
        int left = 0;
        int right = 0;
        for(int i = 0; i < n; i++){
            if(i > right){
                left = right = i;
                while(right < n && c[right - left] == c[right]) right++;
                z[i] = right - left;
                right--;
            }
            else{
                int k = i - left;
                if(z[k] < right - i + 1){
                    z[i] = z[k];
                }
                else{
                    left = i;
                    while(right < n && c[right - left] == c[right]) right++;
                    z[i] = right - left;
                    right--;
                }
            }
        }
        return z;
    }
}
