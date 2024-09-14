package com.ann.madeline;

public class ObvClasses<T> extends AnnClasses<T> {

    public ObvClasses(T[] classes) {
        super.classes = classes;
        classesSize = classes.length;

        if (classesSize < 2) {
            throw new IllegalArgumentException("Classes array must have at least two elements");
        }

        classesArrays = new int[][] { { 1, 1 }, { 1, -1 } };

        while (classesArrays.length != classesSize) {
            expandClassesArrays();
        }
    }

    private void expandClassesArrays() {
        int n = classesArrays.length;
        int m = Math.min(classesSize, n * 2);

        int[][] expandedClassesArray = new int[m][n * 2];

        for (int i = 0; i < m; i += 2) {

            for (int j = 0; j < n; j++) {
                expandedClassesArray[i][j] = classesArrays[i / 2][j];
                expandedClassesArray[i][j + n] = classesArrays[i / 2][j];
            }
        }

        for (int i = 1; i < m; i += 2) {
            for (int j = 0; j < n; j++) {
                expandedClassesArray[i][j] = classesArrays[i / 2][j];
                expandedClassesArray[i][j + n] = -classesArrays[i / 2][j];
            }
        }

        classesArrays = expandedClassesArray;
    }

}
