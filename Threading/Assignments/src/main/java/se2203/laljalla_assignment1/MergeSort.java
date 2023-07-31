package se2203.laljalla_assignment1;

import javafx.application.Platform;

import java.util.Arrays;

public class MergeSort extends SortingHubController implements SortingStrategy, Runnable {

    private static int [] list;
    private static SortingHubController controller;

    public MergeSort(int [] numbers, SortingHubController controller) {
        list = numbers;
        this.controller=controller;
    }



    @Override
    public void sort(int[] numbers) {
        mergeSorting(numbers, 0, numbers.length-1);

    }


    public static void mergeSorting(int[] numbers, int small, int big) {
        if (big <= small) return;

        int mid = (small+big)/2;
        mergeSorting(numbers, small, mid);
        mergeSorting(numbers, mid+1, big);
        merging (numbers, small, mid, big);
    }


    public static void merging (int[] numbers, int small, int mid, int big) {
        int left[] = new int[mid - small + 1];
        int right[] = new int[big - mid];

        int j = 0;
        while (j < left.length) {
            left[j] = numbers[small + j];
            j++;
        }

        int k = 0;
        while (k < right.length) {
            right[k] = numbers[mid + k + 1];
            k++;
        }



        int leftInd = 0;
        int rightInd= 0;


        for (int i = small; i < big + 1; i++) {
            if (leftInd < left.length && rightInd < right.length) {
                if (left[leftInd] < right[rightInd]) {
                    numbers[i] = left[leftInd];
                    leftInd++;
                } else {
                    numbers[i] = right[rightInd];
                    rightInd++;
                }
            } else if (leftInd < left.length) {
                numbers[i] = left[leftInd];
                leftInd++;
            } else if (rightInd < right.length) {
                numbers[i] = right[rightInd];
                rightInd++;
            }
            try {
                Thread.sleep(50);
                controller.updateGraph(numbers);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public void run () {
       sort(list);
    }

}
