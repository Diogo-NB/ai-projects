package com.hebbmodel;

public class HebbModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias = 0.0f;

    public HebbModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        // Initialize weigths arrays
        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = 0.0f;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Weights: ");
        for (float weight : weigths) {
            sb.append(weight).append(" ");
        }
        sb.append("\nBias: ").append(bias);
        return sb.toString();
    }

    public float getBias() {
        return bias;
    }

    public float[] getWeigths() {
        return weigths.clone();
    }

    // Train the model with a single input array
    public void train(int[] input, int expectedOutput) {
        if (input.length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        // Variavéis auxiliares
        float deltaW = 0.0f;

        // Calcular os pesos
        for (int j = 0; j < weigths.length; j++) {
            deltaW = input[j] * expectedOutput;

            weigths[j] += deltaW;
        }

        bias += expectedOutput;

    }

    // Trains the model with multiple inputs arrays
    public void train(int[][] inputs, int[] expectedOutputs) throws IllegalArgumentException {
        if (inputs.length != expectedOutputs.length || inputs[0].length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        for (int i = 0; i < inputs.length; i++) {
            train(inputs[i], expectedOutputs[i]);
        }
    }

    // Test the model
    public float test(int[] array) {
        float result = 0.0f;

        for (int j = 0; j < weigths.length; j++) {
            result += array[j] * weigths[j];
        }
        result += bias;
        return result;
    }

}