package ecc;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.math.BigInteger;

/**
 *
 * @author wing
 */
public class Calculator {
    private BigInteger ONE = BigInteger.ONE;
    private BigInteger ZERO = BigInteger.ZERO;
    private BigInteger THREE = new BigInteger("3");
    private BigInteger TWO = new BigInteger("2");


    public Points Addition(Points P, Points Q, BigInteger a, BigInteger p){
            BigInteger s = null, Xr = null, Yr = null, S = null,top = null, bottom = null;
            Points R = new Points(null,null);
//            if(!P.equalsNull() && !Q.equalsNull()){
//            if (P.equals(Q)){
//            R = Doubling(P,a,p);
//            }else if(P.getX().equals(Q.getX()) ||
//                    P.getY().equals(ZERO.subtract(Q.getY().mod(p))) ||
//                    P.getX().equals(ZERO.subtract(Q.getY().mod(p)))){
//            R = null; // when the point is at infinity
//            System.out.println("the point is at infinity");
//            }else {
                    //when Points are not point in inifinity
                    //define s = (Yp - Yq)/(Xp - Xq)
////            s = (P.getY().subtract(Q.getY())).divide((P.getX().subtract(Q.getX())));
//                t = (P.getY().subtract(Q.getY()));
//                b = P.getX().subtract(Q.getX());
////            System.out.println("entrence top = "+ t +" bottom = "+ b);
//            s = SDivision(t,b,p);
////            s = t.divide(b);
////            System.out.println("s = " + s);
//            s = s.mod(p);
//            if(!s.equals(null)){
//            Xr = s.pow(2).subtract(P.getX()).subtract(Q.getX());
//            Yr = (s.multiply(P.getX().subtract(Xr))).subtract(P.getY());
//            R.setX(Xr.mod(p));
//            R.setY(Yr.mod(p));
//            }
////            return R;
//            }
//            }
////            System.out.println("addition x = " + Xr.mod(p)+ "y = " + Yr.mod(p));
//           return R;       
        
        boolean topNg = false;
        boolean bottomNg = false;
            //when Points are not point in inifinity
            if(!P.equalsNull() && !Q.equalsNull())
            {
                //define s = (Yp - Yq)/(Xp - Xq)
                top = P.getY().subtract(Q.getY());
                bottom = P.getX().subtract(Q.getX());
                //P doesn't equal to Q or -Q
                if(!P.equals(Q) && !bottom.equals(ZERO)){
                    BigInteger d = ZERO;
                    d = top.divide(bottom);
                    if(d.equals(ZERO) || !top.mod(bottom.abs()).equals(ZERO)){
                        //Make absolute value of top or bottom inorder to do the mod calculation
                        if(top.signum() == -1 || bottom.signum() == -1){
                            if(top.signum() == -1){
                                top = top.abs();
                                topNg = true;
                            }
                            if(bottom.signum() == -1){
                                bottom = bottom.abs();
                                bottomNg = true;
                            }
                        }
                        //Simplify the fraction and increase the speed
                        while(!top.gcd(bottom).equals(BigInteger.ONE)){
                            BigInteger GCD = top.gcd(bottom);
                            top = top.divide(GCD);
                            bottom = bottom.divide(GCD);
                        }
                        s = SDivision(top,bottom,p);

                        if(topNg || bottomNg){
                            if(!(topNg && bottomNg)){
                                s = ZERO.subtract(s);
                                s = s.mod(p);
                            }
                        }
                    }else{
                        s = (top.divide(bottom)).mod(p);
                    }
                    if(s !=null)
                    {
                        //Xr = s^2-Xp-Xq
                        Xr = (s.pow(2)).subtract(P.getX()).subtract(Q.getX());
                        R.setX(Xr.mod(p));
                        //Yr = s(Xp-Xr)-Yp
                        Yr = (s.multiply((P.getX()).subtract(R.getX()))).subtract(P.getY());
                        R.setY(Yr.mod(p));
                    }
                }
            }
        return R;
    }
    
    public Points Doubling(Points P, BigInteger a, BigInteger p){
            BigInteger s = null, Xr = null, Yr = null, S = null, t = null, b = null;
            Points R = new Points(null,null);
            
            if(!P.getY().equals(ZERO)){
//            s = (THREE.multiply(P.getX().pow(2)).add(a)).
//                    divide(TWO.multiply(P.getY()));
            t = (THREE.multiply(P.getX().pow(2)).add(a));
            b = (TWO.multiply(P.getY()));
            s = SDivision(t,b,p);
//            s = t.divide(p);
            S = s.mod(p);
            Xr = S.pow(2).subtract(TWO.multiply(P.getX()));
            Yr = (S.multiply(P.getX().subtract(Xr))).subtract(P.getY());
            R.setX(Xr.mod(p));
            R.setY(Yr.mod(p));
            }
//            System.out.println("doubling x = " + Xr.mod(p)+ "y = " + Yr.mod(p));
            return R;
    }
    
    public Points Substraction(Points P, Points Q, BigInteger a, BigInteger p){
           Points R = new Points(null,null);
           BigInteger y = ZERO.subtract(Q.getY().mod(p));
           Points SQ = new Points(Q.getX(),y);
           R = Addition(P,SQ,a,p);
           return R;

    }
    
    
    

    private BigInteger SDivision(BigInteger top, BigInteger bottom, BigInteger n)
    {

        BigInteger pn = n;
        BigInteger topFinal,sum = null;
        BigInteger div = ZERO;
        boolean topNg = false, bottomNg = false;
//        System.out.println("inSDivision debug top = " + top + " bottom = "+ bottom);
            if(top.signum() == -1){
               top = top.abs();
               topNg = true;
            }

            if(bottom.signum() == -1){
               bottom = bottom.abs();
               bottomNg = true;
             }
//        System.out.println("inSDivision (NG) top = " + top + " bottom = "+ bottom);
        if(top.divide(bottom).equals(ZERO) || !top.mod(bottom.abs()).equals(ZERO))
                {
 
        while(!top.gcd(bottom).equals(ONE)){
          BigInteger GCD = top.gcd(bottom);
          top = top.divide(GCD);
          bottom = bottom.divide(GCD);
         }
        //if top and bottom are bigger than p, make them small entough by subtracting p
        while(top.compareTo(pn) == 1 && bottom.compareTo(pn) == 1)
        {
            top = top.subtract(pn);
            bottom = bottom.subtract(pn);

        }
   
        topFinal = top;
//         System.out.println("inSDivision (NG) topFinal? = " + top + " bottom = "+ bottom);
        if (!bottom.equals(ZERO))
        {
            if(bottom.equals(ONE))
                return topFinal;
            do{
                topFinal = topFinal.add(pn);
                if(topFinal.mod(bottom).equals(ZERO))
                {
                     div = topFinal.divide(bottom);
                }
            }
            while(div.equals(ZERO));
//             System.out.println("inSDivision div? = " + div);
//             System.out.println("inSDivision topFinal? = " + topFinal);
        }else
        {
            return null;
        }
        if(topNg || bottomNg){
         if(!(topNg && bottomNg)){
//             System.out.println("top = " + topFinal + " bottom = "+ bottom);
         sum = ZERO.subtract(topFinal.divide(bottom));
//         System.out.println("sum = "+sum);
            }
            else {sum = top.divide(bottom);}
         }     else{
         sum = topFinal.divide(bottom);}

        }else{

        sum = top.divide(bottom);
        }

        return sum;



    }
}
    
