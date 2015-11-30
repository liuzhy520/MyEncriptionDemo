/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsa;

import java.math.BigInteger;

import common.Factory;
import common.RandomNumber;


/**
 *
 * @author wing
 */
public class Genkey {
   private BigInteger n,e,p,q,d;
   private int keyLength = 0;
  

   public Genkey(int keysize){
    keyLength = keysize;
    }

   //to generate the keys
   void generateKey(){
       
       //specify the prime number p
        Factory p1 = new Factory(); 
        p = p1.getPrime(keyLength);
      // System.out.println("now the prime number p is : " + p);
        
       //specify the prime number q
        Factory p2 = new Factory();
        q = p2.getPrime(keyLength);

       System.out.println("now the prime number q is : " + q);
       System.out.println("now the prime number p is : " + p);
      
       BigInteger phiN = (p.subtract(new BigInteger("1"))).multiply(q.subtract(new BigInteger("1")));

       n = p.multiply(q);
        do
        {
         
            e = new RandomNumber(keyLength*2-1).getRandomNumber();
           
        }
       //1<e<phiN
        while((e.compareTo(phiN) != -1) || e.gcd(phiN).compareTo(new BigInteger("1")) != 0);  
        d = e.modInverse(phiN);
        System.out.println("public key e is : " + e);
        System.out.println("private key d is : " + d);
        System.out.println("modular number n is : " + n);

   }

   
   BigInteger getModular(){
       return n;}
   BigInteger getPrivatekey(){
       return d;}
   BigInteger getPublickey(){
       return e;}

}
