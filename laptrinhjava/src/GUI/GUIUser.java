package GUI;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DTO.DTOChucNang;
import DTO.DTOQuyen;
import DTO.DTOTaiKhoan;
import DTO.DonNhap;
import DTO.HoiVien;
import DTO.NhanVien;
import GUI.CONTROLLER.DuyetDonHangCTR;
import GUI.CONTROLLER.ExcelCTR;
import GUI.CONTROLLER.GUIXemDonHang;
import GUI.CONTROLLER.ImportExcelCTR;
import GUI.CONTROLLER.MuaHangCTR;
import GUI.CONTROLLER.QuanLyBangNhanVienCTR;
import GUI.CONTROLLER.QuanLyThietBiCTR;
import GUI.CONTROLLER.delegateCTR;
import GUI.CONTROLLER.hoiVienCTR;
import GUI.CONTROLLER.informationCTR;
import GUI.CONTROLLER.nhapHang;
import GUI.CONTROLLER.thongKeCTR;
import GUI.CONTROLLER.xuLyDSCTR;
import GUI.CONTROLLER.xulyDDNCTR;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import BLL.BLLDangNhap;
import BLL.BLLDonNhap;
import BLL.BLLNhapHang;
import BLL.BLLPhanQuyen;
import BLL.BLLQuanLyDanhSach;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class GUIUser extends JFrame {
	private static final long serialVersionUID = -285217257856323089L;
	//logo
    ImageIcon logo = new ImageIcon("src/asset/img/label/logo.png");
    Image scaleLogoIcon = logo.getImage().getScaledInstance(300, 300,Image.SCALE_DEFAULT);
    ImageIcon logo1 = new ImageIcon("src/asset/img/label/logo1.png");
    Image scaleLogoIcon1 = logo1.getImage().getScaledInstance(500, 500,Image.SCALE_DEFAULT);
    
    //icon chức năng thống kê
    ImageIcon analyticsIcon = new ImageIcon("src/asset/img/icon/analytics-icon.png");
    Image scaleAnalyticsIcon = analyticsIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //logo excel
    ImageIcon excelIcon = new ImageIcon("src/asset/img/icon/excel-icon.png");
    Image scaleExcelIcon = excelIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng xem thông tin cá nhân
    ImageIcon inforIcon = new ImageIcon("src/asset/img/icon/infor-icon.png");
    Image scaleInforIcon = inforIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
    //icon chức năng mua hàng
    ImageIcon cartIcon = new ImageIcon("src/asset/img/icon/cart-icon.png");
    Image scaleCartIcon = cartIcon.getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT);
    
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
    
    public BLLDangNhap bllDangNhap = new BLLDangNhap();

    public GUIUser(DTOTaiKhoan tk, String coSoHienTai) {
		this.setSize(1600, 900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().setLayout(null);
        // Thêm WindowListener để theo dõi sự kiện đóng cửa sổ
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Xử lý khi JFrame đang được đóng
                dispose();
                new GUILogin();
            }
        });

        BLLPhanQuyen bllPhanQuyen = new BLLPhanQuyen();    
        
        //danh sách các nút chức năng
        ArrayList<JButton> dsNut = new ArrayList<JButton>();
        
		//danh sách các chức năng mà user có thể dùng
        ArrayList<DTOChucNang> dsCNUser = bllPhanQuyen.layDsCNTheoIDQuyen(tk.getIDQuyen());
        
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(241, 255, 250));
        rightPanel.setBorder(new LineBorder(new Color(64, 0, 64), 2));
        rightPanel.setBounds(400, 0, 1200, 900);
        
        JButton listBTN = new JButton("Quản lý danh sách");
        listBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                xuLyDSCTR XLDSCtrl = new xuLyDSCTR();
                XLDSCtrl.xuLyDanhSach(rightPanel);
        	}
        });
        listBTN.setSelectedIcon(null);
        listBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        listBTN.setFocusPainted(false);
        listBTN.setIcon(new ImageIcon(scaleCheckListIcon));
        dsNut.add(listBTN);
//        listBTN.setBounds(23, 42, 300, 50);
//        managementPanel.add(listBTN);
        
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
        billBTN.setBounds(23, 103, 300, 50);
        billBTN.setIcon(new ImageIcon(scaleBillIcon));
        billBTN.setFocusPainted(false);
        dsNut.add(billBTN);
        JButton btXemGioHang = new JButton("Xem giỏ hàng");
        btXemGioHang.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        btXemGioHang.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                rightPanel.setLayout(null);
                GUIGioHang GioHang  = new GUIGioHang(tk.getIDTaiKhoan());
                rightPanel.add(GioHang);
        	}
        });
        btXemGioHang.setBounds(23, 103, 300, 50);
        btXemGioHang.setIcon(new ImageIcon(scaleBillIcon));
        btXemGioHang.setFocusPainted(false);
        dsNut.add(btXemGioHang);
//        billBTN.setBounds(23, 103, 300, 50);
//        managementPanel.add(billBTN);

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
        purchaseOrderBTN.setFocusPainted(false);
        purchaseOrderBTN.setIcon(new ImageIcon(scaleBillIcon));
        dsNut.add(purchaseOrderBTN);
        JButton btXemDonHang = new JButton("Xem đơn hàng");
        btXemDonHang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIXemDonHang guiXemDonHang = new GUIXemDonHang(tk.getIDTaiKhoan());
                rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
                rightPanel.setLayout(null);
                rightPanel.add(guiXemDonHang);
            }
        });
        btXemDonHang.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        btXemDonHang.setFocusPainted(false);
        btXemDonHang.setIcon(new ImageIcon(scaleBillIcon));
        dsNut.add(btXemDonHang);

//         JButton goodsBTN = new JButton("Nhập thiết bị");
//         goodsBTN.addActionListener(new ActionListener() {
//         	public void actionPerformed(ActionEvent e) {
//         		BLLNhapThietBi bllNhapThietBi = new BLLNhapThietBi();
//                 DSLoaiThietBi dsLoaiThietBi = new DSLoaiThietBi();
//                 dsLoaiThietBi = bllNhapThietBi.layDSLoaiThietBi();
//                 int soLuongLoaiThietBi = dsLoaiThietBi.dsThietBi.size();
// //                xuLyNhapHang(dsLoaiThietBi,soLuongLoaiThietBi);
//         	}
//         });
//         goodsBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
//         goodsBTN.setFocusPainted(false);
//         goodsBTN.setIcon(new ImageIcon(scaleGoodsIcon));
//         dsNut.add(goodsBTN);
//        goodsBTN.setBounds(23, 164, 300, 50);
//        managementPanel.add(goodsBTN);
        
//        JButton statBTN = new JButton("Thống kê đơn hàng");
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
//        statBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
//        statBTN.setFocusPainted(false);
//        statBTN.setIcon(new ImageIcon(scaleChartIcon));
//        dsNut.add(statBTN);
//        statBTN.setBounds(23, 225, 300, 50);
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
        QuanLyThietBi.setIcon(new ImageIcon(scaleDevicesIcon));
        dsNut.add(QuanLyThietBi);
//        QuanLyThietBi.setBounds(23, 408, 300, 50);
//        managementPanel.add(QuanLyThietBi);
        
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
        delegationBTN.setIcon(new ImageIcon(scalePermissionIcon));
        delegationBTN.setFocusPainted(false);
        dsNut.add(delegationBTN);
//        delegationBTN.setBounds(23, 408, 300, 50);
//        managementPanel.add(delegationBTN);
        
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
                qlbnvCTR.QuanLyBangNhanVien(dsNV,dsTKNV,dsQuyen, rightPanel,tk, coSoHienTai);
        	}
        });
        employeeMNG.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        employeeMNG.setIcon(new ImageIcon(scaleEmployeeIcon));
        employeeMNG.setFocusPainted(false);
        dsNut.add(employeeMNG);
//        employeeMNG.setBounds(23, 286, 300, 50);
//        managementPanel.add(employeeMNG);
        
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
                tenCotHV.add("Hình ảnh");
                hoiVienCTR hvCTR = new hoiVienCTR(rightPanel,tenCotHV,dsHV,bangChinhSua,dataTable,scrollPane,bllQuanLyDanhSach);
                hvCTR.update();
        	}
        });
        memberMNG.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        memberMNG.setIcon(new ImageIcon(scaleMembershipIcon));
        memberMNG.setFocusPainted(false);
        dsNut.add(memberMNG);
        
        //chức năng xem thông tin cá nhân
        JButton showInforBTN = new JButton("Thông tin cá nhân");
        showInforBTN.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
        		informationCTR inforCTR = new informationCTR(tk);
        		rightPanel.add(inforCTR);
			}
		});
        showInforBTN.setFont(new Font("Times New Roman", Font.BOLD, 23));
        showInforBTN.setFocusPainted(false);
        showInforBTN.setIcon(new ImageIcon(scaleInforIcon));
        dsNut.add(showInforBTN);
//        memberMNG.setBounds(23, 347, 300, 50);
//        managementPanel.add(memberMNG);
        
        //chức năng mua hàng
        JButton buyBTN = new JButton("Mua hàng");
        buyBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		rightPanel.removeAll(); // Xóa tất cả các thành phần con khỏi JPanel
                rightPanel.revalidate(); // Cập nhật lại JPanel để hiển thị thay đổi
                rightPanel.repaint(); // Vẽ lại JPanel
        		rightPanel.setLayout(null);
                rightPanel.add(new MuaHangCTR(tk.getIDTaiKhoan()));
        	}
        });
        buyBTN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        buyBTN.setFocusPainted(false);
        buyBTN.setIcon(new ImageIcon(scaleCartIcon));
        dsNut.add(buyBTN);
        
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
        XuatExcelBTN.setBounds(23, 460, 300, 50);
        XuatExcelBTN.setFocusPainted(false);
        XuatExcelBTN.setIcon(new ImageIcon(scaleExcelIcon));
        dsNut.add(XuatExcelBTN);

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
        dsNut.add(importExcelBTN);
        
        JButton importgoods = new JButton("Nhập hàng");
        importgoods.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
                nhapHang nhapHang=new nhapHang();
                BLLNhapHang bllNhapHang=new BLLNhapHang();
                nhapHang.xulyNhapHang(tk, bllNhapHang, rightPanel, coSoHienTai);
        	}
        });
        importgoods.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        importgoods.setIcon(new ImageIcon(scaleEmployeeIcon));
        importgoods.setFocusPainted(false);
        dsNut.add(importgoods);
        
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
        dsNut.add(statisticalBTN);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBorder(new LineBorder(new Color(64, 0, 64), 2));
        leftPanel.setBackground(new Color(0, 191, 91));
        leftPanel.setBounds(0, 0, 400, 900);
        getContentPane().add(leftPanel);
        leftPanel.setLayout(null);
        
        JLabel currUserLB = new JLabel("Người dùng hiện tại: " + bllPhanQuyen.kiemTenUser(tk));
        currUserLB.setBounds(27, 201, leftPanel.getWidth(), 26);
        currUserLB.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        leftPanel.add(currUserLB);
        
        JLabel leftLabel = new JLabel();
        leftLabel.setBounds(49, 0, 300, 200);
        leftLabel.setIcon(new ImageIcon("src/asset/img/label/logo1.png"));
        leftPanel.add(leftLabel);
        
        JButton logOutBTN = new JButton("Đăng xuất");
        logOutBTN.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int result = JOptionPane.showConfirmDialog(null, "Bạn muốn đăng xuất chứ?");
        		if(result == 0) {
                    dispose();
                    new GUILogin();
        		}
        		else {
        			return;
        		}
        	}
        });
        logOutBTN.setBounds(27, 796, 146, 37);
        leftPanel.add(logOutBTN);
        logOutBTN.setFont(new Font("Times New Roman", Font.PLAIN, 17));
        
        JPanel managementPanel = new JPanel();
        managementPanel.setBounds(26, 238, 352, 547);
        managementPanel.setLayout(null);
        managementPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
        		"Chức năng", TitledBorder.LEADING, TitledBorder.TOP, new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30), new Color(70, 78, 71)));
        managementPanel.setBackground(new Color(204, 252, 203));
        
        btnGenerate(dsNut, dsCNUser, managementPanel,leftPanel);
        for(int i =0; i<dsCNUser.size();i++)
        	System.out.println(dsCNUser.get(i).getiDChucNang());

        getContentPane().add(rightPanel);
        rightPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        
        JPanel introPn = new JPanel();
        introPn.setPreferredSize(new Dimension(900, 700));
        introPn.setBackground(new Color(241, 255, 250));
        
        JLabel introLB = new JLabel();
        introLB.setIcon(new ImageIcon(scaleLogoIcon1));
        introPn.add(introLB);
        
        rightPanel.add(introPn);
        
        JLabel logo_1 = new JLabel();
        introPn.add(logo_1);
        this.setIconImage(logo.getImage());
        this.setVisible(true);
	}
    private void btnGenerate(ArrayList<JButton> dsNut, ArrayList<DTOChucNang> dsCNUser, JPanel managementPanel, JPanel leftPanel) {
    	int x = 20;
    	int y = 40;
    	for(DTOChucNang cNang : dsCNUser) {
    		for(JButton btn : dsNut) {
    			if(cNang.getTenChucNang().trim().equals(btn.getText().trim())) {
    				btn.setBounds(x, y, 300, 50);
    				System.out.println(btn.getText());
    				managementPanel.add(btn);
    			}
    		}
    		y+=60;
    	}
    	JScrollPane scrollPane = new JScrollPane(managementPanel);
        scrollPane.setBounds(26,238,352,547);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); 
        managementPanel.setPreferredSize(new Dimension(300,y));
        leftPanel.add(scrollPane);
    }
}
