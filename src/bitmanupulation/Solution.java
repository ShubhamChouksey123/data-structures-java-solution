package bitmanupulation;

public class Solution {

    public String addBinary(String a, String b) {

        int m = a.length();
        int n = b.length();

        StringBuilder stringBuilder = new StringBuilder();

        int index1 = m - 1;
        int index2 = n - 1;
        int carry = 0;
        while (index1 >= 0 || index2 >= 0) {
            int sum = 0;
            if (index1 >= 0) {
                int num1 = a.charAt(index1) - '0';
                sum = sum + num1;
                index1--;
            }

            if (index2 >= 0) {
                int num2 = b.charAt(index2) - '0';
                sum = sum + num2;
                index2--;
            }
            sum += carry;
            if (sum >= 2) {
                carry = 1;
            }else{
                carry = 0;
            }
            stringBuilder.append(sum % 2);
        }

        if(carry >0){
            stringBuilder.append("1");
        }

        stringBuilder.reverse();

        return stringBuilder.toString();
    }
}
