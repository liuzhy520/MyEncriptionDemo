/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsa;

import java.math.BigInteger;

import common.Factory;

/**
 *
 * @author wing
 */
public class Decrypt {
    private String ciphertext;
    public Decrypt(String input){
        ciphertext = input;
    }

    public String getDecryptText(BigInteger d, BigInteger n){
        //String text = "";
        System.out.println("using private key d to decrypt :" + d);
        if(ciphertext == null){
        return "error!";
        }else{
            return decryption(d,n);
        }
       // return text;
    }

    private String decryption(BigInteger d, BigInteger n){
        String output = "";
        BigInteger t = null;
         String[] arr = ciphertext.split(" ");

        for(int i = 0; i < arr.length; i++)
        {
            t = new BigInteger(arr[i]);

            //modPow which is c^d (mod n)
            output += Factory.decodeString(t.modPow(d, n));
        }
        return output;
    }
}
