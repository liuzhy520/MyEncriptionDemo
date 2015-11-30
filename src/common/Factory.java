/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * and prime number test
 */

/* this class is used for
    Manage encode and decode String
    Time management
 
 */
package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;



/**
 *
 * @author wing
 */
public class Factory {
     private long startTime;
    public Factory(){
        startTime = System.currentTimeMillis();

    }

    public BigDecimal getTimeTaken()
    {
        long now = System.currentTimeMillis();

        long elapsed = now - startTime;
        BigDecimal getTime = (BigDecimal.valueOf(elapsed)).divide(new BigDecimal("1000"), 5, BigDecimal.ROUND_UP);


        return getTime;
    }


    /*
     * String Manager
    */
     public static ArrayList encodeString(String plaintext)
    {
        ArrayList array = new ArrayList();

        char[] encode = plaintext.toCharArray();

        for(int i = 0; i < encode.length; i++)
        {
            int j = (int) encode[i];
            BigInteger big = BigInteger.valueOf(j);
            array.add(big);
        }

        return array;
    }

    public static String decodeString(BigInteger intT)
    {
        char decode = (char)intT.intValue();

        return Character.toString(decode);
    }



   //test the prime number using Fermat test
   private boolean testPrime(BigInteger a, int keyLength){
       int counter = 100;
       BigInteger aN = null;

       do{
        BigInteger RanNum = new RandomNumber(keyLength-1).getRandomNumber();
            aN = RanNum;

        if(aN.gcd(a).compareTo(new BigInteger("1")) != 0){
//            System.out.printf(">");
            counter = 0; return false;}
        else if(!aN.modPow(a, a).equals(aN)){

//            System.out.printf(">");
            counter = 0; return false;}
        else counter--;

       }while(counter != 0);
//            System.out.println();
   return true;

   }
      public BigInteger getPrime(int key){
           boolean t1 = false;
           BigInteger probPrime = null;
       do {probPrime = new RandomNumber(key).getRandomNumber();

            t1 = testPrime(probPrime,key);
       }
       while(t1==false);
       return probPrime;
   }

}
