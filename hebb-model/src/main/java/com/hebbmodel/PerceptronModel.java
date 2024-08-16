package com.hebbmodel;

public class PerceptronModel implements ANNModel {

    // Pesos
    private float[] weigths;
    // Bias
    private float bias = 0.0f;

    private float learningRate = 0.1f;

    public PerceptronModel(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        weigths = new float[size];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = 0.0f;
        }
    }

    public PerceptronModel(int size, float learningRate) {
        this(size);
        this.learningRate = learningRate;
    }

    @Override
    public void train(int[] input, int expectedOutput) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    @Override
    public void train(int[][] inputs, int[] expectedOutputs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'train'");
    }

    @Override
    public float test(int[] array) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'test'");
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

    @Override
    public String toString() {
        return "Single Layer Perceptron Model";
    }
}
