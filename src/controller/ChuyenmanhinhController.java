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
import view.*;

/**
 *
 * @author HP
 */
public class ChuyenmanhinhController {
    private JPanel rootPanel;
    private String selectedPanel = "";
    
    private SinhvienPanel svP;
    private HomePanel homeP;
    private MonhocPanel monhocP;
    
    private List<Danhmuc> listItem = null;
    public ChuyenmanhinhController() {
    }
    
    public ChuyenmanhinhController(JPanel rootPanel) {
        this.rootPanel = rootPanel;
        homeP = new HomePanel();
        svP = new SinhvienPanel();
        monhocP = new MonhocPanel();
        
    }
    
    public void setView(JPanel itemPanel, JLabel itemLabel) {
        selectedPanel = "Trangchu";
        itemPanel.setBackground(new Color(96, 100, 91));
        itemLabel.setBackground(new Color(96, 100, 91));
        
        rootPanel.removeAll();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.add(new HomePanel());
        rootPanel.validate();
        rootPanel.repaint();
    }
    
    public void setEvent(List <Danhmuc> listItem) {
        this.listItem = listItem;
        for (Danhmuc item : listItem) {
            item.getLabel().addMouseListener(new LabelEvent(item.getKind(), item.getPanel(), item.getLabel()));
        }
    }
    
    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        
        private JPanel itemPanel;
        private JLabel itemLabel;

        public LabelEvent() {
        }

        public LabelEvent(String kind, JPanel itemPanel, JLabel itemLabel) {
            this.kind = kind;
            this.itemPanel = itemPanel;
            this.itemLabel = itemLabel;
        }
        
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "Trangchu":
                    node = homeP;
                    break;
                case "Sinhvien":
                    node = svP;
                    break;
                case "Monhoc":
                    node = monhocP;
                    break;
                default:
                    break;
            }
            rootPanel.removeAll();
            rootPanel.setLayout(new BorderLayout());
            rootPanel.add(node);
            rootPanel.validate();
            rootPanel.repaint();
            setChangeBackGround(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            selectedPanel = kind;
            itemPanel.setBackground(new Color(96, 100, 191));
            itemLabel.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            itemPanel.setBackground(new Color(96, 100, 191));
            itemLabel.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!selectedPanel.equalsIgnoreCase(kind)) {
                itemPanel.setBackground(new Color(76, 175, 80));
                itemLabel.setBackground(new Color(76, 175, 80));
            }
        }
    }
    
    private void setChangeBackGround(String kind){
        for (Danhmuc item: listItem) {
            if (item.getKind().equalsIgnoreCase(kind)){
                item.getPanel().setBackground(new Color(96, 100, 191));
                item.getLabel().setBackground(new Color(96, 100, 191));
            } else {
                item.getPanel().setBackground(new Color(76, 175, 80));
                item.getLabel().setBackground(new Color(76, 175, 80));
            }
        }
    }
}
