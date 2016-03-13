package com.ccreanga.example.primepalindrome.arithm.primes;

public class BinarySearch {

    public static int lowerBound(int[] a, int left, int right, int key){
        if (a.length==1)
            return 0;
        while(left<right){

            if ((left+1)==right)
                return a[right]==key?right:left;
            int middle = left + (right - left) / 2;
            if (a[middle]<key)
                left = middle;
            else
                right = middle;
        }
        return left;
    }

    public static int upperBound(int[] a, int left, int right, int key){
        if (a.length==1)
            return 0;

        while(left<right){
            if ((left+1)==right)
                return a[left]==key?left:right;
            int middle = left + (right - left) / 2;
            if (a[middle]<key)
                left = middle;
            else
                right = middle;
        }
        return left;
    }




}
