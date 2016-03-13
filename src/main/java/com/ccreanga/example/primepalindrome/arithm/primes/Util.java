package com.ccreanga.example.primepalindrome.arithm.primes;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Util {

    public static void saveArray(File file,int[] ints) throws Exception{
        try(RandomAccessFile raf = new RandomAccessFile(file,"rw")) {
            FileChannel channel = raf.getChannel();
            ByteBuffer buf = channel.map(FileChannel.MapMode.READ_WRITE, 0, 4 * ints.length);
            for (int i : ints) {
                buf.putInt(i);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static byte[] toBytes(long val) {
        byte [] b = new byte[8];
        for (int i = 7; i > 0; i--) {
            b[i] = (byte) val;
            val >>>= 8;
        }
        b[0] = (byte) val;
        return b;
    }

    public static void main(String[] args) {

    }

}
