package com.ccreanga.example.primepalindrome.arithm.primes;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CacheStore {

    private static int[] cacheArray;
    public static final int INTEGER_PRIMES_NO = 105097564;

    static{
        try{
            cacheArray = loadCache(new File("/var/tmp/palindrome/cache.bin"));
        }catch (Exception e){
            cacheArray = regenerateCache();
            try {
                new File("/var/tmp/palindrome/").mkdirs();
                Util.saveArray(new File("/var/tmp/palindrome/cache.bin"),cacheArray);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    private static int[] loadCache(File file) throws IOException {
        int[] cache = new int[INTEGER_PRIMES_NO];
        try(RandomAccessFile raf = new RandomAccessFile(file,"r")) {
            ByteBuffer buf = raf.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, 4 * cache.length);
            for (int i = 0; i < cache.length; i++) {
                cache[i] = buf.getInt(i*4);
            }
        }
        return cache;
    }

    private static int[] regenerateCache(){
        PrimeSieve sieve = new PrimeSieve(Integer.MAX_VALUE);
        int[] cache = new int[INTEGER_PRIMES_NO];
        cache[0]=2;
        int counter = 1;
        for (int i = 0; i < Integer.MAX_VALUE/2; i++) {
            if (sieve.get(i))
                cache[counter++]=i*2+1;
        }
        return cache;
    }

    public static int[] getCache(){
        return cacheArray;
    }


}
