import java.util.*;

public class ProblemSolvingDecodeStrng {

    private static final int offset[] = {-1, 0, 1};

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] grid = new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}};

    }


    private int getClosingParenIndex(String s, int startIndex, int n) {
        for(int i = startIndex + 1; i < n ; i++){
            char c = s.charAt(i);
            if(c == ']')
                return i;
        }
        return 0;
    }

    private int getLastIndexOfNumber(String s, int startIndex, int n){

        for(int i = startIndex + 1 ; i < n ; i++){
            char c = s.charAt(i);
            if(c == '[')
                return i-1;
        }
        return 0;

    }


    private String appendStringNTimes(String ans, String appendString, int n){

        while(n > 0){
            n--;
            ans += appendString;
        }
        return ans;

    }

    public String decodeStringUtil(String s, String ans, int startIndex, int n) {

        System.out.println("Function Call ans : " + ans + " & startIndex : " + startIndex );

        int number = 0;
        String ansInner = "";
        for(int i = startIndex ; i < n ; ){
            System.out.println("ans : " + ans + " & ansInner : " + ansInner + " & number : " + number);
            System.out.println("i : " + i );
            char c = s.charAt(i);
            if(c == '['){
                ansInner = decodeStringUtil(s, "", i+1,  n);
            }
            else if(c <= '9' && c >= '0'){
                int lastIndexOfNumber = getLastIndexOfNumber(s, i, n);
                number = Integer.parseInt(s.substring(i, lastIndexOfNumber + 1));
                i = lastIndexOfNumber + 1;
            }
            else if(c == ']'){
                ans = ans + appendStringNTimes(ans, ansInner,  number);
                number = 0;
            }
            else {
                ansInner +=  c;
                i++;
            }
        }
        return ans;

    }

    public String decodeString(String s) {

        int n = s.length();
        return decodeStringUtil( s, "", 0,  n);

    }

    public String decodeStringOld(String s) {

        int n = s.length();
        String ans  = "";

        String num = "";
        for(int i = 0 ; i < n ; ){
            char c = s.charAt(i);
            // System.out.println("i : "+ i + " & num : " + num );
            if(c == '['){
                int closingParIndex = getClosingParenIndex(s, i, n);
                String appendString = s.substring(i+1, closingParIndex);
                // System.out.println("appendString : "+ appendString );
                ans = appendStringNTimes(ans,  appendString,  Integer.parseInt(num));
                num = "";
                // System.out.println("ans : "+ ans );
                i = closingParIndex + 1;
            }
            else {
                num += c;
                i++;
            }
        }
        return ans;

    }

}
