package com.floresbarco.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageView extends JPanel implements ActionListener {
    private JPanel mainPanel;
    private JLabel label;
    private JButton zoomInBtn;
    private JButton zoomOnBtn;
    private JLabel titleLabel;

    // CONSTRUCTOR
    public ImageView(String title) {
        super(new BorderLayout(), true);
        initComponents(title);
    }

    // INICIALIZAR COMPONENTES
    public void initComponents(String title) {
        titleLabel.setText(title);

        zoomInBtn.addActionListener(this);
        zoomOnBtn.addActionListener(this);

        label.setIcon(new ImageIcon("Report.png"));
        this.add(mainPanel, BorderLayout.CENTER);
    }

    private void zoomIn() {
        Image img = new ImageIcon("Report.png").getImage();
        Image newImage = img.getScaledInstance(label.getWidth()+10, label.getHeight()+10, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(newImage));
    }

    private void zoomOn() {
        Image img = new ImageIcon("Report.png").getImage();
        Image newImage = img.getScaledInstance(label.getWidth()-10, label.getHeight()-10, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(newImage));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == zoomInBtn) {
                zoomIn();
            } else if(e.getSource() == zoomOnBtn) {
                zoomOn();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
