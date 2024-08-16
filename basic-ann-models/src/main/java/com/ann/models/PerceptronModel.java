package com.ann.models;

import com.ann.util.RandomUtil;

public class PerceptronModel implements ANNModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias = 0.0f;

    private float learningRate = 0.01f;

    /**
     * @param size size of the model
     */
    public PerceptronModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = RandomUtil.randomFloat(-0.5f, +0.5f);
        }
    }

    /**
     * @param size         size of the model
     * @param learningRate learning rate of the model
     */
    public PerceptronModel(int size, float learningRate) {
        this(size);
        this.learningRate = learningRate;
    }

    @Override
    public String toString() {
        return "Perceptron Model";
    }

    public float[] getWeigths() {
        return weigths.clone();
    }

    public float getBias() {
        return bias;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void train(int[] input, int target) {
        if (input.length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        float yLiq = test(input);
        int y = yLiq >= 0.0f ? 1 : -1;
        int count = 0;

        while (y != target) {
            count++;
            for (int i = 0; i < input.length; i++) {
                weigths[i] += learningRate * input[i] * target;
            }

            bias += learningRate * target;

            yLiq = test(input);
            y = yLiq >= 0.0f ? 1 : -1;
        }

        System.out.println("Iterações: " + count);
        // Printing the weigths and bias
        System.out.println("Pesos: ");
        for (int i = 0; i < weigths.length; i++) {
            System.out.print(weigths[i] + " ");
        }
        System.out.println();
        System.out.println("Bias: " + bias);
    }

    public void train(int[][] input, int[] target) {
        if (input.length != target.length || input[0].length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        for (int i = 0; i < input.length; i++) {
            train(input[i], target[i]);
        }
    }

    public float test(int[] array) {
        float result = 0.0f;

        for (int j = 0; j < weigths.length; j++) {
            result += array[j] * weigths[j];
        }
        result += bias;
        return result;
    }

    public void reset() {
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = RandomUtil.randomFloat(-0.5f, +0.5f);
        }
        bias = 0.0f;
    }

}
