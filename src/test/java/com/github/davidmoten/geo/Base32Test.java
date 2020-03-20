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
    public void encodeBase32() throws Exception {
        String encode = Base32.encodeBase32(75324, 4);
        assertEquals("29jw", encode);
    }

    //    32進制解密
    @Test
    public void decodeBase32() throws Exception {
        long decode = Base32.decodeBase32("29jw");
        assertEquals(75324, decode);
    }

    //    判斷此char在Base32中的characters陣列的位置為何
    @Test
    public void getCharIndex() throws Exception {
        int charIndex = Base32.getCharIndex('j');
        assertEquals(17, charIndex);
    }

    @Test
    public void padLeftWithZerosToLength() throws Exception {
        String s = Base32.padLeftWithZerosToLength("29jw", 4);
        assertEquals("29jw", s);
    }
}