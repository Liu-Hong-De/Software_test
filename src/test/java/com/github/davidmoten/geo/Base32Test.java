package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Base32Test {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //    32進制加密
    @Test
    public void testEncodeBase32() throws Exception {
        String encodeNegative = Base32.encodeBase32(-2, 2);
        assertEquals("-02", encodeNegative);

        String encode = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode);
    }

    // 32進制加密，補零到12位數
    @Test
    public void testEncodeBase32_2() throws  Exception {
        String encode = Base32.encodeBase32(75324);
        assertEquals("0000000029jw", encode);
    }

    //    32進制解密
    @Test
    public void testDecodeBase32() throws Exception {
        long decodeNegative = Base32.decodeBase32("-29jw");
        assertEquals(-75324, decodeNegative);

        long decode = Base32.decodeBase32("29jw");
        assertEquals(75324, decode);
    }

    //    判斷此char在Base32中的characters陣列的位置為何
    @Test
    public void testGetCharIndex() throws Exception {
        try {
            int noExistChar = Base32.getCharIndex('a');
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("not a base32 character: a"));
        }

        int charIndex = Base32.getCharIndex('j');
        assertEquals(17, charIndex);
    }

    @Test
    public void testPadLeftWithZerosToLength() throws Exception {
        String s = Base32.padLeftWithZerosToLength("29jw", 5);
        assertEquals("029jw", s);
    }

//    test encodeBase32(long i, int length) with ISP
    @Test
    public void testEncodeBase32WithISP() throws Exception {
//        i <= -32, length < 0
        String encodehash = Base32.encodeBase32(-35, -2);
        assertEquals("-13", encodehash);

//        -32 < i < 32, length < 0
        encodehash = Base32.encodeBase32(-10, -2);
        assertEquals("-b", encodehash);

//        i >= 32, length < 0
        encodehash = Base32.encodeBase32(35, -2);
        assertEquals("13", encodehash);

//        i <= -32, length = 0
        encodehash = Base32.encodeBase32(-35, 0);
        assertEquals("-13", encodehash);

//        -32 < i < 32, length = 0
        encodehash = Base32.encodeBase32(-10, 0);
        assertEquals("-b", encodehash);

//        i >= 32, length = 0
        encodehash = Base32.encodeBase32(35, 0);
        assertEquals("13", encodehash);

//        i <= -32, length > 0
        encodehash = Base32.encodeBase32(-35, 5);
        assertEquals("-00013", encodehash);

//        -32 < i < 32, length > 0
        encodehash = Base32.encodeBase32(-10, 5);
        assertEquals("-0000b", encodehash);

//        i >= 32, length > 0
        encodehash = Base32.encodeBase32(35, 5);
        assertEquals("00013", encodehash);
    }

//    test encodeBase32(long i) with ISP
    @Test
    public void testEncodeBase32WithISP_2() throws Exception {
//        i <= -32
        String encodeHash = Base32.encodeBase32(-33);
        assertEquals("-000000000011",encodeHash);

//        -32 < i < 32
        encodeHash = Base32.encodeBase32(-10);
        assertEquals("-00000000000b", encodeHash);

//        i >= 32
        encodeHash = Base32.encodeBase32(33);
        assertEquals("000000000011", encodeHash);
    }

//    test decodeBase32(String hash) with ISP
//    C1：hash start with "-", C2：hash contains the character which can not be converted in Base 32
    @Test
    public void testDecodeBase32WithISP() throws Exception {
        long decodeHash;
//        C1：T, C2：T
        try {
            decodeHash = Base32.decodeBase32("-**83");
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("not a base32 character: *"));
        }

//        C1：T, C2：F
        decodeHash = Base32.decodeBase32("-29jw");
        assertEquals(-75324, decodeHash);

//        C1：F, C2：T
        try {
            decodeHash = Base32.decodeBase32("**83");
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("not a base32 character: *"));
        }

//        C1：F, C2：F
        decodeHash = Base32.decodeBase32("29jw");
        assertEquals(75324, decodeHash);
    }
}