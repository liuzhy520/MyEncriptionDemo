/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;
import java.math.BigInteger;
import java.util.ArrayList;

import common.Factory;
import common.RandomNumber;


/**
 *
 * @author wing
 */
public final  class EC {
    private BigInteger p,a,b;
    private int length;
    private BigInteger FOUR = new BigInteger("4");
    private BigInteger TWENTYSEVEN = new BigInteger("27");
    private BigInteger ZERO = BigInteger.ZERO;
    private BigInteger ONE = BigInteger.ONE;
    private ArrayList points;
//    private BigInteger x,y;
    public EC(int keyLength){
        //intialize the Field p;
        Factory prime = new Factory();
        do{
        p = prime.getPrime(keyLength);
        length = keyLength;
        }while(!p.mod(FOUR).equals(new BigInteger("3")));

        findAB();
        points = CurvePoints();
    }
    //define a and b
    private void findAB(){
        BigInteger A = null,B = null;
        
        do{
            RandomNumber ranL = new RandomNumber(2);
            BigInteger l = ranL.getRandomNumber();
            RandomNumber ranA = new RandomNumber(length-l.intValue());
            A = ranA.getRandomNumber();

            RandomNumber ranB = new RandomNumber(length-1);
            RandomNumber ranC = new RandomNumber(length-2);
            B = ranB.getRandomNumber().add(ranC.getRandomNumber());
        }
        while((((FOUR.multiply(A.pow(3))).add(TWENTYSEVEN.multiply(B.pow(2)))).mod(p)) == ZERO);
        a = A;
        b = B;
    }

    //find out all the curve integer points
    public ArrayList CurvePoints(){
        ArrayList pointsC = new ArrayList();
        BigInteger x=null, y=null;
        int counter = 3;
//        if(findx.compareTo(p) <= -1){
//            return null;
//        }   else
        
        for(x = TWENTYSEVEN;
            x.compareTo(p) == -1;
            x = x.add(ONE))
        {  
            BigInteger FormulaR = ((x.pow(3)).add(a.multiply(x)).add(b)).mod(p);

             for(y = ZERO;
                y.compareTo(p) == -1;
                y = y.add(ONE))
            {   
                BigInteger FormulaL = (y.pow(2)).mod(p);

                if((FormulaR.equals(FormulaL)) && (FormulaR != null) && (FormulaL != null))
                {
                    Points point = new Points(x, y);
                    pointsC.add(point);
                    counter --;
                    if(counter == 0){
                    x = p.add(ONE);
                    y = p.add(ONE);
                    }
                }
            }

            
        }
        return pointsC;

    }

    public Points getApoint(int X){

        Points g = new Points(null,null);

        g = (Points) points.get(X);

        return g;
    }
        public Points getPublicK(int s, Points G){
        Points Apoint = new Points(null,null);
        BigInteger i;
        Apoint = G;
        Calculator cal = new Calculator();
//        if(s.compareTo(BigInteger.ONE) == 1){
        if(s > 1){
//        for(i = new BigInteger("1"); i.compareTo(s) == -1; i = i.add(BigInteger.ONE)){
        for(int j = 2; j <=s ; j++){
            if(Apoint.equals(G)){
                Apoint = cal.Doubling(G, a, p);
//                 System.out.println("j = " +j);
//                System.out.println("Apoint doubling: " + Apoint.getX()+","+Apoint.getY());
                //return Apoint;
            }else {
                Apoint = cal.Addition(Apoint, G, a, p);
                //return Apoint;
//                System.out.println("j = " +j);
//                System.out.println("Apoint Addition: " + Apoint.getX()+","+Apoint.getY());
                }


            }

        }
        return Apoint;
    }
    public BigInteger getFp(){
    return p;
    }
    public BigInteger geta(){
    return a;
    }
    public BigInteger getb(){
    return b;
    }
    


}
