package com.ann.util;

// Vector class implementation for basic linear algebra operations
// adapted from  https://medium.com/swlh/programming-linear-algebra-in-java-vector-operations-6ba08fdd5a1a
public class Vector {

    private float[] v;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[]");
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
            if (i < v.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Vector(float[] v) {
        this.v = v;
    }

    public Vector(int[] v) {
        this.v = new float[v.length];
        for (int i = 0; i < v.length; i++) {
            this.v[i] = v[i];
        }
    }

    public Vector(Vector v) {
        this.v = new float[v.v.length];
        for (int i = 0; i < v.v.length; i++) {
            this.v[i] = v.v[i];
        }
    }

    public static Vector zeros(int n) {
        return new Vector(new float[n]);
    }

    public static Vector random(int n) {
        float[] v = new float[n];
        for (int i = 0; i < n; i++) {
            v[i] = (float) Math.random();
        }
        return new Vector(v);
    }

    public static Vector random(int n, float min, float max) {
        float[] v = new float[n];
        for (int i = 0; i < n; i++) {
            v[i] = (float) (Math.random() * (max - min) + min);
        }
        return new Vector(v);
    }

    public static Vector flatten(Vector[] vectors) {
        checkLengths(vectors);
        int n = vectors.length * vectors[0].v.length;
        Vector result = new Vector(new float[n]);
        int k = 0;
        for (int i = 0; i < vectors.length; i++) {
            for (int j = 0; j < vectors[i].v.length; j++) {
                result.v[k++] = vectors[i].v[j];
            }
        }
        return result;
    }

    public float get(int i) {
        return v[i];
    }

    public void set(int i, float f) {
        v[i] = f;
    }

    public int size() {
        return v.length;
    }

    public void setV(float[] v) {
        this.v = v;
    }

    public float[] getV() {
        return v.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            return equals((Vector) obj);
        }
        return false;
    }

    public boolean equals(Vector v) {
        if (v.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < v.size(); i++) {
            if (v.get(i) != this.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void add(Vector v) {
        add(this, v, this);
    }

    public static Vector add(Vector a, Vector b) {
        Vector result = new Vector(a);
        add(a, b, result);
        return result;
    }

    public static void add(Vector a, Vector b, Vector result) {
        checkLengths(a, b, result);
        for (int i = 0; i < a.v.length; i++) {
            result.v[i] = a.v[i] + b.v[i];
        }
    }

    public void multiply(Vector v) {
        multiply(this, v, this);
    }

    public static Vector multiply(Vector a, Vector b) {
        Vector result = new Vector(a);
        multiply(a, b, result);
        return result;
    }

    public static void multiply(Vector a, Vector b, Vector result) {
        checkLengths(a, b, result);
        for (int i = 0; i < a.v.length; i++) {
            result.v[i] = a.v[i] * b.v[i];
        }
    }

    public void multiply(float scalar) {
        multiply(this, scalar, this);
    }

    public static Vector multiply(Vector a, float scalar) {
        Vector result = new Vector(a);
        multiply(a, scalar, result);
        return result;
    }

    public static void multiply(Vector a, float scalar, Vector result) {
        checkLengths(a, result);
        for (int i = 0; i < a.v.length; i++) {
            result.v[i] = a.v[i] * scalar;
        }
    }

    public float sum() {
        return sum(this);
    }

    public static float sum(Vector a) {
        float sum = 0.0f;

        for (int i = 0; i < a.v.length; i++) {
            sum += a.v[i];
        }

        return sum;
    }

    public static void checkLengths(Vector... vectors) {
        if (vectors.length < 2) {
            throw new IllegalArgumentException("At least two vectors are required.");
        }

        int firstSize = vectors[0].size();

        for (int i = 1; i < vectors.length; i++) {
            if (vectors[i].size() != firstSize) {
                throw new IllegalArgumentException("Vectors are of different lengths");
            }
        }
    }

}