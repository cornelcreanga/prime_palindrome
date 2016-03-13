package com.ccreanga.example.primepalindrome.arithm.primes;

public class SegmentedPrimeSieve {
    private final byte sieve[];
    private final long start;
    /**
     * Creates a segmented sieve in the interval defined by the values of start
     * and end.
     *
     * @param start
     * @param end
     */
    public SegmentedPrimeSieve(long start, long end) {

        if (end - start > Integer.MAX_VALUE)
            throw new IllegalArgumentException();

        // if the starting value is not odd, choose the next one
        start = start % 2 == 0 ? ++start : start;
        int length = (int) ((end - start) / 16 + 1);
        sieve = new byte[length];
        end = start + length * 16 - 2;
        // find all the primes up to sqrt(end)
        int maxPrime = (int) Math.floor(Math.sqrt(end));
        // maximum value of k to sift multiples of primes in the form 2*k+1
        int maxK = maxPrime / 2;
        long intervalHalfSize = 8 * length;
        // let's assume primes in the form 2*k+1 starting from k=1
        int[] primes = CacheStore.getCache();
        int upperLimit = BinarySearch.lowerBound(primes, 0, primes.length,maxPrime);
        for (int k = 1; k <= upperLimit; k++) {
                final int p = primes[k];
                // This is the initial offset to start sifting from (-start%p)
                long offset = (int) ((p - (start % p)) % p);
                // if the offset is odd, start+offset is even, skip it because
                // we don't have even numbers in the sieve. divide by two for
                // the same reason. Note that this step is crucial!
                if (offset % 2 == 1)
                    offset += p;
                offset /= 2;
                for (; offset < intervalHalfSize; offset += p) {

                    sieve[(int)(offset >> 3)] |= (1 << (offset & 7));
                }
        }
        this.start = start;
    }
    public boolean isPrime(long n) {
        n = n % 2 == 0 ? ++n : n;
        if (n < start)
            throw new RuntimeException("The number " + n
                    + " is too small for the values in the sieve.");
        if (n == 2)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        int dn = (int) (n - start);
        int i = dn / 16;
        if (i >= sieve.length)
            throw new RuntimeException("The number " + n
                    + " exceeds the values in the sieve.");
        return ((sieve[i] >> ((dn / 2) & 7)) & 1) == 0;
    }

    public static void main(String[] args) {

//        int step = 1_000_000;
//        long noSteps = Long.MAX_VALUE/step;
//        long t1 = System.currentTimeMillis();
//        for(long i=0;i<noSteps;i++){
//            System.out.println(i*step+"-"+(i+1)*step);
//            SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(i*step,(i+1)*step);
//        }
//        long t2 = System.currentTimeMillis();
//        System.out.println(t2-t1);
//        long t1 = System.currentTimeMillis();
//        SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(100,200);
//        SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(0,1_000_000_000);

//        long t2 = System.currentTimeMillis();
//        System.out.println(t2-t1);
//        long c1=0,c2=0;
//        for (long i = Integer.MAX_VALUE; i <(long)Integer.MAX_VALUE+1_000_000_000 ; i++) {
//            if (!( BigInteger.valueOf(i).isProbablePrime(100)) && sieve.isPrime(i)) {
//                System.out.println(i);
//            }
//        }
        //System.out.println(sieve.isPrime((long)Integer.MAX_VALUE+232343));


        System.out.println((Long.MAX_VALUE-Integer.MAX_VALUE)/1_000_000_000);
//
        long start = (long)(Math.random()*(Long.MAX_VALUE/2));
        long end = start + 1_00_000_000l;
        SegmentedPrimeSieve segmentedPrimeSieve = new SegmentedPrimeSieve(start,end);

        long t1 = System.currentTimeMillis();
        start = (long)(Math.random()*(Long.MAX_VALUE/2));
        end = start + 1_00_000_000l;
//24948
// 4425
//        for (int i = 0; i < 100; i++) {
//            long start = (long)(Math.random()*(Long.MAX_VALUE/2));
//            long end = start + 10_000_000;
//            System.out.println(start+":"+end);
             segmentedPrimeSieve = new SegmentedPrimeSieve(start,end);

//            SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(start,end);
//
//            for (long j = start; j < end; j++) {
//                if (sieve.isPrime(j) != sgmPrimeSieve.isPrime(j))
//                    System.out.println(j);
//            }

//        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2-t1);



//        for (int i = 101; i < 200; i++) {
//            if (sgmPrimeSieve.isPrime(i))
//                System.out.println(i);
//
//        }
//        SegmentedPrimeSieve sieve = new SegmentedPrimeSieve(100,200);
        for (int i = 101; i < 200; i++) {
//            if (sieve.isPrime(i))
//                System.out.println(i);

        }

//        SegmentedPrimeSieve2 sieve2 = new SegmentedPrimeSieve2(100,200);
//        for (int i = 101; i < 200; i++) {
//            if (sieve2.isPrime(i))
//                System.out.println(i);
//
//        }


    }


}
