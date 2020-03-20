package com.github.davidmoten.geo.mem;

import com.google.common.base.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InfoTest<T, R> {
    private Info info;

    @Before
    public void setUp() throws Exception {
        info = new Info(88, 12, 20200317, 12, Optional.of(1));
    }

    @After
    public void tearDown() throws Exception {
        info = null;
    }

    @Test
    public void testId() {
        Optional<Integer> id = Optional.of(1);
        assertEquals(id, info.id());
    }

    @Test
    public void testLat() {
        assertEquals(88, info.lat(), 0.001);
    }

    @Test
    public void lon() {
        assertEquals(12, info.lon(), 0.001);
    }

    @Test
    public void time() {
        assertEquals(20200317, info.time());
    }

    @Test
    public void value() {
        assertEquals(12, info.value());
    }

    @Test
    public void testToString() {
        Optional<Integer> id = Optional.of(1);
        assertEquals("Info [lat=88.0, lon=12.0, time=20200317, value=12, id=Optional.of(1)]", info.toString());
    }
}