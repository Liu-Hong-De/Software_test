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

//    test adjacentHash(String hash, Direction direction) with ISP
//    C1：hash is null, C2：hash.length>0, C3：direction, C4：hash in border
    @Test
    public void testAdjacentHashWithISP() {
//        C1：T, C2：T or F, C3：any direction, C4：T or F
        try {
            GeoHash.adjacentHash(null, Direction.TOP);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：top, C4：T
        assertEquals("f8", GeoHash.adjacentHash("dx", Direction.TOP));

//        C1：F, C2：T, C3：bottom, C4：T
        assertEquals("6r", GeoHash.adjacentHash("d2", Direction.BOTTOM));

//        C1：F, C2：T, C3：left, C4：T
        assertEquals("9f", GeoHash.adjacentHash("d4", Direction.LEFT));

//        C1：F, C2：T, C3：right, C4：T
        assertEquals("e5", GeoHash.adjacentHash("dg", Direction.RIGHT));

//        C1：F, C2：T, C3：top, C4：F
        assertEquals("s", GeoHash.adjacentHash("k", Direction.TOP));

//        C1：F, C2：T, C3：bottom, C4：F
        assertEquals("h", GeoHash.adjacentHash("k", Direction.BOTTOM));

//        C1：F, C2：T, C3：left, C4：F
        assertEquals("7", GeoHash.adjacentHash("k",Direction.LEFT));

//        C1：F, C2：T, C3：right, C4：F
        assertEquals("m", GeoHash.adjacentHash("k", Direction.RIGHT));

//        C1：F, C2：F, C3：any direction, C4：T or F
        try {
            GeoHash.adjacentHash("", Direction.TOP);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test right(String hash) with ISP
//    C1：hash is null, C2：hash.length > 0, C3：hash in border
    @Test
    public void testRightWithISP() {
//        C1：T, C2：T or F, C3：T or F
        try {
            GeoHash.right(null);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：T
        assertEquals("e5", GeoHash.right("dg"));

//        C1：F, C2：T, C3：F
        assertEquals("m", GeoHash.right("k"));

//        C1：F, C2：F, C3：T or F
        try {
            GeoHash.right("");
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test left(String hash) with ISP
//    C1：hash is null, C2：hash.length > 0, C3：hash in border
    @Test
    public void testLeftWithISP() {
//        C1：T, C2：T or F, C3：T or F
        try {
            GeoHash.left(null);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：T
        assertEquals("9f", GeoHash.left("d4"));

//        C1：F, C2：T, C3：F
        assertEquals("7", GeoHash.left("k"));

//        C1：F, C2：F, C3：T or F
        try {
            GeoHash.left("");
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test top(String hash) with ISP
//    C1：hash is null, C2：hash.length > 0, C3：hash in border
    @Test
    public void testTopWithISP() {
//        C1：T, C2：T or F, C3：T or F
        try {
            GeoHash.top(null);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：T
        assertEquals("f8", GeoHash.top("dx"));

//        C1：F, C2：T, C3：F
        assertEquals("s", GeoHash.top("k"));

//        C1：F, C2：F, C3：T or F
        try {
            GeoHash.top("");
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test bottom(String hash) with ISP
//    C1：hash is null, C2：hash.length > 0, C3：hash in border
    @Test
    public void testBottomWithISP() {
//        C1：T, C2：T or F, C3：T or F
        try {
            GeoHash.bottom(null);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：T
        assertEquals("6r", GeoHash.bottom("d2"));

//        C1：F, C2：T, C3：F
        assertEquals("h", GeoHash.bottom("k"));

//        C1：F, C2：F, C3：T or F
        try {
            GeoHash.bottom("");
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test adjacentHash(String hash, Direction direction, int steps) with ISP
//    C1：hash is null, C2：hash.length>0, C3：direction, C4：hash in border, C5：steps
    @Test
    public void testAdjacentHashStepsWithISP() {
//        C1：T, C2：T or F, C3：direction, C4：T or F, C5：steps(>0 or <0)
        try {
            GeoHash.adjacentHash(null, Direction.TOP, 2);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：T, C2：T or F, C3：direction, C4：T or F, C5：steps = 0
        assertEquals(null, GeoHash.adjacentHash(null, Direction.RIGHT, 0));

//        C1：F, C2：T, C3：top, C4：T, C5：steps < 0
        assertEquals("6q", GeoHash.adjacentHash("d2", Direction.TOP, -2));

//        C1：F, C2：T, C3：top, C4：T, C5：steps = 0
        assertEquals("d2", GeoHash.adjacentHash("d2", Direction.TOP, 0));

//        C1：F, C2：T, C3：top, C4：T, C5：steps > 0
        assertEquals("f9", GeoHash.adjacentHash("dx", Direction.TOP, 2));

//        C1：F, C2：T, C3：bottom, C4：T, C5：steps < 0
        assertEquals("f9", GeoHash.adjacentHash("dx", Direction.BOTTOM, -2));

//        C1：F, C2：T, C3：bottom, C4：T, C5：steps = 0
        assertEquals("dx", GeoHash.adjacentHash("dx", Direction.BOTTOM, 0));

//        C1：F, C2：T, C3：bottom, C4：T, C5：steps > 0
        assertEquals("6q", GeoHash.adjacentHash("d2", Direction.BOTTOM, 2));

//        C1：F, C2：T, C3：left, C4：T, C5：steps < 0
        assertEquals("e7", GeoHash.adjacentHash("dg", Direction.LEFT, -2));

//        C1：F, C2：T, C3：left, C4：T, C5：steps = 0
        assertEquals("dg", GeoHash.adjacentHash("dg", Direction.LEFT, 0));

//        C1：F, C2：T, C3：left, C4：T, C5：steps > 0
        assertEquals("9d", GeoHash.adjacentHash("d4", Direction.LEFT, 2));

//        C1：F, C2：T, C3：right, C4：T, C5：steps < 0
        assertEquals("9d", GeoHash.adjacentHash("d4", Direction.RIGHT, -2));

//        C1：F, C2：T, C3：right, C4：T, C5：steps = 0
        assertEquals("d4", GeoHash.adjacentHash("d4", Direction.RIGHT, 0));

//        C1：F, C2：T, C3：right, C4：T, C5：steps > 0
        assertEquals("e7", GeoHash.adjacentHash("dg", Direction.RIGHT, 2));

//        C1：F, C2：T, C3：top, C4：F, C5：steps < 0
        assertEquals("76", GeoHash.adjacentHash("7k", Direction.TOP, -2));

//        C1：F, C2：T, C3：top, C4：F, C5：steps = 0
        assertEquals("7k", GeoHash.adjacentHash("7k", Direction.TOP, 0));

//        C1：F, C2：T, C3：top, C4：F, C5：steps > 0
        assertEquals("7q", GeoHash.adjacentHash("7k", Direction.TOP, 2));

//        C1：F, C2：T, C3：bottom, C4：F, C5：steps < 0
        assertEquals("7q", GeoHash.adjacentHash("7k", Direction.BOTTOM, -2));

//        C1：F, C2：T, C3：bottom, C4：F, C5：steps = 0
        assertEquals("7k", GeoHash.adjacentHash("7k", Direction.BOTTOM, 0));

//        C1；F, C2：T, C3：bottom, C4：F, C5：steps > 0
        assertEquals("76", GeoHash.adjacentHash("7k", Direction.BOTTOM, 2));

//        C1：F, C2：T, C3：left, C4：F, C5：steps < 0
        assertEquals("7u", GeoHash.adjacentHash("7k", Direction.LEFT, -2));

//        C1：F, C2：T, C3：left, C4：F, C5：steps = 0
        assertEquals("7k", GeoHash.adjacentHash("7k", Direction.LEFT, 0));

//        C1：F, C2：T, C3：left, C4：F, C5：steps > 0
        assertEquals("6u", GeoHash.adjacentHash("7k", Direction.LEFT, 2));

//        C1：F, C2：T, C3：right, C4：F, C5：steps < 0
        assertEquals("6u", GeoHash.adjacentHash("7k", Direction.RIGHT, -2));

//        C1：F, C2：T, C3：right, C4：F, C5：steps = 0
        assertEquals("7k", GeoHash.adjacentHash("7k", Direction.RIGHT, 0));

//        C1：F, C2：T, C3：right, C4：F, C5：steps > 0
        assertEquals("7u", GeoHash.adjacentHash("7k", Direction.RIGHT, 2));

//        C1：F, C2：F, C3：any direction, C4：T or F, C5：steps(>0 or <0)
        try {
            GeoHash.adjacentHash("", Direction.TOP, 2);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }

//        C1：F, C2：F, C3：any direction, C4：T or F, C5：steps = 0
        assertEquals("", GeoHash.adjacentHash("", Direction.RIGHT, 0));
    }

//    test neighbours(String hash) with ISP
//    C1：hash is null, C2：hash.length > 0, C3：hash in border
    @Test
    public void testNeighboursWithISP() {
//        C1：T, C2：T or F, C3：T or F
        try {
            GeoHash.neighbours(null);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("hash must be non-null"));
        }

//        C1：F, C2：T, C3：T
        List<String> neighbours = new ArrayList<String>();
        neighbours.add("d0");
        neighbours.add("d8");
        neighbours.add("d3");
        neighbours.add("6r");
        neighbours.add("d1");
        neighbours.add("6p");
        neighbours.add("d9");
        neighbours.add("6x");
        assertEquals(neighbours, GeoHash.neighbours("d2"));

//        C1：F, C2：T, C3：F
        neighbours.clear();
        neighbours.add("7");
        neighbours.add("m");
        neighbours.add("s");
        neighbours.add("h");
        neighbours.add("e");
        neighbours.add("5");
        neighbours.add("t");
        neighbours.add("j");
        assertEquals(neighbours, GeoHash.neighbours("k"));

//        C1：F, C2：F, C3：T or F
        try {
            GeoHash.neighbours("");
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("adjacent has no meaning for a zero length hash that covers the whole world"));
        }
    }

//    test encodeHash(double latitude, double longitude) with ISP
    @Test
    public void testEncodeHashWithISP() {
//        latitude < -90, longitude < -180
        try {
            GeoHash.encodeHash(-100, -200);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        latitude < -90, -180 <= longitude <= 180
        try {
            GeoHash.encodeHash(-100, 50);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        latitude < -90, longitude > 180
        try {
            GeoHash.encodeHash(-100, 200);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        -90 <= latitude <= 90, longitude < -180
        assertEquals("z8cu2yhrn5x1", GeoHash.encodeHash(50, -200));

//        -90 <= latitude <= 90, -180 <= longitude <= 180
        assertEquals("v0gs3y0zh7w1", GeoHash.encodeHash(50, 50));

//        -90 <= latitude <= 90, longitude > 180
        assertEquals("b2yhrn5x1g8c", GeoHash.encodeHash(50, 200));

//        latitude > 90, longitude < -180
        try {
            GeoHash.encodeHash(100, -200);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        latitude > 90, -180 <= longitude <= 180
        try {
            GeoHash.encodeHash(100, 50);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        latitude > 90, longitude > 180
        try {
            GeoHash.encodeHash(100, 200);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }
    }

//    test encodeHash(LatLong p, int length) with ISP
    @Test
    public void testEncodeHashWithISP_LatLong_Length() {
//        LatLong(lat < -90, any lon), any length
        LatLong latLong = new LatLong(-100, 50);
        try {
            GeoHash.encodeHash(latLong, 12);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(any lat, any lon), length <= 0
        latLong = new LatLong(50, -200);
        try {
            GeoHash.encodeHash(latLong, 0);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("length must be greater than zero"));
        }

//        LatLong(-90 <= lat <= 90, lon < -180), 0 < length <= 12
        assertEquals("z8cu2yhrn5x1", GeoHash.encodeHash(latLong, 12));

//        LatLong(any lat, any, lon), length > 12
        try {
            GeoHash.encodeHash(latLong, 13);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("invalid long geohash "));
        }

//        LatLong(-90 <= lat <= 90, -180 <= lon <= 180), 0 < length <= -12
        latLong = new LatLong(50, 50);
        assertEquals("v0gs3y0zh7w1", GeoHash.encodeHash(latLong, 12));

//        LattLong(-90 <= lat <= 90, lon > 180), 0 < length <= 12
        latLong = new LatLong(50, 200);
        assertEquals("b2yhrn5x1g8c", GeoHash.encodeHash(latLong, 12));

//        LatLong(lat > 90, any lon), any length
        latLong = new LatLong(100, 50);
        try {
            GeoHash.encodeHash(latLong, 12);
            fail("no exceqtion");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }
    }

//    test encodeHash(LatLong p) with ISP
    @Test
    public void testEncodeHashWithIDP_LatLong() {
//        LatLong(lat < -90, lon < -180)
        LatLong latLong = new LatLong(-100, -200);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(lat < -90, -180 <= lon <= 180)
        latLong = new LatLong(-100, 50);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(lat < -90, lon > 180)
        latLong = new LatLong(-100, 200);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(-90 <= lat <= 90, lon < -180)
        latLong = new LatLong(50, -200);
        assertEquals("z8cu2yhrn5x1", GeoHash.encodeHash(latLong));

//        LatLong(-90 <= lat <= 90, -180 <= lon <= 180)
        latLong = new LatLong(50, 50);
        assertEquals("v0gs3y0zh7w1", GeoHash.encodeHash(latLong));

//        LatLong(-90 <= lat <= 90, lon > 180)
        latLong = new LatLong(50, 200);
        assertEquals("b2yhrn5x1g8c", GeoHash.encodeHash(latLong));

//        LatLong(lat > 90, lon < -180)
        latLong = new LatLong(100, -200);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(lat > 90, -180 <= lon <= 180)
        latLong = new LatLong(100, 50);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }

//        LatLong(lat > 90, lon > 180)
        latLong = new LatLong(100, 200);
        try {
            GeoHash.encodeHash(latLong);
            fail("no exception");
        } catch (IllegalArgumentException message) {
            assertTrue(message.getMessage().contains("latitude must be between -90 and 90 inclusive"));
        }
    }


}