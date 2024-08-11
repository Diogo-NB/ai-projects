package com.hebbmodel;

public class HebbModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float b = 0.0f;

    public HebbModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        // Inicializando os pesos
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
        sb.append("\nBias: ").append(b);
        return sb.toString();
    }

    public float getB() {
        return b;
    }

    public float[] getWeigths() {
        return weigths.clone();
    }

    // Treina o modelo com apenas um vetor de input
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

        b += expectedOutput;

    }

    // Treina o modelo com múltiplos inputs
    public void train(int[][] inputs, int[] expectedOutputs) throws IllegalArgumentException {
        if (inputs.length != expectedOutputs.length || inputs[0].length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        for (int i = 0; i < inputs.length; i++) {
            train(inputs[i], expectedOutputs[i]);
        }
    }

    public void test(int[] array) {
        float sum = 0.0f;

        for (int j = 0; j < weigths.length; j++) {
            sum += array[j] * weigths[j];
        }
        sum += b;
        System.out.println("sum = " + sum);
        if (sum >= 0) {
            System.out.println("Output: 1");
        } else {
            System.out.println("Output: -1");
        }
    }

}