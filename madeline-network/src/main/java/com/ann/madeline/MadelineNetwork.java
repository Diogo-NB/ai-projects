package com.ann.madeline;

import com.ann.util.Vector;

public class MadelineNetwork {

    private int inputSize; // Tamanho de input
    private int outputSize; // Tamanho da saída / quantidade de saídas

    private Vector[] weigths; // Matriz de pesos
    private Vector biases; // Vetor de bias

    private float maxError;
    private float alpha;

    public MadelineNetwork(int inputSize, int outputSize, float maxError, float learningRate) {
        this.maxError = maxError;
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        alpha = learningRate;

        weigths = new Vector[outputSize];
        for (int i = 0; i < outputSize; i++) {
            weigths[i] = Vector.random(inputSize, -0.5f, +0.5f);
        }

        // Inicializando o vetor de bias
        biases = Vector.random(outputSize, -0.5f, +0.5f);
        System.out.println("biases = " + biases);
    }

    public Vector test(Vector x) {
        Vector y = Vector.zeros(outputSize);

        for (int i = 0; i < outputSize; i++) {
            Vector w = weigths[i];
            float yLiq = Vector.multiply(x, w).sum() + biases.get(i);
            y.set(i, (float) Math.tanh(yLiq));
        }

        return y;
    }

    public void train(Vector[] inputs, Vector[] targets) {
        int epochs = 0;
        float error = 0.0f;

        System.out.println("Inicio do treinamento");

        do {
            epochs++;
            error = 0.0f;

            for (int k = 0; k < inputs.length; k++) {

                Vector x = inputs[k];
                Vector t = targets[k];
                // System.out.println("k = " + k + " ; x = " + x);
                // System.out.println("k = " + k + " ; t = " + t);

                // Calculando o vetor de saída y
                Vector y = test(x);

                // System.out.println("k = " + k + " ; y = " + y);

                Vector d = Vector.subtract(t, y); // d = t - y

                // System.out.println("k = " + k + " ; d = " + d);

                // Calculando o erro
                error += Vector.multiply(d, d).sum() / 2;

                // Atualizando os pesos e bias
                d.multiply(alpha); // d = d * alpha
                for (int i = 0; i < outputSize; i++) {
                    Vector w = weigths[i];
                    w.add(Vector.multiply(x, d.get(i)));
                }

                biases.add(d);
            }

            if (epochs % 100 == 0) {
                System.out.println("epochs = " + epochs + " ; error = " + error);
            }

            // Condição de parada
        } while (error > maxError && epochs < 1000 * 1000);

        System.out.println("epochs = " + epochs + " ; error = " + error);
    }

    @Override
    public String toString() {
        return "MadelineNetwork{" +
                "inputSize = " + inputSize +
                ", outputSize = " + outputSize +
                ", maxError = " + maxError +
                ", learningRate = " + alpha +
                '}';
    }

}
