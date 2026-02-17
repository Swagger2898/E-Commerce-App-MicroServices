package com.ecart.payment.config;

public class practis {



    public static void main(String[] args){

        String s ="100101011010111";
        int k =5;
        System.out.println(maxCount(s,k));

    }

    public static int maxCount(String s , int target) {


        int left =0;
        int maxCount =0;
        int zeroCount =0;

        for(int right=0; right<s.length();right++){

            if(s.charAt(right)=='0'){
                zeroCount++;
            }
            if(zeroCount>target){
                if(s.charAt(left)=='0'){
                  zeroCount--;
                }
                left++;
            }

            maxCount= Math.max(maxCount, right-left+1);
        }
   return maxCount;


    }
}
