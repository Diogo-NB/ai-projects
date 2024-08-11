package com.hebbmodel;

public class Util {

    // Linearliza uma matriz
    public static int[] flattenMatrix(int[][] matrix) {
        int flatArrayLength = matrix.length * matrix[0].length; // Tamanho do array linearizado
        int[] flatArray = new int[flatArrayLength]; // Array linearizado
        int i = 0; // Index para o array linearizado

        // Traverse all values in the grid
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                flatArray[i++] = matrix[j][k];
            }
        }

        return flatArray;
    }

    // Linearliza uma matriz
    public static float[] flattenMatrix(float[][] matrix) {
        int flatArrayLength = matrix.length * matrix[0].length; // Tamanho do array linearizado
        float[] flatArray = new float[flatArrayLength]; // Array linearizado
        int i = 0; // Index para o array linearizado

        // Traverse all values in the grid
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                flatArray[i++] = matrix[j][k];
            }
        }

        return flatArray;
    }

    // Operação de multiplicação entre dois vetores
    public static int[] dot(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        int length = arr1.length;

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    // Operação de multiplicação entre dois vetores
    public static float[] dot(float[] arr1, float[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        int length = arr1.length;

        float[] result = new float[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    // Operação de adição entre dois vetores
    public static int[] add(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        int length = arr1.length;

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    // Operação de adição entre dois vetores
    public static float[] add(float[] arr1, float[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Tamanho inválido!");
        }

        int length = arr1.length;

        float[] result = new float[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }
}