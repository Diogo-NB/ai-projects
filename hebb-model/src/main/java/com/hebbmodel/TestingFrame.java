package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class TestingFrame extends JFrame {

    public TestingFrame(int[][] grid1Data, int[][] grid2Data) {
        setTitle("Test model");
        setSize(600, 600);
        setResizable(false);
        setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));

        PixelGrid testInputGrid = new PixelGrid();
        gridsPanel.add(testInputGrid);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JButton trainButton = new JButton("Test");
        trainButton.addActionListener(e -> {

            // Cria um objeto que representa o modelo Hebbiano
            HebbModel model = new HebbModel(100);

            // Cria vetores de entrada
            int[][] inputs = new int[2][100];

            // Converte as representações em matrizes para vetores unidimensionais
            inputs[0] = Util.flattenMatrix(grid1Data);
            inputs[1] = Util.flattenMatrix(grid2Data);

            // Define os valores esperados de saída para cada entrada
            int[] expectedOutputs = new int[2];
            expectedOutputs[0] = 1;
            expectedOutputs[1] = -1;

            // Treina o modelo com os vetores de entrada e as saídas esperadas
            model.train(inputs, expectedOutputs);

            System.out.println(model);

            // Testa o modelo com os vetores de entrada
            model.test(inputs[0]); // Testa com o primeiro vetor de entrada
            model.test(inputs[1]); // Testa com o segundo vetor de entrada
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding above the buttons
        buttonPanel.add(trainButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

}
