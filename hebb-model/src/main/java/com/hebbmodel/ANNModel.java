package com.hebbmodel;

public interface ANNModel {

    void train(int[] input, int expectedOutput);

    void train(int[][] inputs, int[] expectedOutputs);

    public float test(int[] array);

}