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
    public void testCoverage() {
        long[] hashes = {3, 5, 6, 2};
        CoverageLongs coverageLongs = new CoverageLongs(hashes, 4, 1.8);
        Coverage coverage_long = new Coverage(coverageLongs);

        double ratio = coverage_long.getRatio();
        assertEquals(1.8, ratio, 0.001);

        Set<String> origin_set_hashes = new HashSet<String>();
        origin_set_hashes.add("00");
        origin_set_hashes.add("000");
        origin_set_hashes.add("00000");
        origin_set_hashes.add("000000");

        Set<String> set_hashes = coverage_long.getHashes();
        assertEquals(origin_set_hashes, set_hashes);
    }

    @Test
    public void testGetHashes() {
        Set<String> hashes = coverage.getHashes();
        assertEquals(str, hashes);
    }

    @Test
    public void testGetRatio() {
        double ratio = coverage.getRatio();
        assertEquals(1.2, ratio, 0.001);
    }

//    Set第一個字串的長度
    @Test
    public void testGetHashLength() {
        Set<String> nullSet = new HashSet<String>();
        Coverage coverage_null_set = new Coverage(nullSet, 1.6);
        int size = coverage_null_set.getHashLength();
        assertEquals(0, size);

        size = coverage.getHashLength();
        assertEquals(6, size);
    }

    @Test
    public void testToString() {
        String testToString = coverage.toString();
        assertEquals("Coverage [hashes=[1.5232, 1.9], ratio=1.2]", testToString);
    }
}