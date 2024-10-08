package GUI.CONTROLLER;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.genetics.Fitness;
import org.apache.poi.examples.hssf.usermodel.NewLinesInCells;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.BLLImportExcel;
import BLL.BLLXuatFileExcel;
import DTO.DTOTaiKhoan;
import DTO.HoiVien;
import GUI.renderer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportExcelCTR extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField pathNameTF;
	private BLLImportExcel bllImportExcel = new BLLImportExcel();
	private JComboBox<String> sheetChooser = new JComboBox<String>();
	private JPanel dataPanel = new JPanel();
	public ImportExcelCTR() {
		setBackground(new Color(241, 255, 250));
		this.setSize(1200,900);
		this.setLayout(null);
		
		JLabel title = new JLabel("Nhập file danh sách ");
		title.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 50));
		title.setBounds(416, 0, 440, 78);
		add(title);
		
		JPanel fileInforPN = new JPanel();
		fileInforPN.setLayout(null);
		fileInforPN.setBounds(0, 70, 1200, 175);
		add(fileInforPN);
		
		JLabel lblNewLabel = new JLabel("Thông tin lưu file");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(461, 0, 292, 47);
		fileInforPN.add(lblNewLabel);
		
		JLabel pathNameLB = new JLabel("Đường dẫn đã chọn:");
		pathNameLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		pathNameLB.setBounds(471, 53, 202, 30);
		fileInforPN.add(pathNameLB);
		
		JButton acceptBTN = new JButton("Xác nhận");
		acceptBTN.setBounds(1015, 101, 130, 50);
		fileInforPN.add(acceptBTN);
		
		JComboBox<String> listChooser = new JComboBox<String>();
		listChooser.setEnabled(false);
		listChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pathNameTF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn file sau đó chọn sheet","Import Excel",JOptionPane.ERROR_MESSAGE);
					listChooser.setSelectedIndex(0);
					return;
				}
				String list = (String) listChooser.getSelectedItem();
				switch (list) {
				case "Hội viên":
					int choice = JOptionPane.showConfirmDialog(null, "Bạn chắc muốn chọn danh sách không?","Import Excel",JOptionPane.YES_NO_OPTION);
					if(choice == 0) {
						System.out.println("Hội viên");
						listChooser.setEnabled(false);
						sheetChooser.setEnabled(true);						
					}
					else {
						listChooser.setSelectedIndex(0);
					}
					break;
				case "Nhân viên":
					int choice1 = JOptionPane.showConfirmDialog(null, "Bạn chắc muốn chọn danh sách không?","Import Excel",JOptionPane.YES_NO_OPTION);
					if(choice1 == 0) {
						System.out.println("Nhân viên");
						listChooser.setEnabled(false);
						sheetChooser.setEnabled(true);						
					}
					else {
						listChooser.setSelectedIndex(0);
					}
					sheetChooser.setEnabled(true);
					break;
				default:
					break;
				}
			}
		});
		listChooser.setFont(new Font("Times New Roman", Font.BOLD, 25));
		listChooser.setBackground(Color.WHITE);
		listChooser.setBounds(471, 100, 202, 33);
		listChooser.setModel(new DefaultComboBoxModel<String>(new String[] {"Danh sách", "Hội viên", "Nhân viên"}));
		fileInforPN.add(listChooser);
		
		JButton chooseFileBTN = new JButton("Chọn file:");
		chooseFileBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Tạo JFileChooser
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				
				// Chỉ cho phép chọn tệp
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				// Tạo bộ lọc tệp để chỉ cho phép chọn tệp Excel
				fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel files", "xls", "xlsx"));

				// Hiển thị hộp thoại chọn tệp
				int result = fileChooser.showOpenDialog(fileInforPN);
				
				// Kiểm tra nếu người dùng chọn tệp
				if (result == JFileChooser.APPROVE_OPTION) {
				    // Lấy đường dẫn của tệp được chọn
				    File selectedFile = fileChooser.getSelectedFile();
				    pathNameTF.setText(selectedFile.toString());
				    for (int i = sheetChooser.getItemCount() - 1; i >= 0; i--) {
		                if (!sheetChooser.getItemAt(i).toString().equals("Chọn sheet")) {
		                    sheetChooser.removeItemAt(i); // Sử dụng removeItemAt để xóa theo chỉ số
		                }
		            }
					excelReader(sheetChooser, selectedFile.toString());
					listChooser.setEnabled(true);
					sheetChooser.setEnabled(false);
					
				} else {
				    System.out.println("Không có tệp nào được chọn");
				}

			}
		});
		chooseFileBTN.setBounds(1015, 39, 130, 50);
		fileInforPN.add(chooseFileBTN);
		
		pathNameTF = new JTextField();
		pathNameTF.setEditable(false);
		pathNameTF.setColumns(10);
		pathNameTF.setBounds(682, 55, 309, 30);
		fileInforPN.add(pathNameTF);
		
		JLabel SheetChooser = new JLabel("Chọn sheet:");
		SheetChooser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		SheetChooser.setBounds(106, 55, 112, 27);
		fileInforPN.add(SheetChooser);
		
		sheetChooser = new JComboBox<String>();
		sheetChooser.setBackground(new Color(255, 255, 255));
		sheetChooser.setEnabled(false);
		sheetChooser.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn sheet"}));
		sheetChooser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		sheetChooser.setBounds(238, 53, 150, 30);
		sheetChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pathNameTF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn file sau đó chọn sheet","Import Excel",JOptionPane.ERROR_MESSAGE);
					return;
				}
				else {
					if(!sheetChooser.getSelectedItem().toString().equals("Chọn sheet")) {
						int choice=JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn chọn sheet này không?","Import Excel",JOptionPane.YES_NO_OPTION);
						if(choice == 0) {
							sheetChooser.setEnabled(false);
							if(listChooser.getSelectedItem().equals("Hội viên")) {
								System.out.println("Tạo bảng hội viên");
								taoBangHV("Hội viên", bllImportExcel,dataPanel);
							}
							else if(listChooser.getSelectedItem().equals("Nhân viên")) {
								System.out.println("Tạo bảng nhân viên");
							}
						}
						else if (choice == 1){
							sheetChooser.setSelectedIndex(0);
						}						
					}
					
				}
			}
			
		});
		fileInforPN.add(sheetChooser);
		
		dataPanel.setLayout(null);
		dataPanel.setBounds(0, 250, 1200, 650);
		add(dataPanel);
	}
	public void excelReader(JComboBox<String> sheetChooser, String filePath) {
	    try (FileInputStream fileInputStream = new FileInputStream(filePath);
	        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                String sheetName = workbook.getSheetName(i);
                sheetChooser.addItem(sheetName);
            }
            
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public void taoBangHV(String ds, BLLImportExcel bllImportExcel, JPanel dataPanel) {
		XSSFSheet sheet = null;
		try (FileInputStream fileInputStream = new FileInputStream(pathNameTF.getText());
			XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {
			sheet = workbook.getSheet(sheetChooser.getSelectedItem().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JTable tempTB = null;
		DefaultTableModel tableModel = null;
		if(bllImportExcel.kiemTraDuLieuHV(sheet, ds).equals("Kiểm tra dữ liệu thành công!")) {
		    ArrayList<HoiVien> dsHoiVien = bllImportExcel.getDSHoiVien();
		    ArrayList<DTOTaiKhoan> dsTaiKhoan = bllImportExcel.getDSTaiKhoan();
		    ArrayList<String> tenCot = bllImportExcel.getTenCotHV();

		    // Chuyển ArrayList<String> tenCot sang mảng String[]
		    String[] tenCotStrings = new String[tenCot.size()];
		    for (int j = 0; j < tenCot.size(); j++) {
		        tenCotStrings[j] = tenCot.get(j);
		    }

		    // Khởi tạo DefaultTableModel với tiêu đề cột
		    tableModel = new DefaultTableModel(tenCotStrings, 0);

		    // Kiểm tra kích thước của dsHoiVien và dsTaiKhoan
		    if (dsHoiVien.size() == dsTaiKhoan.size()) {
		        // Thêm dữ liệu từ dsHoiVien và dsTaiKhoan vào bảng
		        for (int i = 0; i < dsHoiVien.size(); i++) {
		            tableModel.addRow(new Object[]{
		                dsHoiVien.get(i).getMaHoiVien(),
		                dsHoiVien.get(i).getHoten(),
		                dsHoiVien.get(i).getGioitinh(),
		                dsHoiVien.get(i).getMail(),
		                dsTaiKhoan.get(i).getIDTaiKhoan(),
		                dsHoiVien.get(i).getSdt(),
		                dsHoiVien.get(i).getNgaysinh(),
		                dsTaiKhoan.get(i).getTaiKhoan(),
		                dsTaiKhoan.get(i).getMatKhau()
		            });
		        }

		        // Tạo JTable với DefaultTableModel và thêm vào JScrollPane
		        tempTB = new JTable(tableModel);
		        tempTB.setBounds(0, 0, 1200, 650);
		        JScrollPane scrollPane = new JScrollPane(tempTB);
		        scrollPane.setBounds(0, 0, 1185, 610);
		        dataPanel.add(scrollPane);
		        dataPanel.revalidate();
		        dataPanel.repaint();
		    } else {
		        System.out.println("Danh sách Hội Viên và Tài Khoản không có cùng số lượng!");
		    }
		}
		else {
			JOptionPane.showMessageDialog(null, bllImportExcel.kiemTraDuLieuHV(sheet, ds),"Export Excel",JOptionPane.ERROR_MESSAGE);
			 return;
		}
	}
}
