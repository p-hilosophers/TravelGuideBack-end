
package com.example.travelg;

import java.util.Collections;
import java.util.List;


public class QuickSort<T extends Comparable<? super T>> {

    public void sort(List<T> list) {

        Collections.sort(list, T::compareTo);

    }
}

//    public void sort(List<T> list, int start, int end)
//    {
//        int i = start;
//        int j = end;
//
//        if (j - i >= 1)
//        {
//            T pivotValue = list.get(start);
//            while (j > i)
//            {
//                while (list.get(i).compareTo(pivotValue)<=0 && i<end && j > i){
//                    i++;
//                }
//                while (list.get(j).compareTo(pivotValue)>=0 && j > start && j >= i){
//                    j--;
//                }
//                if (j > i)
//                    Collections.swap(list,i,j);
//            }
//            Collections.swap(list, start, j);
//            sort(list, start, j - 1);
//            sort(list, j + 1, end);
//        }
//    }