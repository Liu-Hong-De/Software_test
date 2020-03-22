package com.github.davidmoten.geo;

import com.google.common.collect.Sets;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class GeoHashTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

//    @Test
//    public void adjacentHash() {
//        GeoHash.adjacentHash()
//    }

    @Test
    public void testRight() {
        String null_hash = null;
        String zero_hash = "";
        String right;

//        在GeoHash.checkHash中判斷hash是否為null
        try {
            right = GeoHash.right(null_hash);
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        判斷hash是否長度為0
        try {
            right = GeoHash.right(zero_hash);
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }

//        去掉最後一位再加上所屬方向和位置的值(長度為奇數時)
        right = GeoHash.right("25845");
        assertEquals("2584h", right);

//        去掉最後一位再加上所屬方向和位置的值(長度為偶數時)
        right = GeoHash.right("3121");
        assertEquals("3123", right);

//        測試值在邊界的情況
        right = GeoHash.right("2584z");
        assertEquals("2586b", right);

        right = GeoHash.right("232g");
        assertEquals("2335", right);
    }

    @Test
    public void testLeft() {
       String left;

       left = GeoHash.left("25845");
       assertEquals("25844", left);

       left = GeoHash.left("3122");
       assertEquals("3120", left);

       left = GeoHash.left("25840");
       assertEquals("rgxfp", left);

       left = GeoHash.left("312j");
       assertEquals("2crv", left);
    }

    @Test
    public void testTop() {
        String top;

        top = GeoHash.top("25845");
        assertEquals("25847", top);

        top = GeoHash.top("3121");
        assertEquals("3124", top);

        top = GeoHash.top("2584u");
        assertEquals("2585h", top);

        top = GeoHash.top("312r");
        assertEquals("3182", top);
    }

    @Test
    public void testBottom() {
        String bottom;

        bottom = GeoHash.bottom("25847");
        assertEquals("25845", bottom);

        bottom = GeoHash.bottom("3121");
        assertEquals("3120", bottom);

        bottom = GeoHash.bottom("2584n");
        assertEquals("2581y", bottom);

        bottom = GeoHash.bottom("312b");
        assertEquals("310z", bottom);
    }

    @Test
    public void testAdjacentHash() {
        String adjacentHashOpposite = GeoHash.adjacentHash("72892", Direction.RIGHT, -2);
        assertEquals("7283q", adjacentHashOpposite);

        String adjacentHash = GeoHash.adjacentHash("72892", Direction.RIGHT, 2);
        assertEquals("72896", adjacentHash);
    }

    @Test
    public void testNeighbours() {
        List<String> neighbours = new ArrayList<String>();
        neighbours.add("9370");
        neighbours.add("9378");
        neighbours.add("9373");
        neighbours.add("935r");
        neighbours.add("9371");
        neighbours.add("935p");
        neighbours.add("9379");
        neighbours.add("935x");

        assertEquals(neighbours, GeoHash.neighbours("9372"));
    }

    @Test
    public void testEncodeHashWithMaxHashLength() {
        try {
            String encodeHash = GeoHash.encodeHash(91, 3);
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

        assertEquals("s065kk0dc540", GeoHash.encodeHash(2, 3));
    }

    @Test
    public void testEncodeHashWithLatAndLon() {
        LatLong latLong = new LatLong(2, 3);
        assertEquals("s065kk0d", GeoHash.encodeHash(latLong, 8));
    }

    @Test
    public void testEncodeHashWithLatLonAndMaxLength() {
        LatLong latLong = new LatLong(2, 3);
        assertEquals("s065kk0dc540", GeoHash.encodeHash(latLong));
    }

//    @Test
//    public void testEncodeHash2() {
//    }
//
    @Test
    public void testFromLongToString() {
        try {
            String longToString = GeoHash.fromLongToString(-1);
            fail("no exception");
        } catch (Exception message) {
            assertTrue(message.getMessage().contains("invalid long geohash -1"));
        }

        assertEquals("00000000", GeoHash.fromLongToString(8));
    }

//    @Test
//    public void encodeHashToLong() {
//    }
//
//    @Test
//    public void decodeHash() {
//    }

//
    @Test
    public void testHashLengthToCoverBoundingBox() {
        assertEquals(3, GeoHash.hashLengthToCoverBoundingBox(52.4, 4.9, 52.3, 5));
    }

//    此hash是否為此lat和lon的hash之一
    @Test
    public void testHashContains() {
        assertEquals(true, GeoHash.hashContains("s06", 2, 3));
    }

//    bounding box所屬之hash九宮格和ratio是否正確
    @Test
    public void testCoverBoundingBox() {
        Set<String> str = new LinkedHashSet<String>();
        str.add("s0d");
        str.add("s0e");
        str.add("s0s");
        str.add("s0f");
        str.add("s0g");
        str.add("s0u");
        str.add("s14");
        str.add("s15");
        str.add("s1h");
        Coverage cover = new Coverage(str,4.449462890625);
        assertEquals(cover.getHashes(), GeoHash.coverBoundingBox(6, 4, 4, 6).getHashes());
        assertEquals(cover.getRatio(), GeoHash.coverBoundingBox(6, 4, 4, 6).getRatio(), 0.001);
    }

//    @Test
//    public void coverBoundingBoxMaxHashes() {
//    }
//
    @Test
    public void testCoverBoundingBoxWithLength() {
        Set<String> str = new LinkedHashSet<String>();
        str.add("s0");
        str.add("s1");
        Coverage cover = new Coverage(str,31.640625);
        assertEquals(cover.getHashes(), GeoHash.coverBoundingBox(6, 4, 4, 6, 2).getHashes());
        assertEquals(cover.getRatio(), GeoHash.coverBoundingBox(6, 4, 4, 6, 2).getRatio(), 0.001);
    }
//
//    @Test
//    public void coverBoundingBoxLongs() {
//    }
//
    @Test
    public void testHeightDegrees() {
        assertEquals(4.19095158576E-8, GeoHash.heightDegrees(13), 0.001);
    }

//    @Test
//    public void widthDegrees() {
//    }

//    測試此hash的周圍size大小的格子為何
    @Test
    public void testGridAsString() {
        assertEquals("cc f1 f3 f9 fc \ncb f0 F2 F8 fb \n9z dp dr dx dz \n9y dn dq dw dy \n9v dj dm dt dv \n", GeoHash.gridAsString("dr", 2, Sets.newHashSet("f2", "f8")));
    }

    @Test
    public void testGridAsStringWithLine() {
        assertEquals("f0 f2 f8 \ndp dr dx \ndn dq dw \n", GeoHash.gridAsString("dr", -1, -1, 1, 1));
    }

//    @Test
//    public void testGridAsString1() {
//    }
}