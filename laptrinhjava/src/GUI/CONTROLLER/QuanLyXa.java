package GUI.CONTROLLER;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import BLL.BLLQuanLyDanhSach;
import DTO.Xa;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;


public class QuanLyXa extends JPanel {
    private String maXa;
    private JTextField goodsNameTF;
    private JTextField picTF;
    private JTextField loaiXaTF;
    private JTextField materialTF;
    private JTextField lengthTF;
    private JTextField duongKinhTF;
    private JTextField chieuCaoTF;
    private JTextField taiTrongTF;

    public QuanLyXa() {
        setLayout(null);
        setSize(1200, 800);
        setBackground(new Color(241, 255, 250));
        giaoDien();
    }

    public void giaoDien() {
        JButton them = new JButton();
        ImageIcon themBtnImg = new ImageIcon("src/asset/img/button/them-hv.png");
        Image scaleThemBtnImg = themBtnImg.getImage().getScaledInstance(130, 35, Image.SCALE_DEFAULT);
        them.setPreferredSize(new Dimension(130, 35));
        them.setIcon(new ImageIcon(scaleThemBtnImg));
        them.setHorizontalAlignment(SwingConstants.CENTER);
        them.setBorder(null);
        ImageIcon xoaBtnImg = new ImageIcon("src/asset/img/button/xoa-hv.png");
        @SuppressWarnings("unused")
        Image scaleXoaBtnImg = xoaBtnImg.getImage().getScaledInstance(130, 35, Image.SCALE_DEFAULT);

        JButton sua = new JButton();
        sua.setPreferredSize(new Dimension(110, 35));
        ImageIcon suaBtnImg = new ImageIcon("src/asset/img/button/sua-hv.png");
        Image scaleSuaBtnImg = suaBtnImg.getImage().getScaledInstance(130, 35, Image.SCALE_DEFAULT);
        sua.setPreferredSize(new Dimension(130, 35));
        sua.setIcon(new ImageIcon(scaleSuaBtnImg));
        sua.setHorizontalAlignment(SwingConstants.CENTER);
        sua.setBorder(null);

        JPanel tinhNang = new JPanel();
        tinhNang.setLayout(new FlowLayout());
        tinhNang.setBounds(0, 0, 1200, 100);
        add(tinhNang);
        tinhNang.add(them);
        tinhNang.add(sua);
        tinhNang.setBackground(new Color(241, 255, 250));

        JPanel thongTin = new JPanel();
        thongTin.setBackground(new Color(119, 230, 163));
        thongTin.setBounds(0, 100, 1200, 213);
        add(thongTin);
        thongTin.setLayout(null);

        // Initialize JTextFields

        goodsNameTF = new JTextField();
        goodsNameTF.setBounds(145, 30, 149, 36);
        thongTin.add(goodsNameTF);
        goodsNameTF.setColumns(10);

        picTF = new JTextField();
        picTF.setBounds(1020, 30, 149, 36);
        thongTin.add(picTF);
        picTF.setColumns(10);

        loaiXaTF = new JTextField();
        loaiXaTF.setBounds(426, 30, 149, 36);
        thongTin.add(loaiXaTF);
        loaiXaTF.setColumns(10);

        materialTF = new JTextField();
        materialTF.setBounds(145, 98, 149, 36);
        thongTin.add(materialTF);
        materialTF.setColumns(10);

        lengthTF = new JTextField();
        lengthTF.setBounds(426, 98, 149, 36);
        thongTin.add(lengthTF);
        lengthTF.setColumns(10);

        duongKinhTF = new JTextField();
        duongKinhTF.setBounds(738, 29, 149, 39);
        thongTin.add(duongKinhTF);
        duongKinhTF.setColumns(10);

        chieuCaoTF = new JTextField();
        chieuCaoTF.setColumns(10);
        chieuCaoTF.setBounds(738, 98, 149, 36);
        thongTin.add(chieuCaoTF);

        taiTrongTF = new JTextField();
        taiTrongTF.setColumns(10);
        taiTrongTF.setBounds(1020, 98, 149, 36);
        thongTin.add(taiTrongTF);

        // Initialize JLabel

        JLabel goodsNameLB = new JLabel("Tên hàng hóa:");
        goodsNameLB.setLabelFor(goodsNameTF);
        goodsNameLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        goodsNameLB.setBounds(10, 33, 147, 27);
        thongTin.add(goodsNameLB);

        JLabel picLB = new JLabel("Hình ảnh:");
        picLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        picLB.setBounds(910, 33, 100, 27);
        thongTin.add(picLB);

        JLabel loaiXaLB = new JLabel("Loại xà:");
        loaiXaLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        loaiXaLB.setBounds(316, 28, 133, 36);
        thongTin.add(loaiXaLB);

        JLabel materialLB = new JLabel("Chất liệu:");
        materialLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        materialLB.setBounds(20, 96, 110, 36);
        thongTin.add(materialLB);

        JLabel taiTrongLB = new JLabel("Tải trọng:");
        taiTrongLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        taiTrongLB.setBounds(910, 96, 100, 36);
        thongTin.add(taiTrongLB);

        JLabel lengthLB = new JLabel("Chiều dài:");
        lengthLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        lengthLB.setBounds(316, 96, 110, 36);
        thongTin.add(lengthLB);

        JLabel duongKinhLB = new JLabel("Đường kính:");
        duongKinhLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        duongKinhLB.setBounds(607, 33, 110, 27);
        thongTin.add(duongKinhLB);

        JLabel chieuCaoLB = new JLabel("Chiều cao:");
        chieuCaoLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        chieuCaoLB.setBounds(607, 101, 110, 27);
        thongTin.add(chieuCaoLB);
        
        // Initialize JTable and DefaultTableModel
        // Trong phương thức giaoDien
		// Initialize JTable và DefaultTableModel
		JTable bangXa = new JTable();
		bangXa.setFont(new Font("Times New Roman", Font.BOLD, 15));
		bangXa.getTableHeader().setReorderingAllowed(false);
		
		DefaultTableModel modelXa = new DefaultTableModel();
		modelXa.addColumn("Mã hàng hóa");
		modelXa.addColumn("Tên hàng hóa");
		modelXa.addColumn("Loại xà");
		modelXa.addColumn("Chất liệu");
		modelXa.addColumn("Chiều dài");
		modelXa.addColumn("Đường kính");
		modelXa.addColumn("Chiều cao");
		modelXa.addColumn("Tải trọng");
		modelXa.addColumn("Hình ảnh");

		// Gán model cho JTable
		bangXa.setModel(modelXa);
		
		// Lấy danh sách Xa và thêm vào bảng
		BLLQuanLyDanhSach ql = new BLLQuanLyDanhSach();
		ArrayList<Xa> danhSachXa = ql.layDSXa(); // Giả sử bạn có lớp dataThietBi để lấy dữ liệu
		for (Xa xa : danhSachXa) {
		    modelXa.addRow(new Object[]{
		        xa.getMaHangHoa(),
		        xa.getTenLoaiHangHoa(),
		        xa.getLoaiXa(),
		        xa.getChatLieu(),
		        xa.getChieuDai(),
		        xa.getDuongKinh(),
		        xa.getChieuCao(),
		        xa.getTaiTrong(),
		        xa.getHinhAnh()
		    });
}

// Thêm MouseListener cho JTable
        bangXa.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = bangXa.getSelectedRow();
                if (row >= 0) {
                    maXa = modelXa.getValueAt(row, 0).toString().trim();
                    goodsNameTF.setText(modelXa.getValueAt(row, 1).toString().trim());
                    loaiXaTF.setText(modelXa.getValueAt(row, 2).toString().trim());
                    materialTF.setText(modelXa.getValueAt(row, 3).toString().trim());
                    lengthTF.setText(modelXa.getValueAt(row, 4).toString().trim());
                    duongKinhTF.setText(modelXa.getValueAt(row, 5).toString().trim());
                    chieuCaoTF.setText(modelXa.getValueAt(row, 6).toString().trim());
                    taiTrongTF.setText(modelXa.getValueAt(row, 7).toString().trim());
                    picTF.setText(modelXa.getValueAt(row, 8).toString().trim());
                }
            }
        });
        them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(goodsNameTF.getText().equals("")||loaiXaTF.getText().equals("")||materialTF.getText().equals("")||
                lengthTF.getText().equals("")||duongKinhTF.getText().equals("")||chieuCaoTF.getText().equals("")||taiTrongTF.getText().equals("")||
                picTF.getText().equals("")) 
                {
                    JOptionPane.showMessageDialog(null, "Thiếu thông tin");
                    return;
                }
                String regexDecimal = "^-?\\d{1,3}(\\.\\d{1,2})?$";
                if(!lengthTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Chiều dài không hợp lệ");
                    return;
                }
                if(!duongKinhTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Đường kính không hợp lệ");
                    return;
                }
                if(!chieuCaoTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Chiều cao không hợp lệ");
                    return;
                }
                if(!taiTrongTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Tải trọng không hợp lệ");
                    return;
                }
                if(!(picTF.getText().substring(picTF.getText().length() - 4).equals(".png")||picTF.getText().substring(picTF.getText().length() - 4).equals(".jpg")))
                {
                    JOptionPane.showMessageDialog(null, "Sai định dạng ảnh");
                    return;
                }
                if(goodsNameTF.getText().length()>50) {
                    JOptionPane.showMessageDialog(null, "Tên phải < 50 ký tự");
                    return;
                }
                if(loaiXaTF.getText().length()>20) {
                    JOptionPane.showMessageDialog(null, "Tên phải < 20 ký tự");
                    return;
                }
                if(materialTF.getText().length()>20) {
                    JOptionPane.showMessageDialog(null, "Tên phải < 50 ký tự");
                    return;
                }
                String ten = goodsNameTF.getText().trim();
                String hinhAnh = picTF.getText().trim();
                String loai = "Xà";
                String loaiXa = loaiXaTF.getText().trim();
                String chatLieu = materialTF.getText().trim();
                float chieuDai = Float.parseFloat(lengthTF.getText());
                float duongKinh = Float.parseFloat(duongKinhTF.getText());
                float chieuCao = Float.parseFloat(chieuCaoTF.getText());
                float taiTrong = Float.parseFloat(taiTrongTF.getText());
                String kq = ql.themThietBiXa(new Xa(maXa, ten, hinhAnh, loai, loaiXa, chatLieu, chieuDai, duongKinh, chieuCao, taiTrong));
                JOptionPane.showMessageDialog(null,kq);
                modelXa.setRowCount(0);
                ArrayList<Xa> danhSachXa = ql.layDSXa(); // Giả sử bạn có lớp dataThietBi để lấy dữ liệu
                for (Xa xa : danhSachXa) {
                    modelXa.addRow(new Object[]{
                    		xa.getMaHangHoa(),
            		        xa.getTenLoaiHangHoa(),
            		        xa.getLoaiXa(),
            		        xa.getChatLieu(),
            		        xa.getChieuDai(),
            		        xa.getDuongKinh(),
            		        xa.getChieuCao(),
            		        xa.getTaiTrong(),
            		        xa.getHinhAnh()
                    });
                }
            }
        });
        sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(bangXa.getSelectedRow() < 0 ) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn thiết bị ở bảng để sửa");
                    return;
                }
                if(goodsNameTF.getText().equals("")||goodsNameTF.getText().equals("")||goodsNameTF.getText().equals("")||
                goodsNameTF.getText().equals("")||goodsNameTF.getText().equals("")||goodsNameTF.getText().equals("")||goodsNameTF.getText().equals("")||
                goodsNameTF.getText().equals("")) {
                	JOptionPane.showMessageDialog(null, "Thiếu thông tin");
                	return;
                }
                String regexDecimal = "^-?\\d{1,3}(\\.\\d{1,2})?$";
                if(!lengthTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Chiều dài không hợp lệ");
                    return;
                }
                if(!duongKinhTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Đường kính không hợp lệ");
                    return;
                }
                if(!chieuCaoTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Chiều cao không hợp lệ");
                    return;
                }
                if(!taiTrongTF.getText().matches(regexDecimal)) {
                    JOptionPane.showMessageDialog(null, "Tải trọng không hợp lệ");
                    return;
                }
                if(!(picTF.getText().substring(picTF.getText().length() - 4).equals(".png")||picTF.getText().substring(picTF.getText().length() - 4).equals(".jpg")))
                {
                    JOptionPane.showMessageDialog(null, "Sai định dạng ảnh");
                    return;
                }
                if(goodsNameTF.getText().length()>50) {
                    JOptionPane.showMessageDialog(null, "Tên phải <= 50 ký tự");
                    return;
                }
                if(loaiXaTF.getText().length()>20) {
                    JOptionPane.showMessageDialog(null, "Tên loại phải <= 20 ký tự");
                    return;
                }
                if(materialTF.getText().length()>20) {
                    JOptionPane.showMessageDialog(null, "Chất liệu phải <= 20 ký tự");
                    return;
                }
                else 
                {
                    String ten = goodsNameTF.getText();
                    String hinhAnh = picTF.getText();
                    String loai = "Xà";
                    String loaiXa = loaiXaTF.getText();
                    String chatLieu = materialTF.getText();
                    float chieuDai = Float.parseFloat(lengthTF.getText());
                    float duongKinh = Float.parseFloat(duongKinhTF.getText());
                    float chieuCao = Float.parseFloat(chieuCaoTF.getText());
                    float taiTrong = Float.parseFloat(taiTrongTF.getText());
                    String kq = ql.SuaXa(new Xa(maXa, ten, hinhAnh, loai, loaiXa, chatLieu, chieuDai, duongKinh, chieuCao, taiTrong));
                    JOptionPane.showMessageDialog(null, kq);
                    modelXa.setRowCount(0);
                    ArrayList<Xa> danhSachXa = ql.layDSXa();
                    for (Xa xa : danhSachXa) {
                        modelXa.addRow(new Object[]{
                        		xa.getMaHangHoa(),
                		        xa.getTenLoaiHangHoa(),
                		        xa.getLoaiXa(),
                		        xa.getChatLieu(),
                		        xa.getChieuDai(),
                		        xa.getDuongKinh(),
                		        xa.getChieuCao(),
                		        xa.getTaiTrong(),
                		        xa.getHinhAnh()
                        });
                    }
                }
            }
        });

        // Tạo JScrollPane để chứa bảng và cho phép cuộn
        JScrollPane scrollPaneXa = new JScrollPane(bangXa);
        scrollPaneXa.setBounds(0, 320, 1200, 700); // Đặt kích thước cho JScrollPane

        // Thêm JScrollPane vào JPanel
        add(scrollPaneXa);
    }
}
