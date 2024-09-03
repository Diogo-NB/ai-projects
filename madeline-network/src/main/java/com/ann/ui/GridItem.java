package com.ann.ui;

import com.ann.util.Vector;

public class GridItem {

    private int id;

    private String label;

    private int[][] gridData;

    public int getGridSize() {
        return gridData.length;
    }

    public Vector getGridData() {
        Vector[] gridDataVectors = new Vector[gridData.length];
        for (int i = 0; i < gridData.length; i++) {
            gridDataVectors[i] = new Vector(gridData[i]);
        }
        return Vector.flatten(gridDataVectors);
    }

    public String getLabel() {
        return label;
    }

    public GridItem(int id, String label, int[][] gridData) {
        this.id = id;
        this.label = label;
        this.gridData = gridData;
    }

    @Override
    public String toString() {
        return id + ": \'" + label + "\'";
    }

}
