/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.swing.*;

/**
 *
 * @author HP
 */
public class Danhmuc {
    private String kind;
    private JPanel panel;
    private JLabel label;

    public Danhmuc() {
    }

    public Danhmuc(String kind, JPanel panel, JLabel label) {
        this.kind = kind;
        this.panel = panel;
        this.label = label;
    }

    public String getKind() {
        return kind;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    
}
