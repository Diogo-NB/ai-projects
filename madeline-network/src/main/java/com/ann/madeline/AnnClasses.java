package com.ann.madeline;

import com.ann.util.Vector;

public class AnnClasses<T> {

    protected T[] classes;
    protected int[][] classesArrays;
    protected int classesSize;

    protected AnnClasses() {

    }

    public AnnClasses(T[] classes) {
        this.classes = classes;
        this.classesSize = classes.length;

        if (classesSize < 2) {
            throw new IllegalArgumentException("Classes array must have at least two elements");
        }

        this.classesArrays = new int[classesSize][classesSize];

        for (int i = 0; i < classesArrays.length; i++) {
            for (int j = 0; j < classesArrays[i].length; j++) {
                classesArrays[i][j] = 0;
            }
            classesArrays[i][i] = 1;
        }
    }

    public T getClass(Vector y) {
        System.out.println(y);
        float minDistanceFound = Float.MAX_VALUE; // Menor distância encontrada
        T clazz = null;
        Vector[] outputs = getClassesArrays();

        // Para cada output, calcula a distância entre output e y
        for (int i = 0; i < outputs.length; i++) {
            Vector t = outputs[i];

            Vector d = Vector.subtract(t, y);
            d.multiply(d);
            float distance = (float) Math.sqrt(d.sum());
            if (distance < minDistanceFound) {
                minDistanceFound = distance;
                clazz = classes[i];
            }
        }
        return clazz;
    }

    public Vector[] getClassesArrays() {
        Vector[] vectors = new Vector[classesSize];
        for (int i = 0; i < classesSize; i++) {
            vectors[i] = new Vector(classesArrays[i]);
        }
        return vectors;
    }

    public Vector getClassArray(T clazz) {
        int index = -1;
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].equals(clazz)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalArgumentException("Class not found");
        }
        return new Vector(classesArrays[index]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < classesArrays.length; i++) {
            sb.append("[ ");
            for (int j = 0; j < classesArrays[i].length; j++) {
                sb.append(classesArrays[i][j] < 0 ? "" : " ");
                sb.append(classesArrays[i][j]);
                sb.append(" ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

}
