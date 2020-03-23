package com.github.davidmoten.geo.mem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeomemTest {
    private Geomem geomem;

    @Before
    public void setUp() {
        geomem = new Geomem();
    }

    @After
    public void tearDown() {
        geomem = null;
    }

    @Test
    public void testFind() {
        geomem.add(4.92187500, 4.92187500, 12, 18);  // sog
        assertEquals("[]", geomem.find(6, 4, 4, 6, 0, 10).toString());

        geomem.add(4.921875, 4.921875, 3, 18);
        assertEquals("[Info [lat=4.921875, lon=4.921875, time=3, value=18, id=Optional.of(18)]]", geomem.find(6, 4, 4, 6, 0, 10).toString());

        geomem.add(7.99804688, 4.04296875, 2, 10, 2);   // s16v
        assertEquals("[Info [lat=7.99804688, lon=4.04296875, time=2, value=10, id=Optional.of(2)]]", geomem.find(10, 2, 6, 6, 0, 5).toString());
    }

//    @Test
//    public void createRegionFilter() {
//    }
//
//    @Test
//    public void add() {
//    }
//
//    @Test
//    public void testAdd() {
//    }
//
//    @Test
//    public void testAdd1() {
//    }
//
//    @Test
//    public void testAdd2() {
//    }
}