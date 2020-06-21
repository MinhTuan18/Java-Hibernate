/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.MonhocDAO;
import dao.SinhvienDAO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author HP
 */
public class ImportMonHocCSVController {
    private JLabel filenameLB;
    private JButton openBtn;
    private JTextField lopTF;
    private JLabel tbLB;
    private JButton addBtn;
    
    private File file = null;

    public ImportMonHocCSVController(JLabel filenameLB, JButton openBtn, JTextField lopTF, JLabel tbLB, JButton addBtn) {
        this.filenameLB = filenameLB;
        this.openBtn = openBtn;
        this.lopTF = lopTF;
        this.tbLB = tbLB;
        this.addBtn = addBtn;
    }
    
    public void setEvent() {
        openBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                try {
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("file csv", "csv");
                    fileChooser.setFileFilter(filter);
                    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        String filename = file.getName();
                        String[] words = filename.split("\\.");
                        filenameLB.setText(filename);
                        if (words.length > 0) {
                            lopTF.setText(words[0]);
                        }
                        tbLB.setText("");
                        
                    }
                } catch(Exception ex){
                    ex.printStackTrace();
                }
            }
            
        });
        
        addBtn.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (file == null) {
                    tbLB.setText("Vui lòng chọn file CSV");
                } else {
                    if ("".equals(lopTF.getText().trim())) {
                    tbLB.setText("Vui lòng nhập lớp");
                    } else {
                        boolean kq = MonhocDAO.themMonHocTuFileCSV(file.getAbsolutePath(), lopTF.getText().trim());
                        if (kq == true) {
                            tbLB.setText("Thêm thành công");          
                        } else {
                            tbLB.setText("Thêm thất bại");
                        }
                    }
                    
                }

            }
            
        });
    }
}

