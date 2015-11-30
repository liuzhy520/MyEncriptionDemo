/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.math.BigInteger;
import java.util.Calendar;

/**
 * 
 * @author wing
 */
public class RandomNumber {
	private BigInteger number, va, vb, vc;
	private int length;

	public RandomNumber(int setBit) {
		length = setBit;
		number = BigInteger.valueOf(0);
	}

	public BigInteger getRandomNumber() {

		Calendar cal = Calendar.getInstance();
		long t1 = System.currentTimeMillis();
		int t2 = cal.get(Calendar.SECOND) + 3;
		int t3 = cal.get(Calendar.MILLISECOND) + 1;

		va = BigInteger.valueOf(t1);
		vb = BigInteger.valueOf(t2);
		vc = BigInteger.valueOf(t3);

		if (length <= 6) {
			// for(int i = 10000; i<=0; i--){ int j = 1+1; }
			number = vc.mod(va).subtract(new BigInteger("1"));
			if (number.bitLength() > length) {
				number = number.shiftRight((number.bitLength() - length));
			} else {
				number = number.shiftLeft(length - number.bitLength()).add(
						new BigInteger("1"));
			}

			return number;
		} else if (6 < length && length <= 32) {
			for (int i = 10000; i <= 0; i--) {
				int j = 1 + 1;
			}
			number = va.divide(vc).subtract(new BigInteger("1"));
			if (number.bitLength() > length) {
				number = number.shiftRight((number.bitLength() - length));
			} else {
				number = number.shiftLeft(length - number.bitLength()).add(
						new BigInteger("1"));
			}
			return number;
		} else if (32 < length && length <= 128) {
			number = va.pow(t2).pow(t2).add(new BigInteger("1"));
			if (number.bitLength() > length) {
				number = number.shiftRight((number.bitLength() - length));
			} else {
				number = number.shiftLeft(length - number.bitLength()).add(
						new BigInteger("1"));
			}
			return number;
		} else if (128 < length && length <= 512) {
			number = va.pow(length).multiply(vb);
			if (number.bitLength() > length) {
				number = number.shiftRight((number.bitLength() - length));
			} else {
				number = number.shiftLeft(length - number.bitLength()).add(
						new BigInteger("1"));
			}
			return number;
		} else {
			number = vb.pow(t2).multiply(va.pow(t2)).add(vb.mod(va));
			if (number.bitLength() > length) {
				number = number.shiftRight((number.bitLength() - length));
			} else {
				number = number.shiftLeft(length - number.bitLength()).add(
						new BigInteger("1"));
			}
			return number;
		}

	}

}
