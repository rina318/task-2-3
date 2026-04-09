package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Form extends JFrame{
    private JTable tableInput;
    private JTable tableReverseInput;
    private JButton buttonInputFromFile;
    private JButton buttonSimpleDequeReverse;
    private JPanel panel;
    private JButton buttonDequeReverse;

    public Form() {
        super("Таск 3   ");
        setVisible(true);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1000, 600);
        buttonInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputFromFile(tableInput);

            }
        });
        buttonSimpleDequeReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deque<Integer> intDeque = tableToSimpleDeque(tableInput);
                TaskLogic.solve(intDeque);
                dequeToTable(intDeque);
            }
        });

        buttonDequeReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deque<Integer> intDeque = tableToDeque(tableInput);
                TaskLogic.solve(intDeque);
                dequeToTable(intDeque);
            }
        });
    }

    public Deque<Integer> tableToDeque(JTable table) {
        int size = table.getColumnCount();
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = size; i > 0; i--) {
            deque.push((int) table.getValueAt(0,i-1));
        }
        return deque;
    }
    public void dequeToTable(Deque<Integer> queue) {
        Object[][] arr = new Object[1][queue.size()];
        int n = 0;
        String[] columnNames = new String[queue.size()];

        while (!queue.isEmpty()) {
            int q = queue.pop();
            arr[0][n] = q;
            n++;
        }

        for (int x = 0; x < arr[0].length; x++) {
            if (x == 0) {
                columnNames[x] = "Начало";
            } else if (x == arr[0].length - 1){
                columnNames[x] = "Конец";
            } else {
                columnNames[x] = "[" + x + "]";
            }
        }
        tableReverseInput.setModel(new DefaultTableModel(arr, columnNames));
    }

    public Deque<Integer> tableToSimpleDeque(JTable table) {
        int size = table.getColumnCount();
        Deque<Integer> deque = new SimpleDeque<>();
        for (int i = size; i > 0; i--) {
            deque.push((int) table.getValueAt(0,i-1));
        }
        return deque;
    }

    public void inputFromFile(JTable table) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/org/example/input.txt"))) {
            String line = reader.readLine();
            String[] values = line.split("\s+");
            Object[][] arr = new Object[1][values.length];
            String[] columnNames = new String[values.length];

            for (int i = 0; i < arr[0].length; i++) {
                if (i == 0) {
                    columnNames[i] = "Начало";
                } else if (i == arr[0].length - 1){
                    columnNames[i] = "Конец";
                } else {
                    columnNames[i] = "[" + i + "]";
                }
                arr[0][i] = Integer.parseInt(values[i]);
            }
            table.setModel(new DefaultTableModel(arr, columnNames));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
