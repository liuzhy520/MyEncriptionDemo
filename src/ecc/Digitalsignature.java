/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;

import java.math.BigInteger;

import common.RandomNumber;

/**
 *
 * @author wing
 */
public class Digitalsignature {
    private Points signature;
    private EC ec;
    private boolean verify;
    private BigInteger ZERO = BigInteger.ZERO;
    private BigInteger ONE = BigInteger.ONE;
    public Digitalsignature(EC Ec){
        ec = Ec;
    }
//    public void signature(int m, int As, Points g ){
//        int r  , s , k;
//        Points sign = new Points(null,null);
//        do{
//            RandomNumber ran = new RandomNumber(8);
//            k = ran.getRandomNumber().intValue();
////            System.out.println("k = " + k);
//            Points kg = ec.getPublicK(k,g);
////            System.out.println("g = "+kg.getX()+","+kg.getY());
//            r = (kg.getX().intValue())%ec.getFp().intValue();
//            s = ((m+As*r)/k)%ec.getFp().intValue();
////            System.out.println("r = "+r);
////            System.out.println("s = " + s);
//        }while(r == 0 || s == 0);
//
//
//        long R = Long.parseLong(String.valueOf(r));
//        long S = Long.parseLong(String.valueOf(s));
//        BigInteger x = BigInteger.valueOf(R);
//        BigInteger y = BigInteger.valueOf(S);
//
//        sign.setX(x);
//        sign.setY(y);
//        signature = sign;
//    }

    public void signature(long m, int As, Points g){
        BigInteger r = null, s = null, k = null;
        Points sign = new Points(null,null);
        do{
        RandomNumber ran = new RandomNumber(6);
            k = ran.getRandomNumber();
            Points kg = ec.getPublicK(k.intValue(), g);
            r = kg.getX().mod(ec.getFp());
            long as = Long.parseLong(String.valueOf(As));
            s = ONE.divide(k).multiply(BigInteger.valueOf(m).add(BigInteger.valueOf(as).multiply(r)));
            s = s.mod(ec.getFp());

        }while(r.equals(ZERO)|| s.equals(ZERO));
        sign.setX(r);
        sign.setY(s);
        signature = sign;
    }
//    public void verification(int m, Points sign, Points Alice, Points g){
//        if(sign.getX().compareTo(BigInteger.ONE)==1 &&
//                sign.getX().compareTo(ec.getFp())==-1 && sign.getY().compareTo(BigInteger.ONE)==1
//                && sign.getY().compareTo(ec.getFp())==-1 ){
//                int w = (1/sign.getY().intValue())%ec.getFp().intValue();
//                int u1 = (m * w)%ec.getFp().intValue();
//                int u2 = (sign.getX().intValue()* w)%ec.getFp().intValue();
//                Calculator cal = new Calculator();
//                Points fin = cal.Addition(ec.getPublicK(u1, g), ec.getPublicK(u2, Alice), ec.geta(), ec.getFp());
//                BigInteger x1 = fin.getX();
////                if(x1.equals(sign.getX().mod(ec.getFp()))){
//                if(x1.intValue() == sign.getX().intValue()%ec.getFp().intValue()){
//                        verify = true;
//                }else verify = false;
//        }else{
//        System.out.println("invalid signature!");
//        }
//    }
    public void verification(int m, Points sign, Points Alice, Points g){
        BigInteger w = null, u1 = null, u2 = null;
     if(sign.getX().compareTo(BigInteger.ONE)==1 &&
                sign.getX().compareTo(ec.getFp())==-1 && sign.getY().compareTo(BigInteger.ONE)==1
                && sign.getY().compareTo(ec.getFp())==-1 ){

                w = ONE.divide(sign.getY()).mod(ec.getFp());
                long e = Long.parseLong(String.valueOf(m));
                u1 = BigInteger.valueOf(e).multiply(w);
                u1 = u1.mod(ec.getFp());
                int uu1 = u1.intValue();
                u2 = sign.getX().multiply(w);
                u2 = u2.mod(ec.getFp());
                int uu2 = u2.intValue();
                Calculator cal = new Calculator();
                Points fin = cal.Addition(ec.getPublicK(uu1, g), ec.getPublicK(uu2, Alice), ec.geta(), ec.getFp());
                BigInteger x1 = fin.getX();
                if(x1.equals(sign.getX().mod(ec.getFp()))){
                    verify = true;

                }else{
                verify = false;
                }
        }else{
        System.out.println("invalid signature!");
        }

    }

    public Points getSignacture(){
        return signature;
    }
    public boolean getVerification(){
        return verify;
    }
}
