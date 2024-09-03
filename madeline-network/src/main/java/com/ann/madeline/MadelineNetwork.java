package com.ann.madeline;

import java.util.ArrayList;

import com.ann.util.Vector;

public class MadelineNetwork {

    private int inputSize; // Tamanho de input
    private int outputSize; // Tamanho da saída / quantidade de saídas

    private ArrayList<Object> classes; // Classes
    private Vector[] outputs; // Matriz One of Classes
    private Vector[] weigths; // Matriz de pesos
    private Vector biases; // Vetor de bias

    private float maxError;
    private float alpha;

    public MadelineNetwork(int inputSize, Object[] classes, float maxError, float learningRate) {
        this.maxError = maxError;
        this.inputSize = inputSize;
        outputSize = classes.length;
        alpha = learningRate;

        this.classes = new ArrayList<Object>(outputSize);
        weigths = new Vector[outputSize];
        outputs = new Vector[outputSize];

        for (int i = 0; i < outputSize; i++) {
            this.classes.add(classes[i]);

            // Inicializando o vetor de pesos para essa classe
            weigths[i] = Vector.random(inputSize, -0.5f, +0.5f);
            // Inicializando o vetor da matriz One of Classes para essa classe
            outputs[i] = Vector.zeros(outputSize);
            outputs[i].set(i, 1.0f);
        }

        // Inicializando o vetor de bias
        biases = Vector.random(outputSize, -0.5f, +0.5f);
        System.out.println("biases = " + biases);

        for (int i = 0; i < outputSize; i++) {
            System.out.println("Outputs[" + i + "] = " + outputs[i]);
        }

        for (int i = 0; i < outputSize; i++) {
            System.out.println("w[" + i + "] = " + weigths[i]);
        }
    }

    public void train(Vector[] inputs, Object[] outputs) {
        train(inputs, this.outputs);
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
                Vector y = Vector.zeros(outputSize);

                for (int i = 0; i < outputSize; i++) {
                    Vector w = weigths[i];
                    float yLiq = Vector.multiply(x, w).sum() + biases.get(i);
                    y.set(i, (float) Math.tanh(yLiq));
                }

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
                "outputSize = " + outputSize +
                ", maxError = " + maxError +
                ", learningRate = " + alpha +
                '}';
    }

}
