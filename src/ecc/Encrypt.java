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
public class Encrypt {
    private Points message;
    private BigInteger shareK;
    private EC ec;
    public Encrypt(EC Ec){
        ec = Ec;
    }
    public Points encryption(Points input, Points shareK){
        Points cipher = new Points(null,null);
        Calculator cal = new Calculator();
        cipher = cal.Addition(input, shareK, ec.geta(), ec.getFp());
        return cipher;

    }
}
