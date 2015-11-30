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
public class Points {
    private BigInteger x,y;
    public static final Points POINT_INFINITY = new Points();
    private Points()
    {
        this.x = null;
        this.y = null;
    }
    public Points(BigInteger X, BigInteger Y)
    {
        x = X;
        y = Y;
    }
    public BigInteger getX()
    {
        return x;
    }
    public BigInteger getY()
    {
        return y;
    }
    public void setX(BigInteger X)
    {
        x = X;
    }
    public void setY(BigInteger Y)
    {
        y = Y;
    }
    public boolean equalsNull()
    {
        if(x == null && y == null)
        {
            return true;
        }
        else
        return false;
    }


}
