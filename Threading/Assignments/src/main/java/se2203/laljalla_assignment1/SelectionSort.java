package se2203.laljalla_assignment1;

import java.util.Arrays;

public class SelectionSort extends SortingHubController implements SortingStrategy,Runnable{

    private SortingHubController controller;
    private static int [] list;

    public SelectionSort(int [] numbers, SortingHubController controller){
        list=numbers;
        this.controller=controller;
    }

    public void sort (int [] numbers) {
        list=numbers;

        int n = numbers.length;

        //move boundary of unsorted subarray one by one
        for (int i = 0 ; i < n-1; i++ ){
            int nextSmlltIndx =i;

            //find the min element in unsorted array
            for (int j=i+1; j<n; j++){
                if (numbers[j] < numbers[nextSmlltIndx]) {
                    nextSmlltIndx = j;
                }
            }

            //Swap the found minimum element with the first element
            int temp = numbers[nextSmlltIndx];
            numbers[nextSmlltIndx] = numbers[i];
            numbers[i] = temp;

            try {
                Thread.sleep(100);
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
