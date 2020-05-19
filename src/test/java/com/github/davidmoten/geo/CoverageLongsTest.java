package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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

//    test getHashes() with ISP
    @Test
    public void testGetHashesWithISP() {
//        hash element number < count, count > 0
        coverageLongs = new CoverageLongs(hashes, 5, 1.2);
        expectedEx.expect(ArrayIndexOutOfBoundsException.class);
        coverageLongs.getHashes();

//        hash element number = count, count = 0
        hashes = new long[]{};
        long[] ans = {};
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertArrayEquals(ans, coverageLongs.getHashes());

//        hash element number = count, count > 0
        hashes = new long[]{5, 9, 1};
        ans = new long[]{5, 9, 1};
        coverageLongs = new CoverageLongs(hashes, 3, 1.2);
        assertArrayEquals(ans, coverageLongs.getHashes());

//        hash element number > count, count < 0
        coverageLongs = new CoverageLongs(hashes, -3, 1.2);
        expectedEx.expect(NegativeArraySizeException.class);
        coverageLongs.getHashes();

//        hash element number > count, count = 0
        ans = new long[]{};
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertArrayEquals(ans, coverageLongs.getHashes());

//        hash element number > count, count > 0
        ans = new long[]{5, 9};
        coverageLongs = new CoverageLongs(hashes, 2, 1.2);
        assertArrayEquals(ans, coverageLongs.getHashes());
    }

//    test getRatio() with ISP
    @Test
    public void testGetRatioWithISP() {
//        ratio < 0
        coverageLongs = new CoverageLongs(hashes, 3, -1.2);
        assertEquals(-1.2, coverageLongs.getRatio(), 0.001);

//        ratio = 0
        coverageLongs = new CoverageLongs(hashes, 3, 0);
        assertEquals(0, coverageLongs.getRatio(), 0.001);

//        ratio > 0
        coverageLongs = new CoverageLongs(hashes, 3, 1.2);
        assertEquals(1.2, coverageLongs.getRatio(), 0.001);
    }

//    test getHashLength() with ISP
    @Test
    public void testGetHashLengthWithISP() {
//        hashes[0] <= -16, count < 0
        hashes = new long[]{-20, 9, 1};
        coverageLongs = new CoverageLongs(hashes, -1, 1.2);
        assertEquals(12, coverageLongs.getHashLength());

//        hashes[0] <= -16, count = 0
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertEquals(0, coverageLongs.getHashLength());

//        hashes[0] <= -16, count > 0
        coverageLongs = new CoverageLongs(hashes, 1, 1.2);
        assertEquals(12, coverageLongs.getHashLength());

//        -16 < hashes[0] < 16, count < 0
        hashes = new long[]{5, 9, 1};
        coverageLongs = new CoverageLongs(hashes, -1, 1.2);
        assertEquals(5, coverageLongs.getHashLength());

//        -16 < hashes[0] < 16, count = 0
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertEquals(0, coverageLongs.getHashLength());

//        -16 < hashes[0] < 16, count > 0
        coverageLongs = new CoverageLongs(hashes, 1, 1.2);
        assertEquals(5, coverageLongs.getHashLength());

//        hashes[0] >= 16, count < 0
        hashes = new long[]{20, 9, 1};
        coverageLongs = new CoverageLongs(hashes, -1, 1.2);
        assertEquals(4, coverageLongs.getHashLength());

//        hashes[0] >= 16, count = 0
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertEquals(0, coverageLongs.getHashLength());

//        hashes[0] >= 16, count > 0
        coverageLongs = new CoverageLongs(hashes, 1, 1.2);
        assertEquals(4, coverageLongs.getHashLength());
    }

//    test getCount() with ISP
    @Test
    public void testGetCountWithISP() {
//        count < 0
        coverageLongs = new CoverageLongs(hashes, -1, 1.2);
        assertEquals(-1, coverageLongs.getCount());

//        count = 0
        coverageLongs = new CoverageLongs(hashes, 0, 1.2);
        assertEquals(0, coverageLongs.getCount());

//        count > 0
        coverageLongs = new CoverageLongs(hashes, 1, 1.2);
        assertEquals(1, coverageLongs.getCount());
    }

    @Test
    public void testGetHashLengthWithBPC() {
//        P1：{1, 2}
        CoverageLongs coverageLongs = new CoverageLongs(hashes, 0, 1.1);
        assertEquals(0, coverageLongs.getHashLength());

//        P2：{1, 3}
        coverageLongs = new CoverageLongs(hashes, 1, 1.1);
        assertEquals(5, coverageLongs.getHashLength());
    }
}