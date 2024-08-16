package com.hebbmodel;

public interface ANNModel {

    void train(float[] input, float expectedOutput);

    void train(float[][] inputs, float[] expectedOutputs);

    float test(float[] array);

}
