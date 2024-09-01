package com.ann.madeline;

import com.ann.util.Vector;

public class Target {
    private String label;
    private Vector v; // Forma de vetor do target, OBV ou One of Classes

    public Target(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Target) {
            Target t = (Target) o;
            return label.equals(t.label) && v.equals(t.v);
        }
        return false;
    }

}