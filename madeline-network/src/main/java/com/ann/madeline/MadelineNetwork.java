package com.ann.madeline;

import com.ann.util.Vector;

public class MadelineNetwork {

    private int n; // Tamanho de input
    private Vector[] weigths; // Matriz de pesos
    private Vector biases; // Vetor de bias

    private String[] labels; // Vetor de label
    private Vector[] outputs; // Vetor One of Classes ou OBV

    private float maxError;
    private float learningRate;

    public MadelineNetwork(int inputSize, String[] labels, float maxError, float learningRate) {
        this.learningRate = learningRate;
        this.maxError = maxError;
        this.labels = labels;
        this.n = inputSize;

        weigths = new Vector[labels.length];
        for (int i = 0; i < weigths.length; i++) {
            weigths[i] = Vector.random(inputSize, -0.5f, +0.5f);
        }

        // One of classes
        outputs = new Vector[labels.length];
        for (int i = 0; i < labels.length; i++) {
            outputs[i] = Vector.zeros(inputSize);
            outputs[i].set(i, 1.0f);
        }

        biases = Vector.random(inputSize, -0.5f, +0.5f);
    }

    public void train(Vector[] inputs, Vector[] targets) {
        boolean trained = false;
        int count = 0;

        while (!trained) {
            trained = true;

            // Para cada input, atualiza os pesos para cada saída (target)
            for (int i = 0; i < inputs.length; i++) {
                Vector input = inputs[i];

                // Testar o input com todas saídas
                Vector yLiq = Vector.zeros(targets.length);

                // Calcular yLiq
                for (int j = 0; j < weigths.length; j++) {
                    Vector w = weigths[j];
                    Vector inputXw = Vector.multiply(input, w);
                    yLiq.set(j, inputXw.sum());
                }

                yLiq.add(biases);

                Vector y = activationFunction(yLiq);

            }

        }

        System.out.println("Training tries: " + count);

    }

    public Vector activationFunction(Vector yLiq) {
        Vector v = Vector.zeros(n);
        for (int i = 0; i < yLiq.size(); i++) {
            v.set(i, activationFunction(yLiq.get(i)));
        }
        return v;
    }

    public int activationFunction(float yLiq) {
        return yLiq >= 0.0f ? 1 : 0;
    }

    @Override
    public String toString() {
        return "MadelineNetwork{" +
                "n=" + n +
                ", maxError=" + maxError +
                ", learningRate=" + learningRate +
                '}';
    }

}
