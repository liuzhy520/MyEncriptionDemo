/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;

import java.math.BigInteger;
import java.math.BigDecimal;

import common.RandomNumber;



/**
 *
 * @author wing
 */
public class Message {
    private EC ec;
//    private int m, p;
    private Points Ciphertext;
    private BigInteger ZERO = BigInteger.ZERO;
    private BigInteger ONE = BigInteger.ONE;
    private BigInteger FOUR = new BigInteger("4");
    private BigInteger k;
    BigDecimal prob;
        public Message(EC Ec){
            ec = Ec;
           
          calculateK();
        }
        private void calculateK(){
         if(ec.getFp().mod(new BigInteger("4")).equals(new BigInteger("3"))){


             RandomNumber ran = new RandomNumber(6);
              k = ran.getRandomNumber();
//             long k = ran.getRandomNumber().longValue();
             System.out.println("k is: "+k);
             prob = BigDecimal.ONE.divide(new BigDecimal("2").pow(k.intValue()), 3, BigDecimal.ROUND_UP);
            }
        }

        public void messageEmbeder(BigInteger m){
//            
       
//              if(m <(p-k)/k){
//                  for(long j = 0; j<k; j++){
//                  x = m*k+j;
//                  w = (x*x*x + a*x+b)%p;
//                  double W = w;
//                  double Wp = ((p+1)/4);
//                  z = ((long) Math.pow(W, Wp))%p;
//                  System.out.println(x);
//                  System.out.println(w);
//                  System.out.println(z);
//                  if(Math.pow(z, 2) == w){
//                    BigInteger Xj = BigInteger.valueOf(x);
//                    BigInteger Zj = BigInteger.valueOf(z);
//                    System.out.print("x = "+Xj);
//                    Ciphertext.setX(Xj);
//                    Ciphertext.setY(Zj);break;
//                  }
//
//                  }
//
//              }else{System.out.print("the message is too large!!");}
//
//            }
//
           
        Points message = new Points(null,null);
        BigInteger x, w, z;
        if(k != null)
        {
            if(m.compareTo(ec.getFp().subtract(k).divide(k)) == -1)
            {
                for(BigInteger j = BigInteger.ZERO; j.compareTo(k) == -1; j = j.add(ONE))
                {
                   x = (m.multiply(k)).add(j);                 
                    w = ((x.pow(3)).add(ec.geta().multiply(x)).add(ec.getb())).mod(ec.getFp());                    
                    z = w.pow(((ec.getFp().add(ONE)).divide(FOUR)).intValue()).mod(ec.getFp());
                    
//                    System.out.println(x);
//                    System.out.println(w);
//                    System.out.println(z);
                    if(z.pow(2).equals(w))
                    {
                        message.setX(x);
//                        System.out.print("x = "+x);
                        message.setY(z);
                        Ciphertext = message;
                        
                        break;
                    }
                }
            }
            else
            {
                System.out.println("the message : " + m + " is too large");
            }
            }



            
    }
        public Points getEncode(){
        return Ciphertext;
        }
        public BigInteger getK(){
        return k;
        }

}
