package com.ann.models;

public class HebbModel implements ANNModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias = 0.0f;

    /**
     * @param size size of the model
     */
    public HebbModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = 0.0f;
        }
    }

    @Override
    public String toString() {
        return "Hebb Model";
    }

    public float getBias() {
        return bias;
    }

    public float[] getWeigths() {
        return weigths.clone();
    }

    // Train the model with a single input array
    public void train(int[] input, int target) {
        if (input.length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        // Variavéis auxiliares
        float deltaW = 0.0f;

        // Calcular os pesos
        for (int j = 0; j < weigths.length; j++) {
            deltaW = input[j] * target;

            weigths[j] += deltaW;
        }

        bias += target;

    }

    // Trains the model with multiple input arrays
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
            weigths[i] = 0.0f;
        }
        bias = 0.0f;
    }

}