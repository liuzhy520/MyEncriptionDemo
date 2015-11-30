/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;

import common.RandomNumber;

/**
 *
 * @author wing
 */
public class Genkey {
    private EC ec;
    private Points g,Alice,Bob,shareA,shareB;
    private int As, Bs;

    public Genkey(EC ec){
        this.ec = ec;
        g = ec.getApoint(1);
        findkey();
    }
    public Points getGpoint(){
        
        return g;
    }
//        private void Akey(){
//
//        RandomNumber ran1 = new RandomNumber(4);
////        RandomNumber ran2 = new RandomNumber(ran1.getRandomNumber().intValue());
//        As = ran1.getRandomNumber().intValue();
////        As = new BigInteger("2");
////        As = 2;
//        Alice = ec.getPublicK(As, g);
//
//    }
//
//        private void Bkey(){
////        System.out.println("g point is: " + g.getX()+","+ g.getY());
//        RandomNumber ran2 = new RandomNumber(5);
//        Bs = ran2.getRandomNumber().intValue();
////        Bs = 3;
//        Bob = ec.getPublicK(Bs, g);
//
//    }
//        private void ShareA(){
//        shareA = ec.getPublicK(As, Bob);
//        }
//
//        private void ShareB(){
//        shareB = ec.getPublicK(Bs, Alice);
//        }
        private void findkey(){
        Points A = new Points(null,null);
        Points B = new Points(null,null);

             RandomNumber ran1 = new RandomNumber(5);
             As = ran1.getRandomNumber().intValue();
//             As = 2;
             A = ec.getPublicK(As, g);
             RandomNumber ran2 = new RandomNumber(4);
             Bs = ran2.getRandomNumber().intValue();
//             Bs = 3;
             B = ec.getPublicK(Bs, g);

             shareA = ec.getPublicK(As, B);
             shareB = ec.getPublicK(Bs, A);
             Alice = A;
             Bob = B;


        }


    public Points Alice(){
        return Alice;
    }
    public Points Bob(){
        return Bob;
    }
    public int getAS(){
    return As;
    }
    public int getBS(){
    return Bs;
    }
    public Points getShareA(){
    return shareA;
    }
    public Points getShareB(){
    return shareB;
    }
}
