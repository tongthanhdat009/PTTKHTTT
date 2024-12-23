package GUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import BLL.BLLDangNhap;
import BLL.BLLDonNhap;
import BLL.BLLQuanLyDanhSach;
import DTO.DTOQuyen;
import DTO.DonNhap;
import DTO.HoiVien;
import DTO.NhanVien;
import DTO.DTOTaiKhoan;
import GUI.CONTROLLER.DuyetDonHangCTR;
import GUI.CONTROLLER.ExcelCTR;
import GUI.CONTROLLER.ImportExcelCTR;
import GUI.CONTROLLER.QuanLyBangNhanVienCTR;
import GUI.CONTROLLER.QuanLyThietBiCTR;
//import GUI.CONTROLLER.XuatExcelCTR;
import GUI.CONTROLLER.delegateCTR;
import GUI.CONTROLLER.hoiVienCTR;
import GUI.CONTROLLER.thongKeCTR;
import GUI.CONTROLLER.xuLyDSCTR;
import GUI.CONTROLLER.xulyDDNCTR;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;


public class GUIAdmin{
    private JFrame adminFrame = new JFrame("Quản lý cửa hàng dụ cụ thể thao");
    private final int width = 1600;
    private final int height = 900;
    //logo
    ImageIcon logo = new ImageIcon("src/asset/img/label/logo.png");
    Image scaleLogoIcon = logo.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT);
    ImageIcon logo1 = new ImageIcon("src/asset/img/label/logo1.png");
    Image scaleLogoIcon1 = logo1.getImage().getScaledInstance(500, 500,Image.SCALE_DEFAULT);
    
    //logo excel
    ImageIcon excelIcon = new ImageIcon("src/asset/img/icon/excel-icon.png");
    Image scaleExcelIcon = excelIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng mua hàng
    ImageIcon cartIcon = new ImageIcon("src/asset/img/icon/cart-icon.png");
    Image scaleCartIcon = cartIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng thống kê
    ImageIcon analyticsIcon = new ImageIcon("src/asset/img/icon/analytics-icon.png");
    Image scaleAnalyticsIcon = analyticsIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng quản lý hội viên
    ImageIcon membershipIcon = new ImageIcon("src/asset/img/icon/membership-icon.png");
    Image scaleMembershipIcon = membershipIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng quản lý nhân viên
    ImageIcon employeeIcon = new ImageIcon("src/asset/img/icon/employee-icon.png");
    Image scaleEmployeeIcon = employeeIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng quản lý thiết bị
    ImageIcon devicesIcon = new ImageIcon("src/asset/img/icon/devices-icon.png");
    Image scaleDevicesIcon = devicesIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng phân quyền
    ImageIcon permissionIcon = new ImageIcon("src/asset/img/icon/permission-icon.png");
    Image scalePermissionIcon = permissionIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng danh sách
    ImageIcon checkListIcon = new ImageIcon("src/asset/img/icon/checklist-icon.png");
    Image scaleCheckListIcon = checkListIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon tiêu đề phụ chức năng
    ImageIcon managementIcon = new ImageIcon("src/asset/img/icon/project-management-icon.png");
    Image scaleManagementIcon = managementIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);

    ImageIcon upArrowIcon = new ImageIcon("src/asset/img/icon/up-Arrow-icon.png");
    Image scaleUpArrowIcon = upArrowIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    ImageIcon downArrowIcon = new ImageIcon("src/asset/img/icon/down-Arrow-icon.png");
    Image scaleDownArrowIcon = downArrowIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng nhập thiết bị
    ImageIcon dumbbellIcon = new ImageIcon("src/asset/img/icon/dumbbell-icon.png");
    Image scaleDumbbellIcon = dumbbellIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng nhập hàng hóa
    ImageIcon goodsIcon = new ImageIcon("src/asset/img/icon/goods-icon.png");
    Image scaleGoodsIcon = goodsIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);

    //icon chức năng duyệt đơn hàng
    ImageIcon billIcon = new ImageIcon("src/asset/img/icon/bill-icon.png");
    Image scaleBillIcon = billIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng thống kê doanh thu
    ImageIcon chartIcon = new ImageIcon("src/asset/img/icon/stonk-icon.jpg");
    Image scaleChartIcon = chartIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    //tạo viền cho panel
    Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

    //main
    private JPanel mainPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    
    //dòng cuối leftPanel
    JLabel footerLeft = new JLabel("Designed By: SGU FITNESS CLUB");

    //font
    Font italicBoldFont = new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30); //vừa nghiêng vừa in đậm

    //màu cho combobox
    ListCellRenderer<? super String> renderer = new DefaultListCellRenderer() {
		private static final long serialVersionUID = 3791056176578510864L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            // Đặt màu cho phần tử trong JComboBox
            if (isSelected) {
                component.setForeground(new Color(140, 82, 255)); // Màu chữ khi được chọn
                component.setBackground(Color.WHITE); // Màu nền khi được chọn
            } else {
                component.setForeground(Color.BLACK); // Màu chữ mặc định
                component.setBackground(Color.WHITE); // Màu nền mặc định
            }

            return component;
        }
    };
    
    // //màu cho bảng
    // DefaultTableCellRenderer rendererTable = new DefaultTableCellRenderer() {
    //     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    //         Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
    //         // Đặt màu cho các hàng chẵn và lẻ
    //         if (row % 2 == 0) {
    //             component.setBackground(Color.decode("#d2a5e8"));
    //         } else {
    //             component.setBackground(Color.white);
    //         }
            
    //         return component;
    //     }
    // };
    
    public String curr_user = new String();
    
    public BLLDangNhap bllDangNhap = new BLLDangNhap();

    public GUIAdmin(DTOTaiKhoan tk, String coSoHienTai){    
//    	người dùng hiện tại
    	this.curr_user = "Admin";
        //main frame
        adminFrame.setSize(width, height);
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setResizable(false);
        adminFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        adminFrame.getContentPane().setLayout(null);
        adminFrame.setIconImage(logo.getImage());
         // Thêm WindowListener để theo dõi sự kiện đóng cửa sổ
        adminFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                adminFrame.dispose();
                new GUILogin();
            }
        });
        mainPanel.setLocation(0, 0);

        //main
        mainPanel.setSize(new Dimension(1600, 861));
        mainPanel.setLayout(null);
        leftPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));

        //left panel
        leftPanel.setBounds(0,0,400,861);
        leftPanel.setBackground(new Color(0, 191, 99));
        leftPanel.setLayout(null);
        
        // subTitle.setFont(new java.awt.Font("Times New Roman", 1, 35));
        // subTitle2.setFont(new java.awt.Font("Times New Roman", 1, 35));
        footerLeft.setFont(new Font("Times New Roman", Font.BOLD, 13));
        footerLeft.setBounds(10,837,(int)(width * 30/100),20);
                
        //bảng chọn chức năng
        JPanel managementPanel = new JPanel();
        managementPanel.setLayout(null);
        managementPanel.setBounds(25,238,352,547);
        
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(blackBorder,"Chức năng");
        titledBorder.setTitleFont(italicBoldFont);
        managementPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
        		"Chức năng", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30), new Color(70, 78, 71)));
        managementPanel.setBackground(new Color(204, 252, 203));
        JScrollPane scrollPane = new JScrollPane(managementPanel);
        scrollPane.setBounds(26, 238,352,547); // Kích thước và vị trí của JScrollPane
        managementPanel.setPreferredSize(new Dimension(300, 750)); // y là tổng chiều cao của tất cả các nút
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        leftPanel.add(scrollPane);
        
        //chức năng quản lý danh sách
        JButton listBTN = new JButton("Quản lý danh sách");
        listBTN.setSelectedIcon(null);
        listBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        listBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                xuLyDSCTR XLDSCtrl = new xuLyDSCTR();
                XLDSCtrl.xuLyDanhSach(rightPanel);
        	}
        });
        listBTN.setBounds(23, 40, 300, 50);
        listBTN.setFocusPainted(false);
        listBTN.setIcon(new ImageIcon(scaleCheckListIcon));

        managementPanel.add(listBTN);
        
        //chức năng  duyệt đơn hàng
        JButton billBTN = new JButton("Duyệt đơn hàng");
        billBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        billBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                rightPanel.setLayout(null);
                DuyetDonHangCTR ddDonHangCTR  = new DuyetDonHangCTR(coSoHienTai);
                rightPanel.add(ddDonHangCTR);
        	}
        });
        billBTN.setBounds(23, 100, 300, 50);
        billBTN.setIcon(new ImageIcon(scaleBillIcon));
        billBTN.setFocusPainted(false);

        managementPanel.add(billBTN);
        
//        JButton goodsBTN = new JButton("Nhập thiết bị");
//        goodsBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
//        goodsBTN.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		BLLNhapThietBi bllNhapThietBi = new BLLNhapThietBi();
//                DSLoaiThietBi dsLoaiThietBi = new DSLoaiThietBi();
//                dsLoaiThietBi = bllNhapThietBi.layDSLoaiThietBi();
//                int soLuongLoaiThietBi = dsLoaiThietBi.dsThietBi.size();
//                xuLyNhapHang(dsLoaiThietBi,soLuongLoaiThietBi);
//        	}
//        });
//        goodsBTN.setBounds(23, 164, 300, 50);
//        goodsBTN.setFocusPainted(false);
//        goodsBTN.setIcon(new ImageIcon(scaleGoodsIcon));

//        managementPanel.add(goodsBTN);
        
//        //chức năng thống kê đơn hàng
//        JButton statBTN = new JButton("Thống kê đơn hàng");
//        statBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
//        statBTN.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        		BLLThongKeDonHang bllThongKeDonHang = new BLLThongKeDonHang();
//                ArrayList<DTOThongKeDonHang> ds = bllThongKeDonHang.layDSDLoc("NULL", "NULL", "2024-01-01", "2025-01-01");
//                Vector<String> dsTenCoSo = new Vector<>();
//                dsTenCoSo = bllThongKeDonHang.DSMaCoSo();
//                thongKe TK = new thongKe();
//                TK.thongKeTheoSoLuong(ds,dsTenCoSo,"Theo doanh thu",rightPanel );
//        	}
//        });
//        statBTN.setBounds(23, 164, 300, 50);
//        statBTN.setFocusPainted(false);
//        statBTN.setIcon(new ImageIcon(scaleChartIcon));
//        managementPanel.add(statBTN);

        
        JButton QuanLyThietBi = new JButton("Quản lý hàng hóa");
        QuanLyThietBi.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
                QuanLyThietBiCTR newPanel = new QuanLyThietBiCTR();
                rightPanel.add(newPanel);
        	}
        });
        QuanLyThietBi.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        QuanLyThietBi.setBounds(23,160,300,50);
        QuanLyThietBi.setIcon(new ImageIcon(scaleDevicesIcon));
        QuanLyThietBi.setFocusPainted(false);
        managementPanel.add(QuanLyThietBi);
        
        //chức năng phân quyền
        JButton delegationBTN = new JButton("Phân quyền");
        delegationBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
        		new delegateCTR(rightPanel);
        	}
        });
        delegationBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        delegationBTN.setBounds(23,220,300,50);
        delegationBTN.setIcon(new ImageIcon(scalePermissionIcon));
        delegationBTN.setFocusPainted(false);
        managementPanel.add(delegationBTN);
        
        //quản lý nhân viên
        JButton employeeMNG = new JButton("Quản lý nhân viên");
        employeeMNG.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
        		BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
        		ArrayList<NhanVien> dsNV = new ArrayList<>();
        		ArrayList<DTOQuyen> dsQuyen = bllQuanLyDanhSach.layDSQuyenNV();
                dsNV = bllQuanLyDanhSach.getDataNhanVien();
                ArrayList<DTOTaiKhoan>dsTKNV = bllQuanLyDanhSach.layDSTKNV();
                QuanLyBangNhanVienCTR qlbnvCTR = new QuanLyBangNhanVienCTR();
                qlbnvCTR.QuanLyBangNhanVien(dsNV,dsTKNV,dsQuyen, rightPanel, tk, coSoHienTai);
        	}
        });
        employeeMNG.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        employeeMNG.setBounds(23, 280, 300, 50);
        employeeMNG.setIcon(new ImageIcon(scaleEmployeeIcon));
        employeeMNG.setFocusPainted(false);
        managementPanel.add(employeeMNG);
        
        //quản lý hội viên
        JButton memberMNG = new JButton("Quản lý hội viên");
        memberMNG.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
//        		BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
//        		ArrayList<HoiVienCoSo> ds = new ArrayList<>();
//                Vector<String> dsCoSo = new Vector<>();
//                dsCoSo = bllQuanLyDanhSach.layDSMaCoSo();
//                ds = bllQuanLyDanhSach.layDSHoiVienCoSo();
//                QuanLyHoiVienCoSoCTR qlhvcsCTR = new QuanLyHoiVienCoSoCTR();
//                qlhvcsCTR.QuanLyHoiVienCoSo(ds,dsCoSo,rightPanel);
//                
                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane();
                JPanel bangChinhSua = new JPanel();
                
                BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();

                ArrayList<String> tenCotHV = new ArrayList<String>();
                ArrayList<HoiVien> dsHV = bllQuanLyDanhSach.getDataHoiVien();
                tenCotHV.add("Mã hội viên");
                tenCotHV.add("Họ tên hội viên");
                tenCotHV.add("Giới tính");
                tenCotHV.add("Gmail");
                tenCotHV.add("Mã Tài khoản");
                tenCotHV.add("Số điện thoại");
                tenCotHV.add("Ngày sinh");
                tenCotHV.add("Tài khoản");
                tenCotHV.add("Mật khẩu");
                tenCotHV.add("Ảnh đại diện");
                hoiVienCTR hvCTR = new hoiVienCTR(rightPanel,tenCotHV,dsHV,bangChinhSua,dataTable,scrollPane,bllQuanLyDanhSach);
                hvCTR.update();
        	}
        });
        memberMNG.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        memberMNG.setBounds(23, 340, 300, 50);
        memberMNG.setIcon(new ImageIcon(scaleMembershipIcon));
        memberMNG.setFocusPainted(false);
        managementPanel.add(memberMNG);
        
        // //chức năng mua hàng
        // JButton buyBTN = new JButton("Mua Hàng");
        // buyBTN.addActionListener(new ActionListener() {
        // 	public void actionPerformed(ActionEvent e) {
        // 		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
        //         rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
        //         rightPanel.repaint(); // Vẽ lại JPanel
        // 		rightPanel.setLayout(null);
        //         rightPanel.add(new MuaHangCTR("TK082")); //id tài khoản admin
        // 	}
        // });
        // buyBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        // buyBTN.setBounds(23, 400, 300, 50);
        // buyBTN.setFocusPainted(false);
        // buyBTN.setIcon(new ImageIcon(scaleCartIcon));
        // managementPanel.add(buyBTN);
        
        JButton purchaseOrderBTN = new JButton("Duyệt phiếu nhập");
        purchaseOrderBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                BLLDonNhap bllDonNhap=new BLLDonNhap();
                BLLQuanLyDanhSach bllQuanLyDanhSach=new BLLQuanLyDanhSach();
                ArrayList<DonNhap> ds = bllDonNhap.layDsDonNhap(coSoHienTai);
                xulyDDNCTR xulyDDNCTR=new xulyDDNCTR();
                xulyDDNCTR.XuLyDuyetDonNhap(ds, bllDonNhap, bllQuanLyDanhSach, rightPanel, coSoHienTai);
            }
        });
        purchaseOrderBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        purchaseOrderBTN.setBounds(23, 520, 300, 50);
        purchaseOrderBTN.setIcon(new ImageIcon(scaleBillIcon));
        purchaseOrderBTN.setFocusPainted(false);
        managementPanel.add(purchaseOrderBTN);
        
//        JButton informationBTN = new JButton("Thông tin cá nhân");
//        informationBTN.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//				rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
//                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
//                rightPanel.repaint(); // Vẽ lại JPanel
//        		rightPanel.setLayout(null);
//        		informationCTR inforCTR = new informationCTR(new DTOTaiKhoan("TK082", "admin", "admin", "Q0004"));
//        		rightPanel.add(inforCTR);
//			}
//        });
//        informationBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
//        informationBTN.setBounds(23, 590, 300, 50);
//        informationBTN.setFocusPainted(false);
//        managementPanel.add(informationBTN);
        
      //xuất file excel
        JButton XuatExcelBTN = new JButton("Xuất file danh sách");
        XuatExcelBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
        		ExcelCTR excelCTR = new ExcelCTR();
        		rightPanel.add(excelCTR);
			}
        });
        XuatExcelBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        XuatExcelBTN.setBounds(23, 400, 300, 50);
        XuatExcelBTN.setFocusPainted(false);
        XuatExcelBTN.setIcon(new ImageIcon(scaleExcelIcon));
        managementPanel.add(XuatExcelBTN);
        
        JButton importExcelBTN = new JButton("Nhập file danh sách");
        importExcelBTN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                rightPanel.setLayout(null);
                ImportExcelCTR importExcelCTR = new ImportExcelCTR();
                rightPanel.add(importExcelCTR);
            }
        });
        importExcelBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        importExcelBTN.setIcon(new ImageIcon(scaleExcelIcon));
        importExcelBTN.setFocusPainted(false);
        importExcelBTN.setBounds(23, 460, 300, 50);
        managementPanel.add(importExcelBTN);
        
        JButton statisticalBTN = new JButton("Thống kê");
        statisticalBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                rightPanel.setLayout(null);
        		thongKeCTR tkCTR = new thongKeCTR();
        		rightPanel.add(tkCTR);
        		System.out.println("CMM");
        	}
        });
        statisticalBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        statisticalBTN.setBounds(23, 580, 300, 50);
        statisticalBTN.setIcon(new ImageIcon(scaleChartIcon));
        statisticalBTN.setFocusPainted(false);
        managementPanel.add(statisticalBTN);
       
        //chức năng nhập hàng
        // JButton importgoods = new JButton("Nhập hàng");
        // importgoods.addActionListener(new ActionListener() {
        // 	public void actionPerformed(ActionEvent e){
        //         nhapHang nhapHang=new nhapHang();
        //         BLLNhapHang bllNhapHang=new BLLNhapHang();
        //         nhapHang.xulyNhapHang(tk, bllNhapHang, rightPanel, coSoHienTai);
        // 	}
        // });
        // importgoods.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        // importgoods.setIcon(new ImageIcon(scaleEmployeeIcon));
        // importgoods.setFocusPainted(false);
        // importgoods.setBounds(23, 590, 300, 50);
        // managementPanel.add(importgoods);
        
        leftPanel.add(footerLeft);

        //right panel
        rightPanel.setBounds(400,0,1200,900);
        rightPanel.setBackground(new Color(241, 255, 250));
        rightPanel.setBorder(border);
        //giới thiệu app
        JPanel introPn = new JPanel();
        introPn.setPreferredSize(new Dimension(rightPanel.getWidth()-300,700));
        introPn.setBackground(new Color(241, 255, 250));
        // introPn.setLayout(null);
        JLabel logo = new JLabel();
        logo.setIcon(new ImageIcon(scaleLogoIcon1));
        // logo.setBounds((int)(rightPanel.getWidth()*50/100-300/2),0,300,300);
        // introParam.setBounds((int)(rightPanel.getWidth()*50/100-introParam.getWidth()/2),320,(int)(width*70/100),45);

        introPn.add(logo);
        rightPanel.add(introPn);
        //thêm đối tượng
        mainPanel.add(leftPanel);
        
        JButton logOutBTN = new JButton("Đăng xuất");
        logOutBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int result = JOptionPane.showConfirmDialog(mainPanel, "Bạn muốn đăng xuất chứ?");
        		if(result == 0) {
                    adminFrame.dispose();
                    new GUILogin();
        		}
        		else {
        			return;
        		}
        	}
        });
        logOutBTN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        logOutBTN.setBounds(27, 796, 146, 37);
        leftPanel.add(logOutBTN);
        
        
        
        JLabel currUserLB = new JLabel("Người dùng hiện tại: Admin" );
        currUserLB.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        currUserLB.setBounds(25, 212, 352, 26);
        leftPanel.add(currUserLB);
        
        JLabel leftLabel = new JLabel();
        leftLabel.setForeground(new Color(204, 252, 203));
        leftLabel.setBackground(new Color(150, 230, 179));
        leftLabel.setIcon(new ImageIcon("src/asset/img/label/logo1.png"));
        leftLabel.setBounds(65, 0, 312, 214);
        leftPanel.add(leftLabel);
        mainPanel.add(rightPanel);
        adminFrame.getContentPane().add(mainPanel);

        adminFrame.setVisible(true);
    }
}