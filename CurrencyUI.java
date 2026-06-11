package com.globalfinance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class CurrencyUI {

    static final String API_KEY = "70a848aca51defea8547095f";

    public static void main(String[] args) {

        JFrame frame = new JFrame("Currency Converter");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // 🔹 Panel for inputs
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // rows, cols, gaps

        JLabel fromLabel = new JLabel("From (USD):");
        JTextField fromField = new JTextField();

        JLabel toLabel = new JLabel("To (INR):");
        JTextField toField = new JTextField();

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        panel.add(fromLabel);
        panel.add(fromField);
        panel.add(toLabel);
        panel.add(toField);
        panel.add(amountLabel);
        panel.add(amountField);

        // 🔹 Button
        JButton convertBtn = new JButton("Convert");

        // 🔹 Result
        JLabel result = new JLabel("Result: ");
        result.setFont(new Font("Arial", Font.BOLD, 16));

        // 🔹 Add to frame
        frame.add(panel, BorderLayout.CENTER);
        frame.add(convertBtn, BorderLayout.SOUTH);
        frame.add(result, BorderLayout.NORTH);

        // 🎨 Styling
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        convertBtn.setBackground(Color.BLUE);
        convertBtn.setForeground(Color.WHITE);

        // 🔥 Action
        convertBtn.addActionListener(e -> {
            try {
                String from = fromField.getText().toUpperCase();
                String to = toField.getText().toUpperCase();
                double amt = Double.parseDouble(amountField.getText());

                String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                StringBuilder res = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    res.append(line);
                }
                reader.close();

                String json = res.toString();
                String search = "\"" + to + "\":";
                int index = json.indexOf(search);

                int start = index + search.length();
                int end = json.indexOf(",", start);

                double rate = Double.parseDouble(json.substring(start, end));
                double finalAmount = amt * rate;

                result.setText("Converted: " + finalAmount);

            } catch (Exception ex) {
                result.setText("Error!");
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}