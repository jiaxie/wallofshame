package com.wallofshame.repository.peoplesoft;

import org.junit.Test;


public class AesProviderTest {

    @Test
    public void testPasswordEncrypt() throws Exception{
        String plainText = "HJIAO";
        System.out.println(AesPassProvider.encrypt(plainText));
    }

    @Test
    public void testPasswordDecrypt() throws Exception {
        String p = "qOUwj5WpXDKtbCo8stly-g";
        System.out.println(AesPassProvider.decrypt(p));
    }
}
