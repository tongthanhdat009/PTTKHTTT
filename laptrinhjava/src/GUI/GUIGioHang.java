package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BLL.TuanBLL;
import DTO.DTOChiTietHoaDon;
import DTO.HoaDon;
public class GUIGioHang extends JPanel{
    private String idTaiKhoan;
    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }
    public GUIGioHang(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
        giaoDien();
        
    }
    public void giaoDien() {
        setLayout(null);
        setBounds(0, 0, 1200, 900);

        JLabel tieude = new JLabel("Giỏ hàng");
        tieude.setBounds(520, 20, 244, 30);
        tieude.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
        add(tieude);

        JPanel pnDSGioHang = new JPanel();
        pnDSGioHang.setLayout(null);
        JScrollPane scp = new JScrollPane(pnDSGioHang);
        scp.setBounds(0, 60, 1200, 700);
        add(scp);

        TuanBLL bll = new TuanBLL();
        ArrayList<HoaDon> dsGioHang = bll.layDSGioHang(idTaiKhoan);
        int tong = 0;
        int y = 0;
        for (HoaDon hoaDon : dsGioHang) {
            JPanel pnGioHang = new JPanel(null);
            pnGioHang.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            pnGioHang.setBackground(new Color(245, 245, 245)); // Màu nền nhạt

            // Panel thông tin hóa đơn
            JPanel hoaDonPanel = new JPanel(null);
            hoaDonPanel.setBounds(0, 0, 1150, 60);
            hoaDonPanel.setBackground(new Color(230, 230, 250)); // Lavender
            JLabel lblHoaDon = new JLabel("Cơ sở: " + hoaDon.getTenCoSo());
            lblHoaDon.setFont(new Font("Arial", Font.BOLD, 18));
            lblHoaDon.setBounds(20, 15, 800, 30);
            hoaDonPanel.add(lblHoaDon);
            pnGioHang.add(hoaDonPanel);

            // Chi tiết hóa đơn
            ArrayList<DTOChiTietHoaDon> dsChiTietHoaDon = bll.layChiTietGioHang(hoaDon.getMaHoaDon());
            JPanel chiTietPanel = new JPanel(null);
            chiTietPanel.setBackground(Color.WHITE);
            int y2 = 0;
            for (int j = 0; j < dsChiTietHoaDon.size(); j++) {
                DTOChiTietHoaDon chiTiet = dsChiTietHoaDon.get(j);

                JLabel lbSTT = new JLabel(String.valueOf(j + 1));
                lbSTT.setBounds(10, y2, 30, 30);
                lbSTT.setFont(new Font("Arial", Font.PLAIN, 16));
                chiTietPanel.add(lbSTT);

                ImageIcon productImage;
                try {
                    productImage = new ImageIcon(chiTiet.getAnh());
                } catch (Exception e) {
                    productImage = new ImageIcon("src/asset/img/default.png");
                }
                JLabel lblImage = new JLabel(productImage);
                lblImage.setBounds(50, y2, 50, 50);
                chiTietPanel.add(lblImage);

                JLabel lblTen = new JLabel(chiTiet.getTenHangHoa());
                lblTen.setBounds(120, y2, 200, 30);
                lblTen.setFont(new Font("Arial", Font.PLAIN, 16));
                chiTietPanel.add(lblTen);

                int gia = bll.tinhGiaTienChiTietGioHang(hoaDon.getMaCoSo(),chiTiet.getMaHangHoa(),chiTiet.getSoLuong());
                tong+=gia;
                JLabel lblGia = new JLabel("Giá: " + gia + " VND");
                lblGia.setBounds(320, y2, 200, 30);
                lblGia.setFont(new Font("Arial", Font.PLAIN, 16));
                chiTietPanel.add(lblGia);

                JLabel lblSoLuong = new JLabel("Số lượng: " + chiTiet.getSoLuong());
                lblSoLuong.setBounds(520, y2, 200, 30);
                lblSoLuong.setFont(new Font("Arial", Font.PLAIN, 16));
                chiTietPanel.add(lblSoLuong);

                JButton xoa = new JButton("Xóa");
                xoa.setBounds(720, y2, 80, 30);
                xoa.setBackground(Color.RED);
                xoa.setForeground(Color.WHITE);
                chiTietPanel.add(xoa);
                xoa.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        bll.xoaChiTietGioHang(hoaDon.getMaHoaDon(), chiTiet.getMaHangHoa());
                        removeAll();
                        giaoDien();
                        revalidate();
                        repaint();
                    }
                });
                y2 += 60;
            }
            chiTietPanel.setPreferredSize(new Dimension(1150, y2));
            JScrollPane chiTietScroll = new JScrollPane(chiTietPanel);
            chiTietScroll.setBounds(0, 60, 1150, Math.min(y2, 150));
            chiTietScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            chiTietScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            pnGioHang.add(chiTietScroll);
            pnGioHang.setBounds(0, y, 1150, chiTietScroll.getHeight()+60);
            y+=pnGioHang.getHeight();
            pnDSGioHang.add(pnGioHang);
        }
        JPanel pnThangToan = new JPanel(null); 
        pnThangToan.setBounds(0, 760, 1200, 140);  // Đặt vị trí và kích thước cho JPanel
        
        // Tạo JLabel và set text cho tổng cộng
        JLabel lbThanhToan = new JLabel("Tổng cộng: " + tong+" VNĐ");
        lbThanhToan.setFont(new Font("Arial", Font.BOLD, 18));  // Tùy chỉnh font chữ
        lbThanhToan.setBounds(20, 50, 300, 30); // Vị trí và kích thước của JLabel (cách trái 20px, cách trên 50px)
        
        // Tạo JButton và set text
        JButton btThanhToan = new JButton("Thanh toán");
        btThanhToan.setFont(new Font("Arial", Font.PLAIN, 16)); // Tùy chỉnh font chữ
        btThanhToan.setBounds(1000, 50, 150, 40); // Vị trí và kích thước của JButton (cách trái 1000px, cách trên 50px)
        btThanhToan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String s = bll.thanhToanGioHang(idTaiKhoan);
                JOptionPane.showMessageDialog( null, s);
                if(s.equals("Thành công"))
                {
                    removeAll();
                    giaoDien();
                    revalidate();
                    repaint();
                }
            }
        });

        // Thêm các thành phần vào JPanel
        pnThangToan.add(lbThanhToan);
        pnThangToan.add(btThanhToan);
        
        // Thêm JPanel vào JFrame hoặc container chính
        add(pnThangToan);
    }
}
