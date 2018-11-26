package com.example.travelg;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void sortDouble() {
        QuickSort srt = new QuickSort();
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(7.925);
        doubleList.add(14.539);
        doubleList.add(3.252);
        doubleList.add(2.376);
        doubleList.add(10.678);
        srt.sortDouble(doubleList,0,doubleList.size() - 1);
        List<Double> expected = Arrays.asList(2.376, 3.252, 7.925, 10.678, 14.539);
        assertEquals(expected, doubleList);
    }

    @Test
    public void sortInt() {
        QuickSort srt = new QuickSort();
        List<Integer> integerList = new ArrayList<>();
        integerList.add(15);
        integerList.add(8);
        integerList.add(27);
        integerList.add(4);
        integerList.add(3);
        srt.sortInt(integerList,0,integerList.size() - 1);
        List<Integer> expected = Arrays.asList(3, 4, 8, 15, 27);
        assertEquals(expected, integerList);
    }
}