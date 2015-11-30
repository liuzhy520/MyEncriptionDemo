/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rsa;

import java.math.BigInteger;
import java.util.ArrayList;

import common.Factory;

/**
 * 
 * @author wing
 */
public class Digitalsignature {
	private BigInteger key, nS;
	private String username, useremail;
	private String plaintext = "JustTest";
	private String signature;
	private String unSignText;

	public Digitalsignature() {

		signature = null;

	}

	void signing(String input, BigInteger d, BigInteger n) {
		plaintext = input;
		if (plaintext == null) {
			signature = "no plain text!";
		} else {
			ArrayList encodeText = Factory.encodeString(plaintext);
			signature = signature(encodeText, d, n);
		}

	}

	public String getSignature() {
		return signature;
	}

	private String signature(ArrayList encodeText, BigInteger d, BigInteger n) {
		String ciphertext = "";
		for (int i = 0; i < encodeText.size(); i++) {
			BigInteger m = (BigInteger) encodeText.get(i);
			// modPow performs m^d (mod n)
			ciphertext += (m.modPow(d, n)).toString();
			ciphertext += " ";
		}
		return ciphertext;
	}

	boolean verifying(String text, String s, BigInteger e, BigInteger n) {
		if (s != null) {
			unSignText = CheckSign(s, e, n);

			if (unSignText.equals(text)) {
				return true;
			}
		}

		return false;

	}

	public String getunSignText() {
		return unSignText;
	}

	private String CheckSign(String sign, BigInteger e, BigInteger n) {
		String checkText = "";
		BigInteger s = null;
		String[] arr = sign.split(" ");

		for (int i = 0; i < arr.length; i++) {
			s = new BigInteger(arr[i]);

			// modPow performs c^d (mod n)
			checkText += Factory.decodeString(s.modPow(e, n));
		}
		return checkText;
	}

	public void setSetificate(String name, String email, BigInteger e,
			BigInteger n) {
		username = name;
		useremail = email;
		key = e;
		nS = n;
		System.out.println("your cerificate is :");
		System.out.println("name:" + username);
		System.out.println("email:" + useremail);
		System.out.println("public key e:" + key);
		System.out.println("public key n:" + nS);
	}
}
