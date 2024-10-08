package GUI.CONTROLLER;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JTextField;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import BLL.BLLImportExcel;
import BLL.BLLXuatFileExcel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ImportExcelCTR extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField fileNameTF;
	private JTextField pathNameTF;
	private BLLImportExcel bllImportExcel = new BLLImportExcel();
	@SuppressWarnings("unchecked")
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
		
		fileNameTF = new JTextField();
		fileNameTF.setColumns(10);
		fileNameTF.setBounds(233, 59, 150, 30);
		fileInforPN.add(fileNameTF);
		
		JLabel lblNewLabel = new JLabel("Thông tin lưu file");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(461, 0, 292, 47);
		fileInforPN.add(lblNewLabel);
		
		JLabel fileNameLB = new JLabel("Tên file:");
		fileNameLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		fileNameLB.setBounds(143, 55, 80, 30);
		fileInforPN.add(fileNameLB);
		
		JLabel pathNameLB = new JLabel("Đường dẫn đã chọn:");
		pathNameLB.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		pathNameLB.setBounds(471, 53, 202, 30);
		fileInforPN.add(pathNameLB);
		
		JButton acceptBTN = new JButton("Xác nhận");
		acceptBTN.setBounds(1015, 101, 130, 50);
		fileInforPN.add(acceptBTN);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 25));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(471, 100, 202, 33);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Danh sách", "Hội viên", "Nhân viên"}));
		fileInforPN.add(comboBox);
		
		JButton chooseFileBTN = new JButton("Chọn file:");
		chooseFileBTN.setBounds(1015, 39, 130, 50);
		fileInforPN.add(chooseFileBTN);
		
		pathNameTF = new JTextField();
		pathNameTF.setEditable(false);
		pathNameTF.setColumns(10);
		pathNameTF.setBounds(682, 55, 309, 30);
		fileInforPN.add(pathNameTF);
		
		JLabel SheetChooser = new JLabel("Chọn sheet:");
		SheetChooser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
		SheetChooser.setBounds(111, 100, 112, 27);
		fileInforPN.add(SheetChooser);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"Chọn sheet"}));
		comboBox_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		comboBox_1.setBounds(233, 100, 150, 30);
		fileInforPN.add(comboBox_1);
		
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(null);
		dataPanel.setBounds(0, 250, 1200, 650);
		add(dataPanel);
		excelReader();
	}
	public void excelReader() {
	    String excelFilePath = "C:\\Users\\gamin\\Desktop\\a.xlsx";
	    try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
	         XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

	        // Chọn sheet đầu tiên
	    	XSSFSheet sheet = workbook.getSheetAt(0);
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
	            System.out.println(bllImportExcel.kiemTraDuLieu(sheet, excelFilePath));
//	        }
//			System.out.println(bllImportExcel.kiemTraCacCotNhapVao(sheet, "Hội viên"));

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
