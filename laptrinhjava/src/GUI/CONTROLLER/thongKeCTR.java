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
		selectPN.setBounds(0, 76, 1200, 37);
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
		acceptBTN.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		acceptBTN.setVisible(false);
		
		JLabel monthStartLB = new JLabel("Chọn tháng bắt đầu:");
		monthStartLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monthStartLB.setVisible(false);
		
		JComboBox<Integer> monthStart = new JComboBox<Integer>();
		monthStartLB.setLabelFor(monthStart);
		monthStart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthStart.setVisible(false);
		
		JLabel yearStartLB = new JLabel("Chọn năm bắt đầu:");
		yearStartLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		yearStartLB.setVisible(false);
		
		JComboBox<Integer> yearStart = new JComboBox<Integer>();
		yearStartLB.setLabelFor(yearStart);
		yearStart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		yearStart.setVisible(false);
		
		JLabel monthEndLB = new JLabel("Chọn tháng kết thúc:");
		monthEndLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		monthEndLB.setVisible(false);
		
		JComboBox<Integer> monthEnd = new JComboBox<Integer>();
		monthEndLB.setLabelFor(monthEnd);
		monthEnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		monthEnd.setVisible(false);
		
		JLabel yearEndLB = new JLabel("Chọn năm kết thúc:");
		yearEndLB.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		yearEndLB.setVisible(false);
		
		JComboBox<Integer> yearEnd = new JComboBox<Integer>();
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
		JComboBox<String> typeCBB = new JComboBox<String>();
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
				if(typeCBB.getSelectedItem().toString().equals("Thống kê tổng doanh thu")) {
					System.out.print("Thống kê tổng doanh thu");
					yearEndLB.setVisible(false);
					yearEnd.setVisible(false);
					yearStart.setVisible(false);
					yearStartLB.setVisible(false);
					monthStartLB.setVisible(false);
					monthStart.setVisible(false);
					monthEndLB.setVisible(false);
					monthEnd.setVisible(false);
					acceptBTN.setVisible(true);
				}
				if(typeCBB.getSelectedItem().toString().equals("Thống kê tổng chi phí")) {
					System.out.print("Thống kê tổng chi phí");
					yearEndLB.setVisible(false);
					yearEnd.setVisible(false);
					yearStartLB.setVisible(false);
					yearStart.setVisible(false);
					monthStartLB.setVisible(false);
					monthStart.setVisible(false);
					monthEndLB.setVisible(false);
					monthEnd.setVisible(false);
					acceptBTN.setVisible(true);
				}
				if(typeCBB.getSelectedItem().toString().equals("Thống kê doanh thu theo tháng")) {
					System.out.print("Thống kê doanh thu theo tháng");
					yearEndLB.setVisible(true);
					yearEnd.setVisible(true);
					yearStartLB.setVisible(true);
					yearStart.setVisible(true);
					monthStartLB.setVisible(true);
					monthStart.setVisible(true);
					monthEndLB.setVisible(true);
					monthEnd.setVisible(true);
					acceptBTN.setVisible(true);
					
				}
				if(typeCBB.getSelectedItem().toString().equals("Thống kê doanh thu theo năm")) {
					System.out.print("Thống kê doanh thu theo năm");
					yearEnd.setVisible(true);
					yearEndLB.setVisible(true);
					yearStartLB.setVisible(true);
					yearStart.setVisible(true);
					monthStartLB.setVisible(false);
					monthStart.setVisible(false);
					monthEndLB.setVisible(false);
					monthEnd.setVisible(false);
					acceptBTN.setVisible(true);
				}
				if(typeCBB.getSelectedItem().toString().equals("Thống kê chi phí theo tháng")) {
					System.out.print("Thống kê chi phí theo tháng");
					yearEndLB.setVisible(true);
					yearEnd.setVisible(true);
					yearStartLB.setVisible(true);
					yearStart.setVisible(true);
					monthStartLB.setVisible(true);
					monthStart.setVisible(true);
					monthEndLB.setVisible(true);
					monthEnd.setVisible(true);
					acceptBTN.setVisible(true);
				}
				
				if(typeCBB.getSelectedItem().toString().equals("Thống kê chi phí theo năm")) {
					System.out.print("Thống kê chi phí theo năm");
					yearEndLB.setVisible(true);
					yearEnd.setVisible(true);
					yearStartLB.setVisible(true);
					yearStart.setVisible(true);
					monthEndLB.setVisible(false);
					monthEnd.setVisible(false);
					monthStartLB.setVisible(false);
					monthStart.setVisible(false);
					acceptBTN.setVisible(true);
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
		typeCBB.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn cách thống kê", "Thống kê tổng chi phí", "Thống kê tổng doanh thu", "Thống kê doanh thu theo tháng", "Thống kê doanh thu theo năm ", "Thống kê chi phí theo năm", "Thống kê chi phí theo tháng"}));
		typeCBB.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		
		
	}
}
