#!/bin/bash
a2ps-ejava \
    BubbleSort.java \
    InsertionSort.java \
    MergeSort.java \
    QuickSort.java \
    Sorting.java \
    SortStatistics.java \
    SortTest.java \
 		> print.ps
ps2pdf print.ps
rm print.ps
