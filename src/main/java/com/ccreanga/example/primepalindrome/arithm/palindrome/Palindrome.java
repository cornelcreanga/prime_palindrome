package com.ccreanga.example.primepalindrome.arithm.palindrome;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;


public class Palindrome {

    final static char [] DigitTens = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
            '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
            '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
            '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
            '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
            '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
            '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
    } ;

    final static char [] DigitOnes = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    } ;

    final static char[] digits = {
            '0' , '1' , '2' , '3' , '4' , '5' ,
            '6' , '7' , '8' , '9' , 'a' , 'b' ,
            'c' , 'd' , 'e' , 'f' , 'g' , 'h' ,
            'i' , 'j' , 'k' , 'l' , 'm' , 'n' ,
            'o' , 'p' , 'q' , 'r' , 's' , 't' ,
            'u' , 'v' , 'w' , 'x' , 'y' , 'z'
    };
    public static final int MAGIC_NUMBER = 100_000_000;

    private char[] buf = new char[20];

    public boolean checkPalindromeLongNumber(long number) {
        long q;
        int r;
        int charPos = 0;

// Get 2 digits/iteration using longs until quotient fits into an int
        while (number > Integer.MAX_VALUE) {
            q = number / 100;
// really: r = i - (q * 100);
            r = (int)(number - ((q << 6) + (q << 5) + (q << 2)));
            number = q;
            buf[charPos++] = DigitOnes[r];
            buf[charPos++] = DigitTens[r];
        }

// Get 2 digits/iteration using ints
        int q2;
        int i2 = (int)number;
        while (i2 >= 65536) {
            q2 = i2 / 100;
// really: r = i2 - (q * 100);
            r = i2 - ((q2 << 6) + (q2 << 5) + (q2 << 2));
            i2 = q2;
            buf[charPos++] = DigitOnes[r];
            buf[charPos++] = DigitTens[r];
        }

// Fall thru to fast mode for smaller numbers
// assert(i2 <= 65536, i2);
        for (;;) {
            q2 = (i2 * 52429) >>> (16+3);
            r = i2 - ((q2 << 3) + (q2 << 1));  // r = i2-(q2*10) ...
            buf[charPos++] = digits[r];
            i2 = q2;
            if (i2 == 0) break;
        }

        int start = 0;
        int end = charPos-1;
        while (end > start) {
            if (buf[start] != buf[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean checkPalindromeShortNumber2(long num) {

        long temp = num;
        long max = 1;
        while(temp>10){
            max=max*10;
            temp = temp/10;
        }
        long start = 10;
        long end = max;
        temp = num;
        long sTemp=temp, eTemp=temp;
        while (end >= start) {
            if ((eTemp/end) != sTemp%10) {
                return false;
            }
            eTemp%=end;
            end/=10;
            sTemp/=10;
        }
        return true;

    }

    boolean checkPalindromeShortNumber3(long x) {
        if (x < 0) return false;
        int div = 1;
        while (x / div >= 10) {
            div *= 10;
        }
        while (x != 0) {
            long l = x / div;
            long r = x % 10;
            if (l != r) return false;
            x = (x % div) / 10;
            div /= 100;
        }
        return true;
    }

    public long reverse(long num){
        long temp = num;
        long digit;
        long reverse=0;
        while(temp>0) {
            digit = temp%10;
            reverse = reverse*10 + digit;
            temp = temp/10;
        }
        return reverse;
    }

    public boolean checkPalindromeShortNumber(long num) {
        long temp = num;
        long digit;
        long reverse=0;
        while(temp>0) {
            digit = temp%10;
            reverse = reverse*10 + digit;
            temp = temp/10;
        }
        return reverse==num;
    }

    public boolean isPalindrome(String word){
        int start = 0;
        int end = word.length() - 1;
        while (end > start) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public boolean isPalindrome(long num) {
        if (num>= MAGIC_NUMBER)
            return checkPalindromeLongNumber(num);
        return checkPalindromeShortNumber(num);
    }
    public static void main(String[] args) {
//        System.out.println(new Long(Long.MAX_VALUE).toString().length());
//        System.out.println(new Long(Integer.MAX_VALUE).toString().length());
        //long l = 123456789987654321l;
        long l = Integer.MAX_VALUE;
        long t1,t2;

        Palindrome palindrome = new Palindrome();

        long ll = 9223372036854775807l;
//           ll = 8999999999999999999l
        ll=2147483647;
        System.out.println(Long.MAX_VALUE+770000000000000000l);
        palindrome.checkPalindromeLongNumber(Long.MAX_VALUE);

        //lsa 1135,904,925|5104,5656,5382
        for (int c = 0; c < 10; c++) {
            t1 = System.currentTimeMillis();
            for (int i = 0; i <10_000_000; i++) {
                //palindrome.checkPalindromeLongNumber(l);
                palindrome.checkPalindromeShortNumber2(l);
            }
            t2 = System.currentTimeMillis();
            System.out.println(t2-t1);

        }
//        for (long i = 9223372036854775807l; ; i++) {

            //long r = palindrome.reverse(i);
            //if (i==r)
//            if (i)
//                System.out.println(i);

//        }

        //BigInteger bd = new BigInteger("12345678998765432123456789987654321");
        //bd.testBit()


    }
}