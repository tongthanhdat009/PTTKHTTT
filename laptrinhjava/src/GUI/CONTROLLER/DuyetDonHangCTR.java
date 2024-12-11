package GUI.CONTROLLER;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import BLL.TuanBLL;
import DTO.HoaDon;

public class DuyetDonHangCTR extends JPanel {
    private String maCoSo;
    private TuanBLL bll = new TuanBLL();
    
    private JButton btnChuaDuyet;
    private JButton btnDaXuLy;
    private JPanel panelContent;

    public DuyetDonHangCTR(String maCoSo) {
        this.maCoSo = maCoSo;
        setLayout(null);
        setBounds(0, 0, 1200, 900);
        setBackground(new Color(245, 245, 245)); // Màu nền nhẹ nhàng

        // Title: Duyệt Đơn Hàng
        JLabel lblTitle = new JLabel("Duyệt Đơn Hàng", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setBounds(0, 20, 1200, 40);
        lblTitle.setForeground(new Color(0, 51, 102)); // Màu xanh đậm
        add(lblTitle);

        // Nút "Chưa Duyệt"
        btnChuaDuyet = new JButton("Chưa Duyệt");
        btnChuaDuyet.setFont(new Font("Arial", Font.BOLD, 16));
        btnChuaDuyet.setBounds(250, 80, 200, 40);
        btnChuaDuyet.setBackground(new Color(0, 123, 255));
        btnChuaDuyet.setForeground(Color.WHITE);
        btnChuaDuyet.setFocusPainted(false); // Tắt viền khi nhấn
        btnChuaDuyet.setBorderPainted(false);
        btnChuaDuyet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                giaoDienDonChuaDuyet();
            }
        });
        add(btnChuaDuyet);

        // Nút "Đã Xử Lý"
        btnDaXuLy = new JButton("Đã Xử Lý");
        btnDaXuLy.setFont(new Font("Arial", Font.BOLD, 16));
        btnDaXuLy.setBounds(500, 80, 200, 40);
        btnDaXuLy.setBackground(new Color(0, 123, 255));
        btnDaXuLy.setForeground(Color.WHITE);
        btnDaXuLy.setFocusPainted(false); // Tắt viền khi nhấn
        btnDaXuLy.setBorderPainted(false);
        btnDaXuLy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                giaoDienDonDaXuLy();
            }
        });
        add(btnDaXuLy);

        // Panel chứa danh sách hóa đơn
        panelContent = new JPanel();
        panelContent.setLayout(null);
        panelContent.setBounds(100, 140, 1000, 700);
        add(panelContent);
        giaoDienDonChuaDuyet();
    }

    // Giao diện cho danh sách hóa đơn chưa duyệt
    public void giaoDienDonChuaDuyet() {
        panelContent.removeAll();

        ArrayList<HoaDon> dsHoaDonChuaDuyet = bll.layDanhSachHoaDonChuaDuyetCuaCoSo(maCoSo);
        if (dsHoaDonChuaDuyet.isEmpty()) {
            JLabel lblMessage = new JLabel("Không có hóa đơn chưa duyệt.");
            lblMessage.setFont(new Font("Arial", Font.ITALIC, 16));
            lblMessage.setBounds(350, 150, 300, 30);
            panelContent.add(lblMessage);
        } else {
            int yPosition = 10;
            for (HoaDon hoaDon : dsHoaDonChuaDuyet) {
                JPanel hoaDonPanel = new JPanel();
                hoaDonPanel.setLayout(null);
                hoaDonPanel.setBounds(50, yPosition, 900, 80);
                hoaDonPanel.setBackground(new Color(255, 255, 255)); // Màu nền trắng
                hoaDonPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

                // Hiển thị mã hóa đơn
                JLabel maHoaDonLabel = new JLabel("Mã: " + hoaDon.getMaHoaDon());
                maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                maHoaDonLabel.setBounds(10, 10, 120, 30);
                hoaDonPanel.add(maHoaDonLabel);

                // Hiển thị tên cơ sở
                JLabel tenCoSoLabel = new JLabel("Cơ sở: " + hoaDon.getTenCoSo());
                tenCoSoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                tenCoSoLabel.setBounds(140, 10, 200, 30);
                hoaDonPanel.add(tenCoSoLabel);

                // Hiển thị ngày xuất
                JLabel ngayXuatLabel = new JLabel("Xuất: " + hoaDon.getNgayXuatHoaDon());
                ngayXuatLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                ngayXuatLabel.setBounds(360, 10, 150, 30);
                hoaDonPanel.add(ngayXuatLabel);

                // Hiển thị trạng thái
                JLabel trangThaiLabel = new JLabel("Trạng thái: " + hoaDon.getTrangThai());
                trangThaiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                trangThaiLabel.setBounds(520, 10, 160, 30);
                hoaDonPanel.add(trangThaiLabel);

                // Nút "Xem Chi Tiết"
                JButton xemChiTietBtn = new JButton("Xem Chi Tiết");
                xemChiTietBtn.setBounds(700, 10, 120, 30);
                xemChiTietBtn.setFont(new Font("Arial", Font.PLAIN, 14));
                xemChiTietBtn.setBackground(new Color(0, 204, 0));
                xemChiTietBtn.setForeground(Color.WHITE);
                xemChiTietBtn.setFocusPainted(false);
                xemChiTietBtn.setBorderPainted(false);
                xemChiTietBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ChiTietHoaDonCuaNV guiCTHD = new ChiTietHoaDonCuaNV(bll.layChiTietHoaDon(hoaDon.getMaHoaDon()), hoaDon.getTenCoSo(), hoaDon.getTenHoiVien(),hoaDon.getMaCoSo(),hoaDon.getMaHoaDon());
                        guiCTHD.setVisible(true);
                        new Thread(() -> {
                            while (!guiCTHD.isClosed()) {
                                try {
                                    Thread.sleep(1000); // Kiểm tra mỗi 100ms
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            giaoDienDonChuaDuyet();
                            // Cập nhật bảng hoặc thực hiện các hành động khác ở đây
                        }).start();
                    }
                });
                hoaDonPanel.add(xemChiTietBtn);

                panelContent.add(hoaDonPanel);
                yPosition += 90; // Khoảng cách giữa các hàng
            }
        }

        panelContent.revalidate();
        panelContent.repaint();
    }

    // Giao diện cho danh sách hóa đơn đã xử lý
    public void giaoDienDonDaXuLy() {
        panelContent.removeAll();

        ArrayList<HoaDon> dsHoaDonDaXuLy = bll.layDanhSachHoaDonDaXuLyCuaCoSo(maCoSo);
        if (dsHoaDonDaXuLy.isEmpty()) {
            JLabel lblMessage = new JLabel("Không có hóa đơn đã xử lý.");
            lblMessage.setFont(new Font("Arial", Font.ITALIC, 16));
            lblMessage.setBounds(350, 150, 300, 30);
            panelContent.add(lblMessage);
        } else {
            int yPosition = 10;
            for (HoaDon hoaDon : dsHoaDonDaXuLy) {
                JPanel hoaDonPanel = new JPanel();
                hoaDonPanel.setLayout(null);
                hoaDonPanel.setBounds(50, yPosition, 900, 80);
                hoaDonPanel.setBackground(new Color(255, 255, 255)); // Màu nền trắng
                hoaDonPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

                // Hiển thị mã hóa đơn
                JLabel maHoaDonLabel = new JLabel("Mã: " + hoaDon.getMaHoaDon());
                maHoaDonLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                maHoaDonLabel.setBounds(10, 10, 120, 30);
                hoaDonPanel.add(maHoaDonLabel);

                // Hiển thị tên cơ sở
                JLabel tenCoSoLabel = new JLabel("Cơ sở: " + hoaDon.getTenCoSo());
                tenCoSoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                tenCoSoLabel.setBounds(140, 10, 200, 30);
                hoaDonPanel.add(tenCoSoLabel);

                // Hiển thị ngày xuất
                JLabel ngayXuatLabel = new JLabel("Xuất: " + hoaDon.getNgayXuatHoaDon());
                ngayXuatLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                ngayXuatLabel.setBounds(360, 10, 150, 30);
                hoaDonPanel.add(ngayXuatLabel);

                // Hiển thị trạng thái
                JLabel trangThaiLabel = new JLabel("Trạng thái: " + hoaDon.getTrangThai());
                trangThaiLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                trangThaiLabel.setBounds(520, 10, 160, 30);
                hoaDonPanel.add(trangThaiLabel);

                // Nút "Xem Chi Tiết"
                JButton xemChiTietBtn = new JButton("Xem Chi Tiết");
                xemChiTietBtn.setBounds(700, 10, 120, 30);
                xemChiTietBtn.setFont(new Font("Arial", Font.PLAIN, 14));
                xemChiTietBtn.setBackground(new Color(0, 204, 0));
                xemChiTietBtn.setForeground(Color.WHITE);
                xemChiTietBtn.setFocusPainted(false);
                xemChiTietBtn.setBorderPainted(false);
                xemChiTietBtn.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ChiTietHoaDonCuaKhach guiCTHD = new ChiTietHoaDonCuaKhach(bll.layChiTietHoaDon(hoaDon.getMaHoaDon()), hoaDon.getTenCoSo(), hoaDon.getTenHoiVien());
                        guiCTHD.setVisible(true);
                    }
                });
                hoaDonPanel.add(xemChiTietBtn);

                panelContent.add(hoaDonPanel);
                yPosition += 90; // Khoảng cách giữa các hàng
            }
        }

        panelContent.revalidate();
        panelContent.repaint();
    }
}
