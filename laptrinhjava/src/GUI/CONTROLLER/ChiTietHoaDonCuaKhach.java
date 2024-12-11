package GUI.CONTROLLER;

import javax.swing.*;
import DTO.DTOChiTietHoaDon;

import java.awt.*;
import java.util.ArrayList;

public class ChiTietHoaDonCuaKhach extends JFrame {
    public ChiTietHoaDonCuaKhach(ArrayList<DTOChiTietHoaDon> ds, String tenCoSo, String tenKhachHang) {
        // Thiết lập JFrame
        setTitle("Chi Tiết Hóa Đơn");
        setLayout(null);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255)); // Màu nền nhạt

        // Tiêu đề Tên Cơ Sở
        JLabel lblTenCoSo = new JLabel("Tên Cơ Sở: " + tenCoSo);
        lblTenCoSo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenCoSo.setBounds(20, 20, 400, 30);
        add(lblTenCoSo);

        // Tiêu đề Tên Khách Hàng
        JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng: " + tenKhachHang);
        lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenKhachHang.setBounds(20, 60, 400, 30);
        add(lblTenKhachHang);

        // Tính toán chiều cao bảng
        int rowHeight = 30; // Chiều cao mỗi dòng
        int rowCount = ds.size() + 1; // Số dòng (bao gồm header)
        int tableHeight = rowHeight * rowCount;

        // Panel chứa danh sách chi tiết hóa đơn
        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setLayout(new GridLayout(rowCount, 4, 10, 10)); // Bố cục 4 cột
        panelChiTiet.setBackground(Color.WHITE);
        panelChiTiet.setBounds(20, 120, 740, tableHeight);
        panelChiTiet.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Header của bảng
        panelChiTiet.add(new JLabel("STT", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Tên Món Hàng", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Số Lượng", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Giá", SwingConstants.CENTER));

        // Tính toán tổng cộng
        int total = 0;
        int stt = 1;
        for (DTOChiTietHoaDon chiTiet : ds) {
            panelChiTiet.add(new JLabel(String.valueOf(stt), SwingConstants.CENTER));
            panelChiTiet.add(new JLabel(chiTiet.getTenHangHoa(), SwingConstants.CENTER));
            panelChiTiet.add(new JLabel(String.valueOf(chiTiet.getSoLuong()), SwingConstants.CENTER));
            int itemTotal = chiTiet.getGiaMotMon() * chiTiet.getSoLuong();
            panelChiTiet.add(new JLabel(itemTotal + "đ", SwingConstants.CENTER));
            total += itemTotal;
            stt++;
        }

        // Thêm bảng vào JFrame
        add(panelChiTiet);

        // Thêm tổng cộng
        JLabel lblTongCong = new JLabel("Tổng cộng: " + total + "đ");
        lblTongCong.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongCong.setBounds(20, 120 + tableHeight + 10, 740, 30);
        add(lblTongCong);

        // Điều chỉnh chiều cao JFrame nếu cần
        int frameHeight = Math.max(500, 160 + tableHeight + 50 + 40); // Adjusted for total label height
        setSize(800, frameHeight);

        // Nút đóng
        JButton btnDong = new JButton("Đóng");
        btnDong.setBounds(680, frameHeight - 70, 80, 30);
        btnDong.setBackground(new Color(200, 0, 0));
        btnDong.setForeground(Color.WHITE);
        btnDong.addActionListener(e -> dispose());
        add(btnDong);
    }
}
