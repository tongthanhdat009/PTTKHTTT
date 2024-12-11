package GUI.CONTROLLER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import BLL.TuanBLL;
import DTO.HoaDon;

public class GUIXemDonHang extends JPanel {
    private String idTaiKhoan;

    public void setIdTaiKhoan(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public GUIXemDonHang(String idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
        giaoDien();
    }

    public void giaoDien() {
        setLayout(null);
        setBounds(0, 0, 1200, 900);
        setBackground(new Color(230, 240, 255)); // Màu nền nhạt
    
        JLabel tieude = new JLabel("Đơn hàng");
        tieude.setBounds(520, 20, 244, 30);
        tieude.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
        tieude.setForeground(new Color(0, 102, 204)); // Màu chữ xanh đậm
        add(tieude);
    
        // Lấy danh sách hóa đơn
        TuanBLL bll = new TuanBLL();
        ArrayList<HoaDon> dsHoaDon = bll.layDanhSachHoaDonCuaTaiKhoan(idTaiKhoan);
    
        int y = 100; // Vị trí y ban đầu
        int spacing = 60; // Khoảng cách giữa các hàng
    
        for (HoaDon hoaDon : dsHoaDon) {
            // Tạo panel chứa từng hóa đơn
            JPanel hoaDonPanel = new JPanel();
            hoaDonPanel.setLayout(null);
            hoaDonPanel.setBounds(50, y, 1100, 50);
            hoaDonPanel.setBackground(new Color(255, 255, 204)); // Màu nền vàng nhạt
            hoaDonPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    
            // Hiển thị mã hóa đơn
            JLabel maHoaDonLabel = new JLabel("Mã: " + hoaDon.getMaHoaDon());
            maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            maHoaDonLabel.setBounds(10, 10, 120, 30);
            hoaDonPanel.add(maHoaDonLabel);
    
            // Hiển thị tên cơ sở
            JLabel tenCoSoLabel = new JLabel("Cơ sở: " + hoaDon.getTenCoSo());
            tenCoSoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            tenCoSoLabel.setBounds(140, 10, 160, 30);
            hoaDonPanel.add(tenCoSoLabel);
    
            // Hiển thị ngày xuất
            JLabel ngayXuatLabel = new JLabel("Xuất: " + hoaDon.getNgayXuatHoaDon());
            ngayXuatLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            ngayXuatLabel.setBounds(310, 10, 150, 30);
            hoaDonPanel.add(ngayXuatLabel);
    
            // Hiển thị ngày duyệt
            JLabel ngayDuyetLabel = new JLabel("Duyệt: " + hoaDon.getNgayDuyetHoaDon());
            ngayDuyetLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            ngayDuyetLabel.setBounds(470, 10, 150, 30);
            hoaDonPanel.add(ngayDuyetLabel);
    
            // Hiển thị trạng thái
            JLabel trangThaiLabel = new JLabel("Trạng thái: " + hoaDon.getTrangThai());
            trangThaiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            trangThaiLabel.setBounds(630, 10, 160, 30);
            hoaDonPanel.add(trangThaiLabel);
    
            // Nút "Xem chi tiết"
            JButton xemChiTietBtn = new JButton("Xem chi tiết");
            xemChiTietBtn.setBounds(920, 10, 110, 30); // Điều chỉnh kích thước nút
            xemChiTietBtn.setFont(new Font("Arial", Font.PLAIN, 14));
            xemChiTietBtn.setBackground(new Color(0, 153, 0)); // Màu xanh lá cây
            xemChiTietBtn.setForeground(Color.WHITE); // Chữ trắng
            xemChiTietBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ChiTietHoaDonCuaKhach guiCTHD = new ChiTietHoaDonCuaKhach(bll.layChiTietHoaDon(hoaDon.getMaHoaDon()),hoaDon.getTenCoSo(),hoaDon.getTenHoiVien());
                    guiCTHD.setVisible(true);
                }
            });
            hoaDonPanel.add(xemChiTietBtn);
            add(hoaDonPanel);
            y += spacing;
        }
    }
}
