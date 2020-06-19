 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Danhmuc;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.HomePanel;

/**
 *
 * @author HP
 */
public class Chuyenmanhinh {
    private JPanel rootPanel;
    private String selectedPanel;

    public Chuyenmanhinh() {
    }
    
    public Chuyenmanhinh(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }
    
    public void setView(JPanel itemPanel, JLabel itemLabel) {
        selectedPanel = "TrangChu";
        itemPanel.setBackground(new Color(96, 100, 91));
        itemLabel.setBackground(new Color(96, 100, 91));
        
        rootPanel.removeAll();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(new HomePanel());
        rootPanel.validate();
        rootPanel.repaint();
    }
    
    public void setEvent(List <Danhmuc> listItem) {
        for (Danhmuc item : listItem) {
            
        }
    }
    
    class LabelEvent implements MouseListener {

        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
