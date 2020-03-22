package com.github.davidmoten.geo.mem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

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
        Iterable<Info<Integer, Integer>> it = Collections.emptyList();
        geomem.add(4.92187500, 4.92187500, 3, 18);  // sog
        assertEquals("[Info [lat=4.921875, lon=4.921875, time=3, value=18, id=Optional.of(18)]]", geomem.find(6, 4, 4, 6, 0, 10).toString());
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