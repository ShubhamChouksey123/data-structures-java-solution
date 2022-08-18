import java.util.Stack;

public class ProblemSolvingDecodeStrngNew {

    private static final int offset[] = {-1, 0, 1};

    private static int getLastIndexOfNumber(String s, int startIndex, int n) {

        for (int i = startIndex + 1; i < n; i++) {
            char c = s.charAt(i);
            if (c == '[')
                return i - 1;
        }
        return 0;

    }

    private static boolean isANumber(String st) {
        for (int i = 0; i < st.length(); i++) {
            char c = st.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
            if (c == '[') {
                return false;
            }
        }
        return true;
    }

    public static String decodeStringUtil(String s, int n) {

        Stack<String> st = new Stack<>();


        for (int i = 0; i < n; ) {
            System.out.print("i : " + i);
            char c = s.charAt(i);
            if (c == '[' ) {
                st.push(String.valueOf('['));
                i++;
            } else if ( c == ']') {
                i++;
            }
            else if (c <= '9' && c >= '0') {
                int lastIndexOfNumber = getLastIndexOfNumber(s, i, n);
                int number = Integer.parseInt(s.substring(i, lastIndexOfNumber + 1));
                st.add(String.valueOf(number));
                i = lastIndexOfNumber + 1;
            } else {
                String topString = "";
                if (st.size() > 0) {
                    topString = st.pop();
                }

                if(topString.equals("[")){
                    st.push(topString);
                    st.push(String.valueOf(c));
                }
                else if (isANumber(topString)) {
                    st.push(topString);
                    st.push(String.valueOf(c));
                } else {
                    st.push(topString + String.valueOf(c));
                }
                i++;
            }

            System.out.print(" &  st : " + st + "\n");

        }

        System.out.println("st : " + st);

        String ans = "";

        while (st.size() > 0) {
            String topString = st.pop();

            if(topString.equals(String.valueOf('['))){

            }
            else if (!isANumber(topString)) {
                ans =  topString + ans;

            }else{
                int times = Integer.valueOf(topString);
                ans = appendStringNTimes( ans,  times);
            }
        }


        return ans;

    }


    /**
     * abcbc abcbc abcbc
     * @param s
     * @return
     */
    public static String decodeString(String s) {

        int n = s.length();
        return decodeStringUtil(s, n);

    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[][] grid = new int[][]{{9, 9, 8, 1}, {5, 6, 2, 6}, {8, 2, 6, 4}, {6, 2, 2, 2}};

        String ans = decodeString("3[a]2[bc]");
        System.out.println("ans : " + ans);


    }

    private int getClosingParenIndex(String s, int startIndex, int n) {
        for (int i = startIndex + 1; i < n; i++) {
            char c = s.charAt(i);
            if (c == ']')
                return i;
        }
        return 0;
    }

    private static String appendStringNTimes(String appendString, int n) {

        String ans = "";
        while (n > 0) {
            n--;
            ans = ans + appendString;
        }
        return ans;

    }


}
