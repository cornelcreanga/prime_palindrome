package com.ccreanga.example.primepalindrome.arithm.primes;

import com.sun.beans.editors.ByteEditor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class PrimeGenerator {


    private static long interval =  1_000_000;
    private static int processors =  Runtime.getRuntime().availableProcessors();




    public void generatePrimes(long start, long end,OutputStream out) throws IOException, InterruptedException {
        System.out.println("sta:"+start);
        System.out.println("end:"+end);
        int[] cacheArray = CacheStore.getCache();
        long startComputing = start;
        long endComputing = end;
        if (start<2)
            start=2;
        if (start<Integer.MAX_VALUE){
            startComputing = BinarySearch.upperBound(cacheArray,0,cacheArray.length,(int)start);
        }
        if (end<Integer.MAX_VALUE) {
            endComputing = BinarySearch.lowerBound(cacheArray, 0, cacheArray.length,(int)end);
        }
        if (startComputing<Integer.MAX_VALUE)
        for (long i=startComputing;i<=endComputing;i++){
            out.write(Util.toBytes(Long.valueOf(i).longValue()));
            //out.write((""+(cacheArray[(int)i])+"\n").getBytes());
        }
        out.flush();

        if (end>Integer.MAX_VALUE){
            startComputing = Math.max(Integer.MAX_VALUE,startComputing);
            endComputing = end;

            long steps = (long)Math.ceil((endComputing-startComputing)/(double)interval);
            long parallelSteps = (long)(Math.ceil((double) steps / processors));
            System.out.println("parallelSteps:"+parallelSteps);
            int i=0,counter = 0;

            while(i<parallelSteps){

                int arrayLen = processors;
                if (i==(parallelSteps-1)){
                    arrayLen = (int)(steps % processors);
                }
                System.out.println("step:"+i);
                System.out.println("arrayLen:"+arrayLen);

                Thread[] threads = new Thread[arrayLen];
                ByteArrayOutputStream[] primeBuffer = new ByteArrayOutputStream[arrayLen];
                //(int)((end-start)/20)

                int j = 0;
                while ((j<=processors) && (j<arrayLen)){

                    long sieveStart = startComputing+counter*interval;
                    long sieveEnd= Math.min(end,startComputing+(counter+1)*interval);
                    System.out.println("sieveSta:"+sieveStart);
                    System.out.println("sieveEnd:"+sieveEnd);
                    if (counter>0)
                        sieveStart++;
                    primeBuffer[j] = new ByteArrayOutputStream((int)((end-start)/20));
                    threads[j] = new Thread(new PrimeWorker(sieveStart,sieveEnd,primeBuffer[j]));
                    threads[j].start();
                    counter++;
                    j++;
                }
                i++;
                for (Thread t : threads)
                    t.join();
                for(ByteArrayOutputStream streams:primeBuffer){
                    out.write(streams.toByteArray());
                }

            }



//            while(i++<steps){
//                long sieveStart = startComputing+i*interval;
//                long sieveEnd= Math.min(end,endComputing+(i+1)*interval);
//                if (i>0)
//                    sieveStart++;
//
//                Thread[] threads = new Thread[processors];
//                for (int counter = 0; counter < threads.length; counter++) {
//                    threads[counter] = new Thread(new PrimeWorker(sieveStart,sieveEnd,out));
//                    threads[counter].start();
//                }
//                SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(sieveStart,sieveEnd);
//
//            }

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        PrimeGenerator primeGenerator = new PrimeGenerator();
//        primeGenerator.generatePrimes(0,100,System.out);
//        int startComputing = 0;
//        int endComputing = 613;
//        int interval = 100;
//        long steps = (long) Math.ceil((float) (endComputing - startComputing)/interval);
//        for(int i=0;i<steps;i++){
//            if (i>0)
//                System.out.print(startComputing+i*interval+1);
//            else
//                System.out.print(startComputing+i*interval);
//            System.out.print("-");
//            System.out.println(Math.min(endComputing, startComputing + (i + 1) * interval));
//            //SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(startComputing+i*interval,Math.min(end,endComputing+(i+1)*interval));
//        }
//
//        System.out.println( Runtime.getRuntime().availableProcessors());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(10_000_000);
        long start = (long)(Math.random()*(Long.MAX_VALUE/2));
        long end = start + 10_050_000l;
        PrimeGenerator primeGenerator = new PrimeGenerator();
        primeGenerator.generatePrimes(start,end,outputStream);

    }

}
