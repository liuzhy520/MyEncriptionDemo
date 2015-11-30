/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsa;

import java.math.BigInteger;
import java.util.ArrayList;

import common.Factory;

/**
 *
 * @author wing
 */
public class Encrypt {
    private String plaintext;
    public Encrypt(String message){
        plaintext = message;
    }

    public String getCiphertext(BigInteger e, BigInteger n){
        String ciphertext = "";
        System.out.println("key of e to be used for encryption :" + e);
        System.out.println("key of n to be used for encryption :" + n);
        if(plaintext == null){
        return "no message input!! ";
        }else {
            ArrayList array = Factory.encodeString(plaintext);
            ciphertext = encryption(array,e,n);
        return ciphertext;
        }
    }
    private String encryption(ArrayList array, BigInteger e, BigInteger n){
        String text = "";
        for(int i = 0; i < array.size(); i++)
        {
            BigInteger m =(BigInteger) array.get(i);
            
            //modPow which is m^e (mod n)
            text += (m.modPow(e,n)).toString();
            text += " ";
        }
        return text;
    }

}
