package com.hebbmodel;

import java.awt.*;
import javax.swing.*;

public class TestingFrame extends JFrame {

    public TestingFrame(int[][] grid1Data, int[][] grid2Data) {
        setTitle("Test model");
        setSize(800, 600);
        setResizable(false);
        setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel gridsPanel = new JPanel();
        gridsPanel.setLayout(new GridLayout(1, 2, 10, 0));

        PixelGrid testGrid = new PixelGrid();
        gridsPanel.add(testGrid);

        mainPanel.add(gridsPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Add padding above the buttons

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        resultPanel.setPreferredSize(new Dimension(220, 300));
        resultPanel.setMinimumSize(new Dimension(220, 150));
        resultPanel.setMaximumSize(new Dimension(220, 1920));

        JButton testButton = new JButton("Test");
        testButton.addActionListener(e -> {
            // Cria um objeto que representa o modelo Hebbiano
            HebbModel model = new HebbModel(100);

            /* ------------------------- Treinamento --------------------------- */

            // Cria vetores de entrada para o treinamento
            int[][] inputs = new int[2][100];

            // Converte as representações em matrizes para vetores unidimensionais
            inputs[0] = Util.flattenMatrix(grid1Data);
            inputs[1] = Util.flattenMatrix(grid2Data);

            // Define os valores esperados de saída para cada entrada
            int[] expectedOutputs = new int[] { 1, -1 };

            // Treina o modelo com os vetores de entrada e as saídas esperadas
            model.train(inputs, expectedOutputs);

            /* ---------------------------- Teste ------------------------------ */

            int[][] testGridData = testGrid.getGridData();
            int[] testInput = Util.flattenMatrix(testGridData);

            float result = model.test(testInput);
            if (result > 0) {
                // System.err.println("Equivale a primeira imagem");
                resultPanel.add(new JLabel("Equivale a primeira imagem (" + result + ")"));
            } else {
                // System.err.println("Equivale a segunda imagem");
                resultPanel.add(new JLabel("Equivale a segunda imagem (" + result + ")"));
            }

            resultPanel.revalidate();
            resultPanel.repaint();
        });

        buttonPanel.add(testButton);
        resultPanel.add(new JLabel("Resultado"));

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(resultPanel, BorderLayout.EAST);

        add(mainPanel);
    }

}
