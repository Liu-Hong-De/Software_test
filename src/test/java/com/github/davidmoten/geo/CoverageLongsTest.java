package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoverageLongsTest {
    private CoverageLongs coverageLongs;
    private CoverageLongs coverageLongs_count_zero;
    private long[] hashes;

    @Before
    public void setUp() {
        hashes = new long[]{5, 9, 1};
        coverageLongs = new CoverageLongs(hashes, 3, 1.8);

        coverageLongs_count_zero = new CoverageLongs(hashes, 0, 1.8);
    }

    @After
    public void tearDown() {
        hashes = null;
        coverageLongs = null;
        coverageLongs_count_zero = null;

    }

    @Test
    public void testGetHashes() {
        long[] res = coverageLongs.getHashes();
        long[] ans = {5, 9, 1};
        assertArrayEquals(ans, res);
    }

    @Test
    public void testGetRatio() {
        double ratio = coverageLongs.getRatio();
        assertEquals(1.8, ratio, 0.001);
    }

    @Test
    public void testGetHashLength() {
        int hash_length_with_zero = coverageLongs_count_zero.getHashLength();
        assertEquals(0, hash_length_with_zero);

        int hash_length = coverageLongs.getHashLength();
        assertEquals(5, hash_length);
    }

//    @Test
//    public void testToString() {
//        long[] res = coverageLongs.getHashes();
//        String toString = "Coverage [hashes=" + res + ", ratio=1.8]";
//        String oo = coverageLongs.toString();
//        assertEquals(toString, oo);
//    }

    @Test
    public void testGetCount() {
        assertEquals(3, coverageLongs.getCount());
    }
}