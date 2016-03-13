package com.ccreanga.example.primepalindrome.arithm.primes;

import junit.framework.TestCase;

import java.util.Arrays;


public class BinarySearchTest extends TestCase {


    public void testLowerBound() throws Exception {

        for (int counter = 0; counter < 10000; counter++) {
            int toLookFor=0,position=0,foundPosition=0;
            int[] a = null;
            try {
                int len = 1 + (int) (Math.random() * 10);
                a = new int[len];
                int first = (int) (Math.random() * 10);
                for (int i = 0; i < len; i++) {
                    a[i] = first + (int) (Math.random() * 10)+1;
                    first = a[i];
                }

                toLookFor = a[(int) (Math.random() * len)];
                position = BinarySearch.lowerBound(a, 0, a.length, toLookFor);
                foundPosition = 0;
                for (int i = 0; i < len - 1; i++) {
                    if ((a[i] == toLookFor) && (a[i + 1] == toLookFor)) {
                        foundPosition = i+1;
                        break;
                    }
                    if ((a[i] <= toLookFor) && (a[i + 1] > toLookFor)) {
                        foundPosition = i;
                        break;
                    }

                }
                if (a[len-1]==toLookFor)
                    foundPosition = len-1;
                if (a[0]==toLookFor)
                    foundPosition = 0;


                if (position != foundPosition) {
                    System.out.println(toLookFor);
                    System.out.println(position + ":" + foundPosition);
                    System.out.println(Arrays.toString(a));
                    break;
                }
                assertEquals(position, foundPosition);
            }catch(Exception e){
                System.out.println(toLookFor);
                System.out.println(position + ":" + foundPosition);
                System.out.println(Arrays.toString(a));
                e.printStackTrace();
                break;

            }
        }
    }

    public void testUpperBound() throws Exception {

        for (int counter = 0; counter < 10000; counter++) {
            int toLookFor=0,position=0,foundPosition=0;
            int[] a = null;
            try {
                int len = 1 + (int) (Math.random() * 10);
                a = new int[len];
                int first = (int) (Math.random() * 10);
                for (int i = 0; i < len; i++) {
                    a[i] = first + (int) (Math.random() * 10)+1;
                    first = a[i];
                }

                toLookFor = a[(int) (Math.random() * len)];
                position = BinarySearch.upperBound(a, 0, a.length, toLookFor);
                foundPosition = 0;
                for (int i = 0; i < len - 1; i++) {
                    if ((a[i] == toLookFor) && (a[i + 1] == toLookFor)) {
                        foundPosition = i+1;
                        break;
                    }
                    if ((a[i] < toLookFor) && (a[i + 1] >= toLookFor)) {
                        foundPosition = i+1;
                        break;
                    }

                }
                if (a[len-1]==toLookFor)
                    foundPosition = len-1;
                if (a[0]==toLookFor)
                    foundPosition = 0;


                if (position != foundPosition) {
                    System.out.println(toLookFor);
                    System.out.println(position + ":" + foundPosition);
                    System.out.println(Arrays.toString(a));
                    break;
                }
                assertEquals(position, foundPosition);
            }catch(Exception e){
                System.out.println(toLookFor);
                System.out.println(position + ":" + foundPosition);
                System.out.println(Arrays.toString(a));
                e.printStackTrace();
                break;

            }
        }
    }

}