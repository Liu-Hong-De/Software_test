package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class Base32Test {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    //    32進制加密
    @Test
    public void testEncodeBase32() {
        String encodeNegative = Base32.encodeBase32(-2, 2);
        assertEquals("-02", encodeNegative);

        String encode = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode);
    }

    // 32進制加密，補零到12位數
    @Test
    public void testEncodeBase32_2() {
        String encode = Base32.encodeBase32(75324);
        assertEquals("0000000029jw", encode);
    }

    //    32進制解密
    @Test
    public void testDecodeBase32() {
        long decodeNegative = Base32.decodeBase32("-29jw");
        assertEquals(-75324, decodeNegative);

        long decode = Base32.decodeBase32("29jw");
        assertEquals(75324, decode);
    }

    //    判斷此char在Base32中的characters陣列的位置為何
    @Test
    public void testGetCharIndex() throws Exception{
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("not a base32 character: a");
        int noExistChar = Base32.getCharIndex('a');

        int charIndex = Base32.getCharIndex('j');
        assertEquals(17, charIndex);
    }

    @Test
    public void testPadLeftWithZerosToLength() {
        String s = Base32.padLeftWithZerosToLength("29jw", 5);
        assertEquals("029jw", s);
    }

//    test encodeBase32(long i, int length) with ISP
    @Test
    public void testEncodeBase32WithISP() {
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
    public void testEncodeBase32WithISP_2() {
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
    public void testDecodeBase32WithISP() {
        long decodeHash;
//        C1：T, C2：T
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("not a base32 character: *");
        decodeHash = Base32.decodeBase32("-**83");

//        C1：T, C2：F
        decodeHash = Base32.decodeBase32("-29jw");
        assertEquals(-75324, decodeHash);

//        C1：F, C2：T
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("not a base32 character: *");
        decodeHash = Base32.decodeBase32("**83");

//        C1：F, C2：F
        decodeHash = Base32.decodeBase32("29jw");
        assertEquals(75324, decodeHash);
    }

//    test getCharIndex(char ch) with ISP
//    C1：the character in the characterIndexes map
    @Test
    public void testGetCharIndexWithISP() {
//        C1：T
        int ch_Index = Base32.getCharIndex('b');
        assertEquals(10, ch_Index);

//        C1：F
        try {
            ch_Index = Base32.getCharIndex('*');
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("not a base32 character: *"));
        }
    }

//    test padLeftWithZerosToLength(String s, int length) with ISP
//    C1：s is null, C2：s.length >= length
    @Test
    public void testPadLeftWithZerosToLengthWithISP() {
//        C1：T, C2：T or F
        expectedEx.expect(NullPointerException.class);
        String s = Base32.padLeftWithZerosToLength(null, 8);

//        C1：F, C2：T
        s = Base32.padLeftWithZerosToLength("1234", 2);
        assertEquals("1234", s);

//        C1：F, C2：F
        s = Base32.padLeftWithZerosToLength("1234", 6);
        assertEquals("001234", s);
    }

    @Test
    public void testEncodeBase32WithBPC() {
//        P1：{1, 2, 3, 4, 5, 4, 6, 7, 9}
        String encodeHash = Base32.encodeBase32(33, 0);
        assertEquals("11", encodeHash);

//        P4：{1, 2, 3, 4, 6, 7, 9}
        encodeHash = Base32.encodeBase32(10, 0);
        assertEquals("b", encodeHash);

//        P5：{1, 2, 4, 6, 7, 8}
        encodeHash = Base32.encodeBase32(-10, 0);
        assertEquals("-b", encodeHash);
    }

    @Test
    public void testDecodeBase32WithBPC() {
//        P1：{1, 2, 3, 4, 6, 7, 3, 8, 9, 10}
        long decode = Base32.decodeBase32("-1b");
        assertEquals(-42, decode);

//        P3：{1, 2, 3, 4, 5}
        try {
            decode = Base32.decodeBase32("a");
            fail("no message");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("not a base32 character: a"));
        }

//        P5：{1, 2, 3, 8, 10}
        decode = Base32.decodeBase32("");
        assertEquals(0, decode);
    }
}