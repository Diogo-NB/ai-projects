package com.ann;

import com.ann.madeline.AnnClasses;
import com.ann.madeline.ObvClasses;

public class Main {

    public static void main(String[] args) {

        AnnClasses<String> annClasses1 = new ObvClasses<>(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        AnnClasses<String> annClasses2 = new AnnClasses<>(new String[] { "A", "B", "C", "D", "E", "F", "G" });

        System.out.println(annClasses1);
        System.out.println(annClasses2);
        // TrainingFrame mainFrame = new TrainingFrame(10);
        // mainFrame.setTitle("Madeline Network");
        // mainFrame.setVisible(true);
    }
}
