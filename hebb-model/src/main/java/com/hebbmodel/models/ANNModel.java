package com.hebbmodel.models;

public interface ANNModel {

    void train(int[] input, int expectedOutput);

    void train(int[][] inputs, int[] expectedOutputs);

    public float test(int[] array);

}