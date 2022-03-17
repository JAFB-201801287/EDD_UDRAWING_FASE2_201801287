package com.floresbarco.view;

import com.floresbarco.controller.DocumentController;
import com.google.gson.Gson;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class BulkLoad extends JPanel implements ActionListener {
    private JPanel mainPanel;
    private JTextPane editor;
    private JButton loadBtn;
    private JTextField textField1;
    private JScrollPane editorScroll;
    private JScrollPane consoleScroll;
    private JTextPane console;
    private JButton findBtn;

    public BulkLoad() {
        super(new BorderLayout(), true);
        initComponents();
    }

    public void initComponents() {
        findBtn.addActionListener(this);
        loadBtn.addActionListener(this);

        NumberLine editorLineNumber = new NumberLine(editor);
        editorScroll.setRowHeaderView(editorLineNumber);

        NumberLine consoleLineNumber = new NumberLine(console);
        consoleScroll.setRowHeaderView(consoleLineNumber);

        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void findFile() throws IOException {
        String path = DocumentController.getInstance().documentFileChooser(
                "BUSCAR ARCHIVO DE CLIENTES",
                new FileNameExtensionFilter("DOCUMENTOS TIPO JSON", "json")
        );

        if(path != null) {
            String customers = DocumentController.getInstance().documentReding(path);
            editor.setText(customers);
            editor.revalidate();
            editor.repaint();

            Map[] map = new Gson().fromJson(customers, Map[].class);
            System.out.println(map[0]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == findBtn) {
                findFile();
            } else if (e.getSource() == loadBtn) {

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
