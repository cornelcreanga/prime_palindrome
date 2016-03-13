package com.ccreanga.example.primepalindrome.arithm.primes;

import com.ccreanga.example.primepalindrome.arithm.palindrome.Palindrome;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class PrimeWorker implements Runnable{

    private long start,end;
    private OutputStream out;
    private static ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);

    public PrimeWorker(long start, long end,OutputStream collector) {
        this.start = start;
        this.end = end;
        out = collector;
    }

    public void run() {
        SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(start,end);
        for (long i = start; i < end; i++) {
             if (sieve.isPrime(i)){
                 buffer.putLong(0, i);
                 try {
                     out.write(buffer.array());
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
        }
    }
}