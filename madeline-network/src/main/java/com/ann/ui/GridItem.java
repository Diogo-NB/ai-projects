package com.ann.ui;

import com.ann.util.Vector;

public class GridItem {

    private int id;

    private String label;

    private Vector gridData;

    public int getGridSize() {
        return gridData.size();
    }

    public Vector getGridData() {
        return gridData;
    }

    public String getLabel() {
        return label;
    }

    public GridItem(int id, String label, Vector gridData) {
        this.id = id;
        this.label = label;
        this.gridData = gridData;
    }

    @Override
    public String toString() {
        return id + ": \'" + label + "\'";
    }

}
