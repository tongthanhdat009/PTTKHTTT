package GUI.CONTROLLER;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import BLL.BLLQuanLyDanhSach;
import BLL.BLLThongKeDonHang;
import DTO.CoSo;
import DTO.DSCoSo;
import DTO.DTOThongKe;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.sound.midi.SysexMessage;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class thongKeCTR extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BLLThongKeDonHang bllThongKeDonHang = new BLLThongKeDonHang();
	private BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
	@SuppressWarnings("unchecked")
	public thongKeCTR() {
		setBackground(new Color(241, 255, 250));
		this.setSize(1200,900);
		this.setLayout(null);
		
		JLabel title = new JLabel("Thống kê");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(420, 0, 428, 70);
		add(title);
		
		JPanel selectPN = new JPanel();
		selectPN.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		selectPN.setBackground(new Color(119, 230, 160));
		selectPN.setBounds(0, 76, 1200, 45);
		add(selectPN);
		selectPN.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		DSCoSo dsCS = bllQuanLyDanhSach.layDsCoSo();
		ArrayList<String> maCS = new ArrayList<String>();
		for (CoSo cs:dsCS.getDsCoSo()) {
			maCS.add(cs.getMaCoSo());
		}
		JComboBox<String> csCBB = new JComboBox<String>();
		csCBB.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		csCBB.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn cơ sở"}));
		for(CoSo coSo : dsCS.getDsCoSo()) {
			csCBB.addItem(coSo.getMaCoSo());
		}
		csCBB.setVisible(false);
		
		JButton acceptBTN = new JButton("Xác nhận");
		acceptBTN.setBackground(new Color(255, 255, 255));
		acceptBTN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		acceptBTN.setVisible(false);
		
		JLabel monthStartLB = new JLabel("Chọn tháng bắt đầu:");
		monthStartLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monthStartLB.setVisible(false);
		
		JComboBox<Integer> monthStart = new JComboBox<Integer>();
		monthStart.setBackground(new Color(255, 255, 255));
		monthStartLB.setLabelFor(monthStart);
		monthStart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthStart.setVisible(false);
		
		JLabel yearStartLB = new JLabel("Chọn năm bắt đầu:");
		yearStartLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		yearStartLB.setVisible(false);
		
		JComboBox<Integer> yearStart = new JComboBox<Integer>();
		yearStart.setBackground(new Color(255, 255, 255));
		yearStartLB.setLabelFor(yearStart);
		yearStart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		yearStart.setVisible(false);
		
		JLabel monthEndLB = new JLabel("Chọn tháng kết thúc:");
		monthEndLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monthEndLB.setVisible(false);
		
		JComboBox<Integer> monthEnd = new JComboBox<Integer>();
		monthEnd.setBackground(new Color(255, 255, 255));
		monthEndLB.setLabelFor(monthEnd);
		monthEnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthEnd.setVisible(false);
		
		JLabel yearEndLB = new JLabel("Chọn năm kết thúc:");
		yearEndLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		yearEndLB.setVisible(false);
		
		JComboBox<Integer> yearEnd = new JComboBox<Integer>();
		yearEnd.setBackground(new Color(255, 255, 255));
		yearEndLB.setLabelFor(yearEnd);
		yearEnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		yearEnd.setVisible(false);
		
		
		for(int month=1; month<=12 ;month++){
            monthStart.addItem(month);
            monthEnd.addItem(month);
        }
		for(int year=2024;year>=1900;year--){
            yearStart.addItem(year);
            yearEnd.addItem(year);
        }		
		JPanel chartPN = new JPanel();
		chartPN.setBackground(new Color(241, 255, 250));
		chartPN.setBounds(0, 124, 1200, 776);
		add(chartPN);
		JComboBox<String> typeCBB = new JComboBox<String>();
		typeCBB.setBackground(new Color(255, 255, 255));
		typeCBB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(typeCBB.getSelectedItem().equals("Chọn cách thống kê")) {
					System.out.print("Chọn cách thống kế");
					yearEndLB.setVisible(false);
					yearEnd.setVisible(false);
					yearStartLB.setVisible(false);
					yearStart.setVisible(false);
					monthStartLB.setVisible(false);
					monthStart.setVisible(false);
					monthEndLB.setVisible(false);
					monthEnd.setVisible(false);
					acceptBTN.setVisible(false);
				}
				else {
					 for (ActionListener al : acceptBTN.getActionListeners()) {
			                acceptBTN.removeActionListener(al);
			            }
					if(typeCBB.getSelectedItem().toString().equals("Thống kê tổng doanh thu")) {
						yearEndLB.setVisible(false);
						yearEnd.setVisible(false);
						yearStart.setVisible(false);
						yearStartLB.setVisible(false);
						monthStartLB.setVisible(false);
						monthStart.setVisible(false);
						monthEndLB.setVisible(false);
						monthEnd.setVisible(false);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								chartPN.removeAll();
								thongKeTheoDoanhThu tkdt = new thongKeTheoDoanhThu(maCS);
								chartPN.add(tkdt);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
					if(typeCBB.getSelectedItem().toString().equals("Thống kê tổng chi phí")) {
						yearEndLB.setVisible(false);
						yearEnd.setVisible(false);
						yearStartLB.setVisible(false);
						yearStart.setVisible(false);
						monthStartLB.setVisible(false);
						monthStart.setVisible(false);
						monthEndLB.setVisible(false);
						monthEnd.setVisible(false);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								chartPN.removeAll();
								thongKeTheoChiPhi tkcp = new thongKeTheoChiPhi(maCS);
								chartPN.add(tkcp);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
					if(typeCBB.getSelectedItem().toString().equals("Thống kê doanh thu theo tháng")) {
						yearEndLB.setVisible(true);
						yearEnd.setVisible(true);
						yearStartLB.setVisible(true);
						yearStart.setVisible(true);
						monthStartLB.setVisible(true);
						monthStart.setVisible(true);
						monthEndLB.setVisible(true);
						monthEnd.setVisible(true);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("Thống kê doanh thu theo tháng");
								if(Integer.parseInt(monthStart.getSelectedItem().toString()) > Integer.parseInt(monthEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Tháng bắt đầu không được lớn hơn tháng kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								if(Integer.parseInt(yearStart.getSelectedItem().toString()) > Integer.parseInt(yearEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Năm bắt đầu không được lớn hơn Năm kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								
								chartPN.removeAll();
								thongKeDoanhThuTheoThang tkdttt = new thongKeDoanhThuTheoThang(maCS,
										Integer.parseInt(monthStart.getSelectedItem().toString()),
										Integer.parseInt(yearStart.getSelectedItem().toString()),
										Integer.parseInt(monthEnd.getSelectedItem().toString()),
										Integer.parseInt(yearEnd.getSelectedItem().toString()));
								chartPN.add(tkdttt);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
					if(typeCBB.getSelectedItem().toString().trim().equals("Thống kê doanh thu theo năm")) {
						yearEnd.setVisible(true);
						yearEndLB.setVisible(true);
						yearStartLB.setVisible(true);
						yearStart.setVisible(true);
						monthStartLB.setVisible(false);
						monthStart.setVisible(false);
						monthEndLB.setVisible(false);
						monthEnd.setVisible(false);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(Integer.parseInt(yearStart.getSelectedItem().toString()) > Integer.parseInt(yearEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Năm bắt đầu không được lớn hơn Năm kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								chartPN.removeAll();
								thongKeDoanhThuTheoNam tkdttn = new thongKeDoanhThuTheoNam(maCS, Integer.parseInt(yearStart.getSelectedItem().toString()), Integer.parseInt(yearEnd.getSelectedItem().toString()));
								chartPN.add(tkdttn);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
					if(typeCBB.getSelectedItem().toString().equals("Thống kê chi phí theo tháng")) {
						yearEndLB.setVisible(true);
						yearEnd.setVisible(true);
						yearStartLB.setVisible(true);
						yearStart.setVisible(true);
						monthStartLB.setVisible(true);
						monthStart.setVisible(true);
						monthEndLB.setVisible(true);
						monthEnd.setVisible(true);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								System.out.print("Thống kê chi phí theo tháng");
								if(Integer.parseInt(monthStart.getSelectedItem().toString()) > Integer.parseInt(monthEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Tháng bắt đầu không được lớn hơn tháng kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								if(Integer.parseInt(yearStart.getSelectedItem().toString()) > Integer.parseInt(yearEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Năm bắt đầu không được lớn hơn Năm kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								chartPN.removeAll();
								thongKeChiPhiTheoThang tkcptt = new thongKeChiPhiTheoThang(maCS,
										Integer.parseInt(monthStart.getSelectedItem().toString()),
										Integer.parseInt(yearStart.getSelectedItem().toString()),
										Integer.parseInt(monthEnd.getSelectedItem().toString()),
										Integer.parseInt(yearEnd.getSelectedItem().toString()));
								chartPN.add(tkcptt);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
					
					if(typeCBB.getSelectedItem().toString().equals("Thống kê chi phí theo năm")) {
						yearEndLB.setVisible(true);
						yearEnd.setVisible(true);
						yearStartLB.setVisible(true);
						yearStart.setVisible(true);
						monthEndLB.setVisible(false);
						monthEnd.setVisible(false);
						monthStartLB.setVisible(false);
						monthStart.setVisible(false);
						acceptBTN.setVisible(true);
						acceptBTN.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if(Integer.parseInt(yearStart.getSelectedItem().toString()) > Integer.parseInt(yearEnd.getSelectedItem().toString())){
									JOptionPane.showMessageDialog(null, "Năm bắt đầu không được lớn hơn Năm kết thúc","Thống kê doanh thu theo tháng",JOptionPane.ERROR_MESSAGE);
										return;
								}
								chartPN.removeAll();
								thongKeChiPhiTheoNam tkcptn = new thongKeChiPhiTheoNam(maCS, Integer.parseInt(yearStart.getSelectedItem().toString()), Integer.parseInt(yearEnd.getSelectedItem().toString()));
								chartPN.add(tkcptn);
								chartPN.revalidate();
								chartPN.repaint();
							}
						});
					}
				}
				
			}
		});
		selectPN.add(typeCBB);
		selectPN.add(monthStartLB);
		selectPN.add(monthStart);
		selectPN.add(yearStartLB);
		selectPN.add(yearStart);
		selectPN.add(monthEndLB);
		selectPN.add(monthEnd);
		selectPN.add(yearEndLB);
		selectPN.add(yearEnd);
		selectPN.add(acceptBTN);
		typeCBB.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn cách thống kê", "Thống kê tổng chi phí", "Thống kê tổng doanh thu", "Thống kê doanh thu theo tháng", "Thống kê doanh thu theo năm", "Thống kê chi phí theo năm", "Thống kê chi phí theo tháng"}));
		typeCBB.setFont(new Font("Times New Roman", Font.PLAIN, 17));
	}
}
