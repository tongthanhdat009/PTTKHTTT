package GUI.CONTROLLER;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.BLLImportExcel;
import BLL.BLLXuatFileExcel;

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
					System.out.println("Hội viên");
					break;
				case "Nhân viên":
					System.out.println("Nhân viên");
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
					excelReader(sheetChooser, selectedFile.toString());
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
		sheetChooser.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn sheet"}));
		sheetChooser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		sheetChooser.setBounds(238, 53, 150, 30);
		sheetChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pathNameTF.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Vui lòng chọn file sau đó chọn sheet","Import Excel",JOptionPane.ERROR_MESSAGE);
					return;
				}
				System.out.println(sheetChooser.getSelectedItem());
			}
			
		});
		fileInforPN.add(sheetChooser);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(null);
		dataPanel.setBounds(0, 250, 1200, 650);
		add(dataPanel);
	}
	public void excelReader(JComboBox<String> sheetChooser, String filePath) {
//	    String excelFilePath = "C:\\Users\\dat\\Desktop\\a.xlsx";
	    try (FileInputStream fileInputStream = new FileInputStream(filePath);
	         XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

	    	// Lặp qua các sheet và in tên của chúng
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                String sheetName = workbook.getSheetName(i);
                sheetChooser.addItem(sheetName);
                
            }
	        // Chọn sheet đầu tiên
	        // Lặp qua các hàng trong sheet
//	        for (Row row : sheet) {
//	            for (Cell cell : row) {
//	                // Xử lý các kiểu dữ liệu khác nhau
//	                switch (cell.getCellType()) {
//	                    case STRING:
//	                        System.out.print(cell.getStringCellValue() + "\t");
//	                        break;
//	                    case NUMERIC:
//	                        System.out.print(cell.getNumericCellValue() + "\t");
//	                        break;
//	                    case BOOLEAN:
//	                        System.out.print(cell.getBooleanCellValue() + "\t");
//	                        break;
//	                    default:
//	                        System.out.print(" ");
//	                }
//	            }
////	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
