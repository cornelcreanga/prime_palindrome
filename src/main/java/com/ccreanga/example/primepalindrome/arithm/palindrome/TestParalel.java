package com.ccreanga.example.primepalindrome.arithm.palindrome;

public class TestParalel {

    public static void main(String args[]) throws InterruptedException {
        for (int i = 1; i <= 8; ) {
            long t1 = System.currentTimeMillis();
            Thread[] threads = new Thread[i];
            for (int counter = 0; counter < threads.length; counter++) {
                threads[counter] = new Thread(new Worker());
                threads[counter].start();
            }
            for (Thread t : threads)
                t.join();
            long t2 = System.currentTimeMillis();
            System.out.println(t2 - t1);
            i *= 2;
            System.gc();
            System.gc();
            System.gc();
        }
    }
}

/**
 984
 1166
 1490
 2621

 899
 960
 1578
 2340

 **/