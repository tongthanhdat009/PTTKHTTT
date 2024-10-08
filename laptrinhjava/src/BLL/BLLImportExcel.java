package BLL;

import java.lang.classfile.instruction.SwitchCase;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.IfFunc;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import DTO.HoiVien;
import GUI.CONTROLLER.informationCTR;

public class BLLImportExcel {
    private ArrayList<String> tenCot = new ArrayList<String>();
    
    //Lấy tên cột
	public ArrayList<String> layTenCot(XSSFSheet sheet, String ds) {
		// Danh sách tên cột
	    if (ds.equals("Hội viên")) {
	        tenCot.add("Mã hội viên");
	        tenCot.add("Họ tên hội viên");
	        tenCot.add("Giới tính");
	        tenCot.add("Gmail");
	        tenCot.add("Mã Tài khoản");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	    } else if (ds.equals("Nhân viên")) {
	        tenCot.add("Mã nhân viên");
	        tenCot.add("Họ và tên");
	        tenCot.add("Giới tính");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Số căn cước");
	        tenCot.add("Mã cơ sở");
	        tenCot.add("Vai trò");
	        tenCot.add("Lương");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	        tenCot.add("ID Tài Khoản");
	    }
	    return tenCot;
	}
	public boolean kiemTraMaHV(String ma) {
		BLLQuanLyDanhSach bllQuanLyDanhSach = new BLLQuanLyDanhSach();
		ArrayList<HoiVien> dsHoiVien = bllQuanLyDanhSach.getDataHoiVien();
		for(HoiVien hVien : dsHoiVien) {
			if(hVien.getMaHoiVien().equals(ma)) {
				return false;
			}
		}
		return true;
	}
	public String kiemTraDuLieu(XSSFSheet sheet, String ds) {
        tenCot.add("Mã hội viên");
        tenCot.add("Họ tên hội viên");
        tenCot.add("Giới tính");
        tenCot.add("Gmail");
        tenCot.add("Mã Tài khoản");
        tenCot.add("Số điện thoại");
        tenCot.add("Ngày sinh");
        tenCot.add("Tài khoản");
        tenCot.add("Mật khẩu");
        if(kiemTraCacCotNhapVao(sheet, ds).equals("Kiểm tra tên cột không thành công !")) {
        	for(int i=1;i<sheet.getLastRowNum()-1;i++) {
        		XSSFRow row = sheet.getRow(i);
        		for(int j=0;j<tenCot.size();j++) {
        			Cell cell = row.getCell(j);
        			if(cell == null) {
        				return "Có ô không có thông tin vui lòng kiểm tra lại";
        			}
        			else {
        				String text = cell.getStringCellValue();
        				switch (j) {
						case 0:
							String regex_maHV = "^HV\\d{3}$";
							Pattern p_maHV = Pattern.compile(regex_maHV);
							Matcher m_maHV = p_maHV.matcher(text);
							if(text.length() != 5) {
            	            	return "Mã hội viên bắt buộc có 5 kí tự, lỗi tại ô: "+cell.getAddress();
							}
							else if(!m_maHV.matches()) {
								return "Mã hội viên phải bắt đầu bằng HV và 3 chữ số theo sau, lỗi tại ô: "+cell.getAddress();
							}
							else if(!kiemTraMaHV(text)) {
								return "Mã hội viên đã tồn tại vui lòng thêm mã hội viên khác, lỗi tại ô: "+cell.getAddress();
							}
							break;
						case 1:
							String regex_userName = "^[\\p{L}\\p{M}']+(?:[\\s][\\p{L}\\p{M}']+)*$";
            		        Pattern p_userName = Pattern.compile(regex_userName);
            	            Matcher m_userName = p_userName.matcher(text);
            	            if(!(text.length() > 0 && text.length()<=50)) {
            	            	return "Tên hội viên chỉ dài từ 1 đến 50 kí tự, lỗi tại ô: "+cell.getAddress();
            	            }
            	            else if(!m_userName.matches()) {
            	            	return "Tên hội viên không được chứa kí tự đặc biệt và số, lỗi tại ô: " + cell.getAddress();
            	            }
							break;
						case 2:
													
							break;
						case 3:
							
							break;
						case 4:
							
							break;
						case 5:
							
							break;
						case 6:
							
							break;
						case 7:
							
							break;
						case 8:
							
							break;

						default:
							break;
						}
        			}
        			System.out.print(" "+cell.getStringCellValue());
        		}
        		System.out.println();
        	}
        }
        else {
			return kiemTraCacCotNhapVao(sheet, ds);
		}
        return "Kiểm tra dữ liệu thành công!";
	}
	//kiểm tra các tên cột
	public String kiemTraCacCotNhapVao(XSSFSheet sheet, String ds) {

	    // Danh sách tên cột
	    if (ds.equals("Hội viên")) {
	        tenCot.add("Mã hội viên");
	        tenCot.add("Họ tên hội viên");
	        tenCot.add("Giới tính");
	        tenCot.add("Gmail");
	        tenCot.add("Mã Tài khoản");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	    } else if (ds.equals("Nhân viên")) {
	        tenCot.add("Mã nhân viên");
	        tenCot.add("Họ và tên");
	        tenCot.add("Giới tính");
	        tenCot.add("Ngày sinh");
	        tenCot.add("Số điện thoại");
	        tenCot.add("Số căn cước");
	        tenCot.add("Mã cơ sở");
	        tenCot.add("Vai trò");
	        tenCot.add("Lương");
	        tenCot.add("Tài khoản");
	        tenCot.add("Mật khẩu");
	        tenCot.add("ID Tài Khoản");
	    }

	    // Lấy dòng đầu tiên từ sheet
	    int row = 0;
	    Row firstRow = sheet.getRow(row);
	    while (true) {
	    	if(firstRow == null) {
	    		row++;
	    		firstRow = sheet.getRow(row);
	    	}
	    	else {
	    		break;
	    	}
		}
	    // Kiểm tra số lượng cột trong file Excel có khớp với số cột mong đợi không
	    if (firstRow.getPhysicalNumberOfCells() != tenCot.size()) {
	    	return "Số lượng cột trong file Excel không khớp với yêu cầu.";
	    }

	    // Kiểm tra tên các cột
	    for (int i = 0; i < tenCot.size(); i++) {
	        Cell cell = firstRow.getCell(i);
	        if (cell == null || cell.getCellType() != CellType.STRING || !cell.getStringCellValue().equalsIgnoreCase(tenCot.get(i))) {
	            return "Tên cột không khớp: ô '" + cell.getAddress() + "' không khớp với '" + tenCot.get(i) + "'";
	        }
	    }

	    return "Kiểm tra tên cột không thành công !";
	}
}
