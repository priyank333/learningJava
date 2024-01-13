package com.learning.concurrency.exercise.parllelization.forkjoin.mergesort;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortAction extends RecursiveAction {

    int[] list;

    public MergeSortAction(int[] list) {
        this.list = list;
    }

    @Override
    protected void compute() {
        if (list.length <= 25000) {
            mergeSort(this.list);
            return;
        }
        int middleIndex = list.length / 2;
        processSubTasks(middleIndex);
    }

    private void processSubTasks(int middleIndex) {
        int[] leftSubArray = Arrays.copyOfRange(this.list, 0, middleIndex);
        MergeSortAction subTask1 = new MergeSortAction(
                leftSubArray);
        int[] rightSubArray = Arrays.copyOfRange(this.list, middleIndex, this.list.length);
        MergeSortAction subTask2 = new MergeSortAction(
                rightSubArray);

        invokeAll(subTask1, subTask2);
        merge(leftSubArray, rightSubArray, this.list);
    }

    private void mergeSort(int[] nums) {

        if (nums.length <= 1)
            return;

        int middleIndex = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, middleIndex);
        int[] right = Arrays.copyOfRange(nums, middleIndex, nums.length);

        mergeSort(left);
        mergeSort(right);

        merge(left, right, nums);
    }

    private void merge(int[] leftSubarray, int[] rightSubarray, int[] originalArray) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftSubarray.length && j < rightSubarray.length) {
            if (leftSubarray[i] < rightSubarray[j])
                originalArray[k++] = leftSubarray[i++];
            else
                originalArray[k++] = rightSubarray[j++];
        }

        while (i < leftSubarray.length)
            originalArray[k++] = leftSubarray[i++];

        while (j < rightSubarray.length)
            originalArray[k++] = rightSubarray[j++];
    }

    public void show() {
        for (int i = 0; i < this.list.length; i++) {
            System.out.print(this.list[i] + " ");
        }
        System.out.println();
    }
}
