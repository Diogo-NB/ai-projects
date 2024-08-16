package com.ann.models;

public interface ANNModel {

    void train(int[] input, int expectedOutput);

    void train(int[][] inputs, int[] expectedOutputs);

    float test(int[] array);

    void reset();

}