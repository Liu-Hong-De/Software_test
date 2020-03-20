package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CoverageTest {
    private Coverage coverage;
    private Set<String> str;

    @Before
    public void setUp() {
        str = new HashSet<String>();
        str.add("1.5232");
        str.add("1.9");
        coverage = new Coverage(str, 1.2);
    }

    @After
    public void tearDown() {
        str = null;
        coverage = null;
    }

    @Test
    public void getHashes() {
        Set<String> hashes = coverage.getHashes();
        assertEquals(str, hashes);
    }

    @Test
    public void getRatio() {
        double ratio = coverage.getRatio();
        assertEquals(1.2, ratio, 0.001);
    }

//    Set第一個字串的長度
    @Test
    public void getHashLength() {
        int size = coverage.getHashLength();
        assertEquals(6, size);
    }

    @Test
    public void testToString() {
        String testToString = coverage.toString();
        assertEquals("Coverage [hashes=[1.5232, 1.9], ratio=1.2]", testToString);
    }
}