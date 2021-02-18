package Ui;

import IO.JSonFileFactory;
import model.SanPham;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class QuanLySPUI {
    private JPanel pnMain;
    private JTable tblSanPham;
    private JTextField txtMa;
    private JTextField txtTen;
    private JTextField txtDonGia;
    private JButton btnThem;
    private JButton btnTiep;
    private JButton btnXoa;
    private JButton btnThoat;
    private DefaultTableModel modelSanPham;

    private JMenuBar mnuBar;
    private JMenu mnuHeThong;
    private JMenuItem mnuHeThongLuuFile;
    private JMenuItem mnuDocFile;
    private JMenuItem mnuThoat;
    private JMenuItem mnuHeThongDocFile;
    private JMenuItem mnuHeThongThoat;

    private List<SanPham> dsSp = new ArrayList<SanPham>();

    public QuanLySPUI() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThem();
            }
        });
        btnTiep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtMa.setText("");
                txtTen.setText("");
                txtDonGia.setText("");
                txtMa.requestFocus();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyXoa();
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyThoat();
            }
        });
        tblSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if(tblSanPham.getSelectedRow()>=0){
                    SanPham sp = dsSp.get(tblSanPham.getSelectedRow());
                    txtMa.setText(sp.getMa());
                    txtTen.setText(sp.getTen());
                    txtDonGia.setText(String.valueOf(sp.getGia()));
                }
            }
        });
        mnuThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnThoat.doClick();
            }
        });
        mnuHeThongLuuFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyLuuFile();
            }
        });
        mnuDocFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xuLyDocFile();
            }
        });
    }

    private void xuLyDocFile() {
        JFileChooser jfc = new JFileChooser();
        if(jfc.showOpenDialog(null) == jfc.APPROVE_OPTION ){
            String path = jfc.getSelectedFile().getAbsolutePath();
            JSonFileFactory jff = new JSonFileFactory();
            dsSp = jff.docDuLieu(path);
            hienThiDanhSachSanPham();
        }
    }

    private void xuLyLuuFile() {
        JFileChooser jfc = new JFileChooser();
        if(jfc.showSaveDialog(null) == jfc.APPROVE_OPTION ){
            String path = jfc.getSelectedFile().getAbsolutePath();
            JSonFileFactory jff = new JSonFileFactory();
            boolean kq = jff.luuDuLieu(dsSp,path);
            if(kq==true){
                JOptionPane.showMessageDialog(null,"Luu File thanh cong");
            }
            else JOptionPane.showMessageDialog(null,"Luu File tha bai");
        }
    }

    private void xuLyThoat() {
        int ret=JOptionPane.showConfirmDialog(null,"Thoat Phan Mem", "Hoi Thoat",JOptionPane.YES_NO_OPTION);
        if(ret==JOptionPane.YES_OPTION){
            System.exit(0);
        }

    }

    private void xuLyXoa() {
        if(tblSanPham.getSelectedRow()>=0){
            dsSp.remove(tblSanPham.getSelectedRow());
            hienThiDanhSachSanPham();
        }
    }

    private void xuLyThem() {
        SanPham sp = new SanPham();
        sp.setMa(txtMa.getText());
        sp.setTen(txtTen.getText());
        sp.setGia(Double.parseDouble(txtDonGia.getText()));
        dsSp.add(sp);
        hienThiDanhSachSanPham();
    }

    private void hienThiDanhSachSanPham(){
        modelSanPham.setRowCount(0);
        for(SanPham sp: dsSp){
            Vector<Object> vec= new Vector<>();
            vec.add(sp.getMa());
            vec.add(sp.getTen());
            vec.add(sp.getGia());
            modelSanPham.addRow(vec);
        }
    }

    public JPanel getPnMain() {
        return pnMain;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        modelSanPham = new DefaultTableModel();
        modelSanPham.addColumn("Mã Sản Phẩm");
        modelSanPham.addColumn("Tên Sản Phẩm");
        modelSanPham.addColumn("Đơn Giá");
        tblSanPham = new JTable(modelSanPham);
    }

}
