package com.ann.madeline;

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
                classesArrays[i][j] = -1;
            }
            classesArrays[i][i] = 1;
        }
    }

    public T getClass(int index) {
        return classes[index];
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
