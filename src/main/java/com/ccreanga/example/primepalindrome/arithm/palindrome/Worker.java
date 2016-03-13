package com.ccreanga.example.primepalindrome.arithm.palindrome;

public class Worker implements Runnable{
    public void run() {
        long l = Integer.MAX_VALUE*451941231l;
        Palindrome palindrome = new Palindrome();
        for (int i = 0; i <10_000_000; i++) {
            palindrome.checkPalindromeShortNumber(l);
        }
    }
}
