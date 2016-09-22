package com.yesikov.longhashmap;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class LongMapTest {

    private LongMap longMap;

    @Before
    public void executedBeforeEach() {
        longMap = new LongMap();
    }

    @Test
    public void test_longHashMap_put(){
        assertNull(longMap.put(1,"test put"));
    }

    @Test
    public void test_longMap_get(){
        String expected = "test get";
        longMap.put(1,"test get");
        assertTrue(expected.equals(longMap.get(1)));
    }

    @Test
    public void test_longMap_remove(){
        String expected = "test remove";
        longMap.put(1,expected);
        assertTrue(expected.equals(longMap.remove(1)));
    }

    @Test
    public void test_longMap_keys(){
        long [] expected = {11, 333};
        longMap.put(11, "aa");
        longMap.put(333,"bb");
        assertTrue(Arrays.equals(expected, longMap.keys()));
    }

    @Test
    public void test_longMap_values(){
        String [] expected = {"aa","bb","cc"};
        longMap.put(1,"aa");
        longMap.put(2,"bb");
        longMap.put(3,"cc");
        assertTrue(Arrays.equals(expected, longMap.values()));
    }

    @Test
    public void test_longMap_containsKey(){
        longMap.put(123,"aa");
        assertTrue(longMap.containsKey(123));
    }

    @Test
    public void test_longMap_containsValue(){
        String expected = "test";
        longMap.put(777,expected);
        assertTrue(longMap.containsValue(expected));
    }

    @Test
    public void test_longMap_size(){
        long expected = 4;
        longMap.put(1,"aa");
        longMap.put(2,"bb");
        longMap.put(3,"cc");
        longMap.put(4,"dd");
        longMap.put(1,"ee");
        long result = longMap.size();
        assertEquals(expected, result);
    }

}
