package GUI.CONTROLLER;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


import BLL.BLLQuanLyDanhSach;
import DTO.DTOQuyen;
import DTO.DTOTaiKhoan;
import DTO.NhanVien;

public class QuanLyBangNhanVienCTR {
	private JTextField jtf_manv;
	private JTextField jtf_hoten;
	private ButtonGroup btngr;
	private JTextField jtf_sdt;
	private JTextField jtf_cccd;
	private JTextField jtf_luong;
	private JTextField jtf_account;
	private JTextField jtf_password;
	private JTextField jtf_idAccount;

	private JComboBox<String> cbb_vaiTro;
	private JComboBox<String> cbb_CoSo; 
	private JComboBox<Integer> dayCBB;
	private JComboBox<Integer> monthCBB;
	private JComboBox<Integer> yearCBB;
	
	
	private Font italicBoldFont = new Font("Times New Roman", Font.ITALIC | Font.BOLD, 30); //vừa nghiêng vừa in đậm

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
	public void QuanLyBangNhanVien(ArrayList<NhanVien> dsNV, ArrayList<DTOTaiKhoan> dsTKNV,ArrayList<DTOQuyen> dsQuyen, JPanel rightPanel,DTOTaiKhoan tk, String coSoHienTai) {
    	rightPanel.setBackground(new Color(241,255,250));
		BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
    	xoaHienThi(rightPanel);
    	JLabel title = new JLabel("Quản lý nhân viên");
    	title.setFont(new Font("Times New Roman", Font.ITALIC | Font.BOLD, 35));
    	title.setBounds(450, 0, 1000,60);      
    	rightPanel.add(title);
    	Font f = new Font("Times New Roman",Font.BOLD,17);
    	JButton them = new JButton();
        ImageIcon themBtnImg = new ImageIcon("src/asset/img/button/them-hv.png");
        Image scaleThemBtnImg = themBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        them.setPreferredSize(new Dimension (130,35));
        them.setIcon(new ImageIcon(scaleThemBtnImg));
        them.setHorizontalAlignment(SwingConstants.CENTER);
        them.setBorder(null);

    	JButton sua = new JButton();
        sua.setPreferredSize(new Dimension (110,35));
        ImageIcon suaBtnImg = new ImageIcon("src/asset/img/button/sua-hv.png");
        Image scaleSuaBtnImg = suaBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        sua.setPreferredSize(new Dimension (130,35));
        sua.setIcon(new ImageIcon(scaleSuaBtnImg));
        sua.setHorizontalAlignment(SwingConstants.CENTER);
        sua.setBorder(null);

    	JButton timkiem = new JButton();
        timkiem.setPreferredSize(new Dimension (110,35));
        ImageIcon timKiemBtnImg = new ImageIcon("src/asset/img/button/tim-hv.png");
        Image scaletimKiemBtnImg = timKiemBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        timkiem.setPreferredSize(new Dimension (130,35));
        timkiem.setIcon(new ImageIcon(scaletimKiemBtnImg));
        timkiem.setHorizontalAlignment(SwingConstants.CENTER);
        timkiem.setBorder(null);

    	JButton reset  = new JButton();
        reset.setPreferredSize(new Dimension (110,35));
        ImageIcon resetBtnImg = new ImageIcon("src/asset/img/button/reset-hv.png");
        Image scaleXoaBtnImg = resetBtnImg.getImage().getScaledInstance(130,35,Image.SCALE_DEFAULT);
        reset.setPreferredSize(new Dimension (130,35));
        reset.setIcon(new ImageIcon(scaleXoaBtnImg));
        reset.setHorizontalAlignment(SwingConstants.CENTER);
        reset.setBorder(null);
        
    	JPanel chucnang = new JPanel(new FlowLayout());
    	chucnang.add(them);
    	chucnang.add(sua);
    	chucnang.add(timkiem);
    	chucnang.add(reset);
    	chucnang.setBounds(5,100,rightPanel.getWidth()-5,40);
        chucnang.setBackground(new Color(241,255,250));
        rightPanel.add(chucnang);
        
        JLabel jlb_manv = new JLabel("Mã nhân viên: ");
        jtf_manv = new JTextField();
        jlb_manv.setFont(f);
        
        JLabel jlb_hoten = new JLabel("Họ và tên: ");
        jtf_hoten = new JTextField();
        jlb_hoten.setFont(f);
        
        JLabel jlb_gioitinh = new JLabel("Giới tính: ");
        JRadioButton male = new JRadioButton("Nam");
        male.setBackground(new Color(119, 230, 163));
        JRadioButton female = new JRadioButton("Nữ");
        female.setBackground(new Color(119, 230, 163));
        jlb_gioitinh.setFont(f);
        male.setFont(f);
        female.setFont(f);
        btngr = new ButtonGroup();
        btngr.add(male);
        btngr.add(female);
        
        JLabel jlb_account = new JLabel("Tài khoản:");
        jtf_account = new JTextField();
        jlb_account.setFont(f);
        
        JLabel jlb_password = new JLabel("Mật khẩu:");
        jtf_password = new JTextField();
        jlb_password.setFont(f);
        
        JLabel jlb_idAccount = new JLabel("ID Tài Khoản:");
        jtf_idAccount = new JTextField();
        jtf_idAccount.setEditable(false);
        jlb_idAccount.setFont(f);
        
        JLabel jlb_date = new JLabel("Ngày sinh: ");
        jlb_date.setFont(f);
        dayCBB = new JComboBox<Integer>();
        for(int day=1; day<=31 ;day++){
            dayCBB.addItem(day);
        }

        dayCBB.setFont(f);
        dayCBB.setBackground(Color.white);
        dayCBB.setName("Day");
        
        monthCBB = new JComboBox<Integer>();
        for(int month=1; month<=12 ;month++){
            monthCBB.addItem(month);
        }
        monthCBB.setFont(f);
        monthCBB.setBackground(Color.white);
        monthCBB.setName("Month");
        
        yearCBB = new JComboBox<Integer>();
        for(int year=2024;year>=1900;year--){
            yearCBB.addItem(year);
        }
        yearCBB.setFont(f);
        yearCBB.setBackground(Color.white);
        yearCBB.setName("Year");
        
     // Listener thay đổi tháng và năm để cập nhật ngày
        ActionListener updateDaysListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDays(dayCBB, monthCBB, yearCBB);
            }
        };
        monthCBB.addActionListener(updateDaysListener);
        yearCBB.addActionListener(updateDaysListener);
        
        JLabel jlb_sdt = new JLabel("Số điện thoại: ");
        jtf_sdt = new JTextField();
        jlb_sdt.setFont(f);
        
        JLabel jlb_cccd = new JLabel("Căn cước: ");
        jtf_cccd = new JTextField();
        jlb_cccd.setFont(f);
        
        JLabel jlb_macoso = new JLabel("Mã cơ sở: ");
        cbb_CoSo = new JComboBox<String>();
//        DSCoSo dsCS = bllQuanLyDanhSach.layDsCoSo();
        String[] dsCS = {"CS001", "CS002", "CS003"};
        cbb_CoSo.addItem("Cơ sở");
        for(String cs: dsCS) {
        	cbb_CoSo.addItem(cs);
        }
        if(tk.getIDQuyen().equals("Q0002") || tk.getIDQuyen().equals("Q0003")) {
        	cbb_CoSo.setSelectedItem(coSoHienTai);
        	cbb_CoSo.setEnabled(false);
        }
        jlb_macoso.setFont(f);
        
        JLabel jlb_vaitro = new JLabel("Vai trò: ");
        ArrayList<String> dsTenQuyen = bllQuanLyDanhSach.layDSTenQuyenNV();
        cbb_vaiTro = new JComboBox<>();
        cbb_vaiTro.addItem("Vai trò");
        for(String a : dsTenQuyen) {
        	cbb_vaiTro.addItem(a.trim());
        }
        jlb_vaitro.setFont(f);
        
        JLabel jlb_luong = new JLabel("Lương: ");
        jtf_luong = new JTextField();
        jlb_luong.setFont(f);
        
        JPanel nhapLieu = new JPanel(null);
        nhapLieu.setBounds(2, 175, rightPanel.getWidth()-20, 175);
        Border blackBorder = BorderFactory.createLineBorder(Color.BLACK);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(blackBorder,"Nhập liệu");
        titledBorder.setTitleFont(italicBoldFont);
        nhapLieu.setBorder(titledBorder);
        nhapLieu.setBackground(new Color(119, 230, 163));

        jlb_manv.setBounds(10, 50, 120, 30); 
        jtf_manv.setBounds(130, 50, 120, 30); 
        jlb_hoten.setBounds(280, 50, 120, 30); 
        jtf_hoten.setBounds(380, 50, 120, 30); 
        jlb_gioitinh.setBounds(530, 50, 90, 30);
        male.setBounds(630, 50, 70, 30); 
        female.setBounds(720, 50, 60, 30); 
        jlb_date.setBounds(790, 50, 90, 30); 
        dayCBB.setBounds(890,50,60,30);
        monthCBB.setBounds(990,50,75,30);
        yearCBB.setBounds(1090,50,75,30);
        
        jlb_sdt.setBounds(10,100,120,30); 
        jtf_sdt.setBounds(130, 100, 120, 30);
        jlb_cccd.setBounds(280,100,120,30);
        jtf_cccd.setBounds(380,100,120,30);
        jlb_macoso.setBounds(530,100,90,30);
        cbb_CoSo.setBounds(630,100,120,30);
        jlb_vaitro.setBounds(790,100,90,30);
        cbb_vaiTro.setBounds(890,100,120,30);
        
        jlb_luong.setBounds(10,140,90,30);
        jtf_luong.setBounds(130,140,120,30);
        jlb_account.setBounds(280,140,90,30);
        jtf_account.setBounds(380,140,120,30);
        jlb_password.setBounds(530,140,90,30);
        jtf_password.setBounds(630,140,120,30);
        jlb_idAccount.setBounds(790,140,120,30);
        jtf_idAccount.setBounds(890,140,120,30);
        
        nhapLieu.add(jlb_manv);
        nhapLieu.add(jtf_manv);
        nhapLieu.add(jlb_hoten);
        nhapLieu.add(jtf_hoten);
        nhapLieu.add(jlb_gioitinh);
        nhapLieu.add(male);
        nhapLieu.add(female);
        nhapLieu.add(jlb_date);
        nhapLieu.add(dayCBB);
        nhapLieu.add(monthCBB);
        nhapLieu.add(yearCBB);
        nhapLieu.add(jlb_sdt);
        nhapLieu.add(jtf_sdt);
        nhapLieu.add(jlb_cccd);
        nhapLieu.add(jtf_cccd);
        nhapLieu.add(jlb_macoso);
        nhapLieu.add(cbb_CoSo);
        nhapLieu.add(jlb_vaitro);
        nhapLieu.add(cbb_vaiTro);
        nhapLieu.add(jlb_luong);
        nhapLieu.add(jtf_luong);
        nhapLieu.add(jlb_account);
        nhapLieu.add(jtf_account);
        nhapLieu.add(jlb_password);
        nhapLieu.add(jtf_password);
        nhapLieu.add(jlb_idAccount);
        nhapLieu.add(jtf_idAccount);
        
        rightPanel.add(nhapLieu);
        
        DefaultTableModel model = new DefaultTableModel();
        JTable bang = new JTable();
        bang.setRowHeight(30);
        model.addColumn("Mã nhân viên");
        model.addColumn("Họ và tên");
        model.addColumn("Giới tính");
        model.addColumn("Ngày sinh");
        model.addColumn("Số điện thoại");
        model.addColumn("Số căn cước");
        model.addColumn("Mã cơ sở");
        model.addColumn("Vai trò");
        model.addColumn("Lương");
        model.addColumn("Tài khoản");
        model.addColumn("Mật khẩu");
        model.addColumn("ID Tài Khoản");
        
        model.setRowCount(0);
        
        for(int i = 0; i < dsNV.size();i++) {
        	if(coSoHienTai.equals(dsNV.get(i).getMacoso())&&(tk.getIDQuyen().equals("Q0002") || tk.getIDQuyen().equals("Q0003"))) {
        		model.addRow(new Object[] {
        				dsNV.get(i).getMaNhanVien(),dsNV.get(i).getHoten().trim(),dsNV.get(i).getGioitinh(),dsNV.get(i).getNgaysinh(),
        				dsNV.get(i).getSdt(),dsNV.get(i).getSocccd(),dsNV.get(i).getMacoso(),dsQuyen.get(i).getTenQuyen().trim(),
        				dsNV.get(i).getLuong(),dsTKNV.get(i).getTaiKhoan().trim(), dsTKNV.get(i).getMatKhau().trim(),dsNV.get(i).getIDTaiKhoan()
        		});
        	}
        	else if(tk.getIDQuyen().equals("Q0004")){
        		model.addRow(new Object[] {
        				dsNV.get(i).getMaNhanVien(),dsNV.get(i).getHoten().trim(),dsNV.get(i).getGioitinh(),dsNV.get(i).getNgaysinh(),
        				dsNV.get(i).getSdt(),dsNV.get(i).getSocccd(),dsNV.get(i).getMacoso(),dsQuyen.get(i).getTenQuyen().trim(),
        				dsNV.get(i).getLuong(),dsTKNV.get(i).getTaiKhoan().trim(), dsTKNV.get(i).getMatKhau().trim(),dsNV.get(i).getIDTaiKhoan()
        		});
        	}
        }
        bang.setModel(model);
        bang.getTableHeader().setReorderingAllowed(false);
        bang.setFont(new Font("Times New Roman", Font.BOLD, 14));
        bang.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = bang.getSelectedRow();
				if(i >= 0) {
					jtf_manv.setText(model.getValueAt(i, 0).toString().trim());
					jtf_hoten.setText(model.getValueAt(i, 1).toString().trim());
					String gioitinh = (model.getValueAt(i, 2).toString().trim());
					if(gioitinh.equals("Nam")) {
						male.setSelected(true);
					}else {
						female.setSelected(true);
					}
                     String dateString = model.getValueAt(i,3).toString();
                     String[] parts = dateString.split("-");
                     int year = Integer.parseInt(parts[0]);
                     int month = Integer.parseInt(parts[1]);
                     int day = Integer.parseInt(parts[2]);
                     if("Day".equals(dayCBB.getName())){
                         dayCBB.setSelectedItem(day);
                     }

                     if ("Month".equals(monthCBB.getName())){
                         monthCBB.setSelectedItem(month);
                     }

                     if ("Year".equals(yearCBB.getName())){
                         yearCBB.setSelectedItem(year);
                     }
					jtf_sdt.setText(model.getValueAt(i, 4).toString().trim());
					jtf_cccd.setText(model.getValueAt(i, 5).toString().trim());
					cbb_CoSo.setSelectedItem(model.getValueAt(i, 6).toString().trim());
					cbb_vaiTro.setSelectedItem(model.getValueAt(i, 7).toString().trim());
					jtf_luong.setText(model.getValueAt(i, 8).toString().trim());
					jtf_account.setText(model.getValueAt(i, 9).toString().trim());
					jtf_password.setText(model.getValueAt(i, 10).toString().trim());
					jtf_idAccount.setText(model.getValueAt(i, 11).toString().trim());
				}
			}
			
		});
//        thêm nhân viên
        them.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (jtf_password.getText().trim().isEmpty()
                    		||jtf_account.getText().trim().isEmpty()
                    		||jtf_hoten.getText().trim().isEmpty() 
                    		|| btngr.getSelection() == null 
                    		|| jtf_sdt.getText().trim().isEmpty() 
                    		|| jtf_cccd.getText().trim().isEmpty()
                    		|| jtf_luong.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(rightPanel, "Thông tin không được để trống", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                       
                    } 
                    else if(jtf_password.getText().length() < 6) {
                    	JOptionPane.showMessageDialog(rightPanel, "Mật khẩu phải từ 6 kí tự","Error",JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                    else {
                    	try {                            
                            BLLQuanLyDanhSach bllqlds = new BLLQuanLyDanhSach();
                            Date date = new Date(2000,1,1);
                            String ma = bllqlds.layMaNVchuaTonTai();
                            String ten = jtf_hoten.getText().trim();
                            String sdt = jtf_sdt.getText().trim();
                            String cccd = jtf_cccd.getText().trim();
                            String gioitinh = male.isSelected() ? "Nam" : "Nữ";
                            String taiKhoan = jtf_account.getText().trim();
                            String IDTaiKhoan = bllqlds.kiemTraMaTK().trim();
                            String vaitro = cbb_vaiTro.getSelectedItem().toString().trim();
                            String matKhau = jtf_password.getText().trim();
                            String luong = jtf_luong.getText().trim();
                            //kiểm tra số điện thoại
                            if(!bllQuanLyDanhSach.kiemTraSDT(sdt)) {
                            	JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Thêm nhân viên",JOptionPane.ERROR_MESSAGE);
                            	return;
                            }
                            
                            if(bllQuanLyDanhSach.kiemTraTonTaiSDTNhanVien(sdt)){
                                JOptionPane.showMessageDialog(null, "Số điện thoại đã tồn tại!", "Sửa thông tin", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            //regex tài khoản
                            String regex_account = "^[a-zA-Z0-9]{5,20}$";
            				Pattern p_account = Pattern.compile(regex_account);
            				Matcher m_account = p_account.matcher(taiKhoan);
                        	if(!bllQuanLyDanhSach.kiemTraTenTK(taiKhoan)){
                        		JOptionPane.showMessageDialog(null, "Tài khoản không được trùng lập!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                        	}
                        	else if(!m_account.matches()) {
                        		JOptionPane.showMessageDialog(null, "Tài khoản không được chứa kí tự đặc biệt và phải dài từ 5 đến 20 kí tự!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                        	}
                        	
                        	//kiểm tra trùng lặp tài khoản
                        	if(!bllQuanLyDanhSach.kiemTraTenTK(taiKhoan)){
                        		JOptionPane.showMessageDialog(null, "Tài khoản không được trùng lập!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                        	}
                        	
                        	//kiểm tra vai trò nếu không chọn khi thêm
                            if(vaitro.equals("Vai trò")) {
                                JOptionPane.showMessageDialog(rightPanel, "Vui lòng chọn vai trò của nhân viên", "Chọn vai trò nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            
                            //kiểm tra cơ sở nếu không chọn khi thêm
                            String macoso = cbb_CoSo.getSelectedItem().toString().trim();
                            if(macoso.equals("Cơ sở")) {
                                JOptionPane.showMessageDialog(rightPanel, "Vui lòng chọn cơ sở làm việc của nhân viên", "Chọn vai trò nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            
                            //kiểm tra cơ sở nếu không chọn khi thêm
                            String regex_pass = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
                            Pattern p_pass = Pattern.compile(regex_pass);
                            Matcher m_pass = p_pass.matcher(matKhau);
                            if(!m_pass.matches()) {
                            	JOptionPane.showMessageDialog(null, "Mật khẩu phải từ 6 kí tự và bao gồm chữ và số !", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            
                            //regex họ tên có chứa kí tự unicode
                        	String regex_userName = "^[\\p{L}\\p{M}']+(?:[\\s][\\p{L}\\p{M}']+)*$";
            		        Pattern p_userName = Pattern.compile(regex_userName);
            	            Matcher m_userName = p_userName.matcher(ten);
            	            if(!(ten.length() > 0 && ten.length()<=50)) {
            	            	JOptionPane.showMessageDialog(null, "Tên nhân viên dài từ 1 đến 50 kí tự", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);;
            	            	return;
            	            }
            	            else if(!m_userName.matches()) {
            	            	JOptionPane.showMessageDialog(null, "Tên nhân viên không được chứa kí tự đặc biệt và số", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);;
            	            	return;
            	            }
            	            
                            //regex căn cước công dân
                            String regex_cccd = "^[0-9]{12}$";
                            Pattern p_cccd = Pattern.compile(regex_cccd);
                            Matcher m_cccd = p_cccd.matcher(cccd);
                            if(!m_cccd.matches() || cccd.length() > 12) {
                            	JOptionPane.showMessageDialog(null, "Căn cước công dân không hợp lệ!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            if(bllQuanLyDanhSach.kiemTraSoCCCDNhanVien(cccd)){
                                JOptionPane.showMessageDialog(null, "Căn cước công dân đã tồn tại!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            //kiểm tra tuổi của nhân viên
                        	int year = Integer.parseInt(yearCBB.getSelectedItem().toString());
                            int month = Integer.parseInt(monthCBB.getSelectedItem().toString());
                            int day = Integer.parseInt(dayCBB.getSelectedItem().toString());
                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                    		int currentDay = Calendar.getInstance().get(Calendar.DATE);
                    		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
            				if (currentYear - year < 18) {
            				    JOptionPane.showMessageDialog(null, "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại!", "Error", JOptionPane.ERROR_MESSAGE);
            				    return;
            				} 
            				else if (currentYear - year == 18) {
            				    // Kiểm tra tháng và ngày
            				    if (currentMonth < month || (currentMonth == month && currentDay < day)) {
            				    	System.out.println((currentDay) + " " + day);				    
            				        JOptionPane.showMessageDialog(null, "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại!", "Error", JOptionPane.ERROR_MESSAGE);
            				        return;
            				    }
            				}
                            date = new Date(year-1900,month-1,day);
                            
                            //kiểm tra lương
                            int newLuong = 0;
        					if(bllqlds.kiemTraLuong(luong)==-1) {
        						JOptionPane.showMessageDialog(rightPanel, "Lương không được chứa kí tự đặc biệt hoặc chữ","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
        						return;
        					}
        					else {
        						newLuong = Integer.parseInt(luong);
        						if(newLuong <=0) {
        							JOptionPane.showMessageDialog(null, "Lương phải lớn hơn hoặc bằng 0!","Thêm nhân viên",JOptionPane.ERROR_MESSAGE);
        							return;
        						}
        					}
        					
                            NhanVien nv = new NhanVien(ma, ten, gioitinh, date, sdt, cccd, macoso, vaitro, IDTaiKhoan, newLuong);
                            String IDQuyen = new String();
                            if(vaitro.equals("Nhân viên")) {
                            	IDQuyen = "Q0002";
                            }
                            else {
                            	IDQuyen = "Q0003";
                            }
                            DTOTaiKhoan tknv = new DTOTaiKhoan(bllqlds.kiemTraMaTK(),taiKhoan,matKhau,IDQuyen);
                            if (bllqlds.themTK(tknv) && bllqlds.themNV(nv) == true) {
                            	JOptionPane.showMessageDialog(rightPanel, "Thêm nhân viên thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                model.addRow(new Object[]{ma, ten, gioitinh, date, sdt, cccd, macoso, vaitro, luong, taiKhoan, matKhau, IDTaiKhoan});
                            }
                            else {
                            	JOptionPane.showMessageDialog(rightPanel, "Thêm nhân viên không thành công!", "Error", JOptionPane.ERROR_MESSAGE);
                            	return;
                            }
                            jtf_manv.setText("");jtf_hoten.setText("");jtf_sdt.setText("");jtf_cccd.setText("");
                            cbb_CoSo.setSelectedItem("Cơ sở");;btngr.clearSelection();cbb_vaiTro.setSelectedItem("Nhân viên");jtf_luong.setText("");
                            jtf_account.setText("");jtf_password.setText("");jtf_idAccount.setText("");
						} catch (Exception e2) {
							System.out.println(e2);
						}
                    }
                
            }
        });
        //nút reset
        reset.addActionListener(new ActionListener() {
			@Override
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
        
        //sửa thông tin nhân viên
        sua.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
            @Override
			public void actionPerformed(ActionEvent e) {
				int i = bang.getSelectedRow();
				BLLQuanLyDanhSach bllqlds = new BLLQuanLyDanhSach();
                Date date;
                String maGoc = new String();
                String tenGoc = new String();
                String taiKhoanGoc = new String();
                String gioiTinhGoc = new String();
                String ngaySinhGoc = new String();
                String cccdGoc = new String();
				if(i>=0) {
					ngaySinhGoc = bang.getValueAt(i, 3).toString().trim();
					maGoc = bang.getValueAt(i, 0).toString().trim();
					tenGoc = bang.getValueAt(i, 1).toString().trim();
					taiKhoanGoc = bang.getValueAt(i, 9).toString().trim();
					gioiTinhGoc = bang.getValueAt(i, 2).toString().trim();
					cccdGoc = bang.getValueAt(i, 5).toString().trim();
					int year = Integer.parseInt(yearCBB.getSelectedItem().toString());
					int month = Integer.parseInt(monthCBB.getSelectedItem().toString());
					int day = Integer.parseInt(dayCBB.getSelectedItem().toString());
					date = new Date(year - 1900, month - 1, day);
					String ma = jtf_manv.getText().trim();
					String ten = jtf_hoten.getText().trim();
					String sdt = jtf_sdt.getText().trim();
					String cccd = jtf_cccd.getText().trim();
					String macoso = cbb_CoSo.getSelectedItem().toString();
					String gioitinh = male.isSelected() ? "Nam" : "Nữ";
					String vaitro = cbb_vaiTro.getSelectedItem().toString();
					String matKhau = jtf_password.getText().trim();
					String taiKhoan = jtf_account.getText().trim();
					String IDTaiKhoan = jtf_idAccount.getText().trim();
                    String luong = (String)jtf_luong.getText();
                    int newLuong = 0;
                    String IDQuyen = new String();
                    if(vaitro.equals("Nhân viên")) {
                    	IDQuyen = "Q0002";
                    }
                    else {
                    	IDQuyen = "Q0003";
                    }

                    //không cho sửa cccd
                    if(!cccdGoc.equals(cccd)) {
                    	JOptionPane.showMessageDialog(null, "Không được sửa căn cước công dân nhân viên!","Sửa thông tin", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                    //không cho sửa ngày sinh
                    if(!ngaySinhGoc.equals(new Date(year-1900,month-1,day).toString())) {
                    	JOptionPane.showMessageDialog(null, "Không được sửa ngày sinh của nhân viên vui lòng chỉnh lại đúng ngày!","Sửa thông tin", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                    //kiểm tra số điện thoại
                    if(!bllQuanLyDanhSach.kiemTraSDT(sdt)) {
                    	JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!", "Sửa thông tin",JOptionPane.ERROR_MESSAGE);
                    	return;
                    }

                    System.out.println(gioitinh +" "+ gioiTinhGoc);
                    if(!gioitinh.equals(gioiTinhGoc)) {
                    	JOptionPane.showMessageDialog(null, "Không được sửa giới tính nhân viên","Sửa thông tin", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                    //kiểm tra không cho sửa mã nhân viên
					if(!maGoc.equals(jtf_manv.getText())) {
						JOptionPane.showMessageDialog(rightPanel, "Không được sửa mã nhân viên","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//kiểm tra vai trò nếu không chọn khi thêm
                    if(vaitro.equals("Vai trò")) {
                        JOptionPane.showMessageDialog(rightPanel, "Vui lòng chọn vai trò của nhân viên", "Chọn vai trò nhân viên", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    //kiểm tra cơ sở nếu không chọn khi thêm
                    if(macoso.equals("Cơ sở")) {
                        JOptionPane.showMessageDialog(rightPanel, "Vui lòng chọn cơ sở làm việc của nhân viên", "Chọn cơ sở nhân viên", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
					//regex căn cước công dân
                    String regex_cccd = "^[0-9]{12}$";
                    Pattern p_cccd = Pattern.compile(regex_cccd);
                    Matcher m_cccd = p_cccd.matcher(cccd);
                    if(!m_cccd.matches() || cccd.length() > 12) {
                    	JOptionPane.showMessageDialog(null, "Căn cước công dân của nhân viên không hợp lệ!", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                        	            
					//kiểm tra số điện thoại
					if(!bllqlds.kiemTraSDT(sdt)) {
						JOptionPane.showMessageDialog(rightPanel, "Số điện thoại không hợp lệ","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//regex pass
					String regex_pass = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}$";
                    Pattern p_pass = Pattern.compile(regex_pass);
                    Matcher m_pass = p_pass.matcher(matKhau);
                    if(!m_pass.matches()) {
                    	JOptionPane.showMessageDialog(rightPanel, "Mật khẩu phải từ 6 kí tự và bao gồm chữ và số !", "Thêm nhân viên", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    //kiểm tra không cho sửa tên nhân viên
					if(!ten.equals(tenGoc)) {
						JOptionPane.showMessageDialog(rightPanel, "Không được sửa tên nhân viên!","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					//kiểm tra không cho sửa tài khoản nhân viên
					if(!taiKhoan.equals(taiKhoanGoc)){
                		JOptionPane.showMessageDialog(rightPanel, "Không được sửa đổi tên tài khoản!", "Sửa thông tin", JOptionPane.ERROR_MESSAGE);
                        return;
                	}
					
					//kiểm tra lương
					if(bllqlds.kiemTraLuong(luong)==-1) {
						JOptionPane.showMessageDialog(rightPanel, "Lương không hợp lệ","Sửa thông tin",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else {
						newLuong = Integer.parseInt(luong); 
					}
					
					//kiểm tra tuổi của nhân viên
                    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            		int currentDay = Calendar.getInstance().get(Calendar.DATE);
            		int currentMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
    				if (currentYear - year < 18) {
    				    JOptionPane.showMessageDialog(null, "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại!", "Error", JOptionPane.ERROR_MESSAGE);
    				    return;
    				} 
    				else if (currentYear - year == 18) {
    				    // Kiểm tra tháng và ngày
    				    if (currentMonth < month || (currentMonth == month && currentDay < day)) {
    				    	System.out.println((currentDay) + " " + day);				    
    				        JOptionPane.showMessageDialog(null, "Tuổi của nhân viên chưa đủ 18, vui lòng kiểm tra lại!", "Error", JOptionPane.ERROR_MESSAGE);
    				        return;
    				    }
    				}
                    date = new Date(year-1900,month-1,day);
                    
                    DTOTaiKhoan tknv = new DTOTaiKhoan(IDTaiKhoan,taiKhoan,matKhau,IDQuyen);
                    NhanVien nv = new NhanVien(ma, ten, gioitinh, date, sdt, cccd, macoso, vaitro, IDTaiKhoan, newLuong);
                    if(bllqlds.suaThongTinTK(tknv) && bllqlds.suaThongTinNV(nv) && bllqlds.ganLaiQuyenTK(IDTaiKhoan, IDQuyen) ) {
						JOptionPane.showMessageDialog(rightPanel, "Sửa thông tin thành công!","Sửa thông tin",JOptionPane.INFORMATION_MESSAGE);
						bang.setValueAt(ma, i, 0);
						bang.setValueAt(ten, i, 1);
						bang.setValueAt(gioitinh, i, 2);
						bang.setValueAt(date, i, 3);
						bang.setValueAt(sdt, i, 4);
						bang.setValueAt(cccd, i, 5);
						bang.setValueAt(macoso, i, 6);
						bang.setValueAt(vaitro, i, 7);
						bang.setValueAt(luong, i, 8);
						bang.setValueAt(taiKhoan, i, 9);
						bang.setValueAt(matKhau, i, 10);
						return;
                    }
                    else {
						JOptionPane.showMessageDialog(rightPanel, "Sửa thông tin không thành công!","Sửa thông tin",JOptionPane.INFORMATION_MESSAGE);
						return;
                    }
				}
				else {
                    JOptionPane.showMessageDialog(null, "Thiếu thông tin vui lòng chọn 1 dòng để sửa", "Sửa thông tin",JOptionPane.ERROR_MESSAGE);
                    return;
                }
			}

		});
//        tìm kiếm nhân viên
        timkiem.addActionListener(new ActionListener() {
            @SuppressWarnings({ "unchecked" })
			@Override
            public void actionPerformed(ActionEvent e) {
				BLLQuanLyDanhSach bllqlds = new BLLQuanLyDanhSach();
				int year = Integer.parseInt(yearCBB.getSelectedItem().toString());
				int month = Integer.parseInt(monthCBB.getSelectedItem().toString());
				int day = Integer.parseInt(dayCBB.getSelectedItem().toString());
				@SuppressWarnings("deprecation")
                Date date = new Date(year - 1900, month-1, day);
				System.out.println(date);
				String ma = jtf_manv.getText().trim();
				String ten = jtf_hoten.getText().trim();
				String sdt = jtf_sdt.getText().trim();
				String cccd = jtf_cccd.getText().trim();
				String macoso = cbb_CoSo.getSelectedItem().toString();
				String gioitinh = new String();
				if(male.isSelected()) {
					gioitinh = male.getText();
				}
				if(female.isSelected()) {
					gioitinh = female.getText();
				}
				String vaitro = cbb_vaiTro.getSelectedItem().toString();
				@SuppressWarnings("unused")
                String matKhau = jtf_password.getText().trim();
				@SuppressWarnings("unused")
                String taiKhoan = jtf_account.getText().trim();
				String IDTaiKhoan = jtf_idAccount.getText().trim();
                String luong = (String)jtf_luong.getText();
                int newLuong = 0;
				if(bllqlds.kiemTraLuong(luong)==-1) {
					newLuong = 0; 
				}
				else {
					newLuong = Integer.parseInt(luong);
					if(newLuong <=0) {
						JOptionPane.showMessageDialog(null, "Lương phải lớn hơn hoặc bằng 0!","Thêm nhân viên",JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				System.out.println(luong);
                NhanVien nv = new NhanVien(ma, ten, gioitinh, date, sdt, cccd, macoso, vaitro, IDTaiKhoan, newLuong);
                DTOTaiKhoan tk = new DTOTaiKhoan(IDTaiKhoan, taiKhoan, matKhau, "");
                if(bllQuanLyDanhSach.timKiemNV(nv,tk) != null) {
                	Map<String, ArrayList<?>> testMap = bllQuanLyDanhSach.timKiemNV(nv,tk);
                	ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien> ();
                	ArrayList<DTOTaiKhoan> dsTK = new ArrayList<DTOTaiKhoan>();
                	ArrayList<String> dsTenQuyen = new ArrayList<String>();
                	for (Entry<String, ArrayList<?>> entry : testMap.entrySet()) {
                		String key = entry.getKey(); // Lấy khóa
                		ArrayList<?> value = entry.getValue(); // Lấy giá trị
                		if (key.equals("TaiKhoan")) {
                			dsTK = (ArrayList<DTOTaiKhoan>) value;
                			System.out.println(dsTK.size());
                		}
                		else if(key.equals("NhanVien")){
                			dsNhanVien = (ArrayList<NhanVien>)value;
                			System.out.println(dsNV.size());
                		}
                		else {
                			dsTenQuyen = (ArrayList<String>)value;
                			System.out.println(dsNV.size());
                		}
                	}
                	model.setRowCount(0);
                	for(int i=0;i<dsNhanVien.size();i++) {
                		if((dsTK.get(i).getIDQuyen().equals("Q0002") || dsTK.get(i).getIDQuyen().equals("Q0003"))) {
                    		model.addRow(new Object[] {
                    				dsNhanVien.get(i).getMaNhanVien(),dsNhanVien.get(i).getHoten().trim(),dsNhanVien.get(i).getGioitinh(),dsNhanVien.get(i).getNgaysinh(),
                    				dsNhanVien.get(i).getSdt(),dsNhanVien.get(i).getSocccd(),dsNhanVien.get(i).getMacoso(),dsTenQuyen.get(i).trim(),
                    				dsNhanVien.get(i).getLuong(),dsTK.get(i).getTaiKhoan().trim(), dsTK.get(i).getMatKhau().trim(),dsNhanVien.get(i).getIDTaiKhoan()
                    		});
                    	}
                    	else if(dsTK.get(i).getIDQuyen().equals("Q0004")){
                    		model.addRow(new Object[] {
                    				dsNhanVien.get(i).getMaNhanVien(),dsNhanVien.get(i).getHoten().trim(),dsNhanVien.get(i).getGioitinh(),dsNhanVien.get(i).getNgaysinh(),
                    				dsNhanVien.get(i).getSdt(),dsNhanVien.get(i).getSocccd(),dsNhanVien.get(i).getMacoso(),dsTenQuyen.get(i).trim(),
                    				dsNhanVien.get(i).getLuong(),dsTK.get(i).getTaiKhoan().trim(), dsTK.get(i).getMatKhau().trim(),dsNhanVien.get(i).getIDTaiKhoan()
                    		});
                    	}
                	}
                	bang.setModel(model);
                }
                else {
                	JOptionPane.showMessageDialog(null, "Không có kết quả cần tìm!", "Tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
                	return;
                }
            }
        });
        

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(bang);
        scrollPane.setBounds(5, 350, rightPanel.getWidth()-20, rightPanel.getHeight()-390);
        rightPanel.add(scrollPane);
    }
	private static void updateDays(JComboBox<Integer> dayCBB, JComboBox<Integer> monthCBB, JComboBox<Integer> yearCBB) {
        int selectedMonth = (int) monthCBB.getSelectedItem();
        int selectedYear = (int) yearCBB.getSelectedItem();

        // Lấy số ngày tối đa của tháng và năm đã chọn
        int maxDays = getDaysInMonth(selectedMonth, selectedYear);

        // Lưu lại ngày được chọn trước đó
        Integer selectedDay = (Integer) dayCBB.getSelectedItem();

        // Xóa tất cả các mục trong JComboBox của ngày
        dayCBB.removeAllItems();

        // Thêm lại các ngày dựa trên tháng và năm đã chọn
        for (int day = 1; day <= maxDays; day++) {
            dayCBB.addItem(day);
        }

        // Đặt lại ngày đã chọn trước đó nếu có thể
        if (selectedDay != null && selectedDay <= maxDays) {
            dayCBB.setSelectedItem(selectedDay);
        }
    }

    private static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4: case 6: case 9: case 11:
                return 30; // Các tháng 4, 6, 9, 11 có 30 ngày
            case 2:
                if (isLeapYear(year)) {
                    return 29; // Tháng 2 năm nhuận có 29 ngày
                } else {
                    return 28; // Tháng 2 năm thường có 28 ngày
                }
            default:
                return 31; // Các tháng còn lại có 31 ngày
        }
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
