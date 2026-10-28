package dev.shubham.algorithms;

import java.util.*;

class Solution {


    public static int knapsack(int W, int val[], int wt[]) {
        // code here

        int n = val.length;
        int[][] dp = new int[n][W + 1];
        for(int[] row : dp){
            Arrays.fill(row, 0);
        }
        System.out.println("");

        for(int index = 0 ; index < n ; index++){
            for(int weight = 0; weight <= W ; weight++){


                if(index == 0){
                    dp[index][weight] = (wt[index] <= weight) ? val[0] : 0 ;
                    continue;
                }

                // excluded the element at index
                dp[index][weight] = dp[index-1][weight];

                System.out.println("\nMid dp[" + index + "]["+ weight+"] : " + dp[index][weight]);

                if(wt[index] <= weight){
                    // include the element at index

                    System.out.println("Enetred the if condition for index : " + index + " and weight : " + weight);
                    dp[index][weight] = Math.max( dp[index][weight], val[index] + dp[index - 1][weight- wt[index]] );
                }
                System.out.println("End dp[" + index + "]["+ weight+"] : " + dp[index][weight]);
            }
        }

        return dp[n-1][W];
    }

    public static void main(String[] args) {
        int W = 4;
        int val[] = {1, 2, 3};
        int wt[] = {4, 5, 1};
        System.out.println(knapsack(W, val, wt));
    }
}
