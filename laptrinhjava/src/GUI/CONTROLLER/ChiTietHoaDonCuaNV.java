package GUI.CONTROLLER;

import javax.swing.*;
import BLL.TuanBLL;
import DTO.DTOChiTietHoaDon;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ChiTietHoaDonCuaNV extends JFrame {
    TuanBLL bll = new TuanBLL();
    private ArrayList<DTOChiTietHoaDon> ds;
    private String tenCoSo, tenKhachHang, maCoSo, idHoaDon;
    private boolean isClosed = false;
    private String kiemTraGia ;
    private Boolean duHang = true;
    public Boolean isClosed() {
        return isClosed;
    }
    public ChiTietHoaDonCuaNV(ArrayList<DTOChiTietHoaDon> ds, String tenCoSo, String tenKhachHang, String maCoSo, String idHoaDon) {
        this.ds = ds;
        this.tenCoSo = tenCoSo;
        this.tenKhachHang = tenKhachHang;
        this.maCoSo = maCoSo;
        this.idHoaDon = idHoaDon;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isClosed = true; // Set trạng thái khi cửa sổ bị đóng
                super.windowClosing(e);
            }
        });
        // Thiết lập JFrame
        setTitle("Chi Tiết Hóa Đơn");
        setLayout(null);
        setSize(800, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 240, 255)); // Màu nền nhạt

        // Gọi các phương thức để tạo các thành phần giao diện
        createTitle();
        JPanel panelChiTiet = createTable();
        add(panelChiTiet);
        createFooter(panelChiTiet.getHeight());
        addButtons(panelChiTiet.getHeight());
    }

    // Tạo tiêu đề Tên Cơ Sở và Tên Khách Hàng
    private void createTitle() {
        JLabel lblTenCoSo = new JLabel("Tên Cơ Sở: " + tenCoSo);
        lblTenCoSo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenCoSo.setBounds(20, 20, 400, 30);
        add(lblTenCoSo);

        JLabel lblTenKhachHang = new JLabel("Tên Khách Hàng: " + tenKhachHang);
        lblTenKhachHang.setFont(new Font("Arial", Font.BOLD, 16));
        lblTenKhachHang.setBounds(20, 60, 400, 30);
        add(lblTenKhachHang);
    }

    // Tạo bảng chi tiết hóa đơn
    private JPanel createTable() {
        int rowHeight = 30; // Chiều cao mỗi dòng
        int rowCount = ds.size() + 1; // Số dòng (bao gồm header)
        int tableHeight = rowHeight * rowCount;

        // Panel chứa danh sách chi tiết hóa đơn
        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setLayout(new GridLayout(rowCount, 5, 10, 10)); // Bố cục 5 cột
        panelChiTiet.setBackground(Color.WHITE);
        panelChiTiet.setBounds(20, 120, 740, tableHeight);
        panelChiTiet.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Header của bảng
        panelChiTiet.add(new JLabel("STT", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Tên Món Hàng", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Số Lượng", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Kho còn", SwingConstants.CENTER));
        panelChiTiet.add(new JLabel("Giá", SwingConstants.CENTER));

        int total = 0;
        int stt = 1;
        kiemTraGia = "";
        for (DTOChiTietHoaDon chiTiet : ds) {
            // Thêm các thông tin cho mỗi món
            panelChiTiet.add(createDetailLabel(String.valueOf(stt)));
            panelChiTiet.add(createDetailLabel(chiTiet.getTenHangHoa()));
            panelChiTiet.add(createDetailLabel(String.valueOf(chiTiet.getSoLuong())));

            int soLuongKhoCon = bll.laySoLuongHangTaiCoSo(chiTiet.getMaHangHoa(), maCoSo);
            panelChiTiet.add(createDetailLabel(String.valueOf(soLuongKhoCon)));

            int itemTotal = chiTiet.getGiaMotMon() * chiTiet.getSoLuong();
            panelChiTiet.add(createDetailLabel(itemTotal + "đ"));
            if(chiTiet.getGiaMotMon() != bll.layGiaTienMonHang(maCoSo, chiTiet.getMaHangHoa())) {
                kiemTraGia+="Giá "+ chiTiet.getTenHangHoa() + " đã thay đổi\n";
            }
            // Kiểm tra số lượng so với kho còn
            if (chiTiet.getSoLuong() > soLuongKhoCon) {
                // Tô màu đỏ cho các mục không hợp lệ
                duHang = false;
                setRedBackground(panelChiTiet.getComponent(panelChiTiet.getComponentCount() - 5));
                setRedBackground(panelChiTiet.getComponent(panelChiTiet.getComponentCount() - 4));
                setRedBackground(panelChiTiet.getComponent(panelChiTiet.getComponentCount() - 3));
                setRedBackground(panelChiTiet.getComponent(panelChiTiet.getComponentCount() - 2));
                setRedBackground(panelChiTiet.getComponent(panelChiTiet.getComponentCount() - 1));
            }

            total += itemTotal;
            stt++;
        }

        return panelChiTiet;
    }

    // Tạo một JLabel chi tiết cho bảng
    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setOpaque(true);
        return label;
    }

    // Đặt màu đỏ cho background của JLabel
    private void setRedBackground(Component component) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setBackground(Color.RED);
        }
    }

    // Tạo tổng cộng
    private void createFooter(int tableHeight) {
        int total = 0;
        for (DTOChiTietHoaDon chiTiet : ds) {
            total += chiTiet.getGiaMotMon() * chiTiet.getSoLuong();
        }

        JLabel lblTongCong = new JLabel("Tổng cộng: " + total + "đ");
        lblTongCong.setFont(new Font("Arial", Font.BOLD, 16));
        lblTongCong.setBounds(20, 120 + tableHeight + 10, 740, 30);
        add(lblTongCong);
    }

    // Tạo các nút chức năng
    private void addButtons(int tableHeight) {
        int frameHeight = Math.max(500, 160 + tableHeight + 50 + 40); // Điều chỉnh chiều cao JFrame

        // Nút đóng
        JButton btnDong = new JButton("Đóng");
        btnDong.setBounds(680, frameHeight - 120, 80, 30);
        btnDong.setBackground(new Color(200, 0, 0));
        btnDong.setForeground(Color.WHITE);
        btnDong.addActionListener(e -> dispose());
        add(btnDong);

        // Nút Duyệt (Approve)
        JButton btnDuyet = new JButton("Duyệt");
        btnDuyet.setBounds(580, frameHeight - 120, 80, 30);
        btnDuyet.setBackground(new Color(0, 200, 0));
        btnDuyet.setForeground(Color.WHITE);
        btnDuyet.addActionListener(e -> {
            // Implement approve action here
            if(duHang==false) {
                JOptionPane.showMessageDialog(null,"Kho không đủ hàng");
                return;
            }
            if(!kiemTraGia.equals("")) {
                int response = JOptionPane.showConfirmDialog(null, kiemTraGia +"\n Bạn có muốn tiếp tục", "Thông báo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if(response == JOptionPane.OK_OPTION) {
                    JOptionPane.showMessageDialog(null, bll.duyetDon(idHoaDon, ds, maCoSo));
                }
                else return;
            }
            else {
                JOptionPane.showMessageDialog(null, bll.duyetDon(idHoaDon, ds, maCoSo));
            }
        });
        add(btnDuyet);

        // Nút Hủy Đơn (Cancel Order)
        JButton btnHuyDon = new JButton("Hủy");
        btnHuyDon.setBounds(480, frameHeight - 120, 80, 30);
        btnHuyDon.setBackground(new Color(200, 0, 0));
        btnHuyDon.setForeground(Color.WHITE);
        btnHuyDon.addActionListener(e -> {
            String s = bll.huyDon(idHoaDon);  // Gọi phương thức hủy đơn trong BLL
            JOptionPane.showMessageDialog(this, s);
        });
        add(btnHuyDon);
    }
}
