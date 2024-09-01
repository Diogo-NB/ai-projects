package com.ann.ui;

public class GridItem {

    private int id;

    private String label;

    private int[][] gridData;

    public int getGridSize() {
        return gridData.length;
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
