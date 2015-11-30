/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ecc;

import java.math.BigInteger;

/**
 *
 * @author wing
 */
public class Decrypt {
    private EC ec;
    private BigInteger message;
    public Decrypt(EC Ec){
        ec = Ec;
    }
    public void decryption(Points c, Points shareK,BigInteger k ){
        Calculator cal = new Calculator();
        Points m = cal.Substraction(c, shareK, ec.geta(), ec.getFp());
        message = m.getX().divide(k);
    }
    public BigInteger getMessage(){
    return message;
    }
}
