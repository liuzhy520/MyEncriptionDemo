/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

import common.Factory;

/**
 *
 * @author wing
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BigDecimal time;
        BigInteger e = null,d = null,n = null;
        String ciphertext = null;
        String signature = null;
        String text = null;
        boolean selector = true;
        while(selector == true){
        System.out.println("Welcome to RSA implementation!");
        System.out.println("******************************");
        System.out.println("1.Generate RSA keys");
        System.out.println("2.Encryption");
        System.out.println("3.Decryption");
        System.out.println("4.Digital signature signing");
        System.out.println("5.Digital signature verifying");
        System.out.println("0.exit");
        System.out.println("******************************");
        
        System.out.printf("please select 0-5:");
        
        Scanner selection = new Scanner(System.in);
        switch(selection.nextInt()){
            case (1): {
                Scanner input = new Scanner(System.in);
                System.out.println("please input the key size: ");
                int keysize = input.nextInt();
                if(keysize<2){
                System.out.println("invaild key size!");
                break;
                }else
                System.out.println("generating keys.....");
                Genkey k = new Genkey(keysize);
                Factory timer = new Factory();
                k.generateKey();
                time = timer.getTimeTaken();
                System.out.println("It takes: " + time + "seconds to generate keys");
                e = k.getPublickey();
                d = k.getPrivatekey();
                n = k.getModular();
                if(keysize >=2 && keysize <=32){
                System.out.println("warning: these keys maybe cannot encrypt or decrypt currectly!!");
                }
                System.out.println("done!");
            }break;
            case (2):{
                System.out.println("please input your message");
                Scanner scan = new Scanner(System.in);
                String input = scan.nextLine();
                Encrypt en = new Encrypt(input);
                Factory factory = new Factory();
                ciphertext = en.getCiphertext(e, n);
                System.out.println("the ciphertext is :" + ciphertext);
                System.out.println("It takes: " + factory.getTimeTaken() + "seconds to encrypt the message");
                System.out.println("done!");
            }break;
            case (3):{
                if(ciphertext == null){
                System.out.println("no ciphertext!!");
                break;
                }else{
                System.out.println("decrypting.....");
                Factory factory = new Factory();
                Decrypt de = new Decrypt(ciphertext);
                String message = de.getDecryptText(d, n);
                System.out.println("the message is :" + message);
                System.out.println("It takes: " + factory.getTimeTaken() + "seconds to decrypt the message");
                System.out.println("done!");
                
                }
            }break;
            case (4):{
                String name, email;
                Scanner input = new Scanner(System.in);
                Digitalsignature ds = new Digitalsignature();
                System.out.printf("please input your name:");
                name = input.nextLine();
                System.out.printf("please input your email:");
                email = input.nextLine();
                ds.setSetificate(name, email, e, n);
                System.out.println("Please input your plaintext to sign:");
                text = input.nextLine();
                System.out.println("use private key :" + d);
                System.out.println("now signing....");
                Factory factory = new Factory();
                ds.signing(text,d,n);
                signature = ds.getSignature();
                System.out.println("the signature is :" + signature);
                System.out.println("It takes: " + factory.getTimeTaken() + "seconds to do the signature");
                System.out.println("done!");
            }break;
            case (5): {
                if(signature == null){
                System.out.println("no signature!!");
                break;
                }else{
                   boolean result;
                System.out.println("got the signature :" + signature);
                Factory factory = new Factory();
                System.out.println("use public key e :" + e);
                System.out.println("now verifying....");
                Digitalsignature ds = new Digitalsignature();
                result = ds.verifying(text, signature,e,n);
                System.out.println("the unsigned message is :" + ds.getunSignText());
                System.out.println("comparing with the original text :" + text);
                System.out.println("the result is :" + result);
                System.out.println("It takes: " + factory.getTimeTaken() + "seconds to do the signature");
                System.out.println("done!");
                }
            }break;
            case (0): {
                selector = false;
            }break;
            default:{System.out.println("invaild selection!");}

        }
      
        }


    }


}
