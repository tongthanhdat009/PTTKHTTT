package GUI.CONTROLLER;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import BLL.BLLQuanLyDanhSach;
import DTO.hangHoaCoSo;

public class hangHoaCSCTR {
    private Font italicBoldFont = new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30); //vừa nghiêng vừa in đậm
	public hangHoaCSCTR() {
		
	}
	public void xoaHienThi(JPanel rightPanel){
        Component[] components = rightPanel.getComponents();
        for(Component a : components){
            if(!(a instanceof JLabel || a instanceof JComboBox)){
                rightPanel.remove(a);
            }
        }
        rightPanel.revalidate();
        rightPanel.repaint();
    }
	public void QuanLyHangHoaCoSo(ArrayList<hangHoaCoSo> ds, Vector<String> dsMaCoSo, JPanel rightPanel)
    {
        xoaHienThi(rightPanel);
//    	JButton them = new JButton();
//        ImageIcon themBtnImg = new ImageIcon("src/asset/img/button/hh-them.png");
//        Image scaleThemBtnImg = themBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
//        them.setPreferredSize(new Dimension (130,35));
//        them.setIcon(new ImageIcon(scaleThemBtnImg));
//        them.setHorizontalAlignment(SwingConstants.CENTER);
//        them.setBorder(null);
//
//    	JButton xoa  = new JButton();
//        xoa.setPreferredSize(new Dimension (110,35));
//        ImageIcon xoaBtnImg = new ImageIcon("src/asset/img/button/hh-xoa.png");
//        Image scaleXoaBtnImg = xoaBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
//        xoa.setPreferredSize(new Dimension (130,35));
//        xoa.setIcon(new ImageIcon(scaleXoaBtnImg));
//        xoa.setHorizontalAlignment(SwingConstants.CENTER);
//        xoa.setBorder(null);
//
    	JButton sua = new JButton();
        sua.setPreferredSize(new Dimension (110,35));
        ImageIcon suaBtnImg = new ImageIcon("src/asset/img/button/hh-sua.png");
        Image scaleSuaBtnImg = suaBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        sua.setPreferredSize(new Dimension (130,35));
        sua.setIcon(new ImageIcon(scaleSuaBtnImg));
        sua.setHorizontalAlignment(SwingConstants.CENTER);
        sua.setBorder(null);

    	JButton timKiem = new JButton();
        timKiem.setPreferredSize(new Dimension (110,35));
        ImageIcon timKiemBtnImg = new ImageIcon("src/asset/img/button/hh-tim.png");
        Image scaletimKiemBtnImg = timKiemBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        timKiem.setPreferredSize(new Dimension (130,35));
        timKiem.setIcon(new ImageIcon(scaletimKiemBtnImg));
        timKiem.setHorizontalAlignment(SwingConstants.CENTER);
        timKiem.setBorder(null);

        JPanel chucNang = new JPanel(new FlowLayout());
        chucNang.setBackground(new Color(241, 255, 250));
//        chucNang.add(them);
//        chucNang.add(xoa);
        chucNang.add(sua);
        chucNang.add(timKiem);
        chucNang.setBounds(5,100,rightPanel.getWidth()-5,38);
        rightPanel.add(chucNang);

        JPanel nhapLieu = new JPanel(null);
        nhapLieu.setBounds(2, 150, rightPanel.getWidth()-20, 70);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(blackBorder,"Nhập liệu");
        titledBorder.setTitleFont(italicBoldFont);
        nhapLieu.setBackground(new Color(119, 230, 163));
        nhapLieu.setBorder(titledBorder);

        JLabel lbMaHangHoa = new JLabel("Mã hàng hóa: ");
        JLabel lbMaCoSo = new JLabel("Mã cơ sở: ");
        JLabel lbSoLuong = new JLabel("Số lượng: ");
        JLabel lbTrangThai = new JLabel("Trạng thái:");
        JTextField tfMaHangHoa = new JTextField();
        @SuppressWarnings("rawtypes")
        JComboBox cbMaCoSo = new JComboBox<>(dsMaCoSo);
        cbMaCoSo.setBackground(Color.WHITE);
        JComboBox<String> cbTrangThai = new JComboBox<String>();
        cbTrangThai.addItem("Chọn trạng thái");
        cbTrangThai.addItem("Khóa");
        cbTrangThai.addItem("Đang bán");
        cbTrangThai.setBackground(Color.white);
        
        JTextField tfSoLuong = new JTextField();
        int x = 120;
        lbMaHangHoa.setBounds(x, 25, 80, 30); x+=80;
        tfMaHangHoa.setBounds(x+10, 25, 100, 30); x+=110;
        lbMaCoSo.setBounds(x+50, 25, 70, 30); x+=120;
        cbMaCoSo.setBounds(x+10, 25, 100, 30); x+=110;
        lbSoLuong.setBounds(x+50, 25, 70, 30); x+=120;
        tfSoLuong.setBounds(x+10, 25, 100, 30);x+=110;
        lbTrangThai.setBounds(x+50,25,100,30);x+=120;
        cbTrangThai.setBounds(x+10,25,100,30);
        
        tfSoLuong.setEditable(false);
        
        nhapLieu.add(lbMaHangHoa);
        nhapLieu.add(tfMaHangHoa);
        nhapLieu.add(lbMaCoSo);
        nhapLieu.add(cbMaCoSo);
        nhapLieu.add(lbSoLuong);
        nhapLieu.add(tfSoLuong);
        nhapLieu.add(lbTrangThai);
        nhapLieu.add(cbTrangThai);
        rightPanel.add(nhapLieu);

        JTable bang = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        bang.setModel(model);
        bang.setFont(new Font("Times New Roman", Font.BOLD, 14));
        bang.getTableHeader().setReorderingAllowed(false);
        
        model.addColumn("Mã cơ sở");
        model.addColumn("Mã hàng hóa");
        model.addColumn("Trạng Thái");
        model.addColumn("Số lượng");
        model.addColumn("Giá bán");
        model.addColumn("Loại");
        model.addColumn("Tên loại hàng hóa");
        model.addColumn("Hình ảnh");
        // for (int i = 0; i < bang.getColumnCount(); i++) {
        //     bang.getColumnModel().getColumn(i).setCellRenderer(rendererTable);
        // }
        BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
        for(int i=0;i<ds.size();i++)
        model.addRow(new Object[]{ds.get(i).getMaHangHoa(),
        		ds.get(i).getMaCoSo(),
        		ds.get(i).getTrangThai(),
        		ds.get(i).getSoLuong(),
        		ds.get(i).getGiaBan(),
        		ds.get(i).getLoaiHangHoa(),
        		ds.get(i).getTenLoaiHangHoa(),
        		ds.get(i).getHinhAnh()});
        bang.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e)
            {
                int i = bang.getSelectedRow();
                if(i>=0)
                {
                    cbMaCoSo.setSelectedItem(model.getValueAt(i, 0).toString());
                    tfMaHangHoa.setText(model.getValueAt(i, 1).toString());
                    cbTrangThai.setSelectedItem(model.getValueAt(i, 2).toString().trim());
                    tfSoLuong.setText(model.getValueAt(i, 3).toString());
                }
            }
        });
//        them.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e)
//            {
//                if(tfMaHangHoa.getText().equals("")||cbMaCoSo.getSelectedIndex() == 0||tfSoLuong.getText().equals(""))
//                JOptionPane.showMessageDialog(rightPanel, "Thiếu thông tin");
//                else
//                {
//                    String s = bllQuanLyDanhSach.themHangHoaCoSo(tfMaHangHoa.getText(), cbMaCoSo.getSelectedItem().toString(), Integer.parseInt(tfSoLuong.getText()));
//                    JOptionPane.showMessageDialog(rightPanel, s);
//                    if(s.equals("Thành công"))
//                    model.addRow(new Object[]{tfMaHangHoa.getText(), cbMaCoSo.getSelectedItem().toString(), Integer.parseInt(tfSoLuong.getText())});
//                }
//            }
//        });
//        xoa.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e)
//            {
//                if(tfMaHangHoa.getText().equals("")||cbMaCoSo.getSelectedIndex() == 0) JOptionPane.showMessageDialog(rightPanel, "Vui lòng nhập mã cơ sở và mã hàng hóa");
//                else
//                {
//                    String s = bllQuanLyDanhSach.xoaHangHoaCoSo(cbMaCoSo.getSelectedItem().toString(), tfMaHangHoa.getText());
//                    JOptionPane.showMessageDialog(rightPanel, s);
//                    if(s.equals("Thành công"))
//                    for(int i=0;i<model.getRowCount();i++)
//                    if(model.getValueAt(i, 0).toString().equals(tfMaHangHoa.getText()) && model.getValueAt(i, 1).toString().equals(cbMaCoSo.getSelectedItem().toString()))
//                    model.removeRow(i);
//                }
//            }
//        });
        sua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                String maCSGoc = new String();
                String maHHGoc = new String();
                int row = bang.getSelectedRow();
                System.out.println(row);
                if(row==-1){
                    // if(tfMaHangHoa.getText().equals("")||cbMaCoSo.getSelectedIndex() == 0||tfSoLuong.getText().equals("")) {
                    //     JOptionPane.showMessageDialog(rightPanel, "Thiếu thông tin");
                    // 	return;
                    // }
                    JOptionPane.showMessageDialog(rightPanel, "Vui lòng chọn 1 dòng để sửa","Sửa thông tin",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else
                {	
                    maCSGoc = bang.getValueAt(row, 0).toString().trim();
                    maHHGoc = bang.getValueAt(row, 1).toString().trim();
                    if(!maCSGoc.equals(cbMaCoSo.getSelectedItem().toString())){
                        JOptionPane.showMessageDialog(null, "Không được sửa mã cơ sở!","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(!maHHGoc.equals(tfMaHangHoa.getText().toString())){
                        JOptionPane.showMessageDialog(null, "Không được sửa mã hàng hóa!","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if(cbTrangThai.getSelectedItem().toString().equals("Chọn trạng thái")){
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái để sửa");
                		return;
                	}
                    
                    String s = bllQuanLyDanhSach.suaHangHoaCoSo(cbMaCoSo.getSelectedItem().toString(), tfMaHangHoa.getText(), cbTrangThai.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(rightPanel, s,"Sửa thông tin",JOptionPane.INFORMATION_MESSAGE);
                    if(s.equals("Thành công"))
                    {
                    	model.setRowCount(0);
                    	ArrayList<hangHoaCoSo> ds = new ArrayList<>();
                        ds = bllQuanLyDanhSach.layDSHangHoaCoSo();
                        @SuppressWarnings("unused")
                        Vector<String> dsMaCoSo = new Vector<>();
                        dsMaCoSo = bllQuanLyDanhSach.layDSMaCoSo();
                        for(int i=0;i<ds.size();i++)
                        model.addRow(new Object[]{ds.get(i).getMaHangHoa(),
                            ds.get(i).getMaCoSo(),
                            ds.get(i).getTrangThai(),
                            ds.get(i).getSoLuong(),
                            ds.get(i).getGiaBan(),
                            ds.get(i).getLoaiHangHoa(),
                            ds.get(i).getTenLoaiHangHoa(),
                            ds.get(i).getHinhAnh()});
                    }
                }
            }
        });
        timKiem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	model.setRowCount(0);
//                if(tfMaHangHoa.getText().equals("")&&cbMaCoSo.getSelectedIndex()==0) 
//                {
//                    for(int i=0;i<ds.size();i++)
//                    model.addRow(new Object[]{ds.get(i).getMaHangHoa(),
//                    		ds.get(i).getMaCoSo(),
//                    		ds.get(i).getTrangThai(),
//                    		ds.get(i).getSoLuong(),
//                    		ds.get(i).getGiaBan(),
//                    		ds.get(i).getLoaiHangHoa(),
//                    		ds.get(i).getTenLoaiHangHoa(),
//                    		ds.get(i).getHinhAnh()});
//                }
//                else
//                {
                    ArrayList<hangHoaCoSo> ds2 = new ArrayList<>();
                    ds2 = bllQuanLyDanhSach.timKiemHangHoaCoSo(cbMaCoSo.getSelectedItem().toString(), tfMaHangHoa.getText(), cbTrangThai.getSelectedItem().toString());
                    for(int i=0;i<ds2.size();i++)
                    	model.addRow(new Object[]{ds2.get(i).getMaHangHoa(),
                        		ds2.get(i).getMaCoSo(),
                        		ds2.get(i).getTrangThai(),
                        		ds2.get(i).getSoLuong(),
                        		ds2.get(i).getGiaBan(),
                        		ds2.get(i).getLoaiHangHoa(),
                        		ds2.get(i).getTenLoaiHangHoa(),
                        		ds2.get(i).getHinhAnh()});
//                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(bang);
        scrollPane.setBounds(5, 230, rightPanel.getWidth()-20, rightPanel.getHeight()-270);
        rightPanel.add(scrollPane);
    }
}
