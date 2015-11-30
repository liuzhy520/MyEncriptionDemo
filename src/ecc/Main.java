/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;
import java.lang.Long;
import java.math.BigInteger;
import java.util.ArrayList;
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
        boolean selector = true;
        int  As = 0, Bs = 0;
        BigInteger k = null;
        String m = null,sign = null;
        Points g = null, shareK = null,messagetext = null,ciphertext = null,Alice = null;
        EC ec = null;
        int keylength = 0;
        while(selector == true){
        System.out.println("Welcome to ECC implementation!");
        System.out.println("******************************");
        System.out.println("1.Generate ECC keys");
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
            
            System.out.println("please input the keylength:");
            Scanner scan = new Scanner(System.in);
            keylength = scan.nextInt();
            if(keylength <= 8){
                System.out.println("keylength should be more than 8!");break;
            }
               Factory time = new Factory();
                ec = new EC(keylength);
               
                System.out.println("a is:" + ec.geta() + ", b is:"+ ec.getb() + ", p is:"+ ec.getFp());
                
               // curveP = ec.CurvePoints();
               // g = gk.getGpoint();

               System.out.print("three possible G points from the curve: ");
                for(int i = 0; i < 3; i++){

                    System.out.print("(" +ec.getApoint(i).getX().toString() + 
                            "," + ec.getApoint(i).getY().toString() + ")");
//                if(BigInteger.valueOf(i).mod(BigInteger.valueOf(200)).equals(BigInteger.ZERO))
//                    System.out.println();

               }System.out.println();
                Genkey gk = new Genkey(ec);
                g = gk.getGpoint();
                As = gk.getAS();
                Bs = gk.getBS();
                Alice = gk.Alice();
                shareK = gk.getShareA();
                System.out.println("the G point is:(" + gk.getGpoint().getX()+","+gk.getGpoint().getY()+")");
                System.out.println("Alice's secret key is:" + gk.getAS());
                System.out.println("Alice's public key is:(" + gk.Alice().getX()+","+gk.Alice().getY()+")");
                System.out.println("Bob's secret key is:" + gk.getBS());
                System.out.println("Bob's public key is:(" + gk.Bob().getX()+","+gk.Bob().getY()+")");
                System.out.println("Alice's share key is:(" + gk.getShareA().getX()+","+gk.getShareA().getY()+")");
                System.out.println("Bob's share key is:(" + gk.getShareB().getX()+","+gk.getShareB().getY()+")");
                System.out.println(time.getTimeTaken());
                System.out.println("done!");
            }break;
            case (2):{
                System.out.println("please input your message in number:");
                
             
                    Scanner scan = new Scanner(System.in);
                    m = scan.nextLine();
                    long mm = Long.parseLong(m);
                    System.out.println("m : " + mm);
                    Message message = null;
                    do{
                     message = new Message(ec);
                    message.messageEmbeder(BigInteger.valueOf(mm));
                    messagetext = message.getEncode();
                    k = message.getK();
                    }while(message.getEncode() == null);
                    
                    System.out.println("message : ("+ message.getEncode().getX()+","+message.getEncode().getY()+")");
                    Encrypt en = new Encrypt(ec);
                    ciphertext = en.encryption(messagetext, shareK);
                    System.out.println("ciphertext point : " + ciphertext.getX()+","+ ciphertext.getY()+")");
                System.out.println("done!");
            }break;
            case (3):{
                System.out.println("decryption!");
                Decrypt de = new Decrypt(ec);

                de.decryption(ciphertext, shareK, k);
                System.out.println("the message is : "+ de.getMessage());
               
            }break;
            case (4):{
              System.out.println("please input your message in number:");


                    Scanner scan = new Scanner(System.in);
                    sign = scan.nextLine();
                    long mm = Long.parseLong(sign);
//                    int mm = Integer.parseInt(m);
                    System.out.println("m : " + mm);
                    Digitalsignature ds = new Digitalsignature(ec);
                    ds.signature(mm, As,g);
                    System.out.println("get the signature: (" + ds.getSignacture().getX() + "," + ds.getSignacture().getY() + ")");
            }break;
            case (5): {
              System.out.println("verifying!");
              Digitalsignature ds = new Digitalsignature(ec);
              int mm = Integer.parseInt(sign);
              ds.verification(mm, g, Alice, g);
              System.out.println("result is : " + ds.getVerification());
            }break;
            case (0): {
                selector = false;
            }break;
            default:{System.out.println("invaild selection!");}
            }
        
        }
    }

}
