package com.hebbmodel.util;

public class CollectionsUtil {

    // Get a unidimensional array from a bidimensional array
    public static int[] flatten(int[][] matrix) {
        int flatArrayLength = matrix.length * matrix[0].length;
        int[] flatArray = new int[flatArrayLength];
        int i = 0;

        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                flatArray[i++] = matrix[j][k];
            }
        }

        return flatArray;
    }

    // Get a unidimensional array from a bidimensional array
    public static float[] flatten(float[][] matrix) {
        int flatArrayLength = matrix.length * matrix[0].length;
        float[] flatArray = new float[flatArrayLength];
        int i = 0;

        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                flatArray[i++] = matrix[j][k];
            }
        }

        return flatArray;
    }

    public static int[] dot(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Invalid sizes!");
        }

        int length = arr1.length;

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    public static float[] dot(float[] arr1, float[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Invalid sizes!");
        }

        int length = arr1.length;

        float[] result = new float[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    public static int[] add(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Invalid sizes!");
        }

        int length = arr1.length;

        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }

    public static float[] add(float[] arr1, float[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Invalid sizes!");
        }

        int length = arr1.length;

        float[] result = new float[length];

        for (int i = 0; i < length; i++) {
            result[i] = arr1[i] * arr2[i];
        }

        return result;
    }
}