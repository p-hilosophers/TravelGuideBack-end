package com.example.travelg;

import java.util.Collections;
import java.util.List;

public class QuickSort {

    public void sortDouble(List<Double> list, int start, int end)
    {
        int i = start;
        int j = end;

        if (j - i >= 1)
        {
            double pivotValue = list.get(start);
            while (j > i)
            {
                while (list.get(i) <= pivotValue && i < end && j > i){
                    i++;
                }
                while (list.get(j) >= pivotValue && j > start && j >= i){
                    j--;
                }
                if (j > i)
                    Collections.swap(list, i, j);
            }
            Collections.swap(list, start, j);
            sortDouble(list, start, j - 1);
            sortDouble(list, j + 1, end);
        }
    }

    public void sortInt(List<Integer> list, int start, int end)
    {
        int i = start;
        int j = end;

        if (j - i >= 1)
        {
            int pivotValue = list.get(start);
            while (j > i)
            {
                while (list.get(i) <= pivotValue && i < end && j > i){
                    i++;
                }
                while (list.get(j) >= pivotValue && j > start && j >= i){
                    j--;
                }
                if (j > i)
                    Collections.swap(list,i,j);
            }
            Collections.swap(list, start, j);
            sortInt(list, start, j - 1);
            sortInt(list, j + 1, end);
        }
    }
}
