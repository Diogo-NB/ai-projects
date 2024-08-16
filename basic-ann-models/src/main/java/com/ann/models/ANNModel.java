package com.ann.models;

public interface ANNModel {

    void train(int[] input, int target);

    void train(int[][] input, int[] target);

    float test(int[] array);

    void reset();

}