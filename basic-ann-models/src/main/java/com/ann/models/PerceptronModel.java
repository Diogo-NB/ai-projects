package com.ann.models;

public class PerceptronModel implements ANNModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias = 0.0f;

    private float learningRate = 0.1f;

    /**
     * @param size size of the model
     */
    public PerceptronModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = 0.0f;
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

    public void train(int[] input, int expectedOutput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    public void train(int[][] inputs, int[] expectedOutputs) {
        if (inputs.length != expectedOutputs.length || inputs[0].length != weigths.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        for (int i = 0; i < inputs.length; i++) {
            train(inputs[i], expectedOutputs[i]);
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
